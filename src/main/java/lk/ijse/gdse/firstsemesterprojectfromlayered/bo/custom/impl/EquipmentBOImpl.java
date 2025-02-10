package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.EquipmentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Equipment;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentBOImpl implements EquipmentBO {

    EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EQUIPMENT);


    @Override
    public EquipmentDTO findById(String selectedEquipmentID) throws SQLException, ClassNotFoundException {
        Equipment equipment = equipmentDAO.findById(selectedEquipmentID);
        return new EquipmentDTO(equipment.getEquipmentID(),equipment.getEquipmentName());
    }

    @Override
    public int getEquipmentCount() throws SQLException, ClassNotFoundException {
        return equipmentDAO.getEquipmentCount();
    }

    @Override
    public ArrayList<String> getAllAddEquipmentIds() throws SQLException, ClassNotFoundException {
        return equipmentDAO.getAllAddEquipmentIds();
    }

    @Override
    public boolean save(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentDAO.save(new Equipment(dto.getEquipmentID(),dto.getEquipmentName()));
    }

    @Override
    public boolean update(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentDAO.update(new Equipment(dto.getEquipmentID(),dto.getEquipmentName()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return equipmentDAO.delete(ID);
    }

    @Override
    public ArrayList<EquipmentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();
        ArrayList<Equipment> equipments = equipmentDAO.getAll();
        for (Equipment equipment : equipments) {
            equipmentDTOS.add(new EquipmentDTO(equipment.getEquipmentID(),equipment.getEquipmentName()));
        }
        return equipmentDTOS;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return equipmentDAO.getNextID();
    }
}
