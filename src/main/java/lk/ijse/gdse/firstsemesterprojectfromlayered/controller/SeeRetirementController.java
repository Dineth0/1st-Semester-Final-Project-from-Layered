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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.RetirementBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.RetirementDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.RetirementTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeeRetirementController implements Initializable {

    @FXML
    private TableColumn<RetirementTM, Date> coldate;

    @FXML
    private TableColumn<RetirementTM,Double> colfpayment;

    @FXML
    private TableColumn<RetirementTM,String> collabor;

    @FXML
    private TableColumn<RetirementTM,String> colname;

    @FXML
    private TableColumn<RetirementTM,String> colofficer;

    @FXML
    private TableColumn<RetirementTM,String> colretire;

    @FXML
    private TableColumn<RetirementTM,Integer> coltyw;

    @FXML
    private TableView<RetirementTM> tableaddretirement1;

    RetirementBO retirementBO = (RetirementBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RETIREMENT);

    @FXML
    void TableOnClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colretire.setCellValueFactory(new PropertyValueFactory<>("RetirementID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("RetirementDate"));
        coltyw.setCellValueFactory(new PropertyValueFactory<>("TotalYearsWorked"));
        colfpayment.setCellValueFactory(new PropertyValueFactory<>("FundPayment"));

        try {
            refreshPage();;
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<RetirementDTO> retirementDtos = retirementBO.getAll();
        ObservableList<RetirementTM> retirementTMS = FXCollections.observableArrayList();

        for (RetirementDTO retirementDto : retirementDtos) {
            RetirementTM retirementTM = new RetirementTM(
                    retirementDto.getRetirementID(),
                    retirementDto.getLaborID(),
                    retirementDto.getName(),
                    retirementDto.getOfficerID(),
                    (Date.valueOf(String.valueOf(retirementDto.getRetirementDate()))),
                    retirementDto.getTotalYearsWorked(),
                    retirementDto.getFundPayment()

            );
            retirementTMS.add(retirementTM);
        }
        tableaddretirement1.setItems(retirementTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
    }
}
