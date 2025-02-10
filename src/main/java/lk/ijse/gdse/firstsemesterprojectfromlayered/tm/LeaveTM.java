package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class LeaveTM {
    private String LeaveID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date LeaveDate;
    private String Reason;
    private String Status;
}