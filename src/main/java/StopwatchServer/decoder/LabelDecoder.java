/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.decoder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import StopwatchServer.protocol.LabelStatement;

/**
 *
 * @author petr
 */
public class LabelDecoder implements Decoder.Text<LabelStatement>{

    @Override
    public LabelStatement decode(String statement) throws DecodeException {
        return LabelStatement.getInstance(statement);
    }

    @Override
    public boolean willDecode(String statement) {
        return LabelStatement.willDecode(statement);
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }
    
}
