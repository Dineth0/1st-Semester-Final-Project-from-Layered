package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Payment {
    private String PaymentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private double Day_Basic_Salary;
    private double Monthly_Total_Salary;
    private String Status;


    public Payment(String paymentID, String laborID, String officerID, double dayBasicSalary, double monthlyTotalSalary, String status) {
    }
}