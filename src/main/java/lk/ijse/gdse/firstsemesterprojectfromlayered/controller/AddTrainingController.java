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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.FactoryOfficerBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.TrainingBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.TrainingDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.TrainingTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddTrainingController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<TrainingTM, Date> coldate;

    @FXML
    private TableColumn<TrainingTM,String> coldes;

    @FXML
    private TableColumn<TrainingTM,String> collabor;

    @FXML
    private TableColumn<TrainingTM,String> colofficer;

    @FXML
    private TableColumn<TrainingTM,String> coltraining;

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
    private TableView<TrainingTM> tableaddtraining;

    @FXML
    private TextField txtdes;

    TrainingBO trainingBO = (TrainingBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.TRAINING);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        coltraining.setCellValueFactory(new PropertyValueFactory<>("TrainingID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        //colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("TrainingDate"));


        try {
            loadNextTrainingID();
            loadLaborIDs();
            loadOfficerIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load TrainingID").show();
        }
    }

    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException {
        String selectedOfficerID = comofficerid.getSelectionModel().getSelectedItem();
        FactoryOfficerDTO factoryOfficerDto = factoryOfficerBO.findById(selectedOfficerID);

        if (factoryOfficerDto != null) {
            lblofficername.setText(factoryOfficerDto.getName());
        }
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = comlaborid.getSelectionModel().getSelectedItem();
        LaborDTO laborDto = laborBO.findById(selectedLaborID);

        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String TrainingID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = trainingBO.delete(TrainingID );
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Training deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Training...!").show();
            }
        }
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        TrainingTM trainingTM = tableaddtraining.getSelectionModel().getSelectedItem();
        if(trainingTM != null){
            lblid.setText(trainingTM.getTrainingID());
            comlaborid.setValue(trainingTM.getLaborID());
            //lbllaborname.setText(trainingTM.getName());
            comofficerid.setValue(trainingTM.getOfficerID());
            txtdes.setText(trainingTM.getDescription());
            lbldate.setText(trainingTM.getTrainingDate().toString());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        lbldate.setText(LocalDate.now().toString());
        String TrainingID = lblid.getText();
        String LaborID = comlaborid.getValue();
        //String Name = lbllaborname.getText();
        String OfficerID = comofficerid.getValue();
        String Description = txtdes.getText();
        java.sql.Date TrainingDate =  java.sql.Date.valueOf(lbldate.getText());


        TrainingDTO trainingDto = new TrainingDTO(
                TrainingID,
                LaborID,
                OfficerID,
                Description,
                TrainingDate

        );
        boolean isSaved = trainingBO.save(trainingDto);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Training saved...!").show();
        }else{
            refreshPage();
            new Alert(Alert.AlertType.ERROR,"Fail to save Training...!").show();
        }

    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String TrainingID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String OfficerID = comofficerid.getValue();
        String Description = txtdes.getText();
        java.sql.Date TrainingDate =  java.sql.Date.valueOf(lbldate.getText());


        TrainingDTO trainingDto = new TrainingDTO(
                TrainingID,
                LaborID,
                OfficerID,
                Description,
                TrainingDate
        );
        boolean isUpdate = trainingBO.update(trainingDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Training update...!").show();
        } else {
            refreshPage();
            new Alert(Alert.AlertType.ERROR, "Fail to update Training...!").show();
        }

    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextTrainingID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comlaborid.getSelectionModel().clearSelection();
        //comofficerid.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lblofficername.setText("");
        txtdes.setText("");
        lbldate.setText(LocalDate.now().toString());

    }

    public void loadNextTrainingID() {
        try{
            String nextLaborID = trainingBO.getNextID();
            lblid.setText(nextLaborID);
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Labor Not found").show();
        }
    }

    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborid.getItems().addAll(laborIDs);
    }
    private void loadOfficerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> leaveIDs = factoryOfficerBO.getAllOfficerIds();
        comofficerid.getItems().addAll(leaveIDs );
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<TrainingDTO> allTrainings = trainingBO.getAll();
        ObservableList<TrainingTM> trainingTMS = FXCollections.observableArrayList();

        for(TrainingDTO trainingDto : allTrainings){
            TrainingTM trainingTM = new TrainingTM(
                    trainingDto.getTrainingID(),
                    trainingDto.getLaborID(),
                    //trainingDto.getName(),
                    trainingDto.getOfficerID(),
                    trainingDto.getDescription(),
                    (java.sql.Date.valueOf(String.valueOf(trainingDto.getTrainingDate())))


            );
            trainingTMS.add(trainingTM);
        }
        tableaddtraining.setItems(trainingTMS);
    }
}

