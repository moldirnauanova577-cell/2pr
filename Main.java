import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String email;
    private String role;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Геттер және сеттерлер
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return name + " - " + email + " - " + role;
    }
}

class UserManager {
    private List<User> users = new ArrayList<>();

    // Қолданушыны қосу
    public void addUser(User user) {
        users.add(user);
    }

    // Қолданушыны жою
    public boolean removeUser(String email) {
        User user = findUserByEmail(email);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

    // Қолданушыны жаңарту
    public boolean updateUser(String email, String newName, String newRole) {
        User user = findUserByEmail(email);
        if (user != null) {
            user.setName(newName);
            user.setRole(newRole);
            return true;
        }
        return false;
    }

    // DRY: email бойынша іздеуді бір жерге шығару
    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // Барлық қолданушыларды алу
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Қолданушылар қосу
        userManager.addUser(new User("Alice", "alice@example.com", "Admin"));
        userManager.addUser(new User("Bob", "bob@example.com", "User"));

        // Қолданушыны жаңарту
        userManager.updateUser("bob@example.com", "Bobby", "Admin");

        // Қолданушыны жою
        userManager.removeUser("alice@example.com");

        // Барлық қолданушыларды шығару
        for (User user : userManager.getAllUsers()) {
            System.out.println(user);
        }
    }
}