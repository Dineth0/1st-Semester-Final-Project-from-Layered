package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Leave {
    private String LeaveID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date LeaveDate;
    private String Reason;
    private String Status;
}