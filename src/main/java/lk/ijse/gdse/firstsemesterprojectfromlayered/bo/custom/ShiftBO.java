package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ShiftDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShiftBO extends SuperBO {
    public boolean save(ShiftDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(ShiftDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<ShiftDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;

    public int getTotalOvertTime(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;

}
