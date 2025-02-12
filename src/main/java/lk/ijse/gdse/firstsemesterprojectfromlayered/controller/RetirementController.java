package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.RetirementBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.db.DbConnection;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.RetirementDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.RetirementTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RetirementController implements Initializable {

    @FXML
    private AnchorPane RetirementPage;

    @FXML
    private Button addretirement;

    @FXML
    private Button btnreort;

    @FXML
    private TableColumn<RetirementTM,String> collabor;

    @FXML
    private TableColumn<RetirementTM,String> colname;

    @FXML
    private TableColumn<RetirementTM,String> colretire;

    @FXML
    private TableView<RetirementTM> retiretable;


    RetirementBO retirementBO = (RetirementBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RETIREMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colretire.setCellValueFactory(new PropertyValueFactory<>("RetirementID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));


        try {
            //loadNextRetirementID();
            refreshPage();;
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load RetirementID").show();
        }
    }
    public void refreshPage() throws SQLException {
//        loadNextRetirementID();
//        //loadTableData();


    } private void loadTableData() throws SQLException, ClassNotFoundException {
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
        retiretable.setItems(retirementTMS);
    }

    @FXML
    void AddRetirementOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddRetirement.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Union_Members");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = addretirement.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void GenarateRetirementReportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/RetirementReport.jrxml"
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
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }


    }

}

