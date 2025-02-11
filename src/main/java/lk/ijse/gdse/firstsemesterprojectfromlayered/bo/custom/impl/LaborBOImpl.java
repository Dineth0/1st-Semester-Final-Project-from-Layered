package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LaborBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.LaborDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Labor;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LaborDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LaborBOImpl implements LaborBO {
    LaborDAO laborDAO = (LaborDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LABOR);

    @Override
    public LaborDTO  findById(String ID) throws SQLException, ClassNotFoundException {
        Labor labor = laborDAO.findById(ID);
        return new LaborDTO(labor.getLaborID(),labor.getName(),labor.getAge(),labor.getAddress(),labor.getContactNumber());
    }

    @Override
    public ArrayList<String> getAllLaborIDs() throws SQLException, ClassNotFoundException {
        return laborDAO.getAllLaborIDs();
    }

    @Override
    public int getTotalLabors() throws SQLException, ClassNotFoundException {
        return laborDAO.getTotalLabors();
    }

    @Override
    public boolean save(LaborDTO entity) throws SQLException, ClassNotFoundException {
        return laborDAO.save(new Labor(entity.getLaborID(),entity.getName(),entity.getAge(),entity.getAddress(),entity.getContactNumber()));
    }

    @Override
    public boolean update(LaborDTO entity) throws SQLException, ClassNotFoundException {
        return laborDAO.update(new Labor(entity.getLaborID(),entity.getName(),entity.getAge(),entity.getAddress(),entity.getContactNumber()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return laborDAO.delete(ID);
    }

    @Override
    public ArrayList<LaborDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<LaborDTO> laborDTOs = new ArrayList<>();
        ArrayList<Labor> labors = laborDAO.getAll();
        for (Labor labor : labors) {
            laborDTOs.add(new LaborDTO(labor.getLaborID(),labor.getName(),labor.getAge(),labor.getAddress(),labor.getContactNumber()));
        }
        return laborDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return laborDAO.getNextID();
    }
}
