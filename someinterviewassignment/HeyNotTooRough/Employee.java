public class Employee implements Comparable<Employee>{
    private final String name;
    private final String surname;
    private final String position;

    public Employee(String name, String surname, String position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!name.equals(employee.name)) return false;
        return surname.equals(employee.surname);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public int compareTo(Employee o) {
        int compareSurnames = this.getSurname().compareTo(o.getSurname());
        return compareSurnames != 0 ? compareSurnames : this.getName().compareTo(o.getName());
    }
}
