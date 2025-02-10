package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.PaymentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.PaymentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Payment;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public int getTotalPaymentCount() throws SQLException, ClassNotFoundException {
        return paymentDAO.getTotalPaymentCount();
    }

    @Override
    public boolean save(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPaymentID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getDay_Basic_Salary(),dto.getMonthly_Total_Salary(),dto.getStatus()));
    }

    @Override
    public boolean update(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentID(),dto.getLaborID(),dto.getName(), dto.getOfficerID(),dto.getDay_Basic_Salary(),dto.getMonthly_Total_Salary(),dto.getStatus()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(ID);
    }

    @Override
    public ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<PaymentDTO> paymentDTOs = new ArrayList<>();
       List<Payment> payments = paymentDAO.getAll();
       for (Payment payment : payments) {
           paymentDTOs.add(new PaymentDTO(payment.getPaymentID(),payment.getLaborID(),payment.getName(),payment.getOfficerID(),payment.getDay_Basic_Salary(),payment.getMonthly_Total_Salary(),payment.getStatus()));
       }
       return paymentDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return paymentDAO.getNextID();
    }
}
