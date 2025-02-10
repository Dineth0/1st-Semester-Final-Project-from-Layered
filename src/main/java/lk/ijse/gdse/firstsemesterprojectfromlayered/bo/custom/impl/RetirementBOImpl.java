package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.RetirementBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.LaborDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.RetirementDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.db.DbConnection;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Retirement;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.RetirementDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetirementBOImpl implements RetirementBO {

    RetirementDAO retirementDAO = (RetirementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETIREMENT);
    LaborDAO laborDAO = (LaborDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LABOR);

    @Override
    public int getTotalRetirements() throws SQLException, ClassNotFoundException {
        return retirementDAO.getTotalRetirements();
    }

    @Override
    public boolean save(RetirementDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = DbConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            boolean saved = retirementDAO.save(new Retirement(dto.getRetirementID(), dto.getLaborID(), dto.getName(), dto.getOfficerID(), dto.getRetirementDate(), dto.getTotalYearsWorked(), dto.getFundPayment()));
            if (saved) {
                boolean deleted = laborDAO.delete(dto.getLaborID());
                if (deleted) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean update(RetirementDTO dto) throws SQLException, ClassNotFoundException {
        return retirementDAO.update(new Retirement(dto.getRetirementID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getRetirementDate(),dto.getTotalYearsWorked(),dto.getFundPayment()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return retirementDAO.delete(ID);
    }

    @Override
    public ArrayList<RetirementDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Retirement> retirements = retirementDAO.getAll();
        ArrayList<RetirementDTO> retirementDTOs = new ArrayList<>();
        for (Retirement retirement : retirements) {
            retirementDTOs.add(new RetirementDTO(retirement.getRetirementID(),retirement.getLaborID(),retirement.getName(),retirement.getOfficerID(),retirement.getRetirementDate(),retirement.getTotalYearsWorked(),retirement.getFundPayment()));
        }
        return retirementDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return retirementDAO.getNextID();
    }
}
