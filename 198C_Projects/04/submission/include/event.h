#include <stdio.h>

#ifndef EVENT_H
#define EVENT_H

// Struct representing military time
// TODO: complete the struct
struct Time {
  int hour;
  int min;
  int sec;
};

// Struct event
struct Event {
  struct Time st;
  int event_id;
};

// Creates a Event struct given the hour, minute, and second, and event id
//   param hour: int representing the hour 0-23
//   param min: int representing the minute 0-59
//   param sec: int representing the second 0-59
//   param event_id: int representing the event id
//   return: a Event struct with event id and its time
struct Event create_new_event(int hour, int min, int sec, int event_id);

// Gets the event id from a Event struct
//   param e: struct representing a event
//   return: an int representing the id of event e
int get_event_id(struct Event e);

// Gets the time from a Event struct
//   param e: struct representing a event
//   return: a Time struct representing the time of event e
struct Time get_event_time(struct Event e);

// Creates a Time struct representing the time difference between two events of
// today
//   param e1: Event struct representing the first event
//   param e2: Event struct representing the second event
//   return: Time struct representing time between e1 and e2
struct Time elapsed_time(struct Event e1, struct Event e2);

#endif
