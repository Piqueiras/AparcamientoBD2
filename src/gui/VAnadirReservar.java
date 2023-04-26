/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplication.FachadaAplicacion;
import javax.swing.JOptionPane;

/**
 *
 * @author alumnogreibd
 */
public class VAnadirReservar extends javax.swing.JDialog {

    /**
     * Creates new form VAnadirReservar
     */
    
    private FachadaAplicacion fa;
    
    public VAnadirReservar(javax.swing.JDialog parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fa=fa;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        aparcamientoIN = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        codigoIN = new javax.swing.JTextPane();
        vehiculoIN = new javax.swing.JComboBox<String>();
        volver = new javax.swing.JButton();
        reservar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Aparcamiento:");

        jLabel2.setText("Tipo de Vehiculo:");

        jLabel3.setText("Codigo de Plaza:");

        jScrollPane1.setViewportView(aparcamientoIN);

        jScrollPane2.setViewportView(codigoIN);

        vehiculoIN.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Coche", "Moto", "Vehiculo Grande" }));
        vehiculoIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiculoINActionPerformed(evt);
            }
        });

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        reservar.setText("ANADIR");
        reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(vehiculoIN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reservar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(vehiculoIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(reservar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
                        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void vehiculoINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiculoINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehiculoINActionPerformed

    private void reservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarActionPerformed
        
        VAviso aviso;
        
        String aparc = aparcamientoIN.getText();

        if(aparc.isEmpty()){
            aviso=new VAviso(this,true, "Debe seleccionar el aparcamiento");
            aviso.setVisible(true);
            return;
        }
        //FALTA COMRPOBAR QUE EXISTA
        

        String plaza = (String) vehiculoIN.getSelectedItem();
        
        String codeS = codigoIN.getText();

        if(codeS.isEmpty()){
            aviso=new VAviso(this,true, "Debe introducir el codigo de la plaza");
            aviso.setVisible(true);
            return;
        }
        
        int code;
        
        try{   
        code = Integer.parseInt(codeS);       
        }catch (Exception e){
            aviso=new VAviso(this,true, "El codigo de la plaza debe ser un numero");
            aviso.setVisible(true);
            return;
        }
        
        //FALTA COMRPOBAR QUE NO EXISTA
       
        boolean exito=false;
         
        exito=fa.anadirPlazaReserva(aparc, code, plaza);
        
        if(exito){
            JOptionPane.showMessageDialog(this,"Plaza insertada con éxito.");
            this.dispose();
            return;
        }
        else{
            aviso=new VAviso(this,true, "ERROR: revise que el aparcamiento exista o que el número de plaza no esté siendo utilizado");
            aviso.setVisible(true);
            return;
        }
        
    }//GEN-LAST:event_reservarActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane aparcamientoIN;
    private javax.swing.JTextPane codigoIN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reservar;
    private javax.swing.JComboBox<String> vehiculoIN;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
