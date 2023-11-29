import java.util.Scanner;

class Student {
    String name;
    int rollNumber;
    float marks;
}

class User {
    String username;
    String password;
}

public class StudentRecordsSystem {
    private static final int MAX_STUDENTS = 50;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MAX_PASSWORD_LENGTH = 20;

    public static void registerUser(User[] users, int[] userCount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        users[userCount[0]].username = scanner.next();

        System.out.print("Enter password: ");
        users[userCount[0]].password = scanner.next();

        System.out.println("Registration Successfully");

        userCount[0]++;
    }

    public static int loginUser(User[] users, int userCount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter password: ");
        String password = scanner.next();

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].password.equals(password)) {
                System.out.println("Log in Successfully");
                return 1; // Successful login
            }
        }

        System.out.println("Login failed");
        return 0; // Login failed
    }

    public static void addStudent(Student[] students, int[] studentCount) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add Student Information ");
        System.out.print("Enter student name: ");
        students[studentCount[0]].name = scanner.next();

        System.out.print("Enter roll number: ");
        students[studentCount[0]].rollNumber = scanner.nextInt();

        System.out.print("Enter marks: ");
        students[studentCount[0]].marks = scanner.nextFloat();

        System.out.println("Add information Successfully");

        studentCount[0]++;
    }

    public static void displayStudents(Student[] students, int studentCount) {
        System.out.println("\nStudent Records:");
        System.out.println("Name\tRoll Number\tMarks");

        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%s\t%d\t\t%.2f\n", students[i].name, students[i].rollNumber, students[i].marks);
        }
    }

    public static void searchStudent(Student[] students, int studentCount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter roll number to search: ");
        int rollNumber = scanner.nextInt();

        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollNumber == rollNumber) {
                System.out.println("Student Found:");
                System.out.println("Name: " + students[i].name);
                System.out.println("Roll Number: " + students[i].rollNumber);
                System.out.println("Marks: " + students[i].marks);
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public static void deleteStudent(Student[] students, int[] studentCount) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter roll number to delete: ");
        int rollNumber = scanner.nextInt();

        for (int i = 0; i < studentCount[0]; i++) {
            if (students[i].rollNumber == rollNumber) {
                // Delete by shifting elements
                for (int j = i; j < studentCount[0] - 1; j++) {
                    students[j] = students[j + 1];
                }
                studentCount[0]--;
                System.out.println("Student with roll number " + rollNumber + " deleted.");
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

public static void main(String[] args) {
    Student[] students = new Student[MAX_STUDENTS];
    for (int i = 0; i < MAX_STUDENTS; i++) {
        students[i] = new Student();
    }
    User[] users = new User[MAX_STUDENTS];
    for (int i = 0; i < MAX_STUDENTS; i++) {
        users[i] = new User();
    }
    int[] studentCount = {0};
    int[] userCount = {0};
    int loggedIn = 0; // Flag for user login status

    int choice;

    Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nStudent Records System");

            if (loggedIn == 0) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        registerUser(users, userCount);
                        break;

                    case 2:
                        loggedIn = loginUser(users, userCount[0]);
                        if (loggedIn == 0) {
                            System.out.println("Login failed. Invalid username or password.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting program.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Add Student info");
                System.out.println("2. Display Students info");
                System.out.println("3. Search student info");
                System.out.println("4. Delete student info");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(students, studentCount);
                        break;

                    case 2:
                        displayStudents(students, studentCount[0]);
                        break;

                    case 3:
                        searchStudent(students, studentCount[0]);
                        break;

                    case 4:
                        deleteStudent(students, studentCount);
                        break;

                    case 5:
                        loggedIn = 0; // Logout
                        System.out.println("Logged out successfully.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } while (choice != 6);

        scanner.close();
    }
}