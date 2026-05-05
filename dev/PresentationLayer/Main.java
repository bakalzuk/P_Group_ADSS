package PresentationLayer;

import ServiceLayer.EmployeeService;
import ServiceLayer.ShiftService;
import ServiceLayer.DataInitializer;

public class Main {
    public static void main(String[] args) {
        // Step 1: Initialize Services
        EmployeeService empService = new EmployeeService();
        ShiftService shiftService = new ShiftService(empService);

        // Initializing with data
        DataInitializer.seedData(empService, shiftService);

        // Step 2: Initialize UI with the new name
        UserInterface ui = new UserInterface(empService, shiftService);

        // Step 3: Run the application
        ui.start();
    }
}