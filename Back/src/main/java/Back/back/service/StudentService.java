package Back.back.service;

import Back.back.entity.Student;
import Back.back.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        initializeDatabaseIfEmpty();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    private void initializeDatabaseIfEmpty() {
        if (studentRepository.count() == 0) {
            List<Student> students = Arrays.asList(
                    new Student("John", "Doe", "john.doe@example.com"),
                    new Student("Jane", "Smith", "jane.smith@example.com"),
                    new Student("Alice", "Johnson", "alice.johnson@example.com"),
                    new Student("Bob", "Brown", "bob.bobshon@example.com"),
                    new Student("Charlie", "Davis", "charlie.charlton@example.com"),
                    new Student("David", "Evans", "david.raimon@example.com"));
            studentRepository.saveAll(students);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}