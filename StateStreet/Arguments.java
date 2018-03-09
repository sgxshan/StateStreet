

package StateStreet;

import java.util.*;

public class Arguments {
    private final Map<String, String> singleDashExplain = new HashMap<>();
    private final Map<String, String> singleDashValue = new HashMap<>();
    private final Map<String, String> doubleDashExplain = new HashMap<>();
    private final Map<String, String> doubleDashValue = new HashMap<>();

    private static CheckException checkException = new CheckException();
        
    public void addArgSingle(String explain, String singleDashCmd) {
        singleDashExplain.put("-" + singleDashCmd, explain);
    }

    public void addArgDouble(String explain, String doubleDashCmd) {
        doubleDashExplain.put("--" + doubleDashCmd, explain);
    }
    
    public void helper(){
        System.err.println("usage <--field N> [--multiplier N] [-i input] [-o output]");
        if (!doubleDashExplain.isEmpty()) {
            doubleDashExplain.entrySet().forEach((entry) -> {
                System.err.println("\t" + entry.getKey() + "\t" + entry.getValue());
            });
        }
        
        if (!singleDashExplain.isEmpty()) {
            singleDashExplain.entrySet().forEach((entry) -> {
                System.err.println("\t" + entry.getKey() + "\t" + entry.getValue());
            });
        }

        System.exit(0);
    }
    
    public void parse(String[] args) {
        if(args.length<4||! checkException.isValidMultiplier(args[3])||!checkException.isValidField(args[1])){
            helper();
            return;
        }

        if(args.length==8&&"--field".equals(args[0])&&"--multiplier".equals(args[2])&&"-i".equals(args[4])&&"-o".equals(args[6])){
            doubleDashValue.put(args[0], args[1]);
            doubleDashValue.put(args[2], args[3]);
            singleDashValue.put(args[4], args[5]);
            singleDashValue.put(args[6], args[7]);
        }
        else if("--field".equals(args[0])&&"--multiplier".equals(args[2])){
            doubleDashValue.put(args[0], args[1]);
            doubleDashValue.put(args[2], args[3]);
            
        }
        else
            helper();
    }

    public int getField(){
        return Integer.parseInt(doubleDashValue.get("--field"))-1;
        
    }

    public float getMultiplier(){
        return Float.parseFloat(doubleDashValue.get("--multiplier"));
    }
       
    public String getInput(){
        return singleDashValue.get("-i");
    }
        
    public String getOutput(){
        return singleDashValue.get("-o");
    }
   
    
}
