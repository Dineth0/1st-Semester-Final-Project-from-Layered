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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.SupervisorBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.SupervisorDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.SupervisorTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupervisorController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colsupid;

    @FXML
    private Label lblsupid;

    @FXML
    private AnchorPane supervisorpage;

    @FXML
    private TableView<SupervisorTM> suptable;

    @FXML
    private TextField txtname;

    SupervisorBO supervisorBO = (SupervisorBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPERVISOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colsupid.setCellValueFactory(new PropertyValueFactory<>("SupervisorID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));

        try {
            loadNextOfficerID();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load FactoryOfficer").show();
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SuperviosrID = lblsupid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            supervisorBO.delete(SuperviosrID);

            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Officer deleted...!").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to delete Officer...!").show();
        }
    }


//        SupervisorTM factoryOfficerTM = (SupervisorTM) suptable.getSelectionModel().getSelectedItem();
//        if (factoryOfficerTM != null){
//            lblsupid.setText(factoryOfficerTM.getSupervisorID());
//            txtname.setText(factoryOfficerTM.getName());
//
//
//            btnsave.setDisable(true);
//
//            btndelete.setDisable(false);
//            btnupdate.setDisable(false);
//        }

    @FXML
    void onClickedTable(MouseEvent event) {
        SupervisorTM factoryOfficerTM = (SupervisorTM) suptable.getSelectionModel().getSelectedItem();
        if (factoryOfficerTM != null) {
            lblsupid.setText(factoryOfficerTM.getSupervisorID());
            txtname.setText(factoryOfficerTM.getName());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextOfficerID();
        loadTableData();

        btnsave.setDisable(false);

        btnupdate.setDisable(true);
        btndelete.setDisable(true);

        txtname.setText("");

    }
    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SupervisorID = lblsupid.getText();
        String Name = txtname.getText();


        boolean isSaved =  supervisorBO.save(new SupervisorDTO(SupervisorID, Name));
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Officer saved...!").show();

            loadNextOfficerID();
            refreshPage();
            loadTableData();
            txtname.setText("");
        }else{
            refreshPage();
            new Alert(Alert.AlertType.ERROR,"Fail to save Officer...!").show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SupervisorID = lblsupid.getText();
        String name = txtname.getText();


        boolean isUpdate = supervisorBO.update(new SupervisorDTO(SupervisorID, name));
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
        } else {
            refreshPage();
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }
    }
    public void loadNextOfficerID() {
        try{
            String nextOfficerID = supervisorBO.getNextID();
            lblsupid.setText(nextOfficerID);
        }catch(SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Fail to load FactoryOfficer").show();
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SupervisorDTO> factoryOfficerDtos = supervisorBO.getAll();

        if (factoryOfficerDtos != null) {
            ObservableList<SupervisorTM> factoryOfficerTMS = FXCollections.observableArrayList();
            for (SupervisorDTO dto : factoryOfficerDtos) {
                SupervisorTM tm = new SupervisorTM(dto.getSupervisorID(), dto.getName());
                factoryOfficerTMS.add(tm);
            }
            suptable.setItems(factoryOfficerTMS);
        }
    }


    public void restOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        refreshPage();
    }


}
