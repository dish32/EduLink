import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public void addStudent(int id, String name, double marks) {
        StudentNode newNode = new StudentNode(id, name, marks);
        if (head == null) {
            head = newNode;
        } else {
            StudentNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    public String displayStudents() {
        if (head == null)
            return "No students found!\n";

        String result = "";
        StudentNode temp = head;
        while (temp != null) {
            result += "ID: " + temp.id +
                      ", Name: " + temp.name +
                      ", Marks: " + temp.marks + "\n";
            temp = temp.next;
        }
        return result;
    }

    public String searchStudent(int id) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return "Student Found:\nID: " + temp.id +
                       "\nName: " + temp.name +
                       "\nMarks: " + temp.marks;
            }
            temp = temp.next;
        }
        return "Student not found!";
    }

    public String deleteStudent(int id) {
        if (head == null)
            return "List is empty!";

        if (head.id == id) {
            head = head.next;
            return "Student deleted!";
        }

        StudentNode temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;

        if (temp.next == null)
            return "Student not found!";
        else {
            temp.next = temp.next.next;
            return "Student deleted!";
        }
    }
}

// GUI class
public class StudentManagementGUI extends JFrame {
    StudentLinkedList list = new StudentLinkedList();

    JTextField txtId, txtName, txtMarks;
    JTextArea output;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        txtId = new JTextField(10);
        add(txtId);

        add(new JLabel("Name:"));
        txtName = new JTextField(10);
        add(txtName);

        add(new JLabel("Marks:"));
        txtMarks = new JTextField(10);
        add(txtMarks);

        JButton btnAdd = new JButton("Add");
        JButton btnDisplay = new JButton("Display");
        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");

        add(btnAdd);
        add(btnDisplay);
        add(btnSearch);
        add(btnDelete);

        output = new JTextArea(10, 30);
        add(new JScrollPane(output));

        // Button actions
        btnAdd.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            double marks = Double.parseDouble(txtMarks.getText());
            list.addStudent(id, name, marks);
            output.setText("Student added successfully!");
        });

        btnDisplay.addActionListener(e ->
            output.setText(list.displayStudents())
        );

        btnSearch.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            output.setText(list.searchStudent(id));
        });

        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            output.setText(list.deleteStudent(id));
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}

