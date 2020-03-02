package notesapp.demo.Repository;

import notesapp.demo.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
