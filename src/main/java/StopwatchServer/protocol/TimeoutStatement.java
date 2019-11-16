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
public class TimeoutStatement implements Statement {

    private static final String NAME = "timeout";

    public static TimeoutStatement getInstance(String statement) {
        return new TimeoutStatement();
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
