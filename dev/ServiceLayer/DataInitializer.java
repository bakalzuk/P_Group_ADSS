
package ServiceLayer;

import DomainLayer.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class DataInitializer {

    /**
     * Fills the system with initial data for testing and demonstration purposes.
     * Starts from the week of 26/04/2026.
     */
    public static void seedData(EmployeeService empService, ShiftService shiftService) {
        // Base date for the demonstration week
        LocalDate startWeek = LocalDate.of(2026, 4, 26);

        // 1. Create 7 employees with various roles and details
        Employee e1 = new Employee("Yossi Cohen", 101, new String[]{"Cashier", "Shift Manager"}, 12, 901, 55501, DayOfWeek.SATURDAY, LocalDate.now(), "Full", 10000, 50);
        Employee e2 = new Employee("Dana Levy", 102, new String[]{"Cashier"}, 12, 901, 55502, DayOfWeek.FRIDAY, LocalDate.now(), "Part", 0, 45);
        Employee e3 = new Employee("Ran Israeli", 103, new String[]{"storeKeeper"}, 12, 901, 55503, DayOfWeek.SUNDAY, LocalDate.now(), "Full", 8000, 42);
        Employee e4 = new Employee("Noa Amit", 104, new String[]{"Cashier", "storeKeeper"}, 12, 901, 55504, DayOfWeek.MONDAY, LocalDate.now(), "Full", 8500, 44);
        Employee e5 = new Employee("Ariel Mizrahi", 105, new String[]{"Shift Manager"}, 12, 901, 55505, DayOfWeek.TUESDAY, LocalDate.now(), "Full", 12000, 60);
        Employee e6 = new Employee("Maya Barak", 106, new String[]{"storeKeeper"}, 12, 901, 55506, DayOfWeek.WEDNESDAY, LocalDate.now(), "Part", 0, 40);
        Employee e7 = new Employee("Ido Golan", 107, new String[]{"Cashier"}, 12, 901, 55507, DayOfWeek.THURSDAY, LocalDate.now(), "Full", 9000, 48);

        // Add employees to the service
        empService.addEmployee(e1); empService.addEmployee(e2); empService.addEmployee(e3);
        empService.addEmployee(e4); empService.addEmployee(e5); empService.addEmployee(e6);
        empService.addEmployee(e7);

        // 2. Add Constraints for the starting week

        // Yossi (ID 101) - Morning availability, approves double shifts
        empService.insertConstraint(101, new Constraint(101, startWeek, LocalTime.of(8,0), LocalTime.of(16,0), true, 0, true));

        // Dana (ID 102) - Blocked in the morning, does NOT approve double shifts (hard constraint)
        empService.insertConstraint(102, new Constraint(102, startWeek, LocalTime.of(6,0), LocalTime.of(14,0), false, 0, false));

        // Ran (ID 103) - Approves double shifts and offers extra hours
        empService.insertConstraint(103, new Constraint(103, startWeek.plusDays(1), LocalTime.of(8,0), LocalTime.of(16,0), true, 2, true));

        // Ariel (ID 105) - Blocked specifically for evening shift on Monday
        empService.insertConstraint(105, new Constraint(105, startWeek.plusDays(1), LocalTime.of(14,0), LocalTime.of(22,0), false, 0, false));

        // 3. Initialize Shift Models and create empty shifts to be filled
        Map<String, Integer> standardModel = new HashMap<>();
        standardModel.put("Cashier", 2);
        standardModel.put("storeKeeper", 1);

        // Create Sunday morning and evening shifts
        shiftService.createShift(startWeek, 'm', e1, standardModel);
        shiftService.createShift(startWeek, 'e', e5, standardModel);

        // Create Monday morning shift
        shiftService.createShift(startWeek.plusDays(1), 'm', e1, standardModel);
    }
}