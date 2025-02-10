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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.*;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.PaymentDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.PaymentTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPaymentController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<PaymentTM,String> caolofficer;

    @FXML
    private TableColumn<PaymentTM,Double> coldbs;

    @FXML
    private TableColumn<PaymentTM,String> collabor;

    @FXML
    private TableColumn<PaymentTM,Double> colmts;

    @FXML
    private TableColumn<PaymentTM,String> colname;

    @FXML
    private TableColumn<PaymentTM,String> colpayment;

    @FXML
    private TableColumn<PaymentTM,String> colstatus;

    @FXML
    private ComboBox<String> comlaborId;

    @FXML
    private ComboBox<String> comofficerId;

    @FXML
    private ComboBox<String> comstatus;
    private final String[] Status = {"Payment Taken","Not Taken"};

    @FXML
    private Label lblid;

    @FXML
    private Label lbllaborname;

    @FXML
    private Label lblofficername;

    @FXML
    private Label lblovertime;

    @FXML
    private Label lblstatus;

    @FXML
    private Label lblworkingdays;

    @FXML
    private TableView<PaymentTM> paymenttable;

    @FXML
    private TextField txtdbs;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);
    ShiftBO shiftBO = (ShiftBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SHIFT);
    AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ATTENDANCE);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comstatus.getItems().addAll(Status);
        colpayment.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        caolofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldbs.setCellValueFactory(new PropertyValueFactory<>("Day_Basic_Salary"));
        colmts.setCellValueFactory(new PropertyValueFactory<>("Monthly_Total_Salary"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            loadNextOPaymentID();
            loadLaborIDs();
            loadOfficerIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load PaymentID").show();
        }
    }

    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException {
        String selectedOfficerID = comofficerId.getSelectionModel().getSelectedItem();
        FactoryOfficerDTO factoryOfficerDto = factoryOfficerBO.findById(selectedOfficerID);

        if (factoryOfficerDto != null) {
            lblofficername.setText(factoryOfficerDto.getName());
        }
    }

    @FXML
    void Combo2OnAction(ActionEvent event) {
        String SelectedValue = comstatus.getValue();
        lblstatus.setText(SelectedValue);
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = comlaborId.getSelectionModel().getSelectedItem();
        LaborDTO laborDto = laborBO.findById(selectedLaborID);

        if (laborDto != null) {
            lbllaborname.setText(laborDto.getName());
            int currentMonth = LocalDate.now().getMonthValue();
            int currentYear = LocalDate.now().getYear();

            int workingDays = attendanceBO.getWorkingDays(selectedLaborID, currentMonth, currentYear);
            lblworkingdays.setText(String.valueOf(workingDays));

            int OverTime = shiftBO.getTotalOvertTime(selectedLaborID, currentMonth, currentYear);
            lblovertime.setText(String.valueOf(OverTime));
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String PaymentID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = paymentBO.delete(PaymentID );
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Payment...!").show();
            }
        }
    }

    @FXML
    void SaveONAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String PaymentID = lblid.getText();
        String LaborID = comlaborId.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerId.getValue();
        double Day_Basic_Salary = Double.parseDouble(txtdbs.getText());
        String Status = comstatus.getValue();

        int workingDays = Integer.parseInt(lblworkingdays.getText());
        int OverTime = Integer.parseInt(lblovertime.getText());

        double firstTotal = Day_Basic_Salary * workingDays;
        double EPF = firstTotal * 0.1;
        double Monthly_Total_Salary = (firstTotal - EPF) + (OverTime * 210);


        PaymentDTO paymentDto = new PaymentDTO(
                PaymentID,
                LaborID,
                Name,
                OfficerID,
                Day_Basic_Salary,
                Monthly_Total_Salary,
                Status
        );
        boolean isSaved = paymentBO.save(paymentDto);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Payment saved...!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save Payment...!").show();
        }
    }

    @FXML
    void TableOnClicked(MouseEvent event) {
        PaymentTM paymentTM = paymenttable.getSelectionModel().getSelectedItem();
        if(paymentTM != null){
            lblid.setText(paymentTM.getPaymentID());
            comlaborId.setValue(paymentTM.getLaborID());
            lbllaborname.setText(paymentTM.getName());
            comofficerId.setValue(paymentTM.getOfficerID());
            txtdbs.setText(String.valueOf(paymentTM.getDay_Basic_Salary()));
            comstatus.setValue(paymentTM.getStatus());


            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String PaymentID = lblid.getText();
        String LaborID = comlaborId.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerId.getValue();
        double Day_Basic_Salary = Double.parseDouble(txtdbs.getText());
        //String Monthly_Total_Salary = txtmts.getText();
        String Status = comstatus.getValue();

        int workingDays = Integer.parseInt(lblworkingdays.getText());
        int OverTime = Integer.parseInt(lblovertime.getText());

        double firstTotal = Day_Basic_Salary * workingDays;
        double EPF = firstTotal * 0.1;
        double Monthly_Total_Salary = (firstTotal - EPF) + (OverTime * 210);

        PaymentDTO paymentDto = new PaymentDTO(
                PaymentID,
                LaborID,
                Name,
                OfficerID,
                Day_Basic_Salary,
                Monthly_Total_Salary,
                Status
        );
        boolean isUpdate = paymentBO.update(paymentDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Payment...!").show();
        }
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborId.getItems().addAll(laborIDs);
    }
    private void loadOfficerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> leaveIDs = factoryOfficerBO. getAllOfficerIds();
        comofficerId.getItems().addAll(leaveIDs );
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextOPaymentID();
        loadTableData();

        btnsave.setDisable(false);
        btndelete.setDisable(true);
        btnupdate.setDisable(true);

        //comlaborId.getSelectionModel().clearSelection();
        //comofficerId.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lblofficername.setText("");
        lblworkingdays.setText("");
        lblovertime.setText("");
        txtdbs.setText("");
        comstatus.setValue(null);

    }

    private void loadNextOPaymentID()  {
        try{
            String nextLaborID = paymentBO.getNextID();
            lblid.setText(nextLaborID);
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Labor Not found").show();
        }
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
        paymenttable.setItems(paymentTMS);
    }
}
