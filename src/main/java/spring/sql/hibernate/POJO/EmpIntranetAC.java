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
public class EmpIntranetAC implements Serializable {
    private int id;
    private String f_name;
    private String l_name;
    private Date date;
    private String email;
    private String password;
}
