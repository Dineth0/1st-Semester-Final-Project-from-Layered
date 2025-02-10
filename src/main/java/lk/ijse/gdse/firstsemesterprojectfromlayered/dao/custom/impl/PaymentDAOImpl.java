package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.PaymentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public int getTotalPaymentCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT count(*) from Payment where Status='Payment Taken'");
        if(rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Payment values(?,?,?,?,?,?,?)", entity.getPaymentID(), entity.getLaborID(), entity.getName(), entity.getOfficerID(), entity.getDay_Basic_Salary(), entity.getMonthly_Total_Salary(), entity.getStatus());
            return isSaved;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Payment set  LaborID=?, Name=?, OfficerID=?, Day_Besic_Salary=?,Monthly_Total_Salalry=?,Status =? where PaymentID=?", entity.getLaborID(), entity.getName(), entity.getOfficerID(), entity.getDay_Basic_Salary(), entity.getMonthly_Total_Salary(), entity.getStatus(), entity.getPaymentID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Payment where PaymentID=?",ID);
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Payment");
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        while (rst.next()){
            paymentArrayList.add( new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7)));
        }
        return paymentArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PaymentID FROM Payment ORDER BY PaymentID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }
}
