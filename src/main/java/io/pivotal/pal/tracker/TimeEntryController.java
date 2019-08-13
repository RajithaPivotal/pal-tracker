package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newTimeEntry = timeEntryRepository.create(timeEntryToCreate);
        //return ResponseEntity.status(HttpStatus.CREATED).body(newTimeEntry);
        return new ResponseEntity(newTimeEntry, HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

        TimeEntry foundTimeEntry = timeEntryRepository.find(timeEntryId);
        if(foundTimeEntry == null){
            return new ResponseEntity(foundTimeEntry, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(foundTimeEntry, HttpStatus.OK);
        }

    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {

        TimeEntry updateTimeEntry = timeEntryRepository.update(timeEntryId, expected);
        if(updateTimeEntry == null){
            return new ResponseEntity(updateTimeEntry, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(updateTimeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
