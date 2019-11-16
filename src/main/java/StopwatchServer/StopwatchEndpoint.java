/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer;

import StopwatchServer.decoder.CustomDecoder;
import StopwatchServer.decoder.ResetDecoder;
import StopwatchServer.decoder.StartDecoder;
import StopwatchServer.decoder.StartedDecoder;
import StopwatchServer.decoder.TimeDecoder;
import StopwatchServer.decoder.TimeoutDecoder;
import StopwatchServer.protocol.Statement;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import StopwatchServer.decoder.LabelDecoder;
import StopwatchServer.decoder.StopDecoder;

/**
 *
 * @author petr
 */
@ServerEndpoint(value = "/stopwatch", decoders = {ResetDecoder.class, StartDecoder.class, StartedDecoder.class, StopDecoder.class, TimeDecoder.class, TimeoutDecoder.class, LabelDecoder.class, CustomDecoder.class})
public class StopwatchEndpoint {

    public static final String STOPWATCH_PROTOCOL = Logger.GLOBAL_LOGGER_NAME + ".StopwatchProtocol";
    private static final String VALID_ID = "isValidID";

    @OnOpen
    public void onOpen(Session session) {
        Logger.getLogger(STOPWATCH_PROTOCOL).log(Level.INFO, "Connected: " + getID(session), getID(session));
        validateID(session);
        if (hasValidID(session)) {
            Stopwatch stopwatch = Stopwatch.getInstance(session);
            StopwatchProxy.add(stopwatch);
        } else {
            close(session);
        }
    }

    private static String getID(Session session) {
        final String ID = "id";
        return session.getPathParameters().get(ID);
    }

    private void validateID(Session session) {
        if (getID(session) == null || getID(session).equals("")) {
            setValidID(session, false);
        } else {
            boolean contains = StopwatchProxy.contains(getID(session));
            setValidID(session, !contains);
        }
    }

    private void setValidID(Session session, boolean isUnique) {
        session.getUserProperties().put(VALID_ID, isUnique);
    }

    private boolean hasValidID(Session session) {
        return (boolean) session.getUserProperties().get(VALID_ID);
    }

    @OnClose
    public void onClose(Session session) {
        Logger.getLogger(STOPWATCH_PROTOCOL).log(Level.INFO, "Disconnected: " + getID(session), getID(session));
        if (hasValidID(session)) {
            Stopwatch stopwatch = StopwatchProxy.get(getID(session));
            StopwatchProxy.remove(stopwatch);
            StopwatchLogs.remove(getID(session));
        }
    }

    @OnMessage
    public void onMessage(Session session, Statement statement) {
        Logger.getLogger(STOPWATCH_PROTOCOL).log(Level.INFO, "> (ID:" + getID(session) + ") " + statement.toString(), getID(session));
        Stopwatch stopwatch = StopwatchProxy.get(getID(session));
        stopwatch.onMessage(statement);
    }

    @OnMessage
    public void onMessage(Session session, PongMessage pong) {
        Logger.getLogger(StopwatchEndpoint.STOPWATCH_PROTOCOL).log(Level.INFO, "> (ID:" + getID(session) + ") PONG", getID(session));
    }

    public static void send(Session session, Statement statement) {
        try {
            Logger.getLogger(StopwatchEndpoint.STOPWATCH_PROTOCOL).log(Level.INFO, "< (ID:" + getID(session) + ") " + statement.toString(), getID(session));
            session.getBasicRemote().sendText(statement.toString());
        } catch (IOException ex) {
            Logger.getLogger(Stopwatch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ping(Session session) {
        try {
            Logger.getLogger(StopwatchEndpoint.STOPWATCH_PROTOCOL).log(Level.INFO, "< (ID:" + getID(session) + ") PING", getID(session));
            session.getBasicRemote().sendPing(ByteBuffer.allocate(0));
        } catch (IOException ex) {
            Logger.getLogger(StopwatchEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(StopwatchEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(Session session) {
        try {
            session.close();
        } catch (IOException ex) {
            Logger.getLogger(StopwatchEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
