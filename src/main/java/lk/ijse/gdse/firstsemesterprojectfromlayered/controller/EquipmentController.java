package lk.ijse.gdse.firstsemesterprojectfromlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.BOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentUsageBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentUsageDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.tm.EquipmentUsageTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EquipmentController implements Initializable {

    @FXML
    private AnchorPane EquipmenPage;

    @FXML
    private Button addid;

    @FXML
    private ImageView backimage;

    @FXML
    private TableColumn<EquipmentUsageTM, Date> coldate;

    @FXML
    private TableColumn<EquipmentUsageTM,String> colequipname;

    @FXML
    private TableColumn<EquipmentUsageTM,String> colname;

    @FXML
    private TableView<EquipmentUsageTM> equiptable;

    @FXML
    private Label lblcount;

    @FXML
    private Button useid;

    EquipmentUsageBO equipmentUsageBO = (EquipmentUsageBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EQUIPMENTUSAGE);
    EquipmentBO equipmentBO = (EquipmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EQUIPMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colname.setCellValueFactory(new PropertyValueFactory<>("LaborName"));
        colequipname.setCellValueFactory(new PropertyValueFactory<>("EquipmentName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("UseDate"));

        loadEquipmentCount();

        try {

            refreshPage();
            ;
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load LeaveID").show();
        }
    }
    public void refreshPage() throws SQLException {

    }
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentUsageDTO> equipmentUsageDtos = equipmentUsageBO.getAll();
        ObservableList<EquipmentUsageTM> equipmentUsageTMS = FXCollections.observableArrayList();

        for (EquipmentUsageDTO equipmentUsageDto : equipmentUsageDtos) {
            EquipmentUsageTM equipmentUsageTM = new EquipmentUsageTM(
                    equipmentUsageDto.getEquipmentID(),
                    equipmentUsageDto.getEquipmentName(),
                    equipmentUsageDto.getLaborID(),
                    equipmentUsageDto.getLaborName(),
                    (java.sql.Date.valueOf(String.valueOf(equipmentUsageDto.getUseDate())))


            );
            equipmentUsageTMS.add(equipmentUsageTM);
        }
        equiptable.setItems(equipmentUsageTMS);
    }

    @FXML
    void restOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadTableData();
        refreshPage();
    }
    private void loadEquipmentCount() {
        try {

            int equipmentCount = equipmentBO.getEquipmentCount();
            lblcount.setText(String.valueOf(equipmentCount));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AddEquipmentsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddEquipment.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Equipment");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = addid.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void AddUsagesOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EquipmentUsage.fxml"));
        Parent load = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Add Usage");

        stage.initModality(Modality.APPLICATION_MODAL);

        Window underWindow = addid.getScene().getWindow();
        stage.initOwner(underWindow);

        stage.showAndWait();
    }

    @FXML
    void GenarateEquipmentUsageReportOnAction(ActionEvent event) {

    }

    @FXML
    void GenerateEquipmentOnAction(ActionEvent event) {

    }

}
