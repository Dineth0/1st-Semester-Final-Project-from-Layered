package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AccidentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.FactoryOfficerBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.db.DbConnection;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AccidentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.AccidentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccidentController implements Initializable {

    @FXML
    private AnchorPane AccidentPage;

    @FXML
    private TableView<AccidentTM> Accidenttable;

    @FXML
    private Button addinsurance;

    @FXML
    private Button btndelete;

    @FXML
    private Button btndelete11;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<AccidentTM,String> colacc;

    @FXML
    private TableColumn<AccidentTM, Date> coldate;

    @FXML
    private TableColumn<AccidentTM,String> coldes;

    @FXML
    private TableColumn<AccidentTM,String> collabor;

    @FXML
    private TableColumn<AccidentTM,String> colname;

    @FXML
    private TableColumn<AccidentTM,String> colofficer;

    @FXML
private ComboBox<String> comlaborid;

    @FXML
    private ComboBox<String> comofficerid;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblid;

    @FXML
    private Label lbllaborname;

    @FXML
    private Label lblofficername;

    @FXML
    private TextField txtdes;

    AccidentBO accidentBO = (AccidentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ACCIDENT);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        colacc.setCellValueFactory(new PropertyValueFactory<>("AccidentID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("AccidentDate"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try {
            loadNextAccidentID();
            loadLaborIDs();
            loadOfficerIDs();
            loadTableData();
            refreshPage();;
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load  AccidentID").show();
        }
    }

    @FXML
    void AddInsuranceOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddInsurance.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Union_Members");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = addinsurance.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException {
        String selectedOfficerID = String.valueOf(comofficerid.getSelectionModel().getSelectedItem());
        FactoryOfficerDTO factoryOfficerDto = factoryOfficerBO.findById(selectedOfficerID);

        if (factoryOfficerDto != null) {
            lblofficername.setText(factoryOfficerDto.getName());
        }
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = String.valueOf(comlaborid.getSelectionModel().getSelectedItem());
        LaborDTO laborDto = laborBO.findById(selectedLaborID);

        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AccidentID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = accidentBO.delete(AccidentID );
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Accident deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Accident...!").show();
            }
        }
    }

    @FXML
    void GenerateAccidentOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/AccidentReport.jrxml"
                            ));

            Connection connection = DbConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }


    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        AccidentTM accidentTM = Accidenttable.getSelectionModel().getSelectedItem();
        if(accidentTM != null){
            lblid.setText(accidentTM.getAccidentID());
            comlaborid.setValue(accidentTM.getLaborID());
            lbllaborname.setText(accidentTM.getName());
            comofficerid.setValue(accidentTM.getOfficerID());
            lblofficername.setText(accidentTM.getName());
            lbldate.setText(accidentTM.getAccidentDate().toString());
            txtdes.setText(accidentTM.getDescription());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) {

    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lbldate.setText(LocalDate.now().toString());
        String AccidentID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerid.getValue();
        java.sql.Date AccidentDate =  java.sql.Date.valueOf(lbldate.getText());
        String Description = txtdes.getText();


        txtdes.setStyle(txtdes.getStyle() + ";-fx-border-color:  #FFAD60;");


        String DescriptionPattern = "^[A-Za-z ]+$";
        boolean isValidDescription = Description.matches(DescriptionPattern);

        if (!isValidDescription) {
            txtdes.setStyle(txtdes.getStyle() + ";-fx-border-color: red;");
        }
        if (isValidDescription) {
            AccidentDTO accidentDto = new AccidentDTO(
                    AccidentID,
                    LaborID,
                    Name,
                    OfficerID,
                    AccidentDate,
                    Description
            );
            boolean isSaved = accidentBO.save(accidentDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Accident saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Accident...!").show();
            }
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AccidentID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String OfficerID = comofficerid.getValue();
        java.sql.Date AccidentDate =  java.sql.Date.valueOf(lbldate.getText());
        String Description = txtdes.getText();

        AccidentDTO accidentDto = new AccidentDTO(
                AccidentID,
                LaborID,
                lbllaborname.getText(),
                OfficerID,
                AccidentDate,
                Description

        );
        boolean isUpdate = accidentBO.update(accidentDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Accident update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Accident...!").show();
        }
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextAccidentID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comlaborid.getSelectionModel().clearSelection();
        //comofficerid.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lbldate.setText(LocalDate.now().toString());
        txtdes.setText("");

    }


    public void loadNextAccidentID() throws SQLException, ClassNotFoundException {
        String nextAccidentID = accidentBO.getNextID();
        lblid.setText(nextAccidentID);
    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborid.getItems().addAll(laborIDs);
    }
    private void loadOfficerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> OfficerIDs = factoryOfficerBO.getAllOfficerIds();
        comofficerid.getItems().addAll(OfficerIDs);
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<AccidentDTO> accidentDtos = accidentBO.getAll();
        ObservableList<AccidentTM> accidentTMS = FXCollections.observableArrayList();

        for(AccidentDTO accidentDto : accidentDtos){
            AccidentTM accidentTM = new AccidentTM(
                    accidentDto.getAccidentID(),
                    accidentDto.getLaborID(),
                    accidentDto.getName(),
                    accidentDto.getOfficerID(),
                    (java.sql.Date.valueOf(String.valueOf(accidentDto.getAccidentDate()))),
                    accidentDto.getDescription()


            );
            accidentTMS.add(accidentTM);
        }
        Accidenttable.setItems(accidentTMS);
    }


}
