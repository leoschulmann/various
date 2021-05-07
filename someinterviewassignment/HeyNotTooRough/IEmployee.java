import java.util.List;

interface IEmployee {

    boolean addNewEmp(Employee emp);

    List<Employee> listEmpsBySurname(String surname);

    List<Employee> listEmpsByPosition(String position);

    void deleteEmp(String id);
}
