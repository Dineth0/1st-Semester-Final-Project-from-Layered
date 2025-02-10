package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.InsuranceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InsuranceBO extends SuperBO {
    public boolean save(InsuranceDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(InsuranceDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<InsuranceDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;
}
