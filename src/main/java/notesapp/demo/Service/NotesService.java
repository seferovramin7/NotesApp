package notesapp.demo.Service;

import notesapp.demo.Exception.ResourceNotFoundException;
import notesapp.demo.Model.Note;
import notesapp.demo.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    CacheCleanerService cacheCleanerService;

    public Note saveNote(Note note){
      return  notesRepository.save(note);
    }

    public Note getNote(Long noteId){
        try {
            // Sadece daha aydin olsun deye slowService yazdim
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return notesRepository.findById(noteId).orElseThrow( () -> new ResourceNotFoundException("Note", "id", noteId) );
    }

    public List<Note> getAllNotes(){
        try {
            // Sadece daha aydin olsun deye slowService yazdim
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return notesRepository.findAll();
    }

    public Note updateNote(Note note, Long noteId){
        try {
            // Sadece daha aydin olsun deye slowService yazdim
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Note note1 = notesRepository.findById(noteId).orElseThrow( () -> new ResourceNotFoundException("Note" , "Id" , noteId));
        note1.setContent(note.getContent());
        note1.setTitle(note.getTitle());
        Note updatedNote = notesRepository.save(note1);
        return updatedNote;
    }

    public ResponseEntity<?> deleteNote(Long noteId){
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Note note1 = notesRepository.findById(noteId).orElseThrow( () -> new ResourceNotFoundException("Note" , "Id", noteId) );
        notesRepository.delete(note1);
        return ResponseEntity.ok().build();
    }

    public void cacheRemover(){
        cacheCleanerService.evictAllcachesAtIntervals();
    }


}
