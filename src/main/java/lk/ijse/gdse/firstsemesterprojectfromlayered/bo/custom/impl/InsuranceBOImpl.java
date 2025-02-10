package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.InsuranceBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.InsuranceDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Insurance;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.InsuranceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceBOImpl implements InsuranceBO {

    InsuranceDAO insuranceDAO = (InsuranceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSURANCE);

    @Override
    public boolean save(InsuranceDTO dto) throws SQLException, ClassNotFoundException {
        return insuranceDAO.save(new Insurance(dto.getPolicyNumber(),dto.getAccidentID(),dto.getName(),dto.getOfficerID(),dto.getInsurancePayment()));
    }

    @Override
    public boolean update(InsuranceDTO dto) throws SQLException, ClassNotFoundException {
        return insuranceDAO.update(new Insurance(dto.getPolicyNumber(),dto.getAccidentID(),dto.getName(),dto.getOfficerID(),dto.getInsurancePayment()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return insuranceDAO.delete(ID);
    }

    @Override
    public ArrayList<InsuranceDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<InsuranceDTO> insuranceDTOs = new ArrayList<>();
        ArrayList<Insurance> insurances = insuranceDAO.getAll();
        for (Insurance insurance : insurances) {
            insuranceDTOs.add(new InsuranceDTO(insurance.getPolicyNumber(),insurance.getAccidentID(),insurance.getName(),insurance.getOfficerID(),insurance.getInsurancePayment()));
        }
        return insuranceDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return insuranceDAO.getNextID();
    }
}
