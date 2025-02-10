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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AccidentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AccidentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.AccidentTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeAccidentController implements Initializable {



    @FXML
    private TableView<AccidentTM> Accidenttable1;

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

    AccidentBO accidentBO = (AccidentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ACCIDENT);

    @FXML
    void OnTableClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


     colacc.setCellValueFactory(new PropertyValueFactory<>("AccidentID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("AccidentDate"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try {
        refreshPage();;
        loadTableData();
    } catch (Exception e) {
        e.printStackTrace();

    }
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
                (Date.valueOf(String.valueOf(accidentDto.getAccidentDate()))),
                accidentDto.getDescription()


        );
        accidentTMS.add(accidentTM);
    }
    Accidenttable1.setItems(accidentTMS);
}
private void refreshPage() throws SQLException, ClassNotFoundException {

    loadTableData();
}
}

