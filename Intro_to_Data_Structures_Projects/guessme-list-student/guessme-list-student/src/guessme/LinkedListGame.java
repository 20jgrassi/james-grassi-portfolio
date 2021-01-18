package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {

  // TODO: declare data members as necessary

	private int guess;
	private LLIntegerNode priorGuessesHead;
	private boolean gameIsOver;
	
	private LLIntegerNode numListHead;

   /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but DO NOT remove any provided method, and do NOT add
   * new files (as they will be ignored by the autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
	  reset();
  }

  /** Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
	  gameIsOver = false;
	  guess = 1000;
	  
	  priorGuessesHead = null;
	  
	  numListHead = null;
	  for(int i = 9999; i >= 1000; i--) {numListHead = new LLIntegerNode(i, numListHead);}
  }

  /** Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
	  for(LLIntegerNode i = priorGuessesHead; i != null; i = i.getLink()) {
		  if(i.getInfo() == n) {return true;}
	  }
	  return false;
  }

  /** Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
		  int count = 0;
		  for(LLIntegerNode i = priorGuessesHead; i != null; i = i.getLink()) {count++;}
		  return count;
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
  public static int numMatches(int a, int b) {
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
   * or if no candidate is left.
   */
  public boolean isOver() {
	  	return gameIsOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses.
   * The insertion should occur at the end of the prior guesses list,
   * so that the order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
		if(priorGuessesHead == null) {priorGuessesHead = new LLIntegerNode(guess, null); return guess;}
		
		LLIntegerNode currentNode = priorGuessesHead;
	    while(currentNode.getLink() != null) {currentNode = currentNode.getLink();}
	    currentNode.setLink(new LLIntegerNode(guess, null));
	    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess.
   * If nmatches is 4, the previous guess is correct and the game is over.
   * Check project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate 
   * is left (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
	    if(nmatches == 4) {gameIsOver = true; return true;}
	    
	    LLIntegerNode newNumListHead = null;
	    LLIntegerNode newNumListCurrent = null;
	    LLIntegerNode currentNode = null;
	    
	    for(LLIntegerNode i = numListHead; i != null; i = i.getLink()) {
	    	if(numMatches(i.getInfo(), guess) == nmatches) {
	    		if (newNumListHead == null) {newNumListHead = new LLIntegerNode(i.getInfo(), null);}
	    		else {
	    			newNumListCurrent = newNumListHead;
	    			while(newNumListCurrent.getLink() != null) {newNumListCurrent = newNumListCurrent.getLink();}
	    			newNumListCurrent.setLink(new LLIntegerNode(i.getInfo(), null));
	    		}
	    	}
	    }
	    numListHead = newNumListHead;
	    
	    currentNode = numListHead;
	    while(currentNode != null) {
	    	if(!isPriorGuess(currentNode.getInfo())) {guess = currentNode.getInfo(); return true;}
	    	currentNode = currentNode.getLink();
	    }
	    return false;
  }

  /**
   *  Returns the head of the prior guesses list.
   *  Returns null if there hasn't been any prior guess
   */
  public LLIntegerNode priorGuesses() {
	  return priorGuessesHead;
  }

  /**
   * Returns the list of prior guesses as a String. For example,
   * if the prior guesses are 1000, 2111, 3222, in that order,
   * the returned string should be "1000, 2111, 3222", in the same order,
   * with every two numbers separated by a comma and space, except the
   * last number (which should not be followed by either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
	    String guessesString = "";
	    for(LLIntegerNode i = priorGuessesHead; i != null; i = i.getLink()) {guessesString += i.getInfo() + 
	    		( (i.getLink() != null) ? ", " : "" );}
	    return guessesString;
  }

}
