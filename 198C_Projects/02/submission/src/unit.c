#include "unit.h"

// goal: returns degrees C for given degrees F
// param F: temperature in degrees Fahrenheit
// return: temperature in degrees Celsius
//
// TODO: Complete the function
float convert(float F) {

  float C = F;

  C -= 32;
  C *= (5.0/9.0);

  return C;
}

