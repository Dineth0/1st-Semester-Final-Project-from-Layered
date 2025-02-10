package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Insurance {
    private String PolicyNumber;
    private String AccidentID;
    private String Name;
    private String OfficerID;
    private double InsurancePayment;
}