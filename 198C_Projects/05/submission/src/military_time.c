#include "military_time.h"

// goal: creates a Time struct given the hour, minute, and second
// param hour: int representing the hour 0-23
// param min: int representing the minute 0-59
// param sec: int representing the second 0-59
// assumptions:
//   the inputs are that of a valid military time.
// return: a Time struct for the time representing the same time as the integers
//
// TODO: complete the function
struct Time create_time(int hour, int min, int sec) {
  struct Time ret;
  ret.hour = hour;
  ret.min = min;
  ret.sec = sec;
  return ret;
}

// goal: gets the hour from a Time struct
// param t: pointer that point to a struct representing a time
// return: an int representing the hour of time t
//
// TODO: complete the function
int get_hour(struct Time *t) {
  return (*t).hour;
}

// goal: gets the minute from a Time struct
// param t: pointer that point to a struct representing a time
// return: an int representing the minute of time t
//
// TODO: complete the function
int get_min(struct Time *t) {
  return (*t).min;
}

// goal: gets the second from a Time struct
// param t: pointer that point to a struct representing a time
// return: an int representing the second of time t
//
// TODO: complete the function
int get_sec(struct Time *t) {
  return (*t).sec;
}

// goal: sets the hour to a Time struct
// param t: pointer that point to a struct representing a time
//
// TODO: complete the function
void set_hour(struct Time *t, int hour) {
  (*t).hour = hour;
}

// goal: sets the minute to a Time struct
// param t: pointer that point to a struct representing a time
//
// TODO: complete the function
void set_min(struct Time *t, int min) {
  (*t).min = min;
}

// goal: sets the second to a Time struct
// param t: pointer that point to a struct representing a time
//
// TODO: complete the function
void set_sec(struct Time *t, int sec) {
  (*t).sec = sec;
}

// goal: Creates a Time struct representing the difference between two times (t2
// - t1) param t1: pointer that point to a Time struct representing the
// beginning of interval param t2: pointer that point to a Time struct
// representing the end of interval return: Time struct representing time
// between t1 and t2
//
// TODO: complete the function
struct Time elapsed_time(struct Time *t1, struct Time *t2) {
  int hr1 = (*t1).hour; int hr2 = (*t2).hour;
  int min1 = (*t1).min; int min2 = (*t2).min;
  int sec1 = (*t1).sec; int sec2 = (*t2).sec;

  if( hr1 > hr2 || (hr1 == hr2 && min1 > min2) || (hr1 == hr2 && min1 == min2 && sec1 > sec2) ) {
    hr2 += 24;
  }

  int hour_dif = hr2 - hr1;
  int min_dif = min2 - min1;
  int sec_dif = sec2 - sec1;

  if(sec_dif < 0) {sec_dif += 60; min_dif -= 1;}
  if(min_dif < 0) {min_dif += 60; hour_dif -= 1;}

  struct Time dt = {hour_dif, min_dif, sec_dif};
  return dt;
}

// goal: remove a substring of str beginning at index idx and having length len
// param str: the string being altered
// param idx: the starting index of the removed chunk
// param len: the number of characters to remove (length of substring being
// removed) assumptions:
//   0 <= idx
//   str is a valid string that terminates properly
//
// TODO: complete the function
void remove_substr(char str[], int idx, int len) {
  int str_len; //actually the index of the null terminator
  int i;

  for(str_len = 0; str[str_len] != '\0'; str_len++);

  for(i = idx; i + len < str_len; i++) str[i] = str[i + len];

  int len_overshoot = idx + len - str_len;
  if (len_overshoot < 0) len_overshoot = 0;

  str[str_len - len + len_overshoot] = '\0';
}
