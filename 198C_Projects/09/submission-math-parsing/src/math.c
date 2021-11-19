#include "math.h"


// goal: read equations from a file and write them in a solved state to another
// file
//   NOTE: format details specified in instructions
// param qfile: file with math questions to solve
// param afile: file to write equations with answers in
// example:
//   qfile at start of function:
//     12 + 13
//     24 / 5
//     8 * 234
//     65 - 78
//     239 % 13
//   afile after function:
//      12 +  13 = 25
//      24 /   5 = 4
//       8 * 234 = 1872
//      65 -  78 = -13
//     239 %  13 = 5
//
// TODO: Complete the function
void solve(const char* qfile, const char* afile) {

	//initialize variables
	char op;
	int int1; //before operator
	int int2; //after operator
	int int3; //solution

	//open files
	FILE *q = fopen(qfile, "r");
	if (q == NULL) {return;}
	FILE *a = fopen(afile, "w");
	if(a == NULL) {fclose(q); return;}

	//process equations
	while(fscanf(q, "%d %c %d", &int1, &op, &int2) == 3) {
		switch(op) {
			case '+' :
				int3 = int1 + int2;
				break;
			case '-' :
				int3 = int1 - int2;
				break;
			case '*' :
				int3 = int1 * int2;
				break;
			case '/' :
				int3 = int1 / int2;
				break;
			case '%' :
				int3 = int1 % int2;
				break;
		}

		fprintf(a, "%3d %c %3d = %-d\n", int1, op, int2, int3);
	}

	//close files and return
	fclose(q);
	fclose(a);
	return;
}
