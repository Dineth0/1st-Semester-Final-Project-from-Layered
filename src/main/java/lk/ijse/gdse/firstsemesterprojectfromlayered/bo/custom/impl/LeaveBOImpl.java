package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.LeaveBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.LeaveDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Leave;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.LeaveDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveBOImpl implements LeaveBO {

    LeaveDAO leaveDAO = (LeaveDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LEAVE);

    @Override
    public boolean save(LeaveDTO dto) throws SQLException, ClassNotFoundException {
        return leaveDAO.save(new Leave(dto.getLeaveID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getLeaveDate(),dto.getReason(),dto.getStatus()));
    }

    @Override
    public boolean update(LeaveDTO dto) throws SQLException, ClassNotFoundException {
        return leaveDAO.update(new Leave(dto.getLeaveID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getLeaveDate(),dto.getReason(),dto.getStatus()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return leaveDAO.delete(ID);
    }

    @Override
    public ArrayList<LeaveDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<LeaveDTO> leaveDTOs = new ArrayList<>();
        ArrayList<Leave> leaves = leaveDAO.getAll();
        for (Leave leave : leaves) {
            leaveDTOs.add(new LeaveDTO(leave.getLeaveID(),leave.getLaborID(),leave.getName(),leave.getOfficerID(),leave.getLeaveDate(),leave.getReason(),leave.getStatus()));
        }
        return leaveDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return leaveDAO.getNextID();
    }
}
