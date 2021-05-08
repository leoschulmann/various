import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BloodyEnterpriseTest {

    @Test
    void sanityTest() {
        BloodyEnterprise be = new BloodyEnterprise();
        assertNotNull(be.getEmployees());
    }

    @Test
    void testAddNormal() {
        BloodyEnterprise be = new BloodyEnterprise();
        assertTrue(be.addNewEmp(new Employee("Bob", "Bobson", "SEO")));
        assertEquals(1, be.getEmployees().size());
    }

    @Test
    void testAddErroneous() {
        BloodyEnterprise be = new BloodyEnterprise();
        Employee e1 = new Employee("Bob", "Bobson", "SEO");
        Employee e2 = new Employee("Bob", "Bobson", "Janitor");
        assertTrue(be.addNewEmp(e1));
        assertFalse(be.addNewEmp(e2));
        assertEquals(1, be.getEmployees().size());
    }

    @Test
    void testReturnTenEntries() {
        BloodyEnterprise be = new BloodyEnterprise();
        String[] surnames = {"Bobson", "Billson", "Jimson"};
        for (int i = 0; i < 100; i++) {
            be.addNewEmp(new Employee(UUID.randomUUID().toString(), surnames[new Random().nextInt(surnames.length)],
                    UUID.randomUUID().toString()));
        }
        assertEquals(10, be.listEmpsBySurname("Jimson").size());
    }

    @Test
    void testReturnActualQtyIfLessThanTen() {
        BloodyEnterprise be = new BloodyEnterprise();
        String[] surnames = {"Bobson", "Billson", "Jimson"};
        for (int i = 0; i < 10; i++) {
            be.addNewEmp(new Employee(UUID.randomUUID().toString(), surnames[new Random().nextInt(surnames.length)],
                    UUID.randomUUID().toString()));
        }
        assertTrue(be.listEmpsBySurname("Jimson").size() < 10);
    }

    @Test
    void testEqualNamesAndSurnames() {
        BloodyEnterprise be = new BloodyEnterprise();
        Employee e1 = new Employee("Bob", "Bobson", "SEO");
        Employee e2 = new Employee("Bob", "Bobson", "Janitor");
        Employee e3 = new Employee("Bob", "Bobson", "STO");
        be.getEmployees().add(e1);
        be.getEmployees().add(e2);
        be.getEmployees().add(e3);

        List<Employee> list = be.listEmpsBySurname("Bobson");
        assertEquals("Bobson (Janitor)", list.get(1).getSurname());
    }

    @Test
    void getByPostionSorted() {
        BloodyEnterprise be = new BloodyEnterprise();
        Employee e1 = new Employee("Jack", "Jackson", "Janitor");
        Employee e2 = new Employee("Zack", "Zackson", "Janitor");
        Employee e3 = new Employee("Nick", "Nickson", "Janitor");
        Employee e4 = new Employee("Andrew", "Andrewson", "Janitor");
        Employee e5 = new Employee("Don", "Donson", "Janitor");
        Employee e6 = new Employee("Jim", "Jimson", "Janitor");

        be.getEmployees().addAll(List.of(e1, e2, e3, e4, e5, e6));

        List<Employee> janitors = be.listEmpsByPosition("Janitor");
        assertEquals(6, janitors.size());
        assertEquals("Andrew", janitors.get(0).getName());
        assertEquals("Zack", janitors.get(5).getName());
    }

    @Test
    void deleteSuccessfully() {
        BloodyEnterprise be = new BloodyEnterprise();
        for (int i = 0; i < 100; i++) {
            be.addNewEmp(new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()));
        }
        assertEquals(100, be.getEmployees().size());

        be.deleteEmp("42");

        assertEquals(99, be.getEmployees().size());
    }

    @Test
    void deleteErroneous() {
        try {
            BloodyEnterprise be = new BloodyEnterprise();
            for (int i = 0; i < 100; i++) {
                be.addNewEmp(new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()));
            }
            assertEquals(100, be.getEmployees().size());

            be.deleteEmp("pizza");
        } catch (Exception e) {
            assertEquals("HTTP 401:Bad request", e.getMessage());
        }
    }
}