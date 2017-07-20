package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>(0);


  /*
    public TimeEntry create(TimeEntry timeEntry) throws Exception {
        timeEntry.setId((long) timeEntryHashMap.size() + 1);
        timeEntryHashMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }
    */

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = timeEntryHashMap.size() + 1L;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryHashMap.put(id, newTimeEntry);
        return newTimeEntry;
    }


    public TimeEntry find(Long id) {
            return timeEntryHashMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry){
        timeEntry.setId(id);
        timeEntryHashMap.replace(id,timeEntry);
        return timeEntryHashMap.get(id);
    }

    public void delete(Long id){
        timeEntryHashMap.remove(id);

    }
}
