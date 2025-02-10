package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Equipment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquipmentDAO extends CrudDAO<Equipment> {
    public Equipment findById(String selectedEquipmentID) throws SQLException, ClassNotFoundException ;
    public int getEquipmentCount() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllAddEquipmentIds() throws SQLException,ClassNotFoundException;


}
