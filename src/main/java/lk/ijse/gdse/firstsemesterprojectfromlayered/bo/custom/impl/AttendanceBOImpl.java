package lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.AttendanceBO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.DAOFactory;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.AttendanceDAO;
import lk.ijse.gdse.firstsemesterprojectfromlayered.entity.Attendance;
import lk.ijse.gdse.firstsemesterprojectfromlayered.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ATTENDANCE);

    @Override
    public boolean save(AttendanceDTO dto) throws SQLException, ClassNotFoundException {
       return attendanceDAO.save(new Attendance(dto.getAttendanceID(),dto.getLaborID(),dto.getSupervisorID(),dto.getAttendDate(),dto.getStatus()));
    }

    @Override
    public boolean update(AttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.update(new Attendance(dto.getAttendanceID(),dto.getLaborID(),dto.getSupervisorID(),dto.getAttendDate(),dto.getStatus()));

    }

    @Override
    public boolean delete(String ID) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(ID);
    }

    @Override
    public ArrayList<AttendanceDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> attendanceDTOs = new ArrayList<>();
        ArrayList<Attendance> attendances = attendanceDAO.getAll();
        for (Attendance attendance : attendances) {
            attendanceDTOs.add(new AttendanceDTO(attendance.getAttendanceID(),attendance.getLaborID(),attendance.getSupervisorID(),attendance.getAttendDate(),attendance.getStatus()));
        }
        return attendanceDTOs;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return attendanceDAO.getNextID();
    }

    @Override
    public int getWorkingDays(String LaborID, int month, int year) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getWorkingDays(LaborID, month, year);
    }
}
