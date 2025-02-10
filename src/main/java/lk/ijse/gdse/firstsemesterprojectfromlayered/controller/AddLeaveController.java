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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LeaveBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LeaveDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.LeaveTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddLeaveController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<LeaveTM, Date> coldate;

    @FXML
    private TableColumn<LeaveTM, String> collabor;

    @FXML
    private TableColumn<LeaveTM, String> colleave;

    @FXML
    private TableColumn<LeaveTM, String> colname;

    @FXML
    private TableColumn<LeaveTM, String> colofficer;

    @FXML
    private TableColumn<LeaveTM, String> colreason;

    @FXML
    private TableColumn<LeaveTM, String> colstatus;

    @FXML
    private ComboBox<String> combostatus;
    private final String[] Status = {"Approved", "Rejected", "Pending"};

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
    private Label lblstatus;

    @FXML
    private TableView<LeaveTM> tableaddleave;

    @FXML
    private TextField txtreason;

    LeaveBO leaveBO = (LeaveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LEAVE);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        combostatus.getItems().addAll(Status);
        colleave.setCellValueFactory(new PropertyValueFactory<>("LeaveID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("LeaveDate"));
        colreason.setCellValueFactory(new PropertyValueFactory<>("Reason"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            loadNextLeaveID();
            loadLaborIDs();
            loadOfficerIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load LeaveID").show();
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
    void Combo2OnAction(ActionEvent event) {
        String SelectedValue = combostatus.getValue();
        lblstatus.setText(SelectedValue);
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
        String LeaveID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = laborBO.delete(LeaveID);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Leave deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Leave...!").show();
            }
        }
    }

    @FXML
    void OnTableClicked(MouseEvent event) {
        LeaveTM leaveTM = tableaddleave.getSelectionModel().getSelectedItem();
        if (leaveTM != null) {
            lblid.setText(leaveTM.getLeaveID());
            comlaborid.setValue(leaveTM.getLaborID());
            lbllaborname.setText(leaveTM.getName());
            comofficerid.setValue(leaveTM.getOfficerID());
            lbldate.setText(leaveTM.getLeaveDate().toString());
            txtreason.setText(leaveTM.getReason());
            combostatus.setValue(leaveTM.getStatus());

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
        String LeaveID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerid.getValue();
        Date LeaveDate = Date.valueOf(lbldate.getText());
        String Reason = txtreason.getText();
        String Status = combostatus.getValue();

        LeaveDTO leaveDto = new LeaveDTO(
                LeaveID,
                LaborID,
                Name,
                OfficerID,
                LeaveDate,
                Reason,
                Status
        );
        boolean isSaved = leaveBO.save(leaveDto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Leave saved...!").show();
        } else {
            refreshPage();
            new Alert(Alert.AlertType.ERROR, "Fail to save Leave...!").show();

        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String LeaveID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerid.getValue();
        Date LeaveDate = Date.valueOf(lbldate.getText());
        String Reason = txtreason.getText();
        String Status = combostatus.getValue();

        LeaveDTO leaveDto = new LeaveDTO(
                LeaveID,
                LaborID,
                Name,
                OfficerID,
                LeaveDate,
                Reason,
                Status
        );
        boolean isUpdate = leaveBO.update(leaveDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Leave update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Leave...!").show();
        }
    }

    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextLeaveID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comlaborid.getSelectionModel().clearSelection();
        //comofficerid.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lblofficername.setText("");
        lbldate.setText(LocalDate.now().toString());
        txtreason.setText("");
        combostatus.setValue(null);
    }


    public void loadNextLeaveID()  {
        try{
            String nextLaborID =leaveBO.getNextID();
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
        comofficerid.getItems().addAll(leaveIDs);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<LeaveDTO> leaveDtos = leaveBO.getAll();
        ObservableList<LeaveTM> leaveTMS = FXCollections.observableArrayList();

        for (LeaveDTO leaveDto : leaveDtos) {
            LeaveTM leaveTM = new LeaveTM(
                    leaveDto.getLeaveID(),
                    leaveDto.getLaborID(),
                    leaveDto.getName(),
                    leaveDto.getOfficerID(),
                    (Date.valueOf(String.valueOf(leaveDto.getLeaveDate()))),
                    leaveDto.getReason(),
                    leaveDto.getStatus()

            );
            leaveTMS.add(leaveTM);
        }
        tableaddleave.setItems(leaveTMS);
    }
}




