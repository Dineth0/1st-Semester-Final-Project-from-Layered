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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.FactoryOfficerBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.FactoryOfficerTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class FactoryOfficerController implements Initializable {

    @FXML
    private AnchorPane Officerpage;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<FactoryOfficerTM,String> colname;

    @FXML
    private TableColumn<FactoryOfficerTM,String> colofficer;

    @FXML
    private Label lblid;

    @FXML
    private TableView<FactoryOfficerTM> officertable;

    @FXML
    private TextField txtname;

    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));

        try{
            loadNextOfficerID();
            refreshPage();
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load FactoryOfficer").show();
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String OfficerID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            factoryOfficerBO.delete(OfficerID);

            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Officer deleted...!").show();

        } else{
            new Alert(Alert.AlertType.ERROR, "Fail to delete Officer...!").show();
            }
        }

    @FXML
    void OnTablecliked(MouseEvent event) {
        FactoryOfficerTM factoryOfficerTM = officertable.getSelectionModel().getSelectedItem();
        if (factoryOfficerTM != null){
            lblid.setText(factoryOfficerTM.getOfficerID());
            txtname.setText(factoryOfficerTM.getName());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
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
        String OfficerID = lblid.getText();
        String Name = txtname.getText();


        boolean isSaved =  factoryOfficerBO.save(new FactoryOfficerDTO(OfficerID, Name));
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
        String OfficerID = lblid.getText();
        String name = txtname.getText();


        boolean isUpdate = factoryOfficerBO.update(new FactoryOfficerDTO(OfficerID, name));
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
            String nextOfficerID = factoryOfficerBO.getNextID();
            lblid.setText(nextOfficerID);
        }catch(SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Fail to load FactoryOfficer").show();
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<FactoryOfficerDTO> factoryOfficerDtos = factoryOfficerBO.getAll();

        if (factoryOfficerDtos != null) {
            ObservableList<FactoryOfficerTM> factoryOfficerTMS = FXCollections.observableArrayList();
            for (FactoryOfficerDTO dto : factoryOfficerDtos) {
                FactoryOfficerTM tm = new FactoryOfficerTM(dto.getOfficerID(), dto.getName());
                factoryOfficerTMS.add(tm);
            }
            officertable.setItems(factoryOfficerTMS);
        }
    }


}

