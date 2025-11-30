import java.util.*;

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String msg) {
        super(msg);
    }
}

class Loader extends Thread {
    public void run() {
        try {
            System.out.println("Loading.....");
            Thread.sleep(1500);
        } catch (InterruptedException e) {}
    }
}

class Student {
    Integer rollNo;
    String name;
    String email;
    String course;
    Double marks;

    public Student(Integer rollNo, String name, String email, String course, Double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public String calculateGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    public void display() {
        System.out.println("\n--- Student Details ---");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
       System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + calculateGrade());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Roll No (Integer): ");
            Integer roll = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            Double marks = Double.parseDouble(sc.nextLine());

            if (name.isEmpty() || course.isEmpty())
                throw new StudentNotFoundException("Name or course cannot be empty");

            if (marks < 0 || marks > 100)
                throw new IllegalArgumentException("Invalid marks input. Must be 0-100.");

            Loader thread = new Loader();
            thread.start();
            thread.join();

            Student s = new Student(roll, name, email, course, marks);
            s.display();
        }
        catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }
        finally {
            System.out.println("\nProgram execution completed.");
        }
    }
}
