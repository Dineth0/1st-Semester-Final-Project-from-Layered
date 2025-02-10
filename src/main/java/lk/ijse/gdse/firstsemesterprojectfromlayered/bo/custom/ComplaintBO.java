package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ComplaintDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ComplaintBO extends SuperBO {
    public boolean save(ComplaintDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(ComplaintDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<ComplaintDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;
}
