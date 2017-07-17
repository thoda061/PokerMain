/** Cosc 326 Etude 3
 *	Card.java - class for storing info about an individual
 * card in a poker hand.
 * @author Daniel Thomson ID:5040702
 */
public class Card {
    
	//String represent suit of the card
   private String suit;
	//String representing value of the card
   private String value;
	// Int representing suit of the card
   private int suitVal;
	//Int representing value of the card
   private int valueNum;
   
	/**
	 * Constructor that takes a String representing a card 
	 *	(suit and value)
	 * @param card String representing card. 
	 */
   public Card (String card) {
      suit = card.substring(card.length()-1);
      suitVal = setSuitVal();
      value = card.substring(0,card.length()-1);
      valueNum = setNumVal();
   }
   
	/**
	 * Return the int representing the value of the card, using the
	 * the String set in value.
	 * @return Int representing card value;
	 */
   private int setNumVal() {
      int val = 0;
      try {
         val = Integer.valueOf(value);
			//Gives aces highest value
         if(val == 1) {
            val = 14;
         }
      } catch (Exception e) {
			//Deals with letter input for value
         String letterVal = value.toLowerCase();
         switch (letterVal) {
            case "a":
               val = 14;
               break;
            case "j":
               val = 11;
               break;
            case "q":
               val = 12;
               break;
            case "k":
               val = 13;
               break; 
         }   
      }
      return val;
   }
   /**
	 * Return the int representing the suit of the card, using the String
	 * set in suit.
	 * @return Int representing suit
	 */
   private int setSuitVal() {
      String suitLetter = suit.toLowerCase();
      int val = 0;
      switch (suitLetter) {
         case "c":
            val = 1;
				break;
         case "d":
            val = 2;
				break;
         case "h":
            val = 3;
				break;
         case "s":
            val = 4;
				break;
      }
      return val;
   }
	 
	//Getter Methods
   public int getValueNum() {
      return valueNum;
   }

   public int getSuitVal() {
      return suitVal;
   }
   
	//To String Method
   public String toString() {
		StringBuilder sb = new StringBuilder();
		if(valueNum > 10){
			switch(valueNum) {
				case 11:
					sb.append("J");
					break;
				case 12:
					sb.append("Q");
					break;
				case 13:
					sb.append("K");
					break;
				case 14:
					sb.append("A");
					break;
			}
		} else {
			sb.append(value);
		}
		sb.append(suit.toUpperCase());
      return sb.toString();
   }
    
   @Override
   public int hashCode() {
      int hash = 3;
      hash = 23 * hash + this.suitVal;
      hash = 23 * hash + this.valueNum;
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Card other = (Card) obj;
      if (this.suitVal != other.suitVal) {
         return false;
      }
      if (this.valueNum != other.valueNum) {
         return false;
      }
      return true;
   }
    
}
