package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Labor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LaborBO extends SuperBO {

    public LaborDTO findById(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllLaborIDs() throws SQLException, ClassNotFoundException ;

    public int getTotalLabors() throws SQLException, ClassNotFoundException;

    public boolean save(LaborDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(LaborDTO entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<LaborDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;

}



