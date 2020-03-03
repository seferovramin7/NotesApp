package notesapp.demo.Controller;

import notesapp.demo.Model.Note;
import notesapp.demo.Repository.NotesRepository;
import notesapp.demo.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/notes")
@RestController
public class NotesController {

    @Autowired
    NotesService notesService;

    @CachePut(value = "notes",key="#note.id")
    @PostMapping("/add")
    public Note newNote(@RequestBody Note note){
        return notesService.saveNote(note);
    }

    @Cacheable(value = "note", key = "#id")
    @GetMapping("/get")
    public Note getNote(@RequestParam Long id){
       return notesService.getNote(id);
    }

    @Cacheable(value = "notes", sync = true)
    @GetMapping("/all")
    public List<Note> getAllNotes(){
        return notesService.getAllNotes();
    }

    @CachePut(value = "notes", key = "#noteId")
    @PutMapping("/update/{noteId}")
    public Note updateNote(@RequestBody Note note, @PathVariable(value = "noteId") Long noteId){
        return notesService.updateNote(note, noteId);
    }

    @Caching(evict = {
            @CacheEvict(value="note", allEntries=true),
            @CacheEvict(value="notes", allEntries=true)})
    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "noteId") Long id){
        return notesService.deleteNote(id);
    }


    @GetMapping("cache")
    public String cleanCache(){
        notesService.cacheRemover();
        return "Cache is clean ! ";
    }
}
