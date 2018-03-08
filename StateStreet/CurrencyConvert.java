
package StateStreet;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class CurrencyConvert{

    private static boolean isFirstLine = true;
    private static int columnSize;
    private static boolean euroConverter;     //true:convert from usd to euro
    
    private static Arguments arguments = new Arguments();
    private static CheckException checkException = new CheckException();
    private static CurrencyOperation currencyOperation = new CurrencyOperation();
    
    public static void main(String[] args) throws FileNotFoundException, IOException, FileNotFoundException, UnsupportedEncodingException{

        arguments.addArgDouble("Multiply currency value by N(Integer or Float) for the current conversion rate", "multiplier N");
        arguments.addArgDouble("convert CSV field N (Integer) ","field N");
        arguments.addArgSingle("Write to output file","o output");
        arguments.addArgSingle("Read from input file", "i input");
        arguments.parse(args);
        
        int field = arguments.getField();
        float multiplier = arguments.getMultiplier();

        euroConverter = multiplier<1;
       
        Scanner inData = new Scanner(System.in);
        
        
        Pattern pattern = Pattern.compile("(,)?((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)");  
        String line = new String(); 
        StringBuilder sb = new StringBuilder();
        
        while(inData.hasNextLine()){            
            line = inData.nextLine();
            List<String> strList = new ArrayList<>(); 
            checkException.checkCommas(line);
            Matcher matcher = pattern.matcher(line);  
            while(matcher.find()) {  
                String cell = matcher.group(2);     //group(2) is ((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)  
                Pattern pattern2 = Pattern.compile("\"((.)*)\"");  
                Matcher matcher2 = pattern2.matcher(cell);  
                if(matcher2.find()) {  
                    cell = matcher2.group(1);  
                }  
                strList.add(cell);
            }  
            strList.remove(strList.size()-1);
            
            if(isFirstLine){
                columnSize = strList.size();
                for(String s:strList)
                    sb.append(s).append(",");
                System.out.print(sb.deleteCharAt(sb.length()-1).toString() + "\r\n");
                isFirstLine = false;
                sb = new StringBuilder();
                continue;
            }
  
            if(strList.size()!=columnSize){
                System.err.println("Not Success!");
                System.err.println("Found additional comma in "+line);
                System.exit(0);
            }
            
            String newPrice = euroConverter?currencyOperation.euroConverter(strList.get(field), multiplier):currencyOperation.dollarConverter(strList.get(field), multiplier);
            for(int i = 0; i<strList.size(); i++){
                if(i!=field){
                    checkException.checkQuotation(strList.get(i));
                    sb.append(strList.get(i)).append(",");
                }
                else{
                    if(euroConverter)
                        sb.append("\"").append(newPrice).append("\"").append(",");
                    else
                        sb.append(newPrice).append(",");
                }
                   
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.print(sb.toString() + "\r\n");
            sb = new StringBuilder();
        }
        
    }
}



