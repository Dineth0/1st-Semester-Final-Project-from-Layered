package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.TrainingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TrainingBO extends SuperBO {

    public boolean save(TrainingDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(TrainingDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<TrainingDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;
}
