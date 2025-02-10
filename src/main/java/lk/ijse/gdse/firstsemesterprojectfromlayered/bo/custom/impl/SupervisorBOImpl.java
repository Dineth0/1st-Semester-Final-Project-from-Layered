package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.SupervisorBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.SupervisorDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Supervisor;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.SupervisorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupervisorBOImpl implements SupervisorBO {

    SupervisorDAO supervisorDAO = (SupervisorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPERVISOR);

    @Override
    public ArrayList<String> getAllOfficerIds() throws SQLException {
        return supervisorDAO.getAllOfficerIds();
    }

    @Override
    public SupervisorDTO findById(String selectedSupervisorID) throws SQLException {
        Supervisor supervisor = supervisorDAO.findById(selectedSupervisorID);
        return new SupervisorDTO(supervisor.getSupervisorID(),supervisor.getName());
    }

    @Override
    public boolean save(SupervisorDTO dto) throws SQLException, ClassNotFoundException {
        return supervisorDAO.save(new Supervisor(dto.getSupervisorID(),dto.getName()));
    }

    @Override
    public boolean update(SupervisorDTO dto) throws SQLException, ClassNotFoundException {
        return supervisorDAO.update(new Supervisor(dto.getSupervisorID(),dto.getName()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return supervisorDAO.delete(ID);
    }

    @Override
    public ArrayList<SupervisorDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupervisorDTO>supervisorDTOs  = new ArrayList<>();
        ArrayList<Supervisor> Supervisors = supervisorDAO.getAll();
        for (Supervisor supervisor : Supervisors) {
            supervisorDTOs.add(new SupervisorDTO(supervisor.getSupervisorID(), supervisor.getName()));
        }
        return supervisorDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return supervisorDAO.getNextID();
    }
}
