package DomainLayer;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;


/**
 * Represents a single work shift in a specific branch.
 * Manages the assignment of employees to roles for a specific date and time.
 */
public class Shift {
    LocalDate date;
    private char type; // "m" (morning) or "e" (evening)
    private Employee shift_manager;
    private Map<String,Integer> shift_model; //Stores the required staffing levels for the shift.
    private Map<Employee,String> shift_roles; //Stores the actual assignments made for this shift.
    private Map<Employee, Integer> extra_hours_assignments = new HashMap<>(); // map to store extra hours specifically



    //constructor
    public Shift (LocalDate date, char type, Employee shift_manager, Map<String,Integer> shift_model){
        this.date=date;
        this.type=type;
        this.shift_manager = shift_manager;
        this.shift_roles = new HashMap<>();
        this.shift_model = new HashMap<>();

        this.shift_model.put("Cashir",2); //default value
        this.shift_model.put("storeKeeper",2); //default value
    }

    /**
     * Generates a string representation of the shift, including the manager
     * and a list of all current employee assignments.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Shift Date: %s | Type: %s\n", date, (type == 'm' ? "Morning" : "Evening")));
        sb.append(String.format("Manager: %s\n", (shift_manager != null ? shift_manager.getName() : "None")));
        sb.append("Assignments: ");
        if (shift_roles.isEmpty()) {
            sb.append("No assignments yet.");
        } else {
            shift_roles.forEach((emp, role) -> {
                sb.append(String.format("[%s: %s] ", role, emp.getName()));
            });
        }
        return sb.toString();
    }

    public void setShift_model(String role,int amount){
        this.shift_model.put(role,amount);

    }
    public Employee getShift_manager(){
        return this.shift_manager;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public char getType(){
        return this.type;
    }

    public void setShift_roles(Employee e,String role){
        this.shift_roles.put(e,role);
    }
    public Map<Employee,String> getShift_roles(){
        return this.shift_roles;
    }
    public Map<String,Integer> getShift_model(){
        return this.shift_model;
    }

    public void addExtraHoursAssignment(Employee e, int hours) {
        // CHANGE: Method to record extra hours assignment
        this.extra_hours_assignments.put(e, hours);
    }

    public Map<Employee, Integer> getExtraHoursAssignments() {
        // CHANGE: Getter for displaying extra hours later
        return this.extra_hours_assignments;
    }

    /**
     * Validation method to check if an employee is already scheduled for this shift.
     * Prevents double-booking an employee in multiple roles within the same shift.
     * * @param employeeId The ID of the employee to check.
     * @return true if the employee is already assigned, false otherwise.
     */
    public boolean isEmployeeAssigned(int employeeId) {
        for (Object obj : shift_roles.keySet()){
            if (obj instanceof Employee) {
                Employee e = (Employee) obj;
                if (e.getId() == employeeId) {
                    return true;
                }
            }
        }
        return false;
    }
}
