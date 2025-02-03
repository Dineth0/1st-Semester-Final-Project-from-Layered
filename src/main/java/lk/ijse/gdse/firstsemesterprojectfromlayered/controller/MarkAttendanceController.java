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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AttendanceBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.SupervisorBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AttendanceDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.AttendanceTM;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.SupervisorDTO;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MarkAttendanceController implements Initializable {

    @FXML
    private AnchorPane AttendPage;

    @FXML
    private AnchorPane AttendancePage;

    @FXML
    private TableView<AttendanceTM> Attendancetable;

    @FXML
    private Button btndelete;

    @FXML
    private Button btndelete1;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<AttendanceTM,String> colLid;

    @FXML
    private TableColumn<AttendanceTM,String> colSid;

    @FXML
    private TableColumn<AttendanceTM,Date> coldate;

    @FXML
    private TableColumn<AttendanceTM,String> colid;

    @FXML
    private TableColumn<AttendanceTM,String> colstatus;

    @FXML
    private ComboBox<String> comlaborId;

    @FXML
    private ComboBox<String> comsupId;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblid;

    @FXML
    private Label lbllaborname;

    @FXML
    private Label lblsupname;

    @FXML
    private ChoiceBox<String> statusbox;
    private final String[] Status =  {"Present", "Absent"};

    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ATTENDANCE);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    SupervisorBO supervisorBO = (SupervisorBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPERVISOR);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusbox.getItems().addAll(Status);
        lbldate.setText(LocalDate.now().toString());

        colid.setCellValueFactory(new PropertyValueFactory<>("AttendanceID"));
        colLid.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colSid.setCellValueFactory(new PropertyValueFactory<>("SupervisorID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("AttendDate"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            loadNextAttendanceID();
            loadLaborIDs();
            loadSupervisorIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load  AttendanceID").show();
        }
    }


    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException,ClassNotFoundException,NullPointerException {
        String selectedSupervisorID = comsupId.getValue();
        SupervisorDTO supervisorDto = supervisorBO.findById(selectedSupervisorID);

        if (supervisorDto != null) {
            lblsupname.setText(supervisorDto.getName());
        }
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException,NullPointerException {
        String selectedLaborID = comlaborId.getValue();
        LaborDTO laborDto = laborBO.findById(selectedLaborID);


        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = attendanceBO.delete(ID);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, " Attendance deleted...!").show();
            } else {
                refreshPage();
                new Alert(Alert.AlertType.ERROR, "Fail to delete  Attendance...!").show();
            }
        }
    }

    @FXML
    void GenerateCheckAttendReportOnAction(ActionEvent event) {

    }

    @FXML
    void OnClickedTable(MouseEvent event) {
        AttendanceTM attendanceTM = (AttendanceTM) Attendancetable.getSelectionModel().getSelectedItem();
        if (attendanceTM != null){
            lblid.setText(attendanceTM.getAttendanceID());
            comlaborId.setValue(attendanceTM.getLaborID());
            comsupId.setValue(attendanceTM.getSupervisorID());
            lbldate.setText(LocalDate.now().toString());
            statusbox.setValue(attendanceTM.getStatus());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

//    String LaborID = comlaborId.getValue();
//    String SupervisorID = comsupId.getValue();

//        System.out.println(LaborID);
//        System.out.println(SupervisorID);

    @FXML
    void test(ActionEvent event) {





    }

    @FXML
    void SaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException,NullPointerException {


        lbldate.setText(LocalDate.now().toString());
        String AttendanceID =lblid.getText();
        String LaborID =comlaborId.getValue();
        String SupervisorID = comsupId.getValue();
        Date AttendDate = Date.valueOf(lbldate.getText());
        String Status = statusbox.getValue();

        AttendanceDTO attendanceDto = new AttendanceDTO(
                AttendanceID,
                LaborID,
                SupervisorID,
                AttendDate,
                Status);
        boolean isSaved =  attendanceBO.save(attendanceDto);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION," Attendance saved...!").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save  Attendance...!").show();
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AttendanceID = lblid.getText();
        String LaborID = comlaborId.getValue();
        String SupervisorID = comsupId.getValue();
        Date AttendDate = Date.valueOf(lbldate.getText());
        String Status = statusbox.getValue();

        AttendanceDTO attendanceDto = new AttendanceDTO(
                AttendanceID,
                LaborID,
                SupervisorID,
                AttendDate,
                Status
        );

        boolean isUpdate = attendanceBO.update(attendanceDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " Attendance update...!").show();
        } else {
            refreshPage();
            new Alert(Alert.AlertType.ERROR, "Fail to update  Attendance...!").show();
        }
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextAttendanceID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);
        //comlaborId.getSelectionModel().clearSelection();
        //comsupId.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lblsupname.setText("");
        lbldate.setText(LocalDate.now().toString());
        statusbox.setValue(null);
    }

    public void loadNextAttendanceID() {
        try {
            String nextAttendanceID = attendanceBO.getNextID();
            lblid.setText(nextAttendanceID);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,"Attendance not Fond").show();
        }

    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborId.getItems().addAll(laborIDs);
    }

    private void loadSupervisorIDs() throws SQLException {
        ArrayList<String> supervisorIDs = supervisorBO.getAllOfficerIds();
        comsupId.getItems().addAll(supervisorIDs );
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> allAttendances = attendanceBO.getAll();

        ObservableList<AttendanceTM> attendanceTMS = FXCollections.observableArrayList();

        for (AttendanceDTO attendanceDto : allAttendances) {
            AttendanceTM attendanceTM = new AttendanceTM(
                    attendanceDto.getAttendanceID(),
                    attendanceDto.getLaborID(),
                    attendanceDto.getSupervisorID(),
                    attendanceDto.getAttendDate(),
                    attendanceDto.getStatus()
            );
            attendanceTMS.add(attendanceTM);
        }

        Attendancetable.setItems(attendanceTMS);
    }

}