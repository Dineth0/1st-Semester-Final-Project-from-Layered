package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.FactoryOfficerDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.FactoryOfficer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FactoryOfficerDAOImpl implements FactoryOfficerDAO {
    public String getNextID() throws SQLException {
        ResultSet resultSet =  SQLUtil.execute( "SELECT OfficerID from FactoryOfficer order by OfficerID desc limit 1");
        if(resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("F%03d",newIdIndex);
        }
        return "F001";
    }

    @Override
    public ArrayList<String> getAllOfficerIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT OfficerID from FactoryOfficer");
        ArrayList<String> OfficerIDs = new ArrayList<>();
        while (rst.next()){
            OfficerIDs.add(rst.getString(1));
        }
        return OfficerIDs;
    }

    @Override
    public FactoryOfficer findById(String selectedOfficerID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * from FactoryOfficer where OfficerID=?",selectedOfficerID);
        rst.next();
            return new FactoryOfficer (
                    rst.getString(1),
                    rst.getString(2));
    }

    @Override
    public ArrayList<FactoryOfficer> getAll() throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT * from FactoryOfficer");
        ArrayList<FactoryOfficer> factoryOfficerArrayList = new ArrayList<>();
        while (rst.next()){
            factoryOfficerArrayList.add(new FactoryOfficer (
                    rst.getString(1),
                    rst.getString(2)));
        }
        return factoryOfficerArrayList;
    }

    @Override
    public boolean save(FactoryOfficer entity) throws SQLException, ClassNotFoundException {
      return   SQLUtil.execute( "INSERT into FactoryOfficer values (?,?)", entity.getOfficerID(),entity.getName());
    }

    @Override
    public boolean update(FactoryOfficer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update FactoryOfficer set Name=? where OfficerID=?", entity.getName(), entity.getOfficerID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from FactoryOfficer where OfficerID =?",ID);
    }
}
