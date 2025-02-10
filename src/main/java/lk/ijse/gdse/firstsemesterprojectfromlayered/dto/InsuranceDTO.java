package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InsuranceDTO {
    private String PolicyNumber;
    private String AccidentID;
    private String Name;
    private String OfficerID;
    private double InsurancePayment;
}