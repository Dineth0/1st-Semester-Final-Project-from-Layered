package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.RetirementDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RetirementBO extends SuperBO {

    public int getTotalRetirements() throws SQLException, ClassNotFoundException;

    public boolean save(RetirementDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(RetirementDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException ;

    public ArrayList<RetirementDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;

}
