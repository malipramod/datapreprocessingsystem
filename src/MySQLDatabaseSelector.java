
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramod
 */
public class MySQLDatabaseSelector extends javax.swing.JFrame {

    /**
     * Creates new form MySQLDatabaseSelector
     */
    public MySQLDatabaseSelector() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbSelectDatabaseMySQL = new javax.swing.JComboBox();
        cmbSelectTableMySQL = new javax.swing.JComboBox();
        btnConnectMySQL = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMySQLPassword1 = new javax.swing.JTextField();
        txtMySQLUserName = new javax.swing.JTextField();
        btnContinue1 = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCancel1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbSelectDatabaseMySQL.setToolTipText("Select Database");
        cmbSelectDatabaseMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectDatabaseMySQLActionPerformed(evt);
            }
        });

        cmbSelectTableMySQL.setToolTipText("Select Table");
        cmbSelectTableMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectTableMySQLActionPerformed(evt);
            }
        });

        btnConnectMySQL.setText("Connect");
        btnConnectMySQL.setToolTipText("Click to Continue");
        btnConnectMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectMySQLActionPerformed(evt);
            }
        });

        jLabel6.setText("Password");
        jLabel6.setToolTipText("");

        jLabel7.setText("User Name");
        jLabel7.setToolTipText("");

        btnContinue1.setText("Continue");
        btnContinue1.setToolTipText("Click to Continue");
        btnContinue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinue1ActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("Click to Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel1.setText("My SQL");

        btnCancel1.setText("Choose SQL Server");
        btnCancel1.setToolTipText("Click to Cancel");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Database");

        jLabel3.setText("Select Table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbSelectTableMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(102, 102, 102)
                            .addComponent(jLabel1)
                            .addGap(128, 128, 128))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCancel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnContinue1)
                                        .addGap(15, 15, 15)
                                        .addComponent(btnCancel))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMySQLUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtMySQLPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(btnConnectMySQL)))
                                .addGap(7, 7, 7))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSelectDatabaseMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtMySQLUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtMySQLPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnConnectMySQL)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSelectDatabaseMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbSelectTableMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinue1)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18)
                .addComponent(btnCancel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSelectDatabaseMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectDatabaseMySQLActionPerformed
        String[] resuslt = new String[100];
        String databaseName = (String) cmbSelectDatabaseMySQL.getSelectedItem();
        if (!cmbSelectDatabaseMySQL.getSelectedItem().equals("")) {
            DataProviderClass dp = new DataProviderClass();
            String userName,password;
            userName=txtMySQLUserName.getText();
            password=txtMySQLPassword1.getText();
            try {
                resuslt = dp.selectTableFromMySQL(databaseName,userName,password);
                cmbSelectTableMySQL.removeAllItems();
                for (int i = 0; i < resuslt.length; i++) {
                    if (resuslt[i] == null) {
                    } else {
                        cmbSelectTableMySQL.addItem(resuslt[i]);
                    }
                }
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Select Database Name from ComboBox", "Exception", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_cmbSelectDatabaseMySQLActionPerformed

    private void cmbSelectTableMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectTableMySQLActionPerformed

    }//GEN-LAST:event_cmbSelectTableMySQLActionPerformed

    private void btnConnectMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectMySQLActionPerformed
        if (txtMySQLPassword1.getText().equals("") && txtMySQLUserName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Insert User Name and Password");
        } else {
            String[] result = new String[100];
            String userName, password;
            userName = txtMySQLUserName.getText();
            password = txtMySQLPassword1.getText();
          //  cmbSelectDatabaseMySQL.removeAllItems();
            cmbSelectTableMySQL.removeAllItems();
            DataProviderClass dpc = new DataProviderClass();
            try {
                result=dpc.selectDatabaseFromMySQL(userName, password);
                
                for (int i = 0; i < result.length; i++) {
                    if (result[i] == null) {
                    } else { 
                        cmbSelectDatabaseMySQL.addItem(result[i]);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }
    }//GEN-LAST:event_btnConnectMySQLActionPerformed

    private void btnContinue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinue1ActionPerformed
         if ((!cmbSelectDatabaseMySQL.getSelectedItem().toString().equals("")) || (!cmbSelectTableMySQL.getSelectedItem().toString().equals("")))
         {
                
             StateManagerClass stm=new StateManagerClass();
             stm.setDatabase(cmbSelectDatabaseMySQL.getSelectedItem().toString());
              stm.setTableName(cmbSelectTableMySQL.getSelectedItem().toString());
              stm.setDatabaseType(1);
             new Home().setVisible(true);
             setVisible(false);
         }
         else
         {
             JOptionPane.showMessageDialog(this, "Please select Database and Table");
         }
    }//GEN-LAST:event_btnContinue1ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setVisible(false);
        new Home().setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        setVisible(false);
        new DataProviderSelector().setVisible(true);
    }//GEN-LAST:event_btnCancel1ActionPerformed

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
            java.util.logging.Logger.getLogger(MySQLDatabaseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MySQLDatabaseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MySQLDatabaseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MySQLDatabaseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MySQLDatabaseSelector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnConnectMySQL;
    private javax.swing.JButton btnContinue1;
    private javax.swing.JComboBox cmbSelectDatabaseMySQL;
    private javax.swing.JComboBox cmbSelectTableMySQL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtMySQLPassword1;
    private javax.swing.JTextField txtMySQLUserName;
    // End of variables declaration//GEN-END:variables
}
