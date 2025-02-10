package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    private AnchorPane ancBody;

    @FXML
    private AnchorPane dashboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboard();
    }
    private void loadDashboard() {
        try {
            AnchorPane laborpage = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
            ancBody.getChildren().clear();
            ancBody.getChildren().add(laborpage);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void AccidentOnAction(ActionEvent event) throws IOException {
        AnchorPane AccidentPage = FXMLLoader.load(getClass().getResource("/view/Accident.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(AccidentPage);
    }

    @FXML
    void DashboardOnAction(ActionEvent event) {
        loadDashboard();
    }

    @FXML
    void EquipmenOnAction(ActionEvent event) throws IOException {
        AnchorPane EquipmenPage = FXMLLoader.load(getClass().getResource("/view/Equipmen.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(EquipmenPage);
    }

    @FXML
    void FeedbackOnAction(ActionEvent event) throws IOException {
        AnchorPane ComplaintPage = FXMLLoader.load(getClass().getResource("/view/Complaint.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(ComplaintPage);
    }

    @FXML
    void LaborOnAction(ActionEvent event) throws IOException {
        AnchorPane laborpage = FXMLLoader.load(getClass().getResource("/view/Labor.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(laborpage);
    }

    @FXML
    void PaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane PaymentPage = FXMLLoader.load(getClass().getResource("/view/Payment.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(PaymentPage);
    }

    @FXML
    void RetirementOnAction(ActionEvent event) throws IOException {
        AnchorPane RetirementPage = FXMLLoader.load(getClass().getResource("/view/Retirement.fxml"));
        ancBody.getChildren().clear();
        ancBody.getChildren().add(RetirementPage);
    }


}

