package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EquipmentUsage {
    private String EquipmentID;
    private String EquipmentName;
    private String LaborID;
    private String LaborName;
    private Date UseDate;

}