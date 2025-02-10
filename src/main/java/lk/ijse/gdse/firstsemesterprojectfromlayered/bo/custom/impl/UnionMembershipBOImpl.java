package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.UnionMembershipBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.UnionMembershipDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Union_Membership;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.Union_MembershipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UnionMembershipBOImpl implements UnionMembershipBO {

    UnionMembershipDAO unionMembershipDAO = (UnionMembershipDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.UNIONMEMBERSHIP);

    @Override
    public boolean save(Union_MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return unionMembershipDAO.save(new Union_Membership(dto.getMembershipID(),dto.getLaborID(),dto.getName(),dto.getStatus()));
    }

    @Override
    public boolean update(Union_MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return unionMembershipDAO.update(new Union_Membership(dto.getMembershipID(),dto.getLaborID(),dto.getName(),dto.getStatus()));

    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return unionMembershipDAO.delete(ID);
    }

    @Override
    public ArrayList<Union_MembershipDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Union_MembershipDTO> unionMembershipDTOS = new ArrayList<>();
        ArrayList<Union_Membership> unionMemberships = unionMembershipDAO.getAll();
        for (Union_Membership unionMembership : unionMemberships) {
            unionMembershipDTOS.add(new Union_MembershipDTO(unionMembership.getMembershipID(),unionMembership.getLaborID(),unionMembership.getName(),unionMembership.getStatus()));

        }
        return unionMembershipDTOS;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return unionMembershipDAO.getNextID();
    }
}
