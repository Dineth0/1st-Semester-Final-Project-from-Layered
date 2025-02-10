package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentUsageDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquipmentUsageBO extends SuperBO {
    public boolean save(EquipmentUsageDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(EquipmentUsageDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<EquipmentUsageDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException ;

}
