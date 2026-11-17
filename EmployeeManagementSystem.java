import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final List<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting employee management system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from 1 to 6.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Employee Management System ---");
        System.out.println("1. Add employee");
        System.out.println("2. Display all employees");
        System.out.println("3. Search employee by ID");
        System.out.println("4. Update employee");
        System.out.println("5. Delete employee");
        System.out.println("6. Exit");
    }

    private static void addEmployee() {
        int id = readInt("Enter employee ID: ");
        if (findEmployeeById(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readRequiredText("Enter employee name: ");
        String department = readRequiredText("Enter department: ");
        double salary = readNonNegativeDouble("Enter salary: ");

        employees.add(new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n--- Employee Details ---");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter employee ID to search: ");
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("\nEmployee found:");
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Enter the updated details:");
        employee.setName(readRequiredText("Enter employee name: "));
        employee.setDepartment(readRequiredText("Enter department: "));
        employee.setSalary(readNonNegativeDouble("Enter salary: "));
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employees.remove(employee);
        System.out.println("Employee deleted successfully.");
    }

    private static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0 && Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // Display the common validation message below.
            }
            System.out.println("Please enter a valid non-negative salary.");
        }
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static class Employee {
        private final int id;
        private String name;
        private String department;
        private double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        int getId() {
            return id;
        }

        void setName(String name) {
            this.name = name;
        }

        void setDepartment(String department) {
            this.department = department;
        }

        void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format(
                    "ID: %d | Name: %s | Department: %s | Salary: %.2f",
                    id, name, department, salary);
        }
    }
}
