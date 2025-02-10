package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TrainingDTO {
    private String TrainingID;
    private String LaborID;
    private String OfficerID;
    private String Description;
    private Date TrainingDate;
}