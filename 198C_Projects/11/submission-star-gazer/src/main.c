#include "star.h"

// main function for non-test code
// This exists solely for the benefit of the students to test their own code
int main() {

  unsigned int mystar;
  int az; int el; int br;

  pack(&mystar, 30, -60, 90);
  unpack(mystar, &az, &el, &br);
  printf("%i %i %i\n", az, el, br);

  pack(&mystar, 30, -60, 90);
  set_azimuth(&mystar, 3);
  unpack(mystar, &az, &el, &br);
  printf("%i %i %i\n", az, el, br);

  pack(&mystar, 30, -60, 90);
  set_elevation(&mystar, 6);
  unpack(mystar, &az, &el, &br);
  printf("%i %i %i\n", az, el, br);

  unpack((unsigned int) 0, &az, &el, &br);
  printf("%i %i %i\n\n", az, el, br);

  unsigned int zenith;
  unsigned int nadir;
  pack(&zenith, 359, 90, 32767);
  pack(&nadir, 0, -90, 0);

  printf("zenith in binary is: 0b");
  showbits(zenith);
  printf("binary should be:    0b10110011101011010111111111111111 %s\n",
         zenith == 0b10110011101011010111111111111111 ? "ok" : "INCORRECT");

  printf("nadir in binary is:  0b");
  showbits(nadir);
  printf("binary should be:    0b00000000010100110000000000000000 %s\n",
         nadir == 0b00000000010100110000000000000000 ? "ok" : "INCORRECT");

  unsigned int star = zenith;

  set_azimuth(&star, 153);
  printf("star in binary is:   0b");
  showbits(star);
  printf("binary should be:    0b01001100101011010111111111111111 %s\n",
         star == 0b01001100101011010111111111111111 ? "ok" : "INCORRECT");

  set_elevation(&star, -60);
  printf("star in binary is:   0b");
  showbits(star);
  printf("binary should be:    0b01001100111000100111111111111111 %s\n",
         star == 0b01001100111000100111111111111111 ? "ok" : "INCORRECT");

  set_brightness(&star, 31415);
  printf("star in binary is:   0b");
  showbits(star);
  printf("binary should be:    0b01001100111000100111101010110111 %s\n",
         star == 0b01001100111000100111101010110111 ? "ok" : "INCORRECT");

  int azi, ele, bri;
  unpack(star, &azi, &ele, &bri);
  printf("Azimuth: %i (should be 153) %s\n", azi,
         azi == 153 ? "ok" : "INCORRECT");
  printf("Elevation: %i (should be -60) %s\n", ele,
         ele == -60 ? "ok" : "INCORRECT");
  printf("Brightness: %i (should be 31415) %s\n", bri,
         bri == 31415 ? "ok" : "INCORRECT");
}
