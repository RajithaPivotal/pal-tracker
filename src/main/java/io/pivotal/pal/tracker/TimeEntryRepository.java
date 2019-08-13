package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeEntryRepository {

    TimeEntry create(TimeEntry any);

    TimeEntry find(long timeEntryId) ;

    List<TimeEntry> list();

    TimeEntry update(long eq, TimeEntry any) ;

    void delete(long timeEntryId);
}
