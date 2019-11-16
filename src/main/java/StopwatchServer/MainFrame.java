/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StopwatchServer;

import StopwatchServer.endpointserver.StopwatchProxyListener;
import StopwatchServer.endpointserver.StopwatchProxy;
import StopwatchServer.endpointserver.StopwatchLogs;
import StopwatchServer.endpointserver.StopwatchEndpoint;
import StopwatchServer.endpointserver.Stopwatch;
import StopwatchServer.protocol.CustomStatement;
import StopwatchServer.protocol.ResetStatement;
import StopwatchServer.protocol.StartStatement;
import StopwatchServer.protocol.Statement;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerEndpoint;
import org.glassfish.tyrus.server.Server;
import StopwatchServer.protocol.LabelStatement;
import StopwatchServer.protocol.StartedStatement;
import StopwatchServer.protocol.StopStatement;
import StopwatchServer.protocol.TimeStatement;
import StopwatchServer.protocol.TimeoutStatement;

/**
 *
 * @author petr
 */
public class MainFrame extends javax.swing.JFrame {

    private Server server;
    private StringBuffer logBuffer;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        init();
    }

    private void init() {
        setLoggerHandler();
        updateClientUriTextField();
        updateClientList();
        addClientListListener();
    }

    private void addClientListListener() {
        StopwatchProxy.addListener(new StopwatchProxyListener() {
            @Override
            public void update() {
                updateClientList();
            }
        });
    }

    private void updateClientList() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (String id : StopwatchProxy.getIDs()) {
            model.addElement(id);
        }
        String actualValue = clientList.getSelectedValue();
        clientList.setModel(model);
        if (model.contains(actualValue)) {
            clientList.setSelectedValue(actualValue, true);
        } else {
            disableCmdButtons();
        }
    }

    private void setLoggerHandler() {
        removeLogHandlers();
        addMainLogHandler();
        addStopwatchProtocolLogHandler();
    }

    private void removeLogHandlers() throws SecurityException {
        Logger logger = Logger.getLogger("");
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
    }

    private void addMainLogHandler() throws SecurityException {
        logBuffer = new StringBuffer();
        Logger logger = Logger.getLogger("");
        Formatter formatter = new SimpleFormatter();
        logger.addHandler(new Handler() {
            @Override
            public void publish(LogRecord lr) {
                logBuffer.append(formatter.format(lr));
                logTextArea.setText(logBuffer.toString());
                logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void close() throws SecurityException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    private void addStopwatchProtocolLogHandler() {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.addHandler(new Handler() {
            @Override
            public void publish(LogRecord lr) {
                String clientId = lr.getParameters()[0].toString();
                StopwatchLogs.log(clientId, lr.getMessage());
                updateClientLogView(clientId);
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void close() throws SecurityException {
                StopwatchLogs.removeAll();
            }
        });
    }

    private void updateClientLogView(String clientId) {
        if (getClientID().equals(clientId)) {
            clientLogTextArea.setText(StopwatchLogs.get(getClientID()));
            clientLogTextArea.setCaretPosition(clientLogTextArea.getDocument().getLength());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainPanel = new javax.swing.JPanel();
        hostnameTextField = new javax.swing.JTextField();
        portTextField = new javax.swing.JTextField();
        contextTextField = new javax.swing.JTextField();
        bindButton = new javax.swing.JButton();
        unbindButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientList = new javax.swing.JList<>();
        timeTextField = new javax.swing.JTextField();
        stopButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        pingButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        clientLogTextArea = new javax.swing.JTextArea();
        hostnameLabel = new javax.swing.JLabel();
        portLabel = new javax.swing.JLabel();
        contextLabel = new javax.swing.JLabel();
        clientUriTextField = new javax.swing.JTextField();
        clientUriLabel = new javax.swing.JLabel();
        clientListLabel = new javax.swing.JLabel();
        startTimeoutButton = new javax.swing.JButton();
        customTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        clientLogLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        logPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stopwatch protocol test");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        hostnameTextField.setText("localhost");
        hostnameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hostnameTextFieldKeyReleased(evt);
            }
        });

        portTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        portTextField.setText("8025");
        portTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                portTextFieldKeyReleased(evt);
            }
        });

        contextTextField.setText("/robogames");
        contextTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contextTextFieldKeyReleased(evt);
            }
        });

        bindButton.setText("Bind");
        bindButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bindButtonActionPerformed(evt);
            }
        });

        unbindButton.setText("Unbind");
        unbindButton.setEnabled(false);
        unbindButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unbindButtonActionPerformed(evt);
            }
        });

        clientList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        clientList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                clientListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(clientList);

        timeTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        timeTextField.setText("300");
        timeTextField.setEnabled(false);
        timeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTextFieldActionPerformed(evt);
            }
        });

        stopButton.setText("stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        resetButton.setText("reset");
        resetButton.setEnabled(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        pingButton.setText("ping");
        pingButton.setEnabled(false);
        pingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pingButtonActionPerformed(evt);
            }
        });

        clientLogTextArea.setEditable(false);
        clientLogTextArea.setColumns(20);
        clientLogTextArea.setRows(5);
        jScrollPane3.setViewportView(clientLogTextArea);

        hostnameLabel.setText("hostname:");

        portLabel.setText("port:");

        contextLabel.setText("context:");

        clientUriTextField.setEditable(false);
        clientUriTextField.setText("ws://");

        clientUriLabel.setText("Client URI:");

        clientListLabel.setText("Client list:");

        startTimeoutButton.setText("start");
        startTimeoutButton.setEnabled(false);
        startTimeoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTimeoutButtonActionPerformed(evt);
            }
        });

        customTextField.setText("label xxx");
        customTextField.setEnabled(false);
        customTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTextFieldActionPerformed(evt);
            }
        });

        sendButton.setText("send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        clientLogLabel.setText("Client log:");

        closeButton.setText("close");
        closeButton.setEnabled(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resetButton)
                                        .addComponent(pingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stopButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(startTimeoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(timeTextField)))
                                    .addComponent(closeButton)))
                            .addComponent(clientListLabel)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(customTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(sendButton)))
                        .addGap(12, 12, 12)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientLogLabel)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(clientUriLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientUriTextField))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hostnameTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(contextTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addGap(10, 10, 10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bindButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unbindButton)))
                .addGap(12, 12, 12))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, pingButton, resetButton, sendButton, startTimeoutButton, stopButton});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostnameLabel)
                    .addComponent(portLabel)
                    .addComponent(contextLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contextTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bindButton)
                    .addComponent(unbindButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientUriLabel)
                    .addComponent(clientUriTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientListLabel)
                    .addComponent(clientLogLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addComponent(startTimeoutButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopButton)
                                .addGap(6, 6, 6)
                                .addComponent(pingButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton)))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {closeButton, pingButton, resetButton, sendButton, startTimeoutButton, stopButton});

        jTabbedPane1.addTab("Main", mainPanel);

        logPanel.setLayout(new java.awt.BorderLayout());

        logTextArea.setEditable(false);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        logPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Log", logPanel);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bindButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bindButtonActionPerformed
        startServer();
        disableBind();
    }//GEN-LAST:event_bindButtonActionPerformed

    private void disableBind() {
        enableBindViews(false);
    }

    private void enableBind() {
        enableBindViews(true);
    }

    private void enableBindViews(boolean enabled) {
        hostnameTextField.setEnabled(enabled);
        portTextField.setEnabled(enabled);
        contextTextField.setEnabled(enabled);
        bindButton.setEnabled(enabled);
        unbindButton.setEnabled(!enabled);
    }

    private void startServer() throws NumberFormatException {
        String hostname = hostnameTextField.getText();
        Integer port = Integer.parseInt(portTextField.getText());
        String context = contextTextField.getText();

        server = new Server(hostname, port, context, null, StopwatchEndpoint.class);
        try {
            server.start();
        } catch (DeploymentException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void unbindButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unbindButtonActionPerformed
        stopServer();
        enableBind();
    }//GEN-LAST:event_unbindButtonActionPerformed

    private void stopServer() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        Statement stop = StopStatement.getInstance();
        sendStatement(stop);
    }//GEN-LAST:event_stopButtonActionPerformed

    private void sendStatement(Statement statement) {
        Stopwatch stopwatch = StopwatchProxy.get(getClientID());
        stopwatch.send(statement);
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stopServer();
    }//GEN-LAST:event_formWindowClosing

    private void hostnameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hostnameTextFieldKeyReleased
        updateClientUriTextField();
    }//GEN-LAST:event_hostnameTextFieldKeyReleased

    private void portTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portTextFieldKeyReleased
        updateClientUriTextField();
    }//GEN-LAST:event_portTextFieldKeyReleased

    private void contextTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contextTextFieldKeyReleased
        updateClientUriTextField();
    }//GEN-LAST:event_contextTextFieldKeyReleased

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        sendStatement(ResetStatement.getInstance());
    }//GEN-LAST:event_resetButtonActionPerformed

    private void pingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pingButtonActionPerformed
        Stopwatch stopwatch = StopwatchProxy.get(getClientID());
        stopwatch.ping();
    }//GEN-LAST:event_pingButtonActionPerformed

    private void clientListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_clientListValueChanged
        if (getClientID() == null) {
            disableCmdButtons();
        } else {
            updateClientLogView(getClientID());
            enableCmdButtons();
        }
    }//GEN-LAST:event_clientListValueChanged

    private void startTimeoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTimeoutButtonActionPerformed
        startTimeoutButtonAction();
    }//GEN-LAST:event_startTimeoutButtonActionPerformed

    private void startTimeoutButtonAction() {
        String timeout = timeTextField.getText();
        if (StartStatement.isTimeoutFormatValid(timeout)) {
            StartStatement start = StartStatement.getInstance();
            start.setTimeout(timeout);
            sendStatement(start);
        } else {
            JOptionPane.showMessageDialog(this, "Timeout format is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendButtonAction();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void sendButtonAction() {
        String customText = customTextField.getText();
        Statement statement;
        if (TimeoutStatement.willDecode(customText)) {
            statement = TimeoutStatement.getInstance(customText);
        } else if (TimeStatement.willDecode(customText)) {
            statement = TimeStatement.getInstance(customText);
        } else if (StartedStatement.willDecode(customText)) {
            statement = StartedStatement.getInstance(customText);
        } else if (StartStatement.willDecode(customText)) {
            statement = StartStatement.getInstance(customText);
        } else if (ResetStatement.willDecode(customText)) {
            statement = ResetStatement.getInstance(customText);
        } else if (LabelStatement.willDecode(customText)) {
            statement = LabelStatement.getInstance(customText);
        } else {
            statement = CustomStatement.getInstance(customText);
        }
        sendStatement(statement);
    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        Stopwatch stopwatch = StopwatchProxy.get(getClientID());
        stopwatch.close();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void customTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTextFieldActionPerformed
        sendButtonAction();
    }//GEN-LAST:event_customTextFieldActionPerformed

    private void timeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTextFieldActionPerformed
        startTimeoutButtonAction();
    }//GEN-LAST:event_timeTextFieldActionPerformed

    private void updateClientUriTextField() {
        StringBuilder sb = new StringBuilder();
        String endpoint = StopwatchEndpoint.class.getAnnotation(ServerEndpoint.class).value();
        sb.append("ws://").append(hostnameTextField.getText()).append(":").append(portTextField.getText()).append(contextTextField.getText()).append(endpoint).append("?id={ID}");
        clientUriTextField.setText(sb.toString());
    }

    private String getClientID() {
        String clientId = clientList.getSelectedValue();
        if (clientId == null) {
            clientId = "";
        }
        return clientId;
    }

    private void enableCmdButtons() {
        enabledCmdButtons(true);
    }

    private void enabledCmdButtons(boolean enabled) {
        startTimeoutButton.setEnabled(enabled);
        timeTextField.setEnabled(enabled);
        stopButton.setEnabled(enabled);
        resetButton.setEnabled(enabled);
        pingButton.setEnabled(enabled);
        closeButton.setEnabled(enabled);
        customTextField.setEnabled(enabled);
        sendButton.setEnabled(enabled);
    }

    private void disableCmdButtons() {
        enabledCmdButtons(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bindButton;
    private javax.swing.JList<String> clientList;
    private javax.swing.JLabel clientListLabel;
    private javax.swing.JLabel clientLogLabel;
    private javax.swing.JTextArea clientLogTextArea;
    private javax.swing.JLabel clientUriLabel;
    private javax.swing.JTextField clientUriTextField;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel contextLabel;
    private javax.swing.JTextField contextTextField;
    private javax.swing.JTextField customTextField;
    private javax.swing.JLabel hostnameLabel;
    private javax.swing.JTextField hostnameTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel logPanel;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton pingButton;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton startTimeoutButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JTextField timeTextField;
    private javax.swing.JButton unbindButton;
    // End of variables declaration//GEN-END:variables
}
