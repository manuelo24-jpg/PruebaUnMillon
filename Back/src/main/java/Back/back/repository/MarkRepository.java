package Back.back.repository;

import Back.back.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findByStudentId(Long studentId);

    Optional<Mark> findByIdAndStudentId(Long id, Long studentId);
}