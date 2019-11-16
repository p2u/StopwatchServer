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
public class TimeStatement implements Statement {

    private static final String NAME = "time";
    private static final String PREFIX_NAME = NAME + " ";
    private static final String TIME_PATTERN = "\\d+(\\.(\\d){1,3})?";

    private String time = "0";

    public static TimeStatement getInstance() {
        return new TimeStatement();
    }

    public static TimeStatement getInstance(String statement) {
        TimeStatement result = new TimeStatement();
        String value = statement.toLowerCase();
        if (value.startsWith(PREFIX_NAME)) {
            String time = value.substring(PREFIX_NAME.length());
            result.setTime(time);
        }
        return result;
    }

    public static boolean willDecode(String statement) {
        String value = statement.toLowerCase();
        if (value.startsWith(PREFIX_NAME)) {
            String time = statement.substring(PREFIX_NAME.length());
            if (isTimeFormatValid(time)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTimeFormatValid(String timeout) {
        return timeout.matches(TIME_PATTERN);
    }

    public void setTime(String time) {
        if (isTimeFormatValid(time)) {
            this.time = time;
        }
    }

    @Override
    public String toString() {
        return PREFIX_NAME + time;
    }

}
