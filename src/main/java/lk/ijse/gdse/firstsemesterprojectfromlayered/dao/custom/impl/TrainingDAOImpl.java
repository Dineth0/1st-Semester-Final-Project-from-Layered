package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.TrainingDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Training;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainingDAOImpl implements TrainingDAO {
    @Override
    public boolean save(Training entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Training values(?,?,?,?,?)", entity.getTrainingID(), entity.getLaborID(), entity.getOfficerID(), entity.getDescription(), entity.getTrainingDate());
            return isSaved;
    }

    @Override
    public boolean update(Training entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Training set LaborID=?, OfficerID=?, Description=?, Date=? where TrainingID=?", entity.getLaborID(), entity.getOfficerID(), entity.getDescription(), entity.getTrainingDate(), entity.getTrainingID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Training where TrainingID=?",ID);
    }

    @Override
    public ArrayList<Training> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Training");
        ArrayList<Training> trainingArrayList = new ArrayList<>();
        while (rst.next()){
            trainingArrayList.add( new Training(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5)));
        }
        return trainingArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT TrainingID from Training order by TrainingID desc limit 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("T%03d", newIdIndex);
        }
        return "T001";
    }
}
