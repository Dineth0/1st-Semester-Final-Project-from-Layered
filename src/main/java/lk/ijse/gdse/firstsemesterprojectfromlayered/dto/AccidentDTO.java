package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccidentDTO {
    private String AccidentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date AccidentDate;
    private String Description;
}
