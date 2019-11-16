/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer.decoder;

import StopwatchServer.protocol.Statement;
import StopwatchServer.protocol.StartStatement;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class StartDecoder implements Decoder.Text<Statement> {

    @Override
    public Statement decode(String statement) throws DecodeException {
        return StartStatement.getInstance(statement);
    }

    @Override
    public boolean willDecode(String statement) {
        return StartStatement.willDecode(statement);
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

}
