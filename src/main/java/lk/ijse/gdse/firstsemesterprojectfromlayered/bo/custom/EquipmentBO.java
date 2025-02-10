package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquipmentBO extends SuperBO {

    public EquipmentDTO findById(String selectedEquipmentID) throws SQLException, ClassNotFoundException ;

    public int getEquipmentCount() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllAddEquipmentIds() throws SQLException, ClassNotFoundException;

    public boolean save(EquipmentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<EquipmentDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException;


}


