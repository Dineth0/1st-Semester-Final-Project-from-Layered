package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceTM {
    private String AttendanceID;
    private String LaborID;
    private String SupervisorID;
    private Date AttendDate;
    private String Status;
}
