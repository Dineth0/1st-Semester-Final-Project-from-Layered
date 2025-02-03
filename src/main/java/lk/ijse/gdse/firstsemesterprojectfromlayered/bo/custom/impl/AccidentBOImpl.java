package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AccidentBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.AccidentDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Accident;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AccidentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccidentBOImpl implements AccidentBO {

    AccidentDAO accidentDAO = (AccidentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ACCIDENT);

    @Override
    public ArrayList<String> getAllAccidentIDs() throws SQLException, ClassNotFoundException {
        return accidentDAO.getAllAccidentIDs();
    }

    @Override
    public AccidentDTO findById(String selectedAccidentID) throws SQLException, ClassNotFoundException {
        Accident accident = accidentDAO.findById(selectedAccidentID);
        return new AccidentDTO(accident.getAccidentID(),accident.getLaborID(),accident.getName(),accident.getOfficerID(),accident.getAccidentDate(),accident.getDescription());
    }

    @Override
    public boolean save(AccidentDTO dto) throws SQLException, ClassNotFoundException {
        return accidentDAO.save(new Accident(dto.getAccidentID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getAccidentDate(),dto.getDescription()));
    }

    @Override
    public boolean update(AccidentDTO dto) throws SQLException, ClassNotFoundException {
        return accidentDAO.update(new Accident(dto.getAccidentID(),dto.getLaborID(),dto.getName(),dto.getOfficerID(),dto.getAccidentDate(),dto.getDescription()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return accidentDAO.delete(ID);
    }

    @Override
    public ArrayList<AccidentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AccidentDTO> accidentDTOs = new ArrayList<>();
        ArrayList<Accident> accidents = accidentDAO.getAll();
        for (Accident accident : accidents) {
            accidentDTOs.add(new AccidentDTO(accident.getAccidentID(),accident.getLaborID(),accident.getName(),accident.getOfficerID(),accident.getAccidentDate(),accident.getDescription()));
        }
        return accidentDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return accidentDAO.getNextID();
    }
}
