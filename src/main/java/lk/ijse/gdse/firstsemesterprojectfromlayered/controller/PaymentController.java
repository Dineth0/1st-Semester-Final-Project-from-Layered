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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.PaymentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.PaymentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private AnchorPane PaymentPage;

    @FXML
    private Button addpayment;

    @FXML
    private TableColumn<PaymentBO,String> collabor;

    @FXML
    private TableColumn<PaymentBO,Double> colmts;

    @FXML
    private TableColumn<PaymentBO,String> colname;

    @FXML
    private TableColumn<PaymentBO,String> colpayment;

    @FXML
    private TableColumn<PaymentBO,String> colstatus;

    @FXML
    private TableView<PaymentTM> paymenttable1;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colpayment.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colmts.setCellValueFactory(new PropertyValueFactory<>("Monthly_Total_Salary"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load PaymentID").show();
        }
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDtos = paymentBO.getAll();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for(PaymentDTO paymentDto : paymentDtos){
            PaymentTM paymentTM = new PaymentTM(
                    paymentDto.getPaymentID(),
                    paymentDto.getLaborID(),
                    paymentDto.getName(),
                    paymentDto.getOfficerID(),
                    paymentDto.getDay_Basic_Salary(),
                    paymentDto.getMonthly_Total_Salary(),
                    paymentDto.getStatus()


            );
            paymentTMS.add(paymentTM);
        }
        paymenttable1.setItems(paymentTMS);
    }

    @FXML
    void AddPaymnetOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPayment.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Labors");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = addpayment.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void GeneratePaymentReportOnAction(ActionEvent event) {

    }

    @FXML
    void TableOnClicked(MouseEvent event) {

    }

}
