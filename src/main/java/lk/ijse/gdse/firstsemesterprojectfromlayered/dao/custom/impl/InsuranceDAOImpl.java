package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.InsuranceDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Insurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceDAOImpl implements InsuranceDAO {
    @Override
    public boolean save(Insurance entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Insuranace values(?,?,?,?,?)", entity.getPolicyNumber(), entity.getAccidentID(), entity.getName(), entity.getOfficerID(), entity.getInsurancePayment());
        return isSaved;
    }

    @Override
    public boolean update(Insurance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Insuranace set AccidentID=?, OfficerID=?, InsurancePayment=? where PolicyNumber=?", entity.getAccidentID(), entity.getOfficerID(), entity.getInsurancePayment(), entity.getPolicyNumber());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Insuranace where PolicyNumber=?",ID);
    }

    @Override
    public ArrayList<Insurance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Insuranace");
        ArrayList<Insurance> insuranceArrayList = new ArrayList<>();
        while (rst.next()){
            insuranceArrayList.add( new Insurance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)));
        }
        return insuranceArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT PolicyNumber FROM Insuranace ORDER BY PolicyNumber DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("I%03d", newIdIndex);
        }
        return "I001";
    }
}
