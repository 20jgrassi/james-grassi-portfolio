#include <stdio.h>

#ifndef STAR_H
#define STAR_H

// function you may want to use to help debugging
//   taken from https://en.wikipedia.org/wiki/Bitwise_operations_in_C
// goal: prints a binary representation of an (unsigned) int
// param x: variable to print the value of
void showbits(unsigned int x);

// goal: packs an azimuth, elevation, and brightness into a "star"
// param azimuth: desired value of the star's azimuth
//   legal range: 0 to 359 (inclusive)
// param elevation: desired value of the star's elevation
//   legal range: -90 to 90 (inclusive)
// param brightness: desired value of the star's brightness
//   legal range: 0 to 2^15 - 1 (32767) (inclusive)
// psuedo-return *star: variable to pack values into
//   9 most significant bits hold the azimuth
//   8 middle bits hold the elevation (in two's complement)
//   15 least significant bits hold the brightness
// return: error code, -1 if any field outside valid range, 0 otherwise
//   (if return -1, *star may take any value, no guarantees)
int pack(unsigned int* star, int azimuth, int elevation, int brightness);

// goal: unpacks a "star" into the azimuth, elevation, and brightness
//   9 most significant bits hold the azimuth
//   8 middle bits hold the elevation (in two's complement)
//   15 least significant bits hold the brightness
// param star: unsigned integer representing star to unpack
// param *azimuth: variable to unpack azimuth into
// param *elevation: variable to unpack elevation into
// param *brightness: variable to unpack brightness into
void unpack(unsigned int star, int* azimuth, int* elevation, int* brightness);

// goal: sets the azimuth of a "star"
// param azimuth: desired value of the star's azimuth
//   legal range: 0 to 359 (inclusive)
// psuedo-return *star: variable to pack values into
//   9 most significant bits hold the azimuth
//   rest should remain as-is
// return: error code, -1 if azimuth outside valid range, 0 otherwise
//   (if return -1, *star may take any value, no guarantees)
int set_azimuth(unsigned int* star, int azimuth);

// goal: sets the elevation of a "star"
// param elevation: desired value of the star's elevation
//   legal range: -90 to 90 (inclusive)
// psuedo-return *star: variable to pack values into
//   8 middle bits hold the elevation (in two's complement)
//   rest should remain as-is
// return: error code, -1 if elevation outside valid range, 0 otherwise
//   (if return -1, *star may take any value, no guarantees)
int set_elevation(unsigned int* star, int elevation);

// goal: sets the brightness of a "star"
// param brightness: desired value of the star's brightness
//   legal range: 0 to 2^15 - 1 (32767) (inclusive)
// psuedo-return *star: variable to pack values into
//   15 least significant bits hold the brightness
//   rest should remain as-is
// return: error code, -1 if brightness outside valid range, 0 otherwise
//   (if return -1, *star may take any value, no guarantees)
int set_brightness(unsigned int* star, int brightness);

#endif