package io.github.saikg.leetcode.s759;

import io.github.saikg.leetcode.common.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    class Event {
        int employeeId;
        int time;
        int eventType;

        Event(int _eid, int _time, int _et) {
            employeeId = _eid;
            time = _time;
            eventType = _et;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> mergedSchedule = mergeSchedules(schedule);
        List<Interval> freeIntervals = new ArrayList<>();
        for (int i = 0; i < mergedSchedule.size() - 1; i++) {
            int start = mergedSchedule.get(i).end;
            int end = mergedSchedule.get(i+1).start;
            if (start < end) {
                freeIntervals.add(new Interval(start, end));
            }
        }
        return freeIntervals;
    }

    private List<Interval> mergeSchedules(List<List<Interval>> schedule) {
        List<Interval> mergedSchedule = new ArrayList<>();
        PriorityQueue<Event> events = new PriorityQueue<Event>(
                Comparator
                        .comparingInt((Event u) -> u.time)
                        .thenComparingInt(u -> u.eventType)
        );

        int n = schedule.size();
        int[] eventId = new int[n];
        for (int i = 0; i < n; i++) {
            List<Interval> employeeSchedule = schedule.get(i);
            events.add(new Event(i, employeeSchedule.get(0).start, 0));
            events.add(new Event(i, employeeSchedule.get(0).end, 1));
            eventId[i] = 0;
        }

        int busyEmployees = 0, start = 0, end = 0;
        while (!events.isEmpty()) {
            Event event = events.poll();
            int eventType = event.eventType;
            if (eventType == 0) {
                if (busyEmployees == 0) {
                    start = event.time;
                }
                busyEmployees++;
            } else {
                end = event.time;
                busyEmployees--;
                int empId = event.employeeId;
                eventId[empId]++;
                if (schedule.get(empId).size() > eventId[empId]) {
                    Interval nextEvent = schedule.get(empId).get(eventId[empId]);
                    events.add(new Event(empId, nextEvent.start, 0));
                    events.add(new Event(empId, nextEvent.end, 1));
                }
            }

            if (busyEmployees == 0) {
                mergedSchedule.add(new Interval(start, end));
            }
        }
        return mergedSchedule;
    }
}