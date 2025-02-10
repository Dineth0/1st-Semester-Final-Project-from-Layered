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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentUsageBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentUsageDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.EquipmentUsageTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EquipmentUsageController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<EquipmentUsageTM, Date> coldate;

    @FXML
    private TableColumn<EquipmentUsageTM,String> colequip;

    @FXML
    private TableColumn<EquipmentUsageTM,String> colequipname;

    @FXML
    private TableColumn<EquipmentUsageTM,String> collabor;

    @FXML
    private TableColumn<EquipmentUsageTM,String> colname;

    @FXML
    private ComboBox<String> comequipid;

    @FXML
    private ComboBox<String> comlaborid;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblequipname;

    @FXML
    private Label lbllaborname;

    @FXML
    private TableView<EquipmentUsageTM> useequipmentable;

    EquipmentUsageBO equipmentUsageBO = (EquipmentUsageBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EQUIPMENTUSAGE);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EQUIPMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        colequip.setCellValueFactory(new PropertyValueFactory<>("EquipmentID"));
        colequipname.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("LaborName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("UseDate"));

        try {

            loadLaborIDs();
            loadEquipmentIDs();
            loadTableData();
            refreshPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load EquipmentID").show();
        }
    }

    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = comlaborid.getSelectionModel().getSelectedItem();
        LaborDTO laborDto = laborBO.findById(selectedLaborID);

        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
        }
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedEquipmentID = comequipid.getSelectionModel().getSelectedItem();
        EquipmentDTO addEquipmentDto = equipmentBO.findById(selectedEquipmentID);

        if (addEquipmentDto != null) {
            lblequipname.setText(addEquipmentDto.getEquipmentName());
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        java.sql.Date UseDate =  java.sql.Date.valueOf(lbldate.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = equipmentUsageBO.delete(String.valueOf(UseDate));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Usage deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Usage...!").show();
            }
        }
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        EquipmentUsageTM equipmentUsageTM = useequipmentable.getSelectionModel().getSelectedItem();
        if(equipmentUsageTM != null){
            comequipid.setValue(equipmentUsageTM.getEquipmentID());
            lblequipname.setText(equipmentUsageTM.getEquipmentName());
            comlaborid.setValue(equipmentUsageTM.getLaborID());
            lbllaborname.setText(equipmentUsageTM.getLaborName());
            lbldate.setText(equipmentUsageTM.getUseDate().toString());



            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lbldate.setText(LocalDate.now().toString());
        String EquipmentID = comequipid.getValue();
        String EquipmentName = lblequipname.getText();
        String LaborID = comlaborid.getValue();
        String Name = lbllaborname.getText();
        java.sql.Date UseDate =  java.sql.Date.valueOf(lbldate.getText());


        EquipmentUsageDTO equipmentUsageDto = new EquipmentUsageDTO(
                EquipmentID,
                EquipmentName,
                LaborID,
                Name,
                UseDate
        );
        boolean isSaved = equipmentUsageBO.save(equipmentUsageDto);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Equipment Usage saved...!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save Equipment Usage...!").show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EquipmentID = comequipid.getValue();
        String LaborID = comlaborid.getValue();
        java.sql.Date UseDate =  java.sql.Date.valueOf(lbldate.getText());



        EquipmentUsageDTO equipmentUsageDto = new EquipmentUsageDTO(
                EquipmentID,
                lblequipname.getText(),
                LaborID,
                lbllaborname.getText(),
                UseDate

        );
        boolean isUpdate = equipmentUsageBO.update(equipmentUsageDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Usage update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Usage...!").show();
        }
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comequipid.getSelectionModel().clearSelection();
        lblequipname.setText("");
        //comlaborid.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lbldate.setText(LocalDate.now().toString());


    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborid.getItems().addAll(laborIDs);
    }
    private void loadEquipmentIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> equipmentIDs = equipmentBO. getAllAddEquipmentIds();
        comequipid.getItems().addAll(equipmentIDs );
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentUsageDTO> equipmentUsageDtos = equipmentUsageBO.getAll();
        ObservableList<EquipmentUsageTM> equipmentUsageTMS = FXCollections.observableArrayList();

        for(EquipmentUsageDTO equipmentUsageDto : equipmentUsageDtos){
            EquipmentUsageTM equipmentUsageTM = new EquipmentUsageTM(
                    equipmentUsageDto.getEquipmentID(),
                    equipmentUsageDto.getEquipmentName(),
                    equipmentUsageDto.getLaborID(),
                    equipmentUsageDto.getLaborName(),
                    (java.sql.Date.valueOf(String.valueOf(equipmentUsageDto.getUseDate())))



            );
            equipmentUsageTMS.add(equipmentUsageTM);
        }
        useequipmentable.setItems(equipmentUsageTMS);
    }

}
