
/**
 * Student Tester for Maman 13
 */
public class Test1
{
    /**
     */
    public static void main (String[] args)
    {

        Country country1 = new Country ("myCountry");
        
        country1.addCity("JLM", 0, 2, 5, 0, 0, 0);
        country1.addCity("TLV", 0, 0, 4, 0, 0, 0);
        country1.addCity("BSB", 0, 3, 0, 0, 0, 0);
        
        City uniCity = country1.unifyCities("TLV", "JLM");
        System.out.println(country1);
        
     
    }
}
