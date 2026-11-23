import java.sql.*;
import java.util.Scanner;

public class Course {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Step 1: Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Database Connection
            String url = "jdbc:mysql://localhost:3306/course management system";
            String un = "root";
            String pw = "";

            Connection con = DriverManager.getConnection(url, un, pw);

            System.out.println("Database Connected Successfully!");

            int choice;

            do {

                System.out.println("\n===== COURSE MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Course");
                System.out.println("2. Display All Courses");
                System.out.println("3. Search Course");
                System.out.println("4. Update Course");
                System.out.println("5. Delete Course");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    // CREATE - INSERT COURSE
                    case 1:

                        String insert = "INSERT INTO course VALUES (?, ?, ?, ?, ?)";

                        PreparedStatement ps1 = con.prepareStatement(insert);

                        System.out.print("Enter Course ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Course Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Duration: ");
                        String duration = sc.nextLine();

                        System.out.print("Enter Fees: ");
                        double fees = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter Trainer Name: ");
                        String trainer = sc.nextLine();

                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setString(3, duration);
                        ps1.setDouble(4, fees);
                        ps1.setString(5, trainer);

                        int inserted = ps1.executeUpdate();

                        if (inserted > 0) {
                            System.out.println("Course Added Successfully!");
                        }

                        ps1.close();

                        break;

                    // READ - DISPLAY ALL COURSES
                    case 2:

                        String select = "SELECT * FROM course";

                        Statement st = con.createStatement();

                        ResultSet rs = st.executeQuery(select);

                        System.out.println("\n----- ALL COURSE DETAILS -----");

                        boolean found = false;

                        while (rs.next()) {

                            found = true;

                            System.out.println("Course ID: " + rs.getInt(1));

                            System.out.println("Course Name: " + rs.getString(2));

                            System.out.println("Duration: " + rs.getString(3));

                            System.out.println("Fees: " + rs.getDouble(4));

                            System.out.println("Trainer Name: " + rs.getString(5));

                            System.out.println("--------------------------------");
                        }

                        if (!found) {
                            System.out.println("No Courses Available!");
                        }

                        rs.close();
                        st.close();

                        break;

                    // SEARCH - SEARCH COURSE BY ID
                    case 3:

                        System.out.print("Enter Course ID to Search: ");
                        int searchId = sc.nextInt();

                        String search = "SELECT * FROM course WHERE course_id = ?";

                        PreparedStatement ps2 = con.prepareStatement(search);

                        ps2.setInt(1, searchId);

                        ResultSet rs2 = ps2.executeQuery();

                        if (rs2.next()) {

                            System.out.println("\nCourse Found!");

                            System.out.println("Course ID: " + rs2.getInt(1));

                            System.out.println("Course Name: " + rs2.getString(2));

                            System.out.println("Duration: " + rs2.getString(3));

                            System.out.println("Fees: " + rs2.getDouble(4));

                            System.out.println("Trainer Name: " + rs2.getString(5));

                        } else {

                            System.out.println("Course Not Found!");

                        }

                        rs2.close();
                        ps2.close();

                        break;

                    // UPDATE - UPDATE COURSE DETAILS
                    case 4:

                        System.out.print("Enter Course ID to Update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Course Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter New Duration: ");
                        String newDuration = sc.nextLine();

                        System.out.print("Enter New Fees: ");
                        double newFees = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Enter New Trainer Name: ");
                        String newTrainer = sc.nextLine();

                        String update = "UPDATE course SET course_name=?, duration=?, fees=?, trainer_name=? WHERE course_id=?";

                        PreparedStatement ps3 = con.prepareStatement(update);

                        ps3.setString(1, newName);
                        ps3.setString(2, newDuration);
                        ps3.setDouble(3, newFees);
                        ps3.setString(4, newTrainer);
                        ps3.setInt(5, updateId);

                        int result = ps3.executeUpdate();

                        if (result > 0) {

                            System.out.println("Course Updated Successfully!");

                        } else {

                            System.out.println("Course ID Not Found!");

                        }

                        ps3.close();

                        break;

                    // DELETE - DELETE COURSE
                    case 5:

                        System.out.print("Enter Course ID to Delete: ");
                        int deleteId = sc.nextInt();

                        String delete = "DELETE FROM course WHERE course_id = ?";

                        PreparedStatement ps4 = con.prepareStatement(delete);

                        ps4.setInt(1, deleteId);

                        int deleted = ps4.executeUpdate();

                        if (deleted > 0) {

                            System.out.println("Course Deleted Successfully!");

                        } else {

                            System.out.println("Course ID Not Found!");

                        }

                        ps4.close();

                        break;

                    // EXIT
                    case 6:

                        System.out.println("Thank You for Using Course Management System!");

                        break;

                    default:

                        System.out.println("Invalid Choice! Please Try Again.");

                }

            } while (choice != 6);

            // Step 3: Close Connection
            con.close();
            sc.close();

        }

        catch (ClassNotFoundException e) {

            System.out.println("JDBC Driver Not Found!");
            e.printStackTrace();

        }

        catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();

        }
    }
}