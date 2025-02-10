package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.EquipmentUsageDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.EquipmentUsage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentUsageDAOImpl implements EquipmentUsageDAO {
    @Override
    public boolean save(EquipmentUsage entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into EquipmentUsage values(?,?,?,?,?)", entity.getEquipmentID(), entity.getEquipmentName(), entity.getLaborID(), entity.getLaborName(), entity.getUseDate());
        return isSaved;
    }

    @Override
    public boolean update(EquipmentUsage entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update EquipmentUsage set  LaborID=?,  UseDate=?  where EquipmentID=?", entity.getLaborID(), entity.getUseDate(), entity.getEquipmentID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from EquipmentUsage where UseDate=?",ID);
    }

    @Override
    public ArrayList<EquipmentUsage> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from EquipmentUsage");
        ArrayList<EquipmentUsage> equipmentUsageArrayList = new ArrayList<>();
        while (rst.next()){
            equipmentUsageArrayList.add( new EquipmentUsage(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5)));
        }
        return equipmentUsageArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
