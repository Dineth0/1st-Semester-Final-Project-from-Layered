package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InsuranceTM {
    private String PolicyNumber;
    private String AccidentID;
    private String Name;
    private String OfficerID;
    private double InsurancePayment;
}