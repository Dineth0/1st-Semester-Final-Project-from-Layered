package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.ShiftDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftDAOImpl implements ShiftDAO {
    @Override
    public boolean save(Shift entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Shift values(?,?,?,?,?,?,?)", entity.getShiftID(), entity.getLaborID(), entity.getSupervisorID(), entity.getShiftDate(), entity.getStartTime(), entity.getEndTime(), entity.getOverTime());
            return isSaved;
    }

    @Override
    public boolean update(Shift entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Shift set LaborID=?, SupervisorID=?, Date=?, StartTime=?, EndTime=?, OverTime=? where ShiftID=?", entity.getLaborID(), entity.getSupervisorID(), entity.getShiftDate(), entity.getStartTime(), entity.getEndTime(), entity.getOverTime(), entity.getShiftID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Shift where ShiftID=?",ID);
    }

    @Override
    public ArrayList<Shift> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Shift");
        ArrayList<Shift> shiftArraylist = new ArrayList<>();
        while (rst.next()){
            shiftArraylist.add( new Shift(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)));
        }
        return shiftArraylist;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT ShiftID FROM Shift ORDER BY ShiftID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }

    @Override
    public int getTotalOvertTime(String LaborID, int month, int year) throws SQLException, ClassNotFoundException {
        int OverTime = 0;
        ResultSet rst = SQLUtil.execute( "SELECT SUM(OverTime) as TotalOverTime from Shift where LaborID = ? AND Month(Date) = ? AND Year(Date) = ?", LaborID, month, year);
        if (rst.next()) {
            return rst.getInt("TotalOverTime");
        }
        return 0;
    }
}
