package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.LaborTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AddLaborController implements Initializable {

    @FXML
    private AnchorPane Addpage;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<LaborTM, String> coladdress;

    @FXML
    private TableColumn<LaborTM, Integer> colage;

    @FXML
    private TableColumn<LaborTM, Integer> colconnumber;

    @FXML
    private TableColumn<LaborTM, String> colid;

    @FXML
    private TableColumn<LaborTM, String> colname;

    @FXML
    private TableView<LaborTM> labortable;

    @FXML
    private Label lblLaborId;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtconnumber;

    @FXML
    private TextField txtname;

    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colage.setCellValueFactory(new PropertyValueFactory<>("Age"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colconnumber.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
        try {
            loadNextLaborID();
            loadTableData();
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextLaborID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        txtname.setText("");
        txtage.setText("");
        txtaddress.setText("");
        txtconnumber.setText("");
    }

    void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<LaborDTO> laborDtos = laborBO.getAll();

        ObservableList<LaborTM> laborTMS = FXCollections.observableArrayList();

        for (LaborDTO laborDto : laborDtos) {
            LaborTM laborTM = new LaborTM(
                    laborDto.getLaborID(),
                    laborDto.getName(),
                    laborDto.getAge(),
                    laborDto.getAddress(),
                    laborDto.getContactNumber()
            );
            laborTMS.add(laborTM);
        }
        labortable.setItems(laborTMS);
    }


    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String LaborID = lblLaborId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = laborBO.delete(LaborID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Labor deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Labor...!").show();

            }
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String LaborID = lblLaborId.getText();
        String Name = txtname.getText();
        int Age = Integer.parseInt(txtage.getText());
        String Address = txtaddress.getText();
        String ContactNumber = txtconnumber.getText();

        txtname.setStyle(txtname.getStyle() + ";-fx-border-color:   #343a40;");
        txtage.setStyle(txtage.getStyle() + ";-fx-border-color:   #343a40;");
        txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color:   #343a40;");
        txtconnumber.setStyle(txtconnumber.getStyle() + ";-fx-border-color:   #343a40;");

        String NamePattern = "^[A-Za-z ]+$";
        //Integer AgePattern = Integer.parseInt(Age + "^([3-9]|[1-6][0-9])$");
        String AddressPattern = "^[a-zA-Z0-9\s,.'-]{3,}$";
        String ContactNumberPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";


        boolean isValidName = Name.matches(NamePattern);

        boolean isValidAddress = Address.matches(AddressPattern);
        boolean isValiContanctNumber = ContactNumber.matches(ContactNumberPattern);


        if (!isValidName) {
            System.out.println(txtname.getStyle());
            txtname.setStyle(txtname.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");

        }


        if (!isValiContanctNumber) {
            txtconnumber.setStyle(txtconnumber.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName && isValidAddress && isValiContanctNumber) {
            LaborDTO laborDto = new LaborDTO(
                    LaborID,
                    Name,
                    Age,
                    Address,
                    ContactNumber
            );

            boolean isSaved = laborBO.save(laborDto);


            if (isSaved) {

                refreshPage();

                new Alert(Alert.AlertType.INFORMATION, "Labor saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Labor...!").show();
            }
        }

    }


    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String LaborID = lblLaborId.getText();
        String Name = txtname.getText();
        int Age = Integer.parseInt(txtage.getText());
        String Address = txtaddress.getText();
        String ContactNumber = txtconnumber.getText();

        LaborDTO laborDto = new LaborDTO(
                LaborID,
                Name,
                Age,
                Address,
                ContactNumber
        );

        boolean isUpdate = laborBO.update(laborDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Labor Updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Update Labor...!").show();
        }

    }


    @FXML
    void onTableClicked(MouseEvent event) {
        LaborTM laborTM = labortable.getSelectionModel().getSelectedItem();
        if (laborTM != null) {
            lblLaborId.setText(laborTM.getLaborID());
            txtname.setText(laborTM.getName());
            txtage.setText(String.valueOf(laborTM.getAge()));
            txtaddress.setText(laborTM.getAddress());
            txtconnumber.setText(laborTM.getContactNumber());

            btndelete.setDisable(false);
            btnsave.setDisable(true);
            btnupdate.setDisable(false);
        }
    }

    private void loadNextLaborID() throws SQLException, ClassNotFoundException {
        String nextLaborID = laborBO.getNextID();
        lblLaborId.setText(nextLaborID);

    }
}

