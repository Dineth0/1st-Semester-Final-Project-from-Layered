package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.PaymentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.RetirementBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane Dashboard2;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblpayment;

    @FXML
    private Label lbltime;

    @FXML
    private Label lbltotallabor;

    @FXML
    private Label lbltotalretirement;

    @FXML
    private Button seeaccident;

    @FXML
    private Button seeattend;

    @FXML
    private Button seeinsurance;

    @FXML
    private Button seelabor;

    @FXML
    private Button seeleave;

    @FXML
    private Button seepayment;

    @FXML
    private Button seeretirement;

    @FXML
    private Button seeshift;

    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);
    RetirementBO retirementBO = (RetirementBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RETIREMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        lbltime.setText(LocalTime.now().format(formatter));
        loadLaborCount();
        loadRetirementCount();
        loadPaymentCount();
    }

    @FXML
    void AddOfficerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FactoryOfficer.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Factory Officer");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void AddSupervisorOAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Supervisor.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Supervisor");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeAccidentsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeAccidents.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Accidents");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeAttendancesOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeAttendances.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Attendances");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeInsurancesOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeInsurances.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Insurances");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeLaborOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeLabors.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Labors");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeLeavesOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeLeaves.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Leaves");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeePaymentsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeePayments.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Payments");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeRetirementsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeRetirements.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Retirements");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void SeeShiftsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SeeShifts.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("See Shifts");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = seelabor.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }
    private void loadRetirementCount() {
        try {

            int totalRetirements = retirementBO.getTotalRetirements();
            lbltotalretirement.setText(String.valueOf(totalRetirements));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPaymentCount() {
        try {

            int CompletePayment = paymentBO.getTotalPaymentCount();
            lblpayment.setText(String.valueOf(CompletePayment));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadLaborCount() {
        try {

            int totalLabors = laborBO.getTotalLabors();
            lbltotallabor.setText(String.valueOf(totalLabors));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
