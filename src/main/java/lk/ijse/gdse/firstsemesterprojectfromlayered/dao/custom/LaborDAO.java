package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Labor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LaborDAO extends CrudDAO<Labor> {


    public Labor findById(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllLaborIDs() throws SQLException, ClassNotFoundException;

    public int getTotalLabors() throws SQLException, ClassNotFoundException;
}