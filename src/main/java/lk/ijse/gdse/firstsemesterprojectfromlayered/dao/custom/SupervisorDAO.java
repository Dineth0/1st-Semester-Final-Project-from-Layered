package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Supervisor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupervisorDAO extends CrudDAO<Supervisor> {
    public ArrayList<String> getAllOfficerIds() throws SQLException;
    public Supervisor findById(String ID) throws SQLException;

}
