/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplication.FachadaAplicacion;
import aplication.TipoPlaza;
import aplication.Usuario;
import aplication.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class VAnadirVehiculo extends javax.swing.JDialog {
    
    private FachadaAplicacion fa;
    
    public VAnadirVehiculo(java.awt.Dialog parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fa=fa;
        initComponents();
        errorText.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        descripcion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inMatricula = new javax.swing.JTextField();
        inMarca = new javax.swing.JTextField();
        inModelo = new javax.swing.JTextField();
        inAno = new javax.swing.JTextField();
        switchTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        addVehiculo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        inDNI = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        errorText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        descripcion.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        descripcion.setText("Añadir nuevo vehiculo");

        jLabel1.setText("Matrícula");

        jLabel2.setText("Marca");

        jLabel3.setText("Modelo");

        jLabel4.setText("Año");

        inMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inMarcaActionPerformed(evt);
            }
        });

        switchTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coche", "Moto", "Vehículo grande" }));

        jLabel5.setText("Tipo");

        addVehiculo.setText("Añadir");
        addVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehiculoActionPerformed(evt);
            }
        });

        jLabel6.setText("DNI");

        inDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inDNIActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablaUsuario.setModel(new ModeloTablaUsuarios());
        jScrollPane1.setViewportView(tablaUsuario);

        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setText("Error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(25, 25, 25)
                                    .addComponent(inMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(39, 39, 39)
                                    .addComponent(inAno))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(inModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(switchTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(addVehiculo))
                                .addComponent(jLabel5)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(descripcion)
                .addGap(354, 354, 354))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(inMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(inMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(switchTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addVehiculo)
                            .addComponent(errorText)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(inModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(inAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehiculoActionPerformed
        errorText.setVisible(false);
        String dni = "";
        if (tablaUsuario.getRowCount()>0) {
            dni = (String) tablaUsuario.getValueAt(0, 0);
        }
        if(dni.isEmpty()){
            errorText.setText("Debe introducir DNI");
            errorText.setVisible(true);
            return;
        }
        String matricula = inMatricula.getText();
        if(matricula.isEmpty()){
            errorText.setText("Debe introducir matricula");
            errorText.setVisible(true);
            return;
        }
        
        String marca = inMarca.getText();
        String modelo = inModelo.getText();
        String anoStr = inAno.getText();
        String tipoStr = (String) switchTipo.getSelectedItem();
        TipoPlaza tipo;
        
        switch (tipoStr) {
            case "Coche":
                tipo = TipoPlaza.C;
                break;
            case "Moto":
                tipo = TipoPlaza.M;
                break;
            default:
                tipo = TipoPlaza.G;
        }
        
        Integer ano = 0;
        
        if (!anoStr.isEmpty()) {
            ano = Integer.parseInt(anoStr);
        }
        
        
        if (ano<1900) {
            errorText.setText("Año debe ser >1900");
            errorText.setVisible(true);
            return;
        }
        
        Vehiculo v = new Vehiculo(matricula, tipo, marca, modelo, ano);
        fa.registrarVehiculo(dni, v);
    }//GEN-LAST:event_addVehiculoActionPerformed

    private void inDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inDNIActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        /*Vaciar tabla*/ 
        Usuario usuario = fa.comprobarAutentificacion(inDNI.getText());
        ModeloTablaUsuarios m=(ModeloTablaUsuarios) tablaUsuario.getModel();
        ArrayList<Usuario> lista = new ArrayList<>();
        if(usuario!=null)lista.add(usuario);
        m.setFilas(lista);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void inMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inMarcaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVehiculo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel descripcion;
    private javax.swing.JLabel errorText;
    private javax.swing.JTextField inAno;
    private javax.swing.JTextField inDNI;
    private javax.swing.JTextField inMarca;
    private javax.swing.JTextField inMatricula;
    private javax.swing.JTextField inModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> switchTipo;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
}
