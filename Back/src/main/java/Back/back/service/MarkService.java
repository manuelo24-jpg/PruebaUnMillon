package Back.back.service;

import Back.back.entity.Mark;
import Back.back.entity.Student;
import Back.back.repository.MarkRepository;
import Back.back.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private StudentRepository studentRepository;

    public MarkService(MarkRepository markRepository, StudentRepository studentRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
    }

    public List<Mark> getMarksByStudentId(Long studentId) {
        return markRepository.findByStudentId(studentId);
    }

    public Optional<Mark> getMarkByIdAndStudentId(Long id, Long studentId) {
        return markRepository.findByIdAndStudentId(id, studentId);
    }

    public Mark createMark(Long studentId, Mark mark) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        mark.setStudent(student);
        return markRepository.save(mark);
    }

    public Mark updateMark(Long studentId, Long id, Mark markDetails) {
        Mark mark = markRepository.findByIdAndStudentId(id, studentId)
                .orElseThrow(() -> new RuntimeException("Mark not found"));
        mark.setSubject(markDetails.getSubject());
        mark.setGrade(markDetails.getGrade());
        return markRepository.save(mark);
    }

    public void deleteMark(Long studentId, Long id) {
        Mark mark = markRepository.findByIdAndStudentId(id, studentId)
                .orElseThrow(() -> new RuntimeException("Mark not found"));
        markRepository.delete(mark);
    }
}