#include "countfeet.h"

// an integer array with size of 99
// each element in the array represents number of legs of an individual animal
// this array will only contain chickens with 2 legs and rabbits with 4 legs
int animals[ENOUGH];

// get a random number from 1 to 99
// this number will be the number of chickens
int get_random_num() {
  srand(time(0));
  int random = rand() % ENOUGH + 1;
  return random;
}

// create a cage which contains a certain number of chickens and a certain
// number of rabbits the total number of chickens and rabbits is 99 param num:
// int representing number of chickens in the cage
void create_cage_with_chickens_and_rabbits(int num, int *animals) {
  for (int i = 0; i < num; ++i) {
    animals[i] = 2;
  }
  for (int i = num; i < ENOUGH; ++i) {
    animals[i] = 4;
  }
}

// return: the total number of legs in the cage
int get_legs_in_cage(int *animals) {
  int sum = 0;
  for (int i = 0; i < ENOUGH; ++i) {
    sum += animals[i];
  }
  return sum;
}

// get number of rabbits in the cage
// param legs: int representing total number of legs in the cage
// return: number of rabbits in the cage
int get_rabbits(int legs) { return NUM_RABBITS(legs); }

// get number of chickens in the cage
// param legs: int representing total number of legs in the cage
// return: number of chickens in the cage
int get_chickens(int legs) { return NUM_CHICKENS(legs); }

// find which kind of animal has more legs
// if chickens have more legs, print "chickens have more legs"
// if rabbits have more legs, print "rabbits have more legs"
// if they have the same number of legs, print "they have the same number of
// legs" param chickens: int representing number of chickens in the cage param
// rabbits: int representing number of rabbits in the cage
const char *more_legs(int chickens, int rabbits) {
  return MORE_LEGS(chickens, rabbits);
}
