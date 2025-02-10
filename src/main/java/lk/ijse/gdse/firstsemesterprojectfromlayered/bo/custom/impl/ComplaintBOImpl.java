package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.ComplaintBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.ComplaintDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Complaint;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ComplaintDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ComplaintBOImpl implements ComplaintBO {

    ComplaintDAO complaintDAO = (ComplaintDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COMPLAINT);

    @Override
    public boolean save(ComplaintDTO dto) throws SQLException, ClassNotFoundException {
        return complaintDAO.save(new Complaint(dto.getComplaintID(),dto.getLaborID(),dto.getName(),dto.getDescription(),dto.getComplaintDate(),dto.getManager_Seen()));
    }

    @Override
    public boolean update(ComplaintDTO dto) throws SQLException, ClassNotFoundException {
        return complaintDAO.update(new Complaint(dto.getComplaintID(),dto.getLaborID(),dto.getName(),dto.getDescription(),dto.getComplaintDate(),dto.getManager_Seen()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return complaintDAO.delete(ID);
    }

    @Override
    public ArrayList<ComplaintDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ComplaintDTO> complaintDTOs = new ArrayList<>();
        ArrayList<Complaint> complaints = complaintDAO.getAll();
        for (Complaint complaint : complaints) {
            complaintDTOs.add(new ComplaintDTO(complaint.getComplaintID(),complaint.getLaborID(),complaint.getName(),complaint.getDescription(),complaint.getComplaintDate(),complaint.getManager_Seen()));
        }
        return complaintDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return complaintDAO.getNextID();
    }
}
