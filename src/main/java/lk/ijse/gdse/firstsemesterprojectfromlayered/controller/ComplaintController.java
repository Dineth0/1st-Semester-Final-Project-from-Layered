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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.ComplaintBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ComplaintDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.ComplaintTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ComplaintController  implements Initializable {

    @FXML
    private AnchorPane ComplaintPage;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<ComplaintTM,String> colcomplaint;

    @FXML
    private TableColumn<ComplaintTM, Date> coldate;

    @FXML
    private TableColumn<ComplaintTM,String> coldes;

    @FXML
    private TableColumn<ComplaintTM,String> collabor;

    @FXML
    private TableColumn<ComplaintTM,String> colname;

    @FXML
    private TableColumn<ComplaintTM,String> colseen;

    @FXML
    private ComboBox<String> comlaborId;

    @FXML
    private TableView<ComplaintTM> complainttable;

    @FXML
    private ComboBox<String> comseen;
    private final String[] Manager_Seen = {"Yes","No"};

    @FXML
    private Label lbldate;

    @FXML
    private Label lblid;

    @FXML
    private Label lbllaborname;

    @FXML
    private Label lblseen;

    @FXML
    private TextField txtdes;

    ComplaintBO complaintBO = (ComplaintBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.COMPLAINT);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        comseen.getItems().addAll(Manager_Seen);
        colcomplaint.setCellValueFactory(new PropertyValueFactory<>("ComplaintID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("ComplaintDate"));
        colseen.setCellValueFactory(new PropertyValueFactory<>("ManagerSeen"));


        try {
            loadNextComplaintID();
            loadLaborIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load ComplaintID").show();
        }
    }

    @FXML
    void Combo1OnAction(ActionEvent event) {
        String SelectedValue = comseen.getValue();
        lblseen.setText(SelectedValue);
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = comlaborId.getSelectionModel().getSelectedItem();
        LaborDTO laborDto = laborBO.findById(selectedLaborID);

        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ComplaintID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = complaintBO.delete(ComplaintID );
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Complaint deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Complaint...!").show();
            }
        }
    }

    @FXML
    void GenerateComplaintReportOnAction(ActionEvent event) {

    }

    @FXML
    void SaveONAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ComplaintID = lblid.getText();
        String LaborID = comlaborId.getValue();
        String Name = lbllaborname.getText();
        String Description = txtdes.getText();
        java.sql.Date ComplaintDate =  java.sql.Date.valueOf(lbldate.getText());
        String Manager_Seen = comseen.getValue();

        ComplaintDTO complaintDto = new ComplaintDTO(
                ComplaintID,
                LaborID,
                Name,
                Description,
                ComplaintDate,
                Manager_Seen
        );
        boolean isSaved = complaintBO.save(complaintDto);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Complaint saved...!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save Complaint...!").show();
        }
    }

    @FXML
    void TableOnClicked(MouseEvent event) {
        ComplaintTM complaintTM = complainttable.getSelectionModel().getSelectedItem();
        if(complaintTM != null){
            lblid.setText(complaintTM.getComplaintID());
            comlaborId.setValue(complaintTM.getLaborID());
            lbllaborname.setText(complaintTM.getName());
            txtdes.setText(complaintTM.getDescription());
            lbldate.setText(complaintTM.getComplaintDate().toString());
            comseen.setValue(complaintTM.getManagerSeen());

            btnsave.setDisable(false);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ComplaintID = lblid.getText();
        String LaborID = comlaborId.getValue();
        String Description = txtdes.getText();
        java.sql.Date ComplaintDate =  java.sql.Date.valueOf(lbldate.getText());
        String Manager_Seen = comseen.getValue();

        ComplaintDTO complaintDto = new ComplaintDTO(
                ComplaintID,
                LaborID,
                lbllaborname.getText(),
                Description,
                ComplaintDate,
                Manager_Seen
        );
        boolean isUpdate = complaintBO.update(complaintDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Leave Complaint...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Complaint...!").show();
        }
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextComplaintID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comlaborId.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        txtdes.setText("");
        lbldate.setText(LocalDate.now().toString());
        comseen.setValue(null);
    }


    public void loadNextComplaintID() throws SQLException, ClassNotFoundException {
        String nextComplaintID = complaintBO.getNextID();
        lblid.setText(nextComplaintID);
    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborId.getItems().addAll(laborIDs);
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ComplaintDTO> complaintDtos = complaintBO.getAll();
        ObservableList<ComplaintTM> complaintTMS = FXCollections.observableArrayList();

        for(ComplaintDTO complaintDto : complaintDtos){
            ComplaintTM complaintTM = new ComplaintTM(
                    complaintDto.getComplaintID(),
                    complaintDto.getLaborID(),
                    complaintDto.getName(),
                    complaintDto.getDescription(),
                    (java.sql.Date.valueOf(String.valueOf(complaintDto.getComplaintDate()))),
                    complaintDto.getManager_Seen()


            );
            complaintTMS.add(complaintTM);
        }
        complainttable.setItems(complaintTMS);
    }

}

