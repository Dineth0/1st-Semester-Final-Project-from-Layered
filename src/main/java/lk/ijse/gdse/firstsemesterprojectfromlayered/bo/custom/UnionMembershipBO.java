package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.SuperBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.Union_MembershipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UnionMembershipBO extends SuperBO {

    public boolean save(Union_MembershipDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(Union_MembershipDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String ID) throws SQLException, ClassNotFoundException;

    public ArrayList<Union_MembershipDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getNextID() throws SQLException, ClassNotFoundException ;
}
