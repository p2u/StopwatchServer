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
public class StopStatement implements Statement {

    private static final String NAME = "stop";

    public static StopStatement getInstance() {
        return new StopStatement();
    }
    
    public static StopStatement getInstance(String statement) {
        return new StopStatement();
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
