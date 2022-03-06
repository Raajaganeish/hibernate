package spring.sql.hibernate.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpTeamBuildObj implements Serializable {
    private Integer empId;
    private String f_name;
    private String l_name;
    private String email;
    private Integer tId;
    private String tName;
    private Integer bId;
    private String bName;
}
