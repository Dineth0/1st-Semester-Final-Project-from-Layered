package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.ComplaintDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Complaint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplaintDAOImpl implements ComplaintDAO {
    @Override
    public boolean save(Complaint entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Complaint values(?,?,?,?,?,?)", entity.getComplaintID(), entity.getLaborID(), entity.getName(), entity.getDescription(), entity.getComplaintDate(), entity.getManager_Seen());
         return isSaved;
    }

    @Override
    public boolean update(Complaint entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Complaint set LaborID=?, Description =?, Date=?, Manager_Seen=? where ComplaintID=?", entity.getLaborID(), entity.getDescription(), entity.getComplaintDate(), entity.getManager_Seen(), entity.getComplaintID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Complaint where ComplaintID=?",ID);
    }

    @Override
    public ArrayList<Complaint> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Complaint");
        ArrayList<Complaint> complaintArrayList = new ArrayList<>();
        while (rst.next()){
            complaintArrayList.add( new Complaint(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6)));
        }
        return complaintArrayList;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT ComplaintID FROM Complaint ORDER BY ComplaintID DESC LIMIT 1");
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }
}
