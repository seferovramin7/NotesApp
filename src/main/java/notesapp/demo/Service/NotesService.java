package notesapp.demo.Service;

import notesapp.demo.Exception.ResourceNotFoundException;
import notesapp.demo.Model.Note;
import notesapp.demo.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    public Note saveNote(Note note){
      return  notesRepository.save(note);
    }

    public Note getNote(Long noteId){
        return notesRepository.findById(noteId).orElseThrow(
                () -> new ResourceNotFoundException("Note", "Id", noteId));
    }

    public List<Note> getAllNotes(){
        return notesRepository.findAll();
    }

    public Note updateNote(Note note, Long noteId){
        Note note1 = notesRepository.findById(noteId).orElseThrow( () -> new ResourceNotFoundException("Note" , "Id" , noteId));

        note1.setContent(note.getContent());
        note1.setTitle(note.getTitle());

        Note updatedNote = notesRepository.save(note1);

        return updatedNote;
    }

    public ResponseEntity<?> deleteNote(Long noteId){

        Note note1 = notesRepository.findById(noteId).orElseThrow( () -> new ResourceNotFoundException("Note" , "Id", noteId) );

        notesRepository.delete(note1);

        return ResponseEntity.ok().build();
    }

}
