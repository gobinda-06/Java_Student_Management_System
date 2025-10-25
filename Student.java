import java.util.ArrayList;
import java.util.List;

// ======================= Person Class =======================
class Person {
    private int id;
    private String name;
    private int age;
    private String gender;

    // Constructor
    public Person(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    public void setDetails(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void getDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }
}

// ======================= Student Class =======================
class Student extends Person {
    private int rollNo;
    private String courseName;
    private double grade;

    public Student(int id, String name, int age, String gender, int rollNo, String courseName, double grade) {
        super(id, name, age, gender);
        this.rollNo = rollNo;
        this.courseName = courseName;
        this.grade = grade;
    }

    public void enrollCourse(String courseName) {
        this.courseName = courseName;
        System.out.println(getName() + " enrolled in " + courseName);
    }

    public void viewGrades() {
        System.out.println("Grade: " + grade + " in course " + courseName);
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Roll No: " + rollNo);
        System.out.println("Course: " + courseName);
        System.out.println("Grade: " + grade);
    }

    public String getCourseName() { return courseName; }
}

// ======================= Teacher Class =======================
class Teacher extends Person {
    private int employeeId;
    private String subject;
    private double salary;

    public Teacher(int id, String name, int age, String gender, int employeeId, String subject, double salary) {
        super(id, name, age, gender);
        this.employeeId = employeeId;
        this.subject = subject;
        this.salary = salary;
    }

    public void assignGrade(Student student, double grade) {
        System.out.println("Assigning grade " + grade + " to " + student.getName());
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Subject: " + subject);
        System.out.println("Salary: " + salary);
    }

    public String getName() { return super.getName(); }
}

// ======================= Course Class =======================
class Course {
    private int courseId;
    private String courseName;
    private Teacher teacher;
    private List<Student> studentList;

    public Course(int courseId, String courseName, Teacher teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println(student.getName() + " added to course " + courseName);
    }

    public void removeStudent(Student student) {
        studentList.remove(student);
        System.out.println(student.getName() + " removed from course " + courseName);
    }

    public void showCourseInfo() {
        System.out.println("\nCourse ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Teacher: " + teacher.getName());
        System.out.println("Students Enrolled:");
        if (studentList.isEmpty()) {
            System.out.println("  No students enrolled.");
        } else {
            for (Student s : studentList) {
                System.out.println("  - " + s.getName());
            }
        }
    }
}

// ======================= DatabaseOperations Interface =======================
interface DatabaseOperations {
    void saveData();
    void updateData();
    void deleteData();
    void fetchData();
}

// ======================= Admin Class =======================
class Admin implements DatabaseOperations {
    private int adminId;
    private String name;

    public Admin(int adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }

    public void addStudent(Course course, Student student) {
        course.addStudent(student);
    }

    public void removeStudent(Course course, Student student) {
        course.removeStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        System.out.println("Teacher " + teacher.getName() + " added to system.");
    }

    public void assignCourse(Course course, Student student, Teacher teacher) {
        System.out.println("Admin " + name + " assigns course " + course + " to student " + student.getName() +
                " and teacher " + teacher.getName());
    }

    @Override
    public void saveData() { System.out.println("Data saved to database."); }
    @Override
    public void updateData() { System.out.println("Data updated in database."); }
    @Override
    public void deleteData() { System.out.println("Data deleted from database."); }
    @Override
    public void fetchData() { System.out.println("Data fetched from database."); }
}

// ======================= Main Class =======================
public class Main {
    public static void main(String[] args) {
        // Create Teacher
        Teacher t1 = new Teacher(1, "Mr. Sharma", 35, "Male", 501, "Java Programming", 55000);

        // Create Students
        Student s1 = new Student(2, "Gobinda", 21, "Male", 101, "Java", 95.5);
        Student s2 = new Student(3, "Rahul", 22, "Male", 102, "Java", 90.0);

        // Create Course
        Course javaCourse = new Course(1001, "Java Programming", t1);

        // Add students to course
        javaCourse.addStudent(s1);
        javaCourse.addStudent(s2);

        // Show course info
        javaCourse.showCourseInfo();

        // Admin operations
        Admin admin = new Admin(1, "AdminUser");
        admin.addTeacher(t1);
        admin.assignCourse(javaCourse, s1, t1);
        admin.saveData();

        // Display details
        System.out.println("\n--- Student Details ---");
        s1.getDetails();

        System.out.println("\n--- Teacher Details ---");
        t1.getDetails();
    }
}
