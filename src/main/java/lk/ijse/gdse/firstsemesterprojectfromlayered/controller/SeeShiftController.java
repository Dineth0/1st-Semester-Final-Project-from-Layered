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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.ShiftBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ShiftDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.ShiftTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeShiftController implements Initializable {
    @FXML
    private TableColumn<ShiftTM, Date> coldate;

    @FXML
    private TableColumn<ShiftTM,String> colend;

    @FXML
    private TableColumn<ShiftTM,String> collabor;

    @FXML
    private TableColumn<ShiftTM,String> colover;

    @FXML
    private TableColumn<ShiftTM,String> colshift;

    @FXML
    private TableColumn<ShiftTM,String> colstart;

    @FXML
    private TableColumn<ShiftTM,String> colsup;

    @FXML
    private TableView<ShiftTM> tableaddshift1;

    ShiftBO shiftBO = (ShiftBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SHIFT);

    @FXML
    void OnClikedTable(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colshift.setCellValueFactory(new PropertyValueFactory<>("ShiftID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colsup.setCellValueFactory(new PropertyValueFactory<>("SupervisorID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("ShiftDate"));
        colstart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colend.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        colover.setCellValueFactory(new PropertyValueFactory<>("OverTime"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ShiftDTO> shiftDtos = shiftBO.getAll();

        ObservableList<ShiftTM> shiftTMS = FXCollections.observableArrayList();

        for (ShiftDTO shiftDto : shiftDtos) {
            ShiftTM shiftTM = new ShiftTM(
                    shiftDto.getShiftID(),
                    shiftDto.getLaborID(),
                    shiftDto.getSupervisorID(),
                    (Date.valueOf(shiftDto.getShiftDate().toLocalDate())),
                    shiftDto.getStartTime(),
                    shiftDto.getEndTime(),
                    shiftDto.getOverTime()
            );
            shiftTMS.add(shiftTM);
        }

        tableaddshift1.setItems(shiftTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
    }

}

