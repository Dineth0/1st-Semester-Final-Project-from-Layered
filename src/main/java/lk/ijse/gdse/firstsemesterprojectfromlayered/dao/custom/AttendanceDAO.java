package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Attendance;

import java.sql.SQLException;

public interface AttendanceDAO extends CrudDAO<Attendance> {
    public int getWorkingDays(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;
}
