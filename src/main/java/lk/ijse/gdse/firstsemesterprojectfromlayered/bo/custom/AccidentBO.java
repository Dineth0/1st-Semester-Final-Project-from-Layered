package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AccidentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccidentBO extends SuperBO {

    public ArrayList<String> getAllAccidentIDs() throws SQLException, ClassNotFoundException;

    public AccidentDTO findById(String selectedAccidentID) throws SQLException, ClassNotFoundException;

    public boolean save(AccidentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(AccidentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<AccidentDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;

}
