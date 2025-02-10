package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.CrudDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Retirement;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RetirementDAO extends CrudDAO<Retirement> {
    public int getTotalRetirements() throws SQLException, ClassNotFoundException;
}
