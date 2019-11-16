/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author petr
 */
public class StopwatchProxy {

    private static final Map<String, Stopwatch> stopwatches = Collections.synchronizedMap(new HashMap<String, Stopwatch>());

    public static void add(Stopwatch stopwatch) {
        stopwatches.put(stopwatch.getID(), stopwatch);
        notifyListeners();
    }

    public static void remove(Stopwatch stopwatch) {
        if (contains(stopwatch)) {
            stopwatches.remove(stopwatch.getID());
            notifyListeners();
        }
    }

    public static boolean contains(Stopwatch stopwatch) {
        return contains(stopwatch.getID());
    }

    public static boolean contains(String id) {
        return stopwatches.containsKey(id);
    }

    public static void removeAll() {
        for (String id : stopwatches.keySet()) {
            stopwatches.remove(id);
        }
        notifyListeners();
    }

    public static Stopwatch get(String id) {
        return stopwatches.get(id);
    }

    public static Set<String> getIDs() {
        return stopwatches.keySet();
    }

    private static final Set<StopwatchProxyListener> listeners = Collections.synchronizedSet(new HashSet<StopwatchProxyListener>());

    public static void addListener(StopwatchProxyListener listener) {
        listeners.add(listener);
    }

    public static void removeListener(StopwatchProxyListener listener) {
        listeners.remove(listener);
    }

    private static void notifyListeners() {
        for (StopwatchProxyListener listener : listeners) {
            listener.update();
        }
    }
}
