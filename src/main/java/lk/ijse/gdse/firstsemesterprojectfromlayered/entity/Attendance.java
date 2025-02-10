package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attendance {
    private String AttendanceID;
    private String LaborID;
    private String SupervisorID;
    private Date AttendDate;
    private String Status;
}
