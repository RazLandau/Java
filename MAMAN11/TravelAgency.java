/**
 * The TravelAgency class prints the cost of a flight, the checks if the user's credit card is valid.
 * @Author Raz Landau
 * @version 1
 */
import java.util.Scanner;
public class TravelAgency 
{
    /**
     *  Gets two coordinates from user, 
     *  calculates distance
     *  calculates price of flight,
     *  gets credit card number from user
     *  checks if credit card number is valid
     */ 
    public static void main (String [] args)
    {
        final int KM_PER_COST=100, PRICE_PER_COST=50, MAGIC_NUM=7;
        int xCoordinate, yCoordinate, price, creditCardNumber;
        double distance;
        Scanner scan = new Scanner (System.in);
        // Gets coordinates from user
        System.out.println ("Hello, please enter your destination in two integers:");
        xCoordinate = scan.nextInt();
        yCoordinate = scan.nextInt();
        // Assumuing origin point is (0,0), calculates distance according to Pythagorean theorem
        distance = Math.sqrt (xCoordinate * xCoordinate + yCoordinate * yCoordinate);
        // Rounds up distance, and calculates price according to finals KM_PER_COST and PRICE_PER_COST
        price = (int) (Math.ceil(distance / KM_PER_COST)) * PRICE_PER_COST;  
        System.out.println ("The price of the flight is:\n" + price);
        // Gets credit card number from user
        System.out.println ("Please enter your credit card number:");
        creditCardNumber = scan.nextInt();
        // Checks if credit card is valid:
        // Check 1- Checks if credit card number is positive and has 6 digits
        if (!(creditCardNumber < 1000000 && creditCardNumber > 99999))
            System.out.println ("Your credit card is not valid. You cannot buy the ticket.");
        // Check 2- Checks if the remainder from dividing the 5 siginificant digits of the credit card by MAGIC_NUM (7) equals the last digit    
        else if (!((creditCardNumber / 10) % MAGIC_NUM == (creditCardNumber % 10))) 
            System.out.println ("Your credit card is not valid. You cannot buy the ticket.");
                  else  System.out.println ("Your credit card is valid. Bon Voyage!");
             
        }//main
    }//TravelAgency
        
     
        
        