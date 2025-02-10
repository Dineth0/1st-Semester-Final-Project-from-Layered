package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LaborBO extends SuperBO {
    public LaborDTO findById(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllLaborIDs() throws SQLException, ClassNotFoundException ;

//    public int getWorkingDays(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;

    public int getTotalLabors() throws SQLException, ClassNotFoundException;

//    public int getTotalOvertTime(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;

    public boolean save(LaborDTO laborDTO) throws SQLException, ClassNotFoundException;

    public boolean update(LaborDTO laborDTO) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException    ;

    public ArrayList<LaborDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;


}
