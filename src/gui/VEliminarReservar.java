/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplication.FachadaAplicacion;
import javax.swing.JTable;

/**
 *
 * @author alumnogreibd
 */
public class VEliminarReservar extends javax.swing.JDialog {

    /**
     * Creates new form VEliminarReservar
     */
    
    private FachadaAplicacion fa;
        
    public VEliminarReservar(javax.swing.JDialog parent, boolean modal, FachadaAplicacion fa) {
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

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        aparcamientoIN = new javax.swing.JTextPane();
        vehiculoIN = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        codigoIN = new javax.swing.JTextPane();
        buscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPlazasReserva = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Aparcamiento:");

        jLabel2.setText("Tipo de Vehiculo:");

        jScrollPane1.setViewportView(aparcamientoIN);

        vehiculoIN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coche", "Moto", "Vehiculo Grande" }));
        vehiculoIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiculoINActionPerformed(evt);
            }
        });

        jLabel3.setText("Codigo:");

        jScrollPane2.setViewportView(codigoIN);

        buscar.setText("BUSCAR");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        tablaPlazasReserva.setModel(new ModeloTablaPlazaReserva());
        jScrollPane3.setViewportView(tablaPlazasReserva);

        eliminar.setText("ELIMINAR");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        jButton4.setText("Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(333, 333, 333))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(vehiculoIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(vehiculoIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(28, 28, 28)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vehiculoINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiculoINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehiculoINActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        //reinicio de la transacción:
        this.fa.getFbd().getDaoUsuarios().commitTransaction(); //acaba la transacción anterior
        this.fa.getFbd().getDaoUsuarios().beginTransactionSerializable(); //inicia una nueva transaccion
       
        
        ModeloTablaPlazaReserva m;

        m=(ModeloTablaPlazaReserva) tablaPlazasReserva.getModel();
        
        VAviso aviso;
        
        String aparc = aparcamientoIN.getText();

        

        String plaza = (String) vehiculoIN.getSelectedItem();
        
        String tipoPlaza = "C";
        
        switch (plaza) {
            case "Coche":
                tipoPlaza="C";
                break;
            case "Moto":
                tipoPlaza="M";
                break;
            case "Vehiculo Grande":
                tipoPlaza="G";
                break;
            default:
                    break;
        }
        
        String codeS = codigoIN.getText();
        
        int code=-1;
        
        if(!codeS.isEmpty()){
            try{   
            code = Integer.parseInt(codeS);       
            }catch (Exception e){
                aviso=new VAviso(this,false, "El codigo de la plaza debe ser un numero");
                aviso.setVisible(true);
                return;
            }
        }
         
        m.setFilas(fa.obtenerPlazasReserva(aparc,code,tipoPlaza, false));
        if (m.getRowCount() > 0) {
            tablaPlazasReserva.setRowSelectionInterval(0, 0);
        }      
        
    }//GEN-LAST:event_buscarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        
        VAviso aviso;
        
        int fila=tablaPlazasReserva.getSelectedRow();
        if(fila==-1){
            aviso=new VAviso(this,true, "No has seleccionado ninguna plaza");
            aviso.setVisible(true);
            return;
        }

        String ap= tablaPlazasReserva.getValueAt(fila, 0).toString();
        int plaza= Integer.parseInt(tablaPlazasReserva.getValueAt (fila, 1).toString());
        String tipo= tablaPlazasReserva.getValueAt(fila, 2).toString();
        
        VConfirmarER2 vconf;
        
        vconf=new VConfirmarER2(this,true, fa, ap, plaza, tipo);        
        vconf.setVisible(true);
        
        //cada vez que se elimina un plaza y se cierra la ventana de confirmación, entonces se reinicia la tabla de búsquedas
        tablaPlazasReserva.setModel(new ModeloTablaPlazaReserva());
        
    }//GEN-LAST:event_eliminarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        this.fa.getFbd().getDaoUsuarios().commitTransaction();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane aparcamientoIN;
    private javax.swing.JButton buscar;
    private javax.swing.JTextPane codigoIN;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaPlazasReserva;
    private javax.swing.JComboBox<String> vehiculoIN;
    // End of variables declaration//GEN-END:variables
}
