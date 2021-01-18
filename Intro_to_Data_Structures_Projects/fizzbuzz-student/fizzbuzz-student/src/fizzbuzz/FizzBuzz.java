package fizzbuzz;

public class FizzBuzz {
  private final int fizzNumber;
  private final int buzzNumber;

  /**
   * Construct an object that can compute fizzbuzz values for a game of 
   * Fizz and Buzz.
   * 
   * @param fizzNumber an integer between 1 and 9
   * @param buzzNumber an integer between 1 and 9
   */
  public FizzBuzz(int fizzNumber, int buzzNumber) {
    this.fizzNumber = fizzNumber;
    this.buzzNumber = buzzNumber;
  }

  /**
   * Returns the fizzbuzz value for n. The rules are:
   * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
   * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
   * - however, if both the above conditions are true, you must return "fizzbuzz"
   * - if none of the above conditions is true, return the number itself.
   *
   * <p>For example, getValue(1) returns "1".
   * 
   * @param n a positive integer
   * @return the fizzbuzz value, as a String
   */
  public String getValue(int n) {
	  
	  boolean isFizz = false;
	  boolean isBuzz = false;
	  String correctValue = "";
	  
	  if ( (n % fizzNumber == 0) || numberContainsDigit(n, fizzNumber) ) {isFizz = true;}
	  if ( (n % buzzNumber == 0) || numberContainsDigit(n, buzzNumber) ) {isBuzz = true;}
	  
	  if (isFizz || isBuzz) {
		  correctValue += isFizz ? "fizz" : "";
		  correctValue += isBuzz ? "buzz" : "";
	  }
	  else {correctValue = Integer.toString(n);}
	  
	  return correctValue;
  }
  
  private boolean numberContainsDigit(int number, int digit) {
	  
	  if(number % 10 == digit) {return true;} //tests 1's place for digit, base case if found
	  if (number > 9) {return numberContainsDigit(number / 10, digit);}
	  //then removes 1's place, tests recursively
	  return false; //base case if never found
	  
  }

  /**
   * Returns an array of the fizzbuzz values from start to end, inclusive.
   * 
   * <p>For example, if the fizz number is 3 and buzz number is 4,
   * getValues(2,6) should return an array of Strings:
   * 
   * <p>{"2", "fizz", "buzz", "5", "fizz"}
   * 
   * @param start
   *            the number to start from; start > 0
   * @param end
   *            the number to end at; end >= start
   * @return the array of fizzbuzz values
   */
  public String[] getValues(int start, int end) {
	  
    String[] correctRange = new String[(end - start) + 1];
    
    for(int i = 0; i < correctRange.length; i++) {
    	correctRange[i] = getValue(i + start);
    }
    
    return correctRange;
  }
    
  }
