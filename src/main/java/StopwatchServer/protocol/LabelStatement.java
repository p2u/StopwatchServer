/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.protocol;

/**
 *
 * @author petr
 */
public class LabelStatement implements Statement {

    private static final String NAME = "label";
    private static final String PREFIX = NAME + " ";
    
    private String label = "";

    public static LabelStatement getInstance() {
        return new LabelStatement();
    }

    public static LabelStatement getInstance(String statement) {
        LabelStatement result = getInstance();
        String value = statement.toLowerCase();
        if (value.startsWith(PREFIX)) {
            String label = value.substring(PREFIX.length());
            result.setLabel(label);
        }
        return result;
    }
    
    public static boolean willDecode(String statement) {
        String value = statement.toLowerCase();
        return value.equals(NAME) || value.startsWith(PREFIX);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        if (label.equals("")) {
            return NAME;
        }
        return NAME + " " + label;
    }

}
