package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor

@NoArgsConstructor
@Getter
@Setter
@ToString

public class Accident {
    private String AccidentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date AccidentDate;
    private String Description;
}

