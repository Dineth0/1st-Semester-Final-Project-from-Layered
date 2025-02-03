package lk.ijse.gdse.firstsemesterprojectfromlayered.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccidentTM {
    private String AccidentID;
    private String LaborID;
    private String Name;
    private String OfficerID;
    private Date AccidentDate;
    private String Description;
}
