package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FactoryOfficerBO extends SuperBO {
    public String getNextID() throws SQLException, ClassNotFoundException ;

    public ArrayList<String> getAllOfficerIds() throws SQLException, ClassNotFoundException ;

    public FactoryOfficerDTO findById(String selectedOfficerID) throws SQLException;
    public ArrayList<FactoryOfficerDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(FactoryOfficerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(FactoryOfficerDTO dto) throws SQLException,ClassNotFoundException ;

    public boolean delete(String ID) throws SQLException,ClassNotFoundException ;

}
