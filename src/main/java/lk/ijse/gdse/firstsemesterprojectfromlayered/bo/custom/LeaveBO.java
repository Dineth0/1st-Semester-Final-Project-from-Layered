package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LeaveDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LeaveBO extends SuperBO {

    public boolean save(LeaveDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(LeaveDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<LeaveDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;
}
