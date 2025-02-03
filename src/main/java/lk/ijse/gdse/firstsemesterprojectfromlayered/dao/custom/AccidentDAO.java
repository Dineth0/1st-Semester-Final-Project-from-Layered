package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Accident;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccidentDAO extends CrudDAO<Accident> {
    public ArrayList<String> getAllAccidentIDs() throws SQLException, ClassNotFoundException;
    public Accident findById(String selectedAccidentID) throws SQLException,ClassNotFoundException;
}
