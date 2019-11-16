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
public class ResetStatement implements Statement{

    private static final String NAME = "reset";

    public static ResetStatement getInstance() {
        return new ResetStatement();
    }
    
    public static ResetStatement getInstance(String statement) {
        return new ResetStatement();
    }
    
    public static boolean willDecode(String statement) {
        String value = statement.toLowerCase();
        return value.equals(NAME);
    }
    
    @Override
    public String toString() {
        return NAME;
    }
    
}
