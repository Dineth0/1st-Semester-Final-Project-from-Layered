package lk.ijse.gdse.firstsemesterprojectfromlayered.dao;

import lk.ijse.gdse.firstsemesterprojectfromlayered.dao.custom.impl.*;
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes {
        ACCIDENT,EQUIPMENT,ATTENDANCE,COMPLAINT,EQUIPMENTUSAGE,FACTORYOFFICER,INSURANCE,LABOR,LEAVE,PAYMENT,RETIREMENT,SHIFT,SUPERVISOR,TRAINING,UNIONMEMBERSHIP
    }
    public SuperDAO getDAO(DAOTypes type) {
        switch (type) {
            case ACCIDENT:
                return new AccidentDAOImpl();
            case EQUIPMENT:
                return new EquipmentDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case COMPLAINT:
                return new ComplaintDAOImpl();
            case EQUIPMENTUSAGE:
                return (SuperDAO) new EquipmentUsageDAOImpl();
            case FACTORYOFFICER:
                return new FactoryOfficerDAOImpl();
            case INSURANCE:
                return new InsuranceDAOImpl();
            case LABOR:
                return new LaborDAOImpl();
            case LEAVE:
                return new LeaveDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case RETIREMENT:
                return new RetirementDAOImpl();
            case SHIFT:
                return new ShiftDAOImpl();
            case SUPERVISOR:
                return new SupervisorDAOImpl();
            case TRAINING:
                return new TrainingDAOImpl();
            case UNIONMEMBERSHIP:
                return new UnionMembershipDAOImpl();
            default:
                return null;
        }
    }
}
