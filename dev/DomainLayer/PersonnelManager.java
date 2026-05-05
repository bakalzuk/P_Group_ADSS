package DomainLayer;

/**
 * Represents the system administrator with high-level privileges.
 * Handles authentication for the Personnel Management module.
 */

public class PersonnelManager {
    private String username;
    private String password; //

    public PersonnelManager() {
        this.username = "admin";
        this.password = "6789";
    }

    //Verifies if the provided password matches the manager's password.
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }


    public void setPassword(String oldPassword, String newPassword) {
        if (authenticate(oldPassword)) {
            this.password = newPassword;
        }
    }

    public String getUsername() {
        return username;
    }
}
