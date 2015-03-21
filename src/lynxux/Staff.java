/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lynxux;

/**
 *
 * @author rajika
 */
import java.awt.HeadlessException;
import java.sql.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.DTDConstants;
public class Staff extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    String type = null;
    /**
     * Creates new form Staff
     */
    public Staff() {
        initComponents();
        loggedInAs.setText("You're logged in as "+ Login.loggedInUser + "(" + getEmpName() + ")");
        addToTable(); //cancel leaves
        addToTable2();
        pendingAn(); //pending annual leaves
        pendingCas(); //pending casual leaves
        checkAnnLeaves(); //available annual leaves
        checkCasLeaves(); //available casual leaves
        
        this.setLocationRelativeTo(null); //centers the JFrame
    }
    private String getEmpName(){
        String name = null;
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Name from Employees where EmpId=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            name = rst.getString("Name");
            
        }
        catch(Exception e){
            
        }
        return name;
    }
    public void checkCasLeaves(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Casual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            casLeaves.setText(rst.getString("count"));
        }
        catch(Exception e){
        
        }
    }
    public void checkAnnLeaves(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Annual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            
            annLeaves.setText(rst.getString("count"));
        }
        catch(Exception e){
        
        }
    }
    public void pendingCas(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select EmpId from RequestedLeaves where Type='Casual'";
        try{
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            pendingCas.setText(rst.getString("EmpId"));
            
        }
        catch(Exception e){
        
        }
    }
    public void pendingAn(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select EmpId from RequestedLeaves where Type='Annual'";
        try{
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            pendingAnn.setText(rst.getString("EmpId"));
        }
        catch(Exception e){
        
        }
    }
    public void limitCas(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Casual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            if(Integer.parseInt(rst.getString("count"))>5){
                reason.setVisible(true);
            }
            else{
                reason.setVisible(false);
            }
        }
        catch(Exception e){
        
        }
    }
    public void limitAnn(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select count(EmpId) as count from RequestedLeaves where Type='Annual' and Empid=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, Login.loggedInUser);
            rst = pst.executeQuery();
            rst.next();
            if(Integer.parseInt(rst.getString("count"))>20){
                reason.setVisible(true);
            }
            else{
                reason.setVisible(false);
            }
        }
        catch(Exception e){
        
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

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        radiocas = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        radioann = new javax.swing.JRadioButton();
        submit = new javax.swing.JButton();
        dateX = new datechooser.beans.DateChooserCombo();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        casLeaves = new javax.swing.JLabel();
        annLeaves = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pendingCas = new javax.swing.JLabel();
        pendingAnn = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        loggedInAs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Date");

        jLabel3.setText("Type");

        jLabel4.setText("Reason");

        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        radiocas.setText("Casual");
        radiocas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiocasMouseClicked(evt);
            }
        });
        radiocas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiocasActionPerformed(evt);
            }
        });

        reason.setColumns(20);
        reason.setRows(5);
        jScrollPane1.setViewportView(reason);

        radioann.setText("Annual");
        radioann.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioannMouseClicked(evt);
            }
        });
        radioann.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioannActionPerformed(evt);
            }
        });

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        dateX.setFormat(1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radiocas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioann)))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(submit)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(radiocas)
                    .addComponent(radioann))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(submit)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Request Leave", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Type", "Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cancel Leave", jPanel3);

        jLabel1.setText("Casual Leaves");

        jLabel5.setText("Annual Leaves");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(casLeaves, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(annLeaves, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(118, 118, 118))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(casLeaves, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(annLeaves, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Availability", jPanel2);

        jLabel6.setText("Casual Leaves");

        jLabel7.setText("Annual Leaves");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(108, 108, 108)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pendingCas, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(pendingAnn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pendingCas, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(pendingAnn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pending", jPanel4);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Status", "Type"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jTabbedPane2.addTab("", jScrollPane3);

        jTabbedPane1.addTab("Notifications", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loggedInAs, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(loggedInAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        super.dispose();
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void radiocasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiocasActionPerformed
        // TODO add your handling code here:
        type = "Casual";
    }//GEN-LAST:event_radiocasActionPerformed

    private void radioannActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioannActionPerformed
        // TODO add your handling code here:
        type = "Annual";
    }//GEN-LAST:event_radioannActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String sql = "Insert into RequestedLeaves (EmpId,Date,Type,Reason)"+"values(?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,Login.loggedInUser);
            pst.setString(2,date.getText());
            pst.setString(3,type);
            pst.setString(4,reason.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Your submission is successfull");
            super.dispose();
            Staff w = new Staff();
            w.setVisible(true);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_submitActionPerformed
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void radiocasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiocasMouseClicked
        // TODO add your handling code here:
        limitCas();
    }//GEN-LAST:event_radiocasMouseClicked

    private void radioannMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioannMouseClicked
        // TODO add your handling code here:
        limitAnn();
    }//GEN-LAST:event_radioannMouseClicked

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null,"Do you want to cancel you request","Confirm",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            int row = jTable1.getSelectedRow();
            
            String dateX = jTable1.getModel().getValueAt(row,0).toString();
            
            try{
                conn = MySqlConnect.ConnectDB();
                String sql = "Delete from RequestedLeaves where EmpId=? AND Date=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,Login.loggedInUser);
                pst.setString(2,dateX);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Request cancelled");
                this.dispose();
                Staff st = new Staff();
                st.setVisible(true);
            }
            catch(Exception e){
                
            }
            
        }
        else if(result == JOptionPane.NO_OPTION){
            
        }
        
    }//GEN-LAST:event_jTable1MouseReleased
    public void addToTable(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select * from RequestedLeaves";
        String date;
        String type;
        String reason;
        try{
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            while(rst.next()){
                date = rst.getString("Date");
                type = rst.getString("Type");
                reason = rst.getString("Reason");
                
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{date,type,reason});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void addToTable2(){
        conn = MySqlConnect.ConnectDB();
        String sql = "Select Date,Status,Type from DefaultLeaves";
        String date;
        String status;
        String type;
        try{
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            while(rst.next()){
                date = rst.getString("Date");
                status = rst.getString("Status");
                type = rst.getString("Type");
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.addRow(new Object[]{date,status,type});
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public String[] loadForCombo2(){
        String [] emps = null;
        Stack st = new Stack();
        conn = MySqlConnect.ConnectDB();
        String sql2 = "Select EmpId from Employees";
        try{
            pst = conn.prepareStatement(sql2);
            rst = pst.executeQuery();
            rst.next();
            int a = 0;
            while(rst.next()){
                st.push(rst.getString("EmpId"));
            }
            int size = st.size();
            JOptionPane.showMessageDialog(null, st.size());
            emps = new String[size];
            int x = 0;
            while(st.empty()){
                emps[x] = (String)st.pop();
                x++;
            }
        }
        catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return emps;
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel annLeaves;
    private javax.swing.JLabel casLeaves;
    private javax.swing.JTextField date;
    private datechooser.beans.DateChooserCombo dateX;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel loggedInAs;
    private javax.swing.JLabel pendingAnn;
    private javax.swing.JLabel pendingCas;
    private javax.swing.JRadioButton radioann;
    private javax.swing.JRadioButton radiocas;
    private javax.swing.JTextArea reason;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
