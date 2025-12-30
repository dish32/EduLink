import java.util.Scanner;

// Node class
class StudentNode {
    int id;
    String name;
    double marks;
    StudentNode next;

    StudentNode(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.next = null;
    }
}

// Linked List class
class StudentLinkedList {
    StudentNode head;

    // Add student
    public void addStudent(int id, String name, double marks) {
        StudentNode newNode = new StudentNode(id, name, marks);

        if (head == null) {
            head = newNode;
        } else {
            StudentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("✅ Student added successfully!");
    }

    // Display students
    public void displayStudents() {
        if (head == null) {
            System.out.println("⚠️ No students found!");
            return;
        }

        StudentNode temp = head;
        System.out.println("\n--- Student List ---");
        while (temp != null) {
            System.out.println("ID: " + temp.id +
                               ", Name: " + temp.name +
                               ", Marks: " + temp.marks);
            temp = temp.next;
        }
    }

    // Search student
    public void searchStudent(int id) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("🎯 Student Found:");
                System.out.println("ID: " + temp.id +
                                   ", Name: " + temp.name +
                                   ", Marks: " + temp.marks);
                return;
            }
            temp = temp.next;
        }
        System.out.println("❌ Student not found!");
    }

    // Delete student
    public void deleteStudent(int id) {
        if (head == null) {
            System.out.println("⚠️ List is empty!");
            return;
        }

        if (head.id == id) {
            head = head.next;
            System.out.println("🗑️ Student deleted successfully!");
            return;
        }

        StudentNode temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("❌ Student not found!");
        } else {
            temp.next = temp.next.next;
            System.out.println("🗑️ Student deleted successfully!");
        }
    }
}

// Main class
public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    list.addStudent(id, name, marks);
                    break;

                case 2:
                    list.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    list.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    list.deleteStudent(deleteId);
                    break;

                case 5:
                    System.out.println("👋 Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
