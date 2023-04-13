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
        addUsuario = new javax.swing.JButton();
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

        ButtonReservar.setText("Reservar");
        ButtonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonReservarActionPerformed(evt);
            }
        });

        addUsuario.setText("Añadir usuario");
        addUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUsuarioActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGestionPagos)
                .addGap(18, 18, 18)
                .addComponent(ButtonReservar)
                .addGap(18, 18, 18)
                .addComponent(addUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(479, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnGestionPagos)
                    .addComponent(ButtonReservar)
                    .addComponent(addUsuario))
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
        VReservar vreservar=new VReservar(this,true, fa);
        vreservar.setVisible(true);
    }//GEN-LAST:event_ButtonReservarActionPerformed

    private void addUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addUsuarioActionPerformed
    
    /**
    * @param args the command line argumentss
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonReservar;
    private javax.swing.JButton addUsuario;
    private javax.swing.JButton btnGestionPagos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

}
