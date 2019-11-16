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
public class StartStatement implements Statement {

    private static final String NAME = "start";
    private static final String PREFIX_NAME = NAME + " ";
    private static final String TIMEOUT_PATTERN = "\\d+";

    private String timeout = "0";

    public static StartStatement getInstance() {
        return new StartStatement();
    }

    public static StartStatement getInstance(String statement) {
        StartStatement start = new StartStatement();
        String value = statement.toLowerCase();
        if (value.startsWith(PREFIX_NAME)) {
            String timeout = value.substring(PREFIX_NAME.length());
            start.setTimeout(timeout);
        }
        return start;
    }

    public static boolean willDecode(String statement) {
        String value = statement.toLowerCase();
        if (value.startsWith(PREFIX_NAME)) {
            String timeout = statement.substring(PREFIX_NAME.length());
            if (isTimeoutFormatValid(timeout)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTimeoutFormatValid(String timeout) {
        return timeout.matches(TIMEOUT_PATTERN);
    }

    public void setTimeout(String timeout) {
        if (isTimeoutFormatValid(timeout)) {
            this.timeout = timeout;
        }
    }

    @Override
    public String toString() {
        return PREFIX_NAME + timeout;
    }

}
