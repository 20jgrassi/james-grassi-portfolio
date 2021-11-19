#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#ifndef COUNTFEET_H
#define COUNTFEET_H

// TODO: add a line of code to make next line avaiable
#define CODE_START
#ifdef CODE_START

// TODO: define default size for animal array
#define ENOUGH 99

// TODO: defeine NUM_RABBITS macro
#define NUM_RABBITS(legs) ( ((legs) - 99*2) / 2 )

// TODO: defeine NUM_CHICKENS macro
#define NUM_CHICKENS(legs) ( 99 - (((legs) - 99*2) / 2) )

#define CHICKENS_MORE_LEGS "chickens have more legs"
#define RABBITS_MORE_LEGS "rabbits have more legs"
#define SAME_LEGS "they have the same number of legs"
#define MORE_LEGS(a, b)                    \
  (((a)*2) > ((b)*4)) ? CHICKENS_MORE_LEGS \
                      : ((a)*2 == (b)*4) ? SAME_LEGS : RABBITS_MORE_LEGS

// get a random number from 1 to 99
// this number will be the number of chickens
int get_random_num();

// create a cage which contains a certain number of chickens and a certain
// number of rabbits the total number of chickens and rabbits is 99 param num:
// int representing number of chickens in the cage
void create_cage_with_chickens_and_rabbits(int num, int *animals);

// return: the total number of legs in the cage
int get_legs_in_cage(int *animals);

// get number of rabbits in the cage
// param legs: int representing total number of legs in the cage
// return: number of rabbits in the cage
int get_rabbits(int legs);

// get number of chickens in the cage
// param legs: int representing total number of legs in the cage
// return: number of chickens in the cage
int get_chickens(int legs);

// find which kind of animal has more legs
// if chickens have more legs, print "chickens have more legs"
// if rabbits have more legs, print "rabbits have more legs"
// if they have the same number of legs, print "they have the same number of
// legs" param chickens: int representing number of chickens in the cage param
// rabbits: int representing number of rabbits in the cage
const char *more_legs(int chickens, int rabbits);

#endif

#endif
