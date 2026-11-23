import java.io.*;
import java.util.Scanner;

public class StudentRecordManagement {

    static Scanner sc = new Scanner(System.in);

    static String fileName = "students.txt";

    // Method to add student
    public static void addStudent() {

        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(fileName, true));

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Student Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Student Marks: ");
            double marks = sc.nextDouble();

            String record = id + "," + name + "," +
                    age + "," + course + "," + marks;

            bw.write(record);
            bw.newLine();

            bw.close();

            System.out.println("Student Added Successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view all students
    public static void viewStudents() {

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));

            String line;

            System.out.println("\n----- STUDENT RECORDS -----");

            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");

                System.out.println("ID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Age: " + data[2]);
                System.out.println("Course: " + data[3]);
                System.out.println("Marks: " + data[4]);

                System.out.println("---------------------------");
            }

            br.close();

        } catch (IOException e) {
            System.out.println("No student records found.");
        }
    }

    // Method to search student
    public static void searchStudent() {

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));

            System.out.print("Enter Student ID to Search: ");
            int searchID = sc.nextInt();

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == searchID) {

                    System.out.println("\nStudent Found!");
                    System.out.println("ID: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Age: " + data[2]);
                    System.out.println("Course: " + data[3]);
                    System.out.println("Marks: " + data[4]);

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student Not Found!");
            }

            br.close();

        } catch (IOException e) {
            System.out.println("File Error!");
        }
    }

    // Method to update student
    public static void updateStudent() {

        try {
            File file = new File(fileName);

            BufferedReader br = new BufferedReader(
                    new FileReader(file));

            System.out.print("Enter Student ID to Update: ");
            int updateID = sc.nextInt();
            sc.nextLine();

            String line;
            StringBuilder records = new StringBuilder();

            boolean found = false;

            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == updateID) {

                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter New Marks: ");
                    double marks = sc.nextDouble();

                    line = id + "," + name + "," + age +
                            "," + course + "," + marks;

                    found = true;
                }

                records.append(line).append("\n");
            }

            br.close();

            if (found) {

                BufferedWriter bw = new BufferedWriter(
                        new FileWriter(file));

                bw.write(records.toString());
                bw.close();

                System.out.println("Student Updated Successfully!");

            } else {
                System.out.println("Student Not Found!");
            }

        } catch (IOException e) {
            System.out.println("File Error!");
        }
    }

    // Method to delete student
    public static void deleteStudent() {

        try {
            File file = new File(fileName);

            BufferedReader br = new BufferedReader(
                    new FileReader(file));

            System.out.print("Enter Student ID to Delete: ");
            int deleteID = sc.nextInt();

            String line;
            StringBuilder records = new StringBuilder();

            boolean found = false;

            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == deleteID) {
                    found = true;
                    continue;
                }

                records.append(line).append("\n");
            }

            br.close();

            if (found) {

                BufferedWriter bw = new BufferedWriter(
                        new FileWriter(file));

                bw.write(records.toString());
                bw.close();

                System.out.println("Student Deleted Successfully!");

            } else {
                System.out.println("Student Not Found!");
            }

        } catch (IOException e) {
            System.out.println("File Error!");
        }
    }

    // Main method
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" STUDENT RECORD MANAGEMENT");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank You! Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}