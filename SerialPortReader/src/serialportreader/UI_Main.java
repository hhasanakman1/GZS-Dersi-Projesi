package serialportreader;

import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
/**
 * @author TA
 */
public class UI_Main extends javax.swing.JFrame implements SerialPortEventListener {

    Connection con=null;
    PreparedStatement prs=null;
    ResultSet rs=null;
    public static void setComPortName(String aComPortName) {
        comPortName = aComPortName;
    }

    String inputLine;
    String[] readArray = new String[100];
    SerialPort serialPort;
    int i = 0;
    private static String comPortName = "COM3";//BU BİLGİYİ FORM DAN DA ALABİLİRİZ

    public static String getComPortName() {
        return comPortName;
    }
    private static final String PORT_NAMES[] = {getComPortName(),};

    private BufferedReader input;
    private static OutputStream output;
    private static int TIME_OUT = 2000;
    private static int DATA_RATE = 9600;

    public void initialize() {
        System.setProperty("gnu.io.rxtx.SerialPorts", getComPortName());        
        CommPortIdentifier portId = null;        
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    System.out.println(portName);
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            
            JOptionPane.showMessageDialog(null," PORTUNA BAĞLI CİHAZ YOK!","HATA",JOptionPane.ERROR_MESSAGE);
            System.out.println("PORTA BAĞLI CİHAZ YOK!");
            return;
        }
System.out.println(portId);
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
           serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
DefaultListModel model = new DefaultListModel();  
    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                if (input.ready() == true) {
                    //**********************************************
                    //ARDUINO dan SERİ PORTTAN GELEN VERİ BURADAN KONTROL EDİLİR
                    //**********************************************
                    con=veritabani.baglan();
                    
                    inputLine = input.readLine();
                    String [] deger = inputLine.split(" ");
                    String sicaklik = deger[0];
                    String nem = deger[1];
                    String topraknem = deger[2];
                    System.out.println(inputLine);
                    Object node = "Sicaklik: "+deger[0]+ " Nem: "+deger[1]+" ToprakNem: "+deger[2];
                    model.addElement(node);
                    
                    System.out.println(inputLine);
                    
                    
                    con=veritabani.baglan();
                        Date now = new Date();
                        System.out.println(now.toString()); 
                        String Sql="INSERT INTO veri(Sicaklik,Nem,Toprak_Nem,Tarih) VALUES('"+sicaklik+"','"+nem+"','"+topraknem+"','"+now.toString()+"')";
                    try{
                        prs=con.prepareStatement(Sql);
                       
                       
                        prs.executeUpdate(Sql);   
                        
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }
        }

    }       catch (IOException ex) {
                Logger.getLogger(UI_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
    }
    public UI_Main() {
        initComponents();
        
        
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBoundRate = new javax.swing.JTextField();
        txtComPortName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        txtTimeOut = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtReadData = new java.awt.TextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        listeleBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SERİ PORT VERİ OKU");
        setBackground(new java.awt.Color(255, 153, 153));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        txtBoundRate.setText("9600");
        txtBoundRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBoundRateActionPerformed(evt);
            }
        });

        txtComPortName.setText("COM3");

        jLabel1.setText("PORT İSMİ");

        jLabel2.setText("BOUND RATE");

        btnConnect.setText("Bağlan");
        btnConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        txtTimeOut.setText("2000");

        jLabel3.setText("TIME OUT");

        txtReadData.setMinimumSize(new java.awt.Dimension(20, 80));

        jLabel4.setText("Okunan Veri");

        jList1.setModel(model);
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Durdur");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listeleBtn.setText("Listele");
        listeleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeleBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtComPortName, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(txtReadData, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBoundRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(txtTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(listeleBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConnect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBoundRate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtReadData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listeleBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        setComPortName(txtComPortName.getText());
        DATA_RATE = Integer.parseInt(txtBoundRate.getText());
        TIME_OUT = Integer.parseInt(txtTimeOut.getText());
        btnConnect.setText("BAĞLAN");
  
        initialize();
 
        txtReadData.setText(txtComPortName.getText()+" PORT AÇILDI\n");
        txtReadData.setText(txtReadData.getText() + "Veri Bekleniyor...\n");
        model.clear();

    }//GEN-LAST:event_btnConnectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        close();
        txtReadData.setText(txtReadData.getText() + "KAPATILIYOR...\n");
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBoundRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBoundRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBoundRateActionPerformed

    private void listeleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeleBtnActionPerformed
       bilgiListele bl=new bilgiListele();
       bl.setVisible(true);
       
    }//GEN-LAST:event_listeleBtnActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Main().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listeleBtn;
    private javax.swing.JTextField txtBoundRate;
    private javax.swing.JTextField txtComPortName;
    private java.awt.TextArea txtReadData;
    private javax.swing.JTextField txtTimeOut;
    // End of variables declaration//GEN-END:variables

}
