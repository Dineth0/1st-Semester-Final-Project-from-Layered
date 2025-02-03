module lk.ijse.gdse.firstsemesterprojectfromlayered {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires jdk.jfr;
    requires mysql.connector.j;


    opens lk.ijse.gdse.firstsemesterprojectfromlayered.controller to javafx.fxml;
    exports lk.ijse.gdse.firstsemesterprojectfromlayered;
    //opens lk.ijse.gdse.firstsemesterprojectfromlayered.tm to javafx.fxml;
    opens lk.ijse.gdse.firstsemesterprojectfromlayered.tm to javafx.base;


}