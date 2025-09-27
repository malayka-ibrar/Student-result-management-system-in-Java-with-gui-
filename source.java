package dao;

import models.Student;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student student) {
        String query = "INSERT INTO students (student_id, full_name, course, marks, grade) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getMarks());
            stmt.setString(5, student.getGrade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setFullName(rs.getString("full_name"));
                student.setCourse(rs.getString("course"));
                student.setMarks(rs.getInt("marks"));
                student.setGrade(rs.getString("grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Other CRUD methods (update, delete, get by id, etc.)
}
package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 20, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 160, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform authentication logic here using DAO classes
                // Example: AdminDAO adminDAO = new AdminDAO();
                // boolean authenticated = adminDAO.authenticate(username, password);

                // Example logic for now
                if (username.equals("admin") && password.equals("password")) {
                    new AdminPanelFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminLoginFrame();
    }
}