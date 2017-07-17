import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/** Cosc 326 Etude 3
 *	PokerMain.java - Main class
 * @author Daniel Thomson ID:5040702
 */
public class PokerMain {
	
   //Regular Expression for detecting correct format of input.
   private static final String PATTERN ="(([1-9]|([1][0-3])|[KQJAkqja])[sdhcSD"+
      "HC][\\/\\s\\-])(([1-9]|([1][0-3])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])(([1"+
      "-9]|([1][0-3])|[KQJAkqja])[sdhcSDHC][\\/\\s\\-])(([1-9]|([1][0-3])|[KQJ"+
      "Akqja])[sdhcSDHC][\\/\\s\\-])(([1-9]|([1][0-3])|[KQJAkqja])[sdhcSDHC])";
   //Count for the number of seperator in the input
   private static int sepCount;
    
   /**
    * Reads in line, determines whether it match regex,
    * if it does separates cards
    * into ArrayList, sort them from lowest to highest, checks for duplicate
    * cards, then outputs sorted hand. If input does match regex or has 
    * duplicates prints invalid.
    * @param args arguments (not used)
    */
   public static void main(String[]args) {
      Scanner sc = new Scanner(System.in);
      while(sc.hasNextLine()) {
         sepCount = 0;
         String input = sc.nextLine();
         boolean match = Pattern.matches(PATTERN, input);
         if(match) {
            ArrayList<Card> sepInput = seperate(input);
            sepInput = sort(sepInput);
            if(!(validate(sepInput))|| sepCount > 1 ) {
               System.out.println("Invalid: " + input);
            }else{
	       combine(sepInput);
	    }
         } else {
            System.out.println("Invalid: " + input);
         }
      }
   }
	
   /**
    * Takes string as input and separates it in to individual Card objects
    * stored in ArrayList.
    * @param input String representing hand of cards.
    * @return ArrayList of Card objects.
    */
   public static ArrayList<Card> seperate(String input) {
      char seperator = '?';
      int lastSep = 0,numCards = 0;
      ArrayList<Card> hand = new ArrayList();
      if(input.contains("/")) {
         seperator = '/';
         sepCount++;
      }
      if (input.contains("-")) {
         seperator = '-';
         sepCount++;
      }
      if (input.contains(" ")) {
         seperator = ' ';
         sepCount++;
      }
      for(int i = 0; i < input.length(); i++) {
         if(input.charAt(i) == seperator) {
         hand.add(new Card(input.substring(lastSep, i)));
         numCards++;
         lastSep = i+1;
         if(numCards == 4) {
            hand.add(new Card(input.substring(lastSep)));
            numCards++;
            }
         }
      }
      return hand;
   }
	
   /**
    * Takes an ArrayList of Card objects and sorts them from lowest
    * to highest using an insertion sort
    * @param hand ArrayList of Card objects to be sorted
    * @return ArrayList of sort Cards
    */
   public static ArrayList<Card> sort (ArrayList<Card> hand) {
      int pos;
      int i;
      int key;
      Card cKey;

      for(pos = 1; pos < hand.size(); pos++) {
         cKey = hand.get(pos);
         key = cKey.getValueNum();
         for(i = (pos-1); i >= 0; i--) {
            Card currCard = hand.get(i);
            if(currCard.getValueNum() > key || (currCard.getValueNum() == key &&
            currCard.getSuitVal() > cKey.getSuitVal())) {
               hand.set(i+1, currCard);
               hand.set(i, cKey);
            } else {
               i = -1;
            }
         }
      }
      return hand;
   }
    
   /**
    * Determines whether ArrayList of Cards contains any
    * cards of duplicate values.
    * @param hand ArrayList of cards
    * @return boolean representing whether hand is valid or not.
    */
   public static boolean validate (ArrayList<Card> hand) {
      for(int i = 0; i < hand.size(); i ++) {
         for(int j = i+1; j < hand.size(); j++) {
            if(hand.get(i).equals(hand.get(j))) {
               return false;
            }
         }
      }
      return true;
   }
        
   /**
    * Combines sorted ArrayList of cards back into a single string
    * representing a hand.
    * @param hand String representing sort hand
    */
   public static void combine (ArrayList<Card> hand) {
      StringBuilder output = new StringBuilder();
      for(int i = 0; i < hand.size(); i++) {
         output.append(hand.get(i).toString());
         if(i < hand.size() - 1) {
            output.append(" ");
         }
      }
      System.out.println(output.toString());
   }
		
}
