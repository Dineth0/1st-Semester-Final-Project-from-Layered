package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.FactoryOfficerBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.FactoryOfficerDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.FactoryOfficer;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.FactoryOfficerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FactoryOfficerBOImpl implements FactoryOfficerBO {

    FactoryOfficerDAO factoryOfficerDAO = (FactoryOfficerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FACTORYOFFICER);

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return factoryOfficerDAO.getNextID();
    }

    @Override
    public ArrayList<String> getAllOfficerIds() throws SQLException, ClassNotFoundException {
        return factoryOfficerDAO.getAllOfficerIds();
    }


    @Override
    public FactoryOfficerDTO findById(String selectedOfficerID) throws SQLException {
        FactoryOfficer factoryOfficer = factoryOfficerDAO.findById(selectedOfficerID);
        return new FactoryOfficerDTO(factoryOfficer.getOfficerID(), factoryOfficer.getName());
    }

    @Override
    public ArrayList<FactoryOfficerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FactoryOfficerDTO> factoryOfficerDTOS = new ArrayList<>();
        ArrayList<FactoryOfficer> factoryOfficers = factoryOfficerDAO.getAll();
        for(FactoryOfficer factoryOfficer : factoryOfficers) {
            factoryOfficerDTOS.add(new FactoryOfficerDTO(factoryOfficer.getOfficerID(), factoryOfficer.getName()));
        }
        return factoryOfficerDTOS;
    }

    @Override
    public boolean save(FactoryOfficerDTO dto) throws SQLException, ClassNotFoundException {
        return factoryOfficerDAO.save(new FactoryOfficer(dto.getOfficerID(), dto.getName()));

    }

    @Override
    public boolean update(FactoryOfficerDTO dto) throws SQLException, ClassNotFoundException {
        return factoryOfficerDAO.update(new FactoryOfficer(dto.getOfficerID(), dto.getName()));

    }




    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException{
        return factoryOfficerDAO.delete(ID);
    }
}
