/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VPrincipal.java
 *
 * Created on 27-ene-2011, 10:31:24
 */

package gui;


/**
 *
 * @author basesdatos
 */
public class VPrincipal extends javax.swing.JFrame {
  
    aplication.FachadaAplicacion fa;
    
    /** Creates new form VPrincipal */
    public VPrincipal(aplication.FachadaAplicacion fa) {
        this.fa=fa;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGestionPagos = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        ButtonReservar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        registrarinfrBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aparcamientos USC");
        setName("vPrincipal"); // NOI18N
        setResizable(false);

        btnGestionPagos.setText("Historial de Pagos");
        btnGestionPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionPagosActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        ButtonReservar.setText("Reservar/Aparcar");
        ButtonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonReservarActionPerformed(evt);
            }
        });

        jButton1.setText("Anadir Plaza");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar Plaza");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        registrarinfrBtn.setText("Registrar infracción");
        registrarinfrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarinfrBtnActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGestionPagos)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonReservar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registrarinfrBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(438, Short.MAX_VALUE)
                .addComponent(registrarinfrBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnGestionPagos)
                    .addComponent(ButtonReservar)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Biblioteca Informática");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionPagosActionPerformed
        fa.historialPagos();
    }//GEN-LAST:event_btnGestionPagosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit (0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void ButtonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonReservarActionPerformed
        VAviso aviso;
        if(fa.getUsuario().getNumeroInfracciones()>=5){
            aviso=new VAviso(this,true, "Tienes demasiadas infracciones como para reservar o aparcar.");
            aviso.setVisible(true);
            return;
        }
        VReservarAparcar vreservar=new VReservarAparcar(this,true, fa);
        vreservar.setVisible(true);
    }//GEN-LAST:event_ButtonReservarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        VAnadir vanadir=new VAnadir(this,true, fa);
        vanadir.setVisible(true);
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        VEliminar veliminar=new VEliminar(this,true, fa);
        veliminar.setVisible(true);
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void registrarinfrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarinfrBtnActionPerformed
        VRegistroInfracciones vregistroinfracciones = new VRegistroInfracciones(fa);
        vregistroinfracciones.setVisible(true);
    }//GEN-LAST:event_registrarinfrBtnActionPerformed
    
    /**
    * @param args the command line argumentss
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonReservar;
    private javax.swing.JButton btnGestionPagos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton registrarinfrBtn;
    // End of variables declaration//GEN-END:variables

}
