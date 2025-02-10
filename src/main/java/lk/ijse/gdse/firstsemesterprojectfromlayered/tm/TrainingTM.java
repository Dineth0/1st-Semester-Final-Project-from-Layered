package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TrainingTM {
    private String TrainingID;
    private String LaborID;
    private String OfficerID;
    private String Description;
    private Date TrainingDate;
}