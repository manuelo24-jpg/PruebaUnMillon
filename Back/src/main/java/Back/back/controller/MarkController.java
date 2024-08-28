package Back.back.controller;

import Back.back.entity.Mark;
import Back.back.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/marks")
public class MarkController {

    @Autowired
    private MarkService markService;

    @GetMapping
    public List<Mark> getMarksByStudentId(@PathVariable Long studentId) {
        return markService.getMarksByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mark> getMarkByIdAndStudentId(@PathVariable Long studentId, @PathVariable Long id) {
        return markService.getMarkByIdAndStudentId(id, studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mark> createMark(@PathVariable Long studentId, @RequestBody Mark mark) {
        Mark createdMark = markService.createMark(studentId, mark);
        return ResponseEntity.ok(createdMark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mark> updateMark(@PathVariable Long studentId, @PathVariable Long id,
            @RequestBody Mark markDetails) {
        Mark updatedMark = markService.updateMark(studentId, id, markDetails);
        return ResponseEntity.ok(updatedMark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long studentId, @PathVariable Long id) {
        markService.deleteMark(studentId, id);
        return ResponseEntity.noContent().build();
    }
}