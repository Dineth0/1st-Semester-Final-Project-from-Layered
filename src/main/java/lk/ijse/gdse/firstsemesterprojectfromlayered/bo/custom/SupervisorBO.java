package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.SupervisorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupervisorBO extends SuperBO {
    public ArrayList<String> getAllOfficerIds() throws SQLException;

    public SupervisorDTO findById(String ID) throws SQLException;

    public boolean save(SupervisorDTO dto) throws SQLException, ClassNotFoundException;


    public boolean update(SupervisorDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<SupervisorDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;
}

