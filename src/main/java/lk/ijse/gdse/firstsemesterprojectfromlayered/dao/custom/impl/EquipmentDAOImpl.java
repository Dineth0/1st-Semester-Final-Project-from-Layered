package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.EquipmentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Equipment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentDAOImpl implements EquipmentDAO {

    @Override
    public Equipment findById(String selectedEquipmentID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from Equipment where EquipmentID=?",selectedEquipmentID);
        if (rst.next()){
            return new  Equipment (
                    rst.getString(1),
                    rst.getString(2));
        }
        return null;
    }

    @Override
    public int getEquipmentCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT count(*) from Equipment ");
        if (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public ArrayList<String> getAllAddEquipmentIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT EquipmentID from Equipment");
        ArrayList<String> EquipmentIDs = new ArrayList<>();
        while (rst.next()){
            EquipmentIDs.add(rst.getString(1));
        }
        return EquipmentIDs;
    }

    @Override
    public boolean save(Equipment entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Equipment values(?,?)", entity.getEquipmentID(), entity.getEquipmentName());
        return isSaved;
    }

    @Override
    public boolean update(Equipment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Equipment set  EquipmentName=? where EquipmentID=?", entity.getEquipmentName(), entity.getEquipmentID()
        );
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Equipment where EquipmentID=?",ID);
    }

    @Override
    public ArrayList<Equipment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Equipment");
        ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
        while (rst.next()){
            equipmentArrayList.add( new Equipment(
                    rst.getString(1),
                    rst.getString(2)));
        }
        return equipmentArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT EquipmentID FROM Equipment ORDER BY EquipmentID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("U%03d", newIdIndex);
        }
        return "U001";
    }
}
