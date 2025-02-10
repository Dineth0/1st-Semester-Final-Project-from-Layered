package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.TrainingBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.TrainingDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Training;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.TrainingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TrainingBOImpl implements TrainingBO {

    TrainingDAO trainingDAO = (TrainingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TRAINING);

    @Override
    public boolean save(TrainingDTO dto) throws SQLException, ClassNotFoundException {
        return trainingDAO.save(new Training(dto.getTrainingID(),dto.getLaborID(),dto.getOfficerID(),dto.getDescription(),dto.getTrainingDate()));
    }

    @Override
    public boolean update(TrainingDTO dto) throws SQLException, ClassNotFoundException {
        return trainingDAO.update(new Training(dto.getTrainingID(),dto.getLaborID(),dto.getOfficerID(),dto.getDescription(),dto.getTrainingDate()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return trainingDAO.delete(ID);
    }

    @Override
    public ArrayList<TrainingDTO> getAll() throws SQLException, ClassNotFoundException {
         ArrayList<TrainingDTO> trainingDTOs = new ArrayList<>();
         ArrayList<Training> trainings = trainingDAO.getAll();
         for (Training training : trainings) {
             trainingDTOs.add(new TrainingDTO(training.getTrainingID(),training.getLaborID(),training.getOfficerID(),training.getDescription(),training.getTrainingDate()));
         }
         return trainingDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return trainingDAO.getNextID();
    }
}
