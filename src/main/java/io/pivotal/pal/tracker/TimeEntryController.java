package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository tme) {
        this.timeEntryRepository = tme;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry tme){
        TimeEntry timeEntryCreated = timeEntryRepository.create(tme);
        return ResponseEntity.created(null).body(timeEntryCreated);
    }

    @GetMapping("/{timeEntryID}")
    public @ResponseBody ResponseEntity<TimeEntry> read(@PathVariable long timeEntryID){
        TimeEntry timeEntryFound = timeEntryRepository.find(timeEntryID);
        if (timeEntryFound == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(timeEntryFound);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        if (timeEntryList == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(timeEntryList);
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry toUpdate){
        TimeEntry timeEntryUpdate = timeEntryRepository.update(id, toUpdate);
        if (timeEntryUpdate == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(timeEntryUpdate);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
