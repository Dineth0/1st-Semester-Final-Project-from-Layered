package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ComplaintTM {
    private String ComplaintID;
    private String LaborID;
    private String Name;
    private String Description;
    private Date ComplaintDate;
    private String ManagerSeen;
}
