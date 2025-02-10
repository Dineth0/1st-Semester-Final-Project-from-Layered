package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.RetirementDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Retirement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetirementDAOImpl implements RetirementDAO {
    @Override
    public int getTotalRetirements() throws SQLException, ClassNotFoundException {
        String query = "SELECT count(*) from Retirement";
        ResultSet rst  = SQLUtil.execute(query);
        if(rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean save(Retirement entity) throws SQLException, ClassNotFoundException {
        boolean isRetirementSaved = SQLUtil.execute("insert into Retirement values (?,?,?,?,?,?,?)", entity.getRetirementID(), entity.getLaborID(), entity.getName(), entity.getOfficerID(), Date.valueOf(String.valueOf(entity.getRetirementDate())), entity.getTotalYearsWorked(), entity.getFundPayment());
         return isRetirementSaved;
    }

    @Override
    public boolean update(Retirement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Retirement set  LaborID=?, OfficerID=?, RetirementDate=?,TotalYearsWorked=?, FundPayment =? where RetirementID=?", entity.getLaborID(), entity.getOfficerID(), entity.getRetirementDate(), entity.getTotalYearsWorked(), entity.getFundPayment(), entity.getRetirementID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Retirement where RetirementID=?",ID);
    }

    @Override
    public ArrayList<Retirement> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Retirement");
        ArrayList<Retirement> retirementArrayList = new ArrayList<>();
        while (rst.next()){
            retirementArrayList.add( new Retirement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getInt(6),
                    rst.getDouble(7)));
        }
        return retirementArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT RetirementID FROM Retirement ORDER BY RetirementID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("R%03d", newIdIndex);
        }
        return "R001";
    }
}
