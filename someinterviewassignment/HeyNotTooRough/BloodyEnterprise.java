import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BloodyEnterprise implements IEmployee {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean addNewEmp(Employee emp) {
        if (employees.stream().anyMatch(e -> e.getName().equals(emp.getName()))) {
            return false;
        } else {
            employees.add(emp);
            return true;
        }
    }

    @Override
    public List<Employee> listEmpsBySurname(String surname) {
        // в списке к их ФИО добавить должность - lol
        List<Employee> raw = employees.stream().filter(e -> e.getSurname().equals(surname)).collect(Collectors.toList());
        Set<Employee> set = new HashSet<>();

        raw.forEach(e -> {
            if (!set.add(e)) {
                set.add(new Employee(e.getName(),
                        e.getSurname() + " (" + e.getPosition() + ")", e.getPosition()));
            }
        });

        return set.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Employee> listEmpsByPosition(String position) {
        return employees.stream().filter(e -> e.getPosition().equals(position))
                .sorted().limit(10).collect(Collectors.toList());
    }

    @Override
    public void deleteEmp(String id) {
        //Удалить по ид - lol id
        try {
            employees.remove(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new RuntimeException("HTTP 401:Bad request");
        }
    }
}
