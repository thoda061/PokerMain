/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dathomson
 */
public class Card {
    
    private String suit;
    private String value;
    private int suitVal;
    private int valueNum;
    
    public Card (String card) {
        suit = card.substring(card.length()-1);
        suitVal = setSuitVal();
        value = card.substring(0,card.length()-1);
        valueNum = setNumVal();
    }
    
    private int setNumVal() {
        int val = 0;
        try {
            val = Integer.valueOf(value);
            if(val == 1) {
                val = 14;
            }
        } catch (Exception e) {
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

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getValueNum() {
        return valueNum;
    }

    public int getSuitVal() {
        return suitVal;
    }
    
    public String toString() {
        return value+suit;
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
