/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.decoder;

import StopwatchServer.protocol.TimeStatement;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author petr
 */
public class TimeDecoder implements Decoder.Text<TimeStatement>{

    @Override
    public TimeStatement decode(String statement) throws DecodeException {
        return TimeStatement.getInstance(statement);
    }

    @Override
    public boolean willDecode(String statement) {
        return TimeStatement.willDecode(statement);
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }
    
}
