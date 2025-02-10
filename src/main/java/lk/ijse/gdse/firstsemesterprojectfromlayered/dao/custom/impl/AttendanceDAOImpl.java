package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.AttendanceDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean save(Attendance entity) throws SQLException, ClassNotFoundException,NullPointerException {
        return  SQLUtil.execute("insert into Attendance values(?,?,?,?,?)", entity.getAttendanceID(), entity.getLaborID(), entity.getSupervisorID(), entity.getAttendDate(), entity.getStatus());
    }

    @Override
    public boolean update(Attendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update Attendance set LaborID=?, SupervisorID=?, Date=?, Status=? where AttendanceID=?", entity.getLaborID(), entity.getSupervisorID(), entity.getAttendDate(), entity.getStatus(), entity.getAttendanceID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Attendance where AttendanceID=?",ID);
    }

    @Override
    public ArrayList<Attendance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Attendance");
        ArrayList<Attendance> attendanceArrayLIst = new ArrayList<>();
        while (rst.next()){
            attendanceArrayLIst.add(new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getString(5)
            ));
        }
        return attendanceArrayLIst;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT AttendanceID from Attendance order by AttendanceID desc limit 1");
        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("A%03d",newIdIndex);
        }
        return  "A001";
    }

    @Override
    public int getWorkingDays(String LaborID, int month, int year) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT count(*) as WorkingDays FROM Attendance WHERE LaborID = ? AND MONTH(Date) = ? AND YEAR(Date) = ? AND status = 'Present'", LaborID, month, year);
        if (rst.next()) {
            return rst.getInt("WorkingDays");
        }
        return 0;
    }
}
