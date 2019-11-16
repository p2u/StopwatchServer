/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author petr
 */
public class StopwatchLogs {

    private static final Map<String, StringBuffer> logs = Collections.synchronizedMap(new HashMap<>());

    public static void add(String id) {
        logs.put(id, new StringBuffer());
    }

    public static void log(String id, String msg) {
        if (!logs.containsKey(id)) {
            add(id);
        }
        logs.get(id).append(msg).append(System.lineSeparator());
    }

    public static String get(String id) {
        if (id == null || !logs.containsKey(id)) {
            return "";
        }
        return logs.get(id).toString();

    }

    public static void remove(String id) {
        logs.remove(id);
    }

    public static void removeAll() {
        for (String id : logs.keySet()) {
            remove(id);
        }
    }

}
