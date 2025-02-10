package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Shift;

import java.sql.SQLException;

public interface ShiftDAO extends CrudDAO<Shift> {
    public int getTotalOvertTime(String LaborID, int month, int year) throws SQLException, ClassNotFoundException;
}
