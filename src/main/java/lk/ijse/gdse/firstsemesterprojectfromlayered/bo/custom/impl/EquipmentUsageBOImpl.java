package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.EquipmentUsageBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.EquipmentUsageDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.EquipmentUsage;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.EquipmentUsageDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentUsageBOImpl implements EquipmentUsageBO {

    EquipmentUsageDAO equipmentUsageDAO = (EquipmentUsageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EQUIPMENTUSAGE);

    @Override
    public boolean save(EquipmentUsageDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentUsageDAO.save(new EquipmentUsage(dto.getEquipmentID(),dto.getEquipmentName(),dto.getLaborID(),dto.getLaborName(),dto.getUseDate()));
    }

    @Override
    public boolean update(EquipmentUsageDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentUsageDAO.update(new EquipmentUsage(dto.getEquipmentID(),dto.getEquipmentName(),dto.getLaborID(),dto.getLaborName(),dto.getUseDate()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return equipmentUsageDAO.delete(ID);
    }

    @Override
    public ArrayList<EquipmentUsageDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EquipmentUsageDTO> equipmentUsagesDTOs = new ArrayList<>();
        List<EquipmentUsage> equipmentUsages = equipmentUsageDAO.getAll();
        for (EquipmentUsage equipmentUsage : equipmentUsages) {
            equipmentUsagesDTOs.add(new EquipmentUsageDTO(equipmentUsage.getEquipmentID(),equipmentUsage.getEquipmentName(),equipmentUsage.getLaborID(),equipmentUsage.getLaborName(),equipmentUsage.getUseDate()));
        }
        return equipmentUsagesDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
