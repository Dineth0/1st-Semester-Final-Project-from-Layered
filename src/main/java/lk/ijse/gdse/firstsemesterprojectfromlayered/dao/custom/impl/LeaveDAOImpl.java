package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.LeaveDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Leave;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveDAOImpl implements LeaveDAO {
    @Override
    public boolean save(Leave entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Vacation values(?,?,?,?,?,?,?)", entity.getLeaveID(), entity.getLaborID(), entity.getName(), entity.getOfficerID(), entity.getLeaveDate(), entity.getReason(), entity.getStatus());
        return isSaved;
    }

    @Override
    public boolean update(Leave entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Vacation set  LaborID=?, Name=?, OfficerID=?, Date=?,Reason=?, Status =? where LeaveID=?", entity.getLaborID(), entity.getName(), entity.getOfficerID(), entity.getLeaveDate(), entity.getReason(), entity.getStatus(), entity.getLeaveID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from vacation where LeaveID=?",ID);
    }

    @Override
    public ArrayList<Leave> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Vacation");
        ArrayList<Leave> leaveArrayList = new ArrayList<>();
        while (rst.next()){
            leaveArrayList.add( new Leave(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6),
                    rst.getString(7)));
        }
        return leaveArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT LeaveID FROM Vacation ORDER BY LeaveID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;

            return String.format("L%03d", newIdIndex);
        }
        return "L001";
    }
}
