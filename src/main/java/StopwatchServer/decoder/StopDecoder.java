/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.decoder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import StopwatchServer.protocol.StopStatement;

/**
 *
 * @author petr
 */
public class StopDecoder implements Decoder.Text<StopStatement>{

    @Override
    public StopStatement decode(String statement) throws DecodeException {
        return StopStatement.getInstance(statement);
    }

    @Override
    public boolean willDecode(String statement) {
        return StopStatement.willDecode(statement);
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }
    
}
