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
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.FactoryOfficerBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.RetirementBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.RetirementDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.RetirementTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddRetirementController implements Initializable {

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

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
    private ComboBox<String> comlaborid;

    @FXML
    private ComboBox<String> comofficerid;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblid;

    @FXML
    private Label lbllaborname;

    @FXML
    private Label lblofficername;

    @FXML
    private TableView<RetirementTM> tableaddretirement;

    @FXML
    private TextField txtfpayment;

    @FXML
    private TextField txttyw;

    RetirementBO retirementBO = (RetirementBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RETIREMENT);
    LaborBO laborBO = (LaborBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LABOR);
    FactoryOfficerBO factoryOfficerBO = (FactoryOfficerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.FACTORYOFFICER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(LocalDate.now().toString());
        colretire.setCellValueFactory(new PropertyValueFactory<>("RetirementID"));
        collabor.setCellValueFactory(new PropertyValueFactory<>("LaborID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colofficer.setCellValueFactory(new PropertyValueFactory<>("OfficerID"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("RetirementDate"));
        coltyw.setCellValueFactory(new PropertyValueFactory<>("TotalYearsWorked"));
        colfpayment.setCellValueFactory(new PropertyValueFactory<>("FundPayment"));

        try {
            loadNextRetirementID();
            loadLaborIDs();
            loadOfficerIDs();
            loadTableData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load RetirementID").show();
        }
    }

    @FXML
    void Combo1OnAction(ActionEvent event) throws SQLException {
        String selectedOfficerID = comofficerid.getSelectionModel().getSelectedItem();
        if (selectedOfficerID != null) {
            FactoryOfficerDTO factoryOfficerDto = factoryOfficerBO.findById(selectedOfficerID);

            if (factoryOfficerDto != null) {
                lblofficername.setText(factoryOfficerDto.getName());
            }
        }
    }

    @FXML
    void ComboOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedLaborID = comlaborid.getSelectionModel().getSelectedItem();
        if (selectedLaborID != null) {
            LaborDTO laborDto = laborBO.findById(selectedLaborID);

            if (laborDto != null) {
                lbllaborname.setText(laborDto.getName());
            }
        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String RetirementID = lblid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = retirementBO.delete(RetirementID);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Retirement deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Retirement...!").show();
            }
        }
    }

    @FXML
    void ResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        try {
            lbldate.setText(LocalDate.now().toString());
            String RetirementID = lblid.getText();
            String LaborID = comlaborid.getValue();
            String Name = lbllaborname.getText();
            String OfficerID = comofficerid.getValue();
            java.sql.Date RetirementDate =  java.sql.Date.valueOf(lbldate.getText());
            int TotalYearsWorked = Integer.parseInt(txttyw.getText());
            double FundPayment = Double.parseDouble(txtfpayment.getText());

            RetirementDTO retirementDto = new RetirementDTO(
                    RetirementID,
                    LaborID,
                    Name,
                    OfficerID,
                    RetirementDate,
                    TotalYearsWorked,
                    FundPayment
            );

            boolean isSaved = retirementBO.save(retirementDto);
            if (isSaved) {
                refreshPage();
                loadTableData();
                new Alert(Alert.AlertType.INFORMATION, "Retirement saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Retirement...!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void TableOnClicked(MouseEvent event) {
        RetirementTM retirementTM = tableaddretirement.getSelectionModel().getSelectedItem();
        if (retirementTM != null) {
            lblid.setText(retirementTM.getRetirementID());
            comlaborid.setValue(retirementTM.getLaborID());
            lbllaborname.setText(retirementTM.getName());
            comofficerid.setValue(retirementTM.getOfficerID());
            lbldate.setText(retirementTM.getRetirementDate().toString());
            txttyw.setText(String.valueOf(retirementTM.getTotalYearsWorked()));
            txtfpayment.setText(String.valueOf(retirementTM.getFundPayment()));

            btnsave.setDisable(true);

            btndelete.setDisable(false);
            btnupdate.setDisable(false);
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String RetirementID = lblid.getText();
        String LaborID = comlaborid.getValue();
        String Name = lbllaborname.getText();
        String OfficerID = comofficerid.getValue();
        java.sql.Date RetirementDate =  java.sql.Date.valueOf(lbldate.getText());
        int TotalYearsWorked = Integer.parseInt(txttyw.getText());
        double FundPayment = Double.parseDouble(txtfpayment.getText());

        RetirementDTO retirementDto = new RetirementDTO(
                RetirementID,
                LaborID,
                Name,
                OfficerID,
                RetirementDate,
                TotalYearsWorked,
                FundPayment
        );
        boolean isUpdate = retirementBO.update(retirementDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Retirement update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Retirement...!").show();
        }
    }
    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextRetirementID();
        loadTableData();

        btndelete.setDisable(true);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);

        //comlaborid.getSelectionModel().clearSelection();
        //comofficerid.getSelectionModel().clearSelection();
        lbllaborname.setText("");
        lblofficername.setText("");
        lbldate.setText(LocalDate.now().toString());
        txttyw.setText("");
        txtfpayment.setText("");

    }



    public void loadNextRetirementID() throws SQLException, ClassNotFoundException {
        String nextRetirementID = retirementBO.getNextID();
        lblid.setText(nextRetirementID);
    }
    private void loadLaborIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> laborIDs = laborBO.getAllLaborIDs();
        comlaborid.getItems().addAll(laborIDs);
    }

    private void loadOfficerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> leaveIDs = factoryOfficerBO.getAllOfficerIds();
        comofficerid.getItems().addAll(leaveIDs);
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
                    (java.sql.Date.valueOf(String.valueOf(retirementDto.getRetirementDate()))),
                    retirementDto.getTotalYearsWorked(),
                    retirementDto.getFundPayment()

            );
            retirementTMS.add(retirementTM);
        }
        tableaddretirement.setItems(retirementTMS);
    }


}

