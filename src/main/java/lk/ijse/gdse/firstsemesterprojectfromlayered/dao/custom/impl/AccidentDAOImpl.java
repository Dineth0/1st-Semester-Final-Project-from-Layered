package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.AccidentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentDAOImpl implements AccidentDAO {

    @Override
    public ArrayList<String> getAllAccidentIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT AccidentID from Accident");
        ArrayList<String> AccidentIDs = new ArrayList<>();
        while (rst.next()){
            AccidentIDs.add(rst.getString(1));
        }
        return AccidentIDs;
    }

    @Override
    public Accident findById(String selectedAccidentID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from Accident where AccidentID=?",selectedAccidentID);
        if (rst.next()){
            return new Accident(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public boolean save(Accident entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Accident values(?,?,?,?,?,?)", entity.getAccidentID(), entity.getLaborID(), entity.getName(), entity.getOfficerID(), entity.getAccidentDate(), entity.getDescription());
        return isSaved;
    }

    @Override
    public boolean update(Accident entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Accident set LaborID=?, OfficerID=?, Date=?, Description=? where AccidentID=?", entity.getLaborID(), entity.getOfficerID(), entity.getAccidentDate(), entity.getDescription(), entity.getAccidentID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
    return SQLUtil.execute("delete from Accident where AccidentID=?",ID);
    }

    @Override
    public ArrayList<Accident> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Accident");
        ArrayList<Accident> accidentArrayList = new ArrayList<>();
        while (rst.next()){
            accidentArrayList.add( new Accident(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6)
            ));
        }
        return accidentArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT AccidentID FROM Accident ORDER BY AccidentID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("A%03d", newIdIndex);
        }
        return "A001";
    }
}
