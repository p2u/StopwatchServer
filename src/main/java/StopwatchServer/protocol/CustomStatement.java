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
public class CustomStatement implements Statement {

    private String statement;

    public static CustomStatement getInstance(String statement) {
        CustomStatement cs = new CustomStatement();
        cs.statement = statement;
        return cs;
    }

    @Override
    public String toString() {
        return "<custom> " + statement;
    }

    public static boolean willDecode(String statement) {
        return true;
    }

}
