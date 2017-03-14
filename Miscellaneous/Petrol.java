/**
 * The Petrol class converts a car's petrol cosumption from miles per 1 gallon to liters per 100km
 * @Author Raz Landau
 * @version 1
 */
import java.util.Scanner;
public class Petrol
{
     /**
     *  Gets car's petrol cosumption in miles/gallon from user,
     *  converts car's petrol consumption from miles/gallon to liters/100km
     *  rounds up result
     */ 
    public static void main (String [] args)
    {
        final double KM_PER_MILE = 1.609, LITERS_PER_GALLON = 3.785, LITERS_PER_X_KM = 100;
        final int MAX=100;
        double milesPerOneGallon, gallonsPerOneMile, litersPerMile, litersPerOneKm, litersPerVariableKm, afterRounding;
        Scanner scan = new Scanner (System.in);
        // Gets car petrol consumption in miles/gallon from user, assuming input is valid (as instructed)
        System.out.println ("Please enter the car's petrol consumption measured in miles/gallon. ");
        milesPerOneGallon = scan.nextDouble();
        // Coverts miles/gallon to liters/100km:
        // Since we're looking for distance/fuel, converts gallons per 1 mile to miles per 1 gallon
        gallonsPerOneMile = 1 / milesPerOneGallon;
        // Converts gallons per 1 mile to liters per 1km, using finals
        litersPerOneKm = gallonsPerOneMile * LITERS_PER_GALLON / KM_PER_MILE;
        // Converts liters per 1km to liters per 100km
        litersPerVariableKm = litersPerOneKm * LITERS_PER_X_KM;
        // Rounding final output, as instructed
        afterRounding = Math.round (litersPerVariableKm * MAX) / (MAX * 1.0); // 
        // Output
        System.out.println("The car's petrol consumption converted to liters/100km is\n"+afterRounding);
    } //main
} //Petrol
