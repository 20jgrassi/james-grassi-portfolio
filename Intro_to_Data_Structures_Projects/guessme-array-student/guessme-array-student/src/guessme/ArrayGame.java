package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game.
 */
public class ArrayGame {

  // stores the next number to guess
  private int guess;
  
  // TODO: declare additional data members, such as arrays that store
  // prior guesses, eliminated candidates etc.
  
  private int[] priorGuesses;
  private boolean gameIsOver;
  
  private boolean[] elimKey;
  private int[] numList;

  // NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
  // You MAY NOT use any Collection type (such as ArrayList) provided by Java.

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, otherwise your
   * code will fail the JUnit tests.
   * Also DO NOT create any new Java files, as they will
   * be ignored by the autograder!
   *******************************************************/

  // ArrayGame constructor method
  public ArrayGame() {
    elimKey = new boolean[9000];
    numList = new int[9000];
    reset();
  }

  /**
   *  Resets data members and game state so we can play again.
   */
  public void reset() {
    guess = 1000;
    priorGuesses = new int[0];
    gameIsOver = false;
    for(int i = 0; i < 9000; i++) {
      elimKey[i] = true;
      numList[i] = i + 1000;
    }
  }

  /**
   *  Returns true if n is a prior guess; false otherwise.
   */
  public boolean isPriorGuess(int n) {
    for(int priorGuess : priorGuesses) {
      if (n == priorGuess) {return true;}
    }
    return false;
  }

  /**
   *  Returns the number of guesses so far.
   */
  public int numGuesses() {
    return priorGuesses.length;
  }

  /**
   * Returns the number of matches between integers a and b.
   * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
   * The return value must be between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example:
   *   1234 and 4321 have 0 match;
   *   1234 and 1114 have 2 matches (1 and 4);
   *   1000 and 9000 have 3 matches (three 0's).
   */
  public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
    int matches = 0;
    for(int i = 0; i < 4; i++) {
      matches += (a % 10 == b % 10) ? 1 : 0;
      a /= 10; b /= 10;
    }
    return matches;
  }

  /**
   * Returns true if the game is over; false otherwise.
   * The game is over if the number has been correctly guessed
   * or if all candidates have been eliminated.
   */
  public boolean isOver() {
    return gameIsOver;
  }

  /**
   *  Returns the guess number and adds it to the list of prior guesses.
   */
  public int getGuess() {
    int[] newPriorGuesses = new int[numGuesses() + 1];
    for(int i = 0; i < numGuesses(); i++) {
      newPriorGuesses[i] = priorGuesses[i];
    }
    newPriorGuesses[numGuesses()] = guess;
    priorGuesses = newPriorGuesses;
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if all candidates
   * have been eliminated (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    if (nmatches == 4) {gameIsOver = true; return true;}
    for (int i = 0; i < 9000; i++) {
      if (elimKey[i] == true && !(numMatches(guess, numList[i]) == nmatches)) {
        elimKey[i] = false;
      }
    }
    for (int i = 0; i < 9000; i++) {
      if (elimKey[i] == true && !isPriorGuess(numList[i])) {
        guess = numList[i];
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the list of guesses so far as an integer array.
   * The size of the array must be the number of prior guesses.
   * Returns null if there has been no prior guess
   */
  public int[] priorGuesses() {
    if (numGuesses() == 0) {return null;}
    return priorGuesses;
  }
}
