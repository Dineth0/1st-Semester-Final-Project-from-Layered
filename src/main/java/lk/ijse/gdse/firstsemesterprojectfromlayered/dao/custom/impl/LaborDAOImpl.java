package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.LaborDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Labor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LaborDAOImpl implements LaborDAO {


    @Override
    public Labor findById(String ID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * from Labor where LaborID=?", ID);
        if (rst.next()) {
            return new Labor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllLaborIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT LaborID from Labor");
        ArrayList<String> LaborIDs = new ArrayList<>();
        while (rst.next()) {
            LaborIDs.add(rst.getString(1));
        }
        return LaborIDs;
    }

    @Override
    public int getTotalLabors() throws SQLException, ClassNotFoundException {
        String query = "SELECT count(*) as TotalLabors FROM Labor";
        ResultSet rst = SQLUtil.execute(query);
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean save(Labor entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Labor values (?,?,?,?,?)", entity.getLaborID(), entity.getName(), entity.getAge(), entity.getAddress(), entity.getContactNumber());
        return isSaved;
    }

    @Override
    public boolean update(Labor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Labor set  Name=?, Age=?, Address=?,ContactNumber=? where LaborID=?", entity.getName(), entity.getAge(), entity.getAddress(), entity.getContactNumber(), entity.getLaborID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Labor where LaborID=?", ID);
    }

    @Override
    public ArrayList<Labor> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * from Labor");
        ArrayList<Labor> laborArrayList = new ArrayList<>();
        while (rst.next()) {
            laborArrayList.add(new Labor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)));
        }
        return laborArrayList;
    }

    @Override
    public String getNextID() throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT LaborID from Labor order by LaborID desc limit 1");
        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("L%03d",newIdIndex);
        }
        return  "L001";
    }
}
