package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.SupervisorDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Supervisor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupervisorDAOImpl implements SupervisorDAO {
    @Override
    public ArrayList<String> getAllOfficerIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT SupervisorID from Supervisor");
        ArrayList<String> supervisorIDs = new ArrayList<>();
        while (rst.next()){
            supervisorIDs.add(rst.getString(1));
        }
        return supervisorIDs;
    }

    @Override
    public Supervisor findById(String ID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supervisor WHERE SupervisorID=?",ID);
        rst.next();
        return new Supervisor(
                rst.getString(1),
                rst.getString(2));
    }

    @Override
    public boolean save(Supervisor entity) throws SQLException, ClassCastException {
        SQLUtil.execute("INSERT INTO Supervisor (SupervisorID,Name) VALUES (?,?)", entity.getSupervisorID(), entity.getName());
            return false;
    }

    @Override
    public boolean update(Supervisor entity) throws SQLException, ClassCastException {
      return   SQLUtil.execute("UPDATE Supervisor SET Name=? WHERE SupervisorID=?",entity.getName(),entity.getSupervisorID());
    }

    @Override
    public boolean  delete(String ID) throws SQLException, ClassCastException {
       return SQLUtil.execute("DELETE FROM Supervisor WHERE SupervisorID=?",ID);
    }

    @Override
    public ArrayList<Supervisor> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM Supervisor");
        ArrayList<Supervisor> supervisorArrayList = new ArrayList<>();
        while (rst.next()){
            supervisorArrayList.add(new Supervisor(
                    rst.getString("SupervisorID"),rst.getString("Name")));
        }
        return supervisorArrayList;
    }


    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SupervisorID FROM Supervisor ORDER BY SupervisorID DESC LIMIT 1;");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }
}
