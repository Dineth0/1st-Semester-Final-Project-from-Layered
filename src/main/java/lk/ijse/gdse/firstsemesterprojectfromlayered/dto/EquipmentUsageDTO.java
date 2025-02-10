package lk.ijse.gdse.firstsemesterprojectfromlayered.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EquipmentUsageDTO {
    private String EquipmentID;
    private String EquipmentName;
    private String LaborID;
    private String LaborName;
    private Date UseDate;

}
