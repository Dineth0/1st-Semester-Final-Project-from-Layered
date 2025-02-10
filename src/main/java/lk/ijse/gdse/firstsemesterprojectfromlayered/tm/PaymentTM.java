package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentTM {
    private String PaymentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private double Day_Basic_Salary;
    private double Monthly_Total_Salary;
    private String Status;
}
