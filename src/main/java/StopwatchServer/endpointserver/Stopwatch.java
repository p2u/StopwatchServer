/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.endpointserver;

import StopwatchServer.protocol.Statement;
import StopwatchServer.protocol.TimeStatement;
import StopwatchServer.protocol.TimeoutStatement;
import javax.websocket.Session;

/**
 *
 * @author petr
 */
public class Stopwatch {

    private static final String ID = "id";

    private Session session;

    public static Stopwatch getInstance(Session session) {
        return new Stopwatch(session);
    }

    public Stopwatch(Session session) {
        setSession(session);
    }

    private void setSession(Session session) {
        this.session = session;
    }

    public String getID() {
        return session.getPathParameters().get(ID);
    }

    public void send(Statement statement) {
        StopwatchEndpoint.send(session, statement);
    }

    public void onMessage(Statement statement) {
        if (statement instanceof TimeStatement) {
            // TODO Save time
        } else if (statement instanceof TimeoutStatement) {
            // TODO Timeout
        }
    }
    
    public void ping() {
        StopwatchEndpoint.ping(session);
    }
    
    public void close() {
        StopwatchEndpoint.close(session);
    }
}
