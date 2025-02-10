package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.EquipmentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddEquipmentController implements Initializable {

    @FXML
    private TableView<EquipmentTM> Equipmenttable;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<EquipmentTM, String> colequip;

    @FXML
    private TableColumn<EquipmentTM, String> colequipname;

    @FXML
    private Label lblid;

    @FXML
    private TextField txtname;

    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EQUIPMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colequip.setCellValueFactory(new PropertyValueFactory<>("EquipmentID"));
        colequipname.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));

        try {
            loadNextEquipmenID();
            loadTableData();
            refreshPage();
            ;


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load EquipmentID").show();
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EquipmentID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = equipmentBO.delete(EquipmentID);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Equipment deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Equipment...!").show();
            }
        }
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        EquipmentTM addEquipmentTM = Equipmenttable.getSelectionModel().getSelectedItem();
        if (addEquipmentTM != null) {
            lblid.setText(addEquipmentTM.getEquipmentID());
            txtname.setText(addEquipmentTM.getEquipmentName());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EquipmentID = lblid.getText();

        String EquipmentName = txtname.getText();


        EquipmentDTO addEquipmentDto = new EquipmentDTO(
                EquipmentID,
                EquipmentName
        );
        boolean isSaved = equipmentBO.save(addEquipmentDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Equipment saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Equipment...!").show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EquipmentID = lblid.getText();
        String EquipmentName = txtname.getText();


        EquipmentDTO addEquipmentDto = new EquipmentDTO(
                EquipmentID,
                EquipmentName

        );
        boolean isUpdate = equipmentBO.update(addEquipmentDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Equipment update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Equipment...!").show();
        }
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextEquipmenID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);


        txtname.setText("");

    }

    public void loadNextEquipmenID() throws SQLException, ClassNotFoundException {
        String nextEquipmenID = equipmentBO.getNextID();
        lblid.setText(nextEquipmenID);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentDTO> addEquipmentDtos = equipmentBO.getAll();
        ObservableList<EquipmentTM> addEquipmentTMS = FXCollections.observableArrayList();

        for (EquipmentDTO addEquipmentDto : addEquipmentDtos) {
            EquipmentTM addEquipmentTM = new EquipmentTM(
                    addEquipmentDto.getEquipmentID(),
                    addEquipmentDto.getEquipmentName()


            );
            addEquipmentTMS.add(addEquipmentTM);
        }
        Equipmenttable.setItems(addEquipmentTMS);

    }
}
