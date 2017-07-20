package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntry) throws Exception;
    
    TimeEntry find(Long id);

    List<TimeEntry> list();

    TimeEntry update(Long id, TimeEntry timeEntry);

    void delete(Long id);
}
