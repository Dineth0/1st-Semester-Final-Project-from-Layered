package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentDTO {
    private String PaymentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private double Day_Basic_Salary;
    private double Monthly_Total_Salary;
    private String Status;



    public PaymentDTO(String paymentID, String laborID, String name, String officerID, double dayBasicSalary, String status) {
    }
}
