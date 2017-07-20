package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) throws Exception {
            TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
//            return ResponseEntity.status(HttpStatus.CREATED).body(timeEntry);
            return new ResponseEntity<>(timeEntry, CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry != null) return ResponseEntity.ok(timeEntry);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id,timeEntry);
        if(updatedTimeEntry != null) return ResponseEntity.ok(updatedTimeEntry);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
