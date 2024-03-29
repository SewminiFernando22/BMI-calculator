import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

class User {
    private int id;
    private String name;
    private int yearOfBirth;
    private double height; // in meters
    private double weight; // in kilograms

    public User(int id, String name, int yearOfBirth, double height, double weight) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Year of Birth: " + yearOfBirth);
        System.out.println("Height: " + height + " meters");
        System.out.println("Weight: " + weight + " kilograms");
    }

    public double calculateBMI() {
        return weight / Math.pow(height, 2);
    }

    public int calculateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - yearOfBirth;
    }
}

public class BmiCalculator {
    private static ArrayList<User> userList = new ArrayList<>();
    private static int nextUserId = 1;
    private static final int MAX_USERS = 5;

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n------ BMI Application Menu ------");
            System.out.println("1. Create a record");
            System.out.println("2. Show BMI data for all users");
            System.out.println("3. Show BMI data for a selected user");
            System.out.println("4. Delete all records");
            System.out.println("5. Exit application");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (userList.size() < MAX_USERS) {
                        createRecord();
                    } else {
                        System.out.println("Maximum number of users reached.");
                    }
                    break;
                case 2:
                    showAllBmiData();
                    break;
                case 3:
                    showBmiDataForUser();
                    break;
                case 4:
                    deleteAllRecords();
                    break;
                case 5:
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void createRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------ Create User ------");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter year of birth: ");
        int yearOfBirth = scanner.nextInt();
        System.out.print("Enter height in centimeters: ");
        double heightCM = scanner.nextDouble();
        double height = heightCM / 100; // convert height to meters
        System.out.print("Enter weight in kilograms: ");
        double weight = scanner.nextDouble();
        User newUser = new User(nextUserId, name, yearOfBirth, height, weight);
        userList.add(newUser);
        System.out.println("User created successfully!");
        nextUserId++;
    }

    private static void showAllBmiData() {
        System.out.println("\n------ All Records ------");
        if (userList.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (User user : userList) {
                user.display();
                displayBMIInfo(user);
                System.out.println("Age: " + user.calculateAge());
                System.out.println("-------------------------------");
            }
        }
    }

    private static void showBmiDataForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID to view: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user != null) {
            user.display();
            displayBMIInfo(user);
            System.out.println("Age: " + user.calculateAge());
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    private static void displayBMIInfo(User user) {
        double bmi = user.calculateBMI();
        System.out.printf("BMI: %.2f\n", bmi);
        if (bmi < 16) {
            System.out.println("BMI Category: Severe undernourishment");
        } else if (bmi <= 16.9) {
            System.out.println("BMI Category: Medium undernourishment");
        } else if (bmi <= 18.4) {
            System.out.println("BMI Category: Slight undernourishment");
        } else if (bmi <= 24.9) {
            System.out.println("BMI Category: Normal nutrition state");
        } else if (bmi <= 29.9) {
            System.out.println("BMI Category: Overweight");
        } else if (bmi <= 39.9) {
            System.out.println("BMI Category: Obesity");
        } else {
            System.out.println("BMI Category: Pathological obesity");
        }
    }

    private static void deleteAllRecords() {
        userList.clear();
        System.out.println("All records deleted successfully.");
    }

    private static void exitApplication() {
        System.out.println("Exiting the application. Goodbye!");
    }

    private static User findUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

