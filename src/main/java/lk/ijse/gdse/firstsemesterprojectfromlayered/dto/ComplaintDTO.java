package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ComplaintDTO {
    private String ComplaintID;
    private String LaborID;
    private String Name;
    private String Description;
    private Date ComplaintDate;
    private String Manager_Seen;
}