package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {

    public boolean save(AttendanceDTO dto) throws SQLException, ClassNotFoundException,NullPointerException;

    public boolean update(AttendanceDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<AttendanceDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;

    public int getWorkingDays(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;

}


