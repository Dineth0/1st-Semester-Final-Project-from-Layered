package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.ShiftBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.ShiftDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Shift;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.ShiftDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftBOImpl implements ShiftBO {

    ShiftDAO shiftDAO = (ShiftDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SHIFT);

    @Override
    public boolean save(ShiftDTO dto) throws SQLException, ClassNotFoundException {
        return shiftDAO.save(new Shift(dto.getShiftID(),dto.getLaborID(),dto.getSupervisorID(),dto.getShiftDate(),dto.getStartTime(),dto.getEndTime(),dto.getOverTime()));
    }

    @Override
    public boolean update(ShiftDTO dto) throws SQLException, ClassNotFoundException {
        return shiftDAO.update(new Shift(dto.getShiftID(),dto.getLaborID(),dto.getSupervisorID(),dto.getShiftDate(),dto.getStartTime(),dto.getEndTime(),dto.getOverTime()));
    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return shiftDAO.delete(ID);
    }

    @Override
    public ArrayList<ShiftDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ShiftDTO> shiftDTOs = new ArrayList<>();
        ArrayList<Shift> shifts = shiftDAO.getAll();
        for (Shift shift : shifts) {
            shiftDTOs.add(new ShiftDTO(shift.getShiftID(),shift.getLaborID(),shift.getSupervisorID(),shift.getShiftDate(),shift.getStartTime(),shift.getEndTime(),shift.getOverTime()));
        }
        return shiftDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return shiftDAO.getNextID();
    }

    @Override
    public int getTotalOvertTime(String LaborID, int month, int year) throws SQLException, ClassNotFoundException {
        return shiftDAO.getTotalOvertTime(LaborID, month, year);
    }
}
