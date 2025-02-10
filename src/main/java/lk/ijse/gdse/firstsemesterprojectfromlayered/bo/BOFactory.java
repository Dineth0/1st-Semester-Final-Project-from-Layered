package lk.ijse.gdse.firstsemesterprojectfromlayered.bo;

import lk.ijse.gdse.firstsemesterprojectfromlayered.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }
    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        ACCIDENT,EQUIPMENT,ATTENDANCE,COMPLAINT,EQUIPMENTUSAGE,FACTORYOFFICER,INSURANCE,LABOR,LEAVE,PAYMENT,RETIREMENT,SHIFT,SUPERVISOR,TRAINING,UNIONMEMBERSHIP
    }
    public SuperBO getBO(BOTypes types) {

        switch (types) {
            case ACCIDENT:
                return (SuperBO) new AccidentBOImpl();
            case EQUIPMENT:
                return (SuperBO) new EquipmentBOImpl();
            case ATTENDANCE:
                return  new AttendanceBOImpl();
            case COMPLAINT:
                return (SuperBO) new ComplaintBOImpl();
            case EQUIPMENTUSAGE:
                return (SuperBO) new EquipmentUsageBOImpl();
            case FACTORYOFFICER:
                return (SuperBO) new FactoryOfficerBOImpl();
            case INSURANCE:
                return (SuperBO) new InsuranceBOImpl();
            case LABOR:
                return  new LaborBOImpl();
            case LEAVE:
                return (SuperBO) new LeaveBOImpl();
            case PAYMENT:
                return (SuperBO) new PaymentBOImpl();
            case RETIREMENT:
                return (SuperBO) new RetirementBOImpl();
            case SHIFT:
                return (SuperBO) new ShiftBOImpl();
            case SUPERVISOR:
                return (SuperBO) new SupervisorBOImpl();
            case TRAINING:
                return (SuperBO) new TrainingBOImpl();
            case UNIONMEMBERSHIP:
                return (SuperBO) new UnionMembershipBOImpl();
            default:
                return null;
        }
    }

}
