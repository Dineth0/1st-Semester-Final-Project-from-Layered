package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AttendanceBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AttendanceDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.AttendanceTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeAttendanceController implements Initializable {

    @FXML
    private TableView<AttendanceTM> Attendancetable1;

    @FXML
    private TableColumn<AttendanceTM,String> colLid;

    @FXML
    private TableColumn<AttendanceTM,String> colSid;

    @FXML
    private TableColumn<AttendanceTM, Date> coldate;

    @FXML
    private TableColumn<AttendanceTM,String> colid;

    @FXML
    private TableColumn<AttendanceTM,String> colstatus;

    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    @FXML
    void OnClickedTable(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colid.setCellValueFactory(new PropertyValueFactory<>("AttendanceID"));
        colLid.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colSid.setCellValueFactory(new PropertyValueFactory<>("SupervisorID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("AttendDate"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {

            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> attendanceDtos = attendanceBO.getAll();

        ObservableList<AttendanceTM> attendanceTMS = FXCollections.observableArrayList();

        for (AttendanceDTO attendanceDto : attendanceDtos) {
            AttendanceTM attendanceTM = new AttendanceTM(
                    attendanceDto.getAttendanceID(),
                    attendanceDto.getLaborID(),
                    attendanceDto.getSupervisorID(),
                    (Date.valueOf(String.valueOf(attendanceDto.getAttendDate()))),
                    attendanceDto.getStatus()
            );
            attendanceTMS.add(attendanceTM);
        }

        Attendancetable1.setItems(attendanceTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
    }
}



