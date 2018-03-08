
package StateStreet;

import java.util.regex.*;

public class CheckException{ 
    
    public void checkQuotation(String s){
        int count = 0;
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i)=='"')
                count++;
        }
        if(count%2!=0){
            System.err.println("Not Success!");
            System.err.println("Found invalid quotation mark in " + s);
        }
    }
    
    public void checkCommas(String line){
        //start or end with comma
        if(line.startsWith(",")||line.endsWith(",")) {
            System.err.println("Not Success!");
            System.err.println("Found invalid comma in " + line);
            System.exit(0);
        }
        
        //check additional consecutive comma     
        String pattern = "[\\s]*[,][\\s]*[,]{1,}([\\s]+|[,]+)*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        if (m.find( )) {
           System.err.println("Not Success!");
           System.err.println("Found consecutive commas in " + line );
           System.exit(0);
        } 
    }
    
    public boolean isValidPrice(String value) {  
        try {  
            Float.parseFloat(value);  
        } catch (NumberFormatException e) {  
            return false;  
        }  
        return true;  
    }
    
    public static boolean isValidMultiplier(String value) {  
        try {  
            Float.parseFloat(value);  
        } catch (NumberFormatException e) {  
            return false;  
        }  
        return true;  
    }
    
    public static boolean isValidField(String value) {  
        try {  
            Integer.parseInt(value);  
        } catch (NumberFormatException e) {  
            return false;  
        }  
        return true;  
    }
    
}



