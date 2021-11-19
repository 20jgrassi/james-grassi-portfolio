#include "event.h"

// Creates a Event struct given the hour, minute, and second, and event id
//   param hour: int representing the hour 0-23
//   param min: int representing the minute 0-59
//   param sec: int representing the second 0-59
//   param event_id: int representing the event id >= 0
//   return: a Event struct with event id and its time
// TODO: complete the function
struct Event create_new_event(int hour, int min, int sec, int event_id) {
  struct Time event_time = {hour, min, sec};
  struct Event new_event = {event_time, event_id};
  return new_event;
}

// Gets the event id from a Event struct
//   param e: struct representing a event
//   return: an int representing the id of event e
// TODO: complete the function
int get_event_id(struct Event e) {
  return e.event_id;
}

// Gets the time from a Event struct
//   param e: struct representing a event
//   return: a Time struct representing the time of event e
// TODO: complete the function
struct Time get_event_time(struct Event e) {
  return e.st;
}

// Creates a Time struct representing the time difference between two events of
// today
//   param e1: Event struct representing the first event
//   param e2: Event struct representing the second event
//   return: Time struct representing time between e1 and e2
// TODO: complete the function
struct Time elapsed_time(struct Event e1, struct Event e2) {

  if (e1.st.hour >  e2.st.hour)
  {return elapsed_time(e2, e1);}
  else if (e1.st.hour == e2.st.hour && e1.st.min > e2.st.min)
  {return elapsed_time(e2, e1);}
  else if (e1.st.hour == e2.st.hour && e1.st.min == e2.st.min && e1.st.sec > e2.st.sec)
  {return elapsed_time(e2, e1);}

  int hour_dif = e2.st.hour - e1.st.hour;
  int min_dif = e2.st.min - e1.st.min;
  int sec_dif = e2.st.sec - e1.st.sec;

  if(sec_dif < 0) {sec_dif += 60; min_dif -= 1;}
  if(min_dif < 0) {min_dif += 60; hour_dif -= 1;}

  struct Time time_dif = {hour_dif, min_dif, sec_dif};

  return time_dif;
}
