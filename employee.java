import java.io.Serializable;
import java.util.*;

public class employee implements Serializable {
    int empno;
    String ename;
    String esurname;
    Date DOB;
    String role;
    String reportingto;
    int salary;
 
    employee(int empno, String ename, String esurname,Date DOB,String role,String reportingto, int salary) {
        this.empno = empno;
        this.ename = ename;
        this.esurname = esurname;
        this.DOB = DOB;
        this.role = role;
        this.reportingto = reportingto;
        this.salary = salary;
    }

    public String toString() {
        return empno + " " + ename+ " " +esurname+ " " + DOB + " " + role + " " +reportingto+ " " + salary;
    }

}