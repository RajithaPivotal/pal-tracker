package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private List<TimeEntry> timeEntryList = new ArrayList<>();
    private long count = 0;


    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntryNew = new TimeEntry(++count, timeEntry);
        timeEntryList.add(timeEntryNew);
        return timeEntryNew;
    }

    public TimeEntry find(long id) {
        for(TimeEntry entry: timeEntryList){
            if(entry.getId() == id){
                return entry;
            }
        }

        return null;
    }

    public List<TimeEntry> list() {

        return timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        //List<TimeEntry> newTimeEntryList = new ArrayList<>();
        //TimeEntry updatedEntry = null;
        int tmpCount = 0;
        for(TimeEntry entry: timeEntryList){
            if(entry.getId() == id){
                entry = new TimeEntry(entry.getId(), timeEntry);
                timeEntryList.set(tmpCount, entry);
                return entry;
                //entry = new TimeEntry(entry.getId(), timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
                //updatedEntry = entry;
            }
            //newTimeEntryList.add(entry);
            tmpCount++;
        }
        return null;
    }

    public void delete(long id) {
        int tmpCount = 0;
        for(TimeEntry entry: timeEntryList){
            if(entry.getId() == id){
                break;
            }
            tmpCount++;
        }
        timeEntryList.remove(tmpCount);
    }
}
