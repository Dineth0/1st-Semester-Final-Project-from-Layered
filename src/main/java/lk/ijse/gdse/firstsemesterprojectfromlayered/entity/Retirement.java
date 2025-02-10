package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Retirement {
    private String RetirementID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date RetirementDate;
    private int TotalYearsWorked;
    private double FundPayment;



}
