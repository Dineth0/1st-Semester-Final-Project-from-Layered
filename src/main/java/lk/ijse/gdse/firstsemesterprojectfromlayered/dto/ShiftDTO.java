package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class
ShiftDTO {
    private String shiftID;
    private String LaborID;
    private String SupervisorID;
    private Date ShiftDate;
    private String StartTime;
    private String EndTime;
    private String OverTime;


}
