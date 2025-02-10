package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.FactoryOfficer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FactoryOfficerDAO extends CrudDAO<FactoryOfficer> {
    public ArrayList<String> getAllOfficerIds() throws SQLException;
    public FactoryOfficer findById(String selectedOfficerID) throws SQLException;

}
