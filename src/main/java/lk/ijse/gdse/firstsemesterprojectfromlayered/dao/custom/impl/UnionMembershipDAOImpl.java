package lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.SQLUtil;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.UnionMembershipDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Union_Membership;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnionMembershipDAOImpl implements UnionMembershipDAO {
    @Override
    public boolean save(Union_Membership entity) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtil.execute("insert into Union_Membership values (?,?,?,?)", entity.getMembershipID(), entity.getLaborID(), entity.getName(), entity.getStatus());
            return isSaved;
    }

    @Override
    public boolean update(Union_Membership entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("update Union_Membership set  LaborID=?, Name=?, Status=? where MembershipID=?", entity.getLaborID(), entity.getName(), entity.getStatus(), entity.getMembershipID());
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("delete from Union_Membership where MembershipID=?",ID);
    }

    @Override
    public ArrayList<Union_Membership> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT * from Union_Membership");
        ArrayList<Union_Membership> union_membershipArrayLIst = new ArrayList<>();
        while (rst.next()){
            union_membershipArrayLIst.add( new Union_Membership(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return union_membershipArrayLIst;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT MembershipID from Union_Membership order by MembershipID desc limit 1");
        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("M%03d",newIdIndex);
        }
        return  "M001";
    }
}
