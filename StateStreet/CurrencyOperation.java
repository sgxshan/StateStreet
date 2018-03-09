
package StateStreet;
import java.util.*;
        
public class CurrencyOperation{
    
    public final char euroSign = (char)0x20ac;
    private CheckException checkException = new CheckException();

    //convert dollar to euro
    public String euroConverter(String dollar, float multiplier){
        //check if dollar is valid
        if(!checkException.isValidPrice(dollar)){
            System.err.println("Invalid dollar format in " + dollar);
            System.exit(1);
        }           
        
        float newPrice = multiplier*Float.parseFloat(dollar);
        StringBuilder sb = new StringBuilder(String.valueOf(newPrice).replace(".", ","));
        sb.insert(0,euroSign);
        return sb.toString();
    }
    
    //convert euro to dollar
    public String dollarConverter(String euro, float multiplier){
        //remove quotation mark
       	if(!euro.startsWith(""+euroSign))
	    System.err.println("Invalid euro currency format in " + euro); 
        euro = euro.substring(1,euro.length()).replace(",", ".");  
        //check if euro is valid
        if(!checkException.isValidPrice(euro)){
            System.err.println("Invalid euro currency format in " + euro);
            System.exit(1);
        }
        
        float newPrice = multiplier*Float.parseFloat(euro);
        return String.valueOf(newPrice);
    }

}



