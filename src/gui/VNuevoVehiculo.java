/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import aplication.TipoPlaza;
import aplication.Vehiculo;

/**
 *
 * @author alumnogreibd
 */
public class VNuevoVehiculo extends javax.swing.JDialog {
    
    private ModeloListaVehiculos lista;
    public VNuevoVehiculo(java.awt.Dialog parent, boolean modal, ModeloListaVehiculos mv) {
        super(parent, modal);
        lista=mv;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        descripcion.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        descripcion.setText("Añadir nuevo vehiculo");

        jLabel1.setText("Matrícula");

        jLabel2.setText("Marca");

        jLabel3.setText("Modelo");

        jLabel4.setText("Año");

        switchTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coche", "Moto", "Vehículo grande" }));

        jLabel5.setText("Tipo");

        addVehiculo.setText("Añadir");
        addVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(39, 39, 39)
                                .addComponent(inAno))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(inModelo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25)
                                .addComponent(inMarca)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(switchTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(descripcion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addVehiculo)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(switchTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(inAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(addVehiculo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehiculoActionPerformed
        VAviso aviso;
        String matricula = inMatricula.getText();
        if(matricula.isEmpty()){
            aviso=new VAviso(this,true, "Debe introducir matricula");
            aviso.setVisible(true);
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
        
        
        Integer ano = Integer.parseInt(anoStr);
        
        Vehiculo v = new Vehiculo(matricula, tipo, marca, modelo, ano);
        
        this.lista.nuevoElemento(v);
    }//GEN-LAST:event_addVehiculoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVehiculo;
    private javax.swing.JLabel descripcion;
    private javax.swing.JTextField inAno;
    private javax.swing.JTextField inMarca;
    private javax.swing.JTextField inMatricula;
    private javax.swing.JTextField inModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> switchTipo;
    // End of variables declaration//GEN-END:variables
}
