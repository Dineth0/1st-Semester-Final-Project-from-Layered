package lk.ijse.gdse.firstsemesterprojectfromlayered.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Labor implements Serializable {
    private String LaborID;
    private String Name;
    private int Age;
    private String Address;
    private String ContactNumber;


}
