/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;
import java.util.Date;
import aplication.FachadaAplicacion;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author alumnogreibd
 */
public class VHacerReserva extends javax.swing.JDialog {

    /**
     * Creates new form VSeleccionarVehiculo
     */
    private FachadaAplicacion fa;
    private String aparcamiento;
    private int plaza;
    private String tipo;
    
    public String getAparcamiento() {
        return aparcamiento;
    }

    public int getPlaza() {
        return plaza;
    }

    public String getTipo(){
        return tipo;
    }
        
    public VHacerReserva(javax.swing.JDialog parent, boolean modal,  FachadaAplicacion fa, String ap, int plaza, String tipo) {
        super(parent, modal);
        this.fa=fa;    
        this.aparcamiento=ap;
        this.plaza=plaza;
        this.tipo=tipo;
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
        jButton1 = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaVehiculos = new javax.swing.JTable();
        BuscarButton = new javax.swing.JButton();
        hacerReservaButton = new javax.swing.JButton();
        label5 = new java.awt.Label();
        jDateChooserFin = new com.toedter.calendar.JDateChooser();
        label8 = new java.awt.Label();
        AvisoFFin = new java.awt.Label();
        AvisoVehiculo = new java.awt.Label();
        jScrollPane4 = new javax.swing.JScrollPane();
        hora9 = new javax.swing.JTextPane();
        label6 = new java.awt.Label();
        jScrollPane5 = new javax.swing.JScrollPane();
        jDNInput = new javax.swing.JTextPane();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label1.setText("Reserva");

        label2.setText("Plaza");

        label3.setText("Aparcamiento");

        label4.setText("Tipo");

        jTextPane1.setEditable(false);
        jTextPane1.setText(String.valueOf(this.getPlaza()));
        jScrollPane1.setViewportView(jTextPane1);

        jTextPane2.setEditable(false);
        jTextPane2.setText(this.getAparcamiento());
        jScrollPane2.setViewportView(jTextPane2);

        jTextPane3.setEditable(false);
        jTextPane3.setText(this.getTipo());
        jScrollPane3.setViewportView(jTextPane3);

        tablaVehiculos.setModel(new gui.ModeloTablaVehiculo());
        jScrollPane6.setViewportView(tablaVehiculos);

        BuscarButton.setText("Buscar");
        BuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarButtonActionPerformed(evt);
            }
        });

        hacerReservaButton.setText("Hacer reserva");
        hacerReservaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hacerReservaButtonActionPerformed(evt);
            }
        });

        label5.setText("Fecha de Fin");

        jDateChooserFin.setMinSelectableDate(new Date());

        label8.setText("Hora");

        AvisoFFin.setForeground(new java.awt.Color(255, 51, 51));

        AvisoVehiculo.setForeground(new java.awt.Color(255, 51, 51));

        hora9.setEditable(false);
        hora9.setText("9");
        jScrollPane4.setViewportView(hora9);

        label6.setText("DNI: ");

        jScrollPane5.setViewportView(jDNInput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(AvisoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(hacerReservaButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooserFin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AvisoFFin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BuscarButton)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BuscarButton)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AvisoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AvisoFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hacerReservaButton)
                            .addComponent(jButton1))
                        .addGap(15, 15, 15))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();//salir de la ventana
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarButtonActionPerformed
        ModeloTablaVehiculo m;

        m=(ModeloTablaVehiculo) tablaVehiculos.getModel();
        
        if(jDNInput.getText().isEmpty()){
//si el dni esta vacio, muestra todos los vehiculos
            m.setFilas(fa.obtenerVehiculosReserva());
            if (m.getRowCount() > 0) {
                tablaVehiculos.setRowSelectionInterval(0, 0);
            }}
        else{
            //Si se ha introducido dni, se buscan los vehiculos del usuario con ese dni
            m.setFilas(fa.obtenerVehiculosReserva(jDNInput.getText()));}
        if (m.getRowCount() > 0) {
            tablaVehiculos.setRowSelectionInterval(0, 0);
        }
        
    }//GEN-LAST:event_BuscarButtonActionPerformed
//Funcion privada que dadas dos fechas, calcula si estan separadas por mas de 7 dias; util para los usuariosNoUSC, que no pueden reservar mas de 7 dias
private static boolean estanSeparadosPorMasDe7Dias(Date fecha1, Date fecha2) {
    long diffMilis = Math.abs(fecha2.getTime() - fecha1.getTime());
    long diffDias = diffMilis / (1000 * 60 * 60 * 24);

    return diffDias > 7;
}
    private void hacerReservaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hacerReservaButtonActionPerformed
        VAviso aviso;
        int horaInicio, horaFin;
        Date Inicio, Fin;
        
        if(jDateChooserFin.getDate()==null){//No se ha seleccionado fechaFin en el jcalendar
            AvisoFFin.setText("No has seleccionado fecha de Fin.");
            return;
        }  
        AvisoFFin.setText("");

        int fila=tablaVehiculos.getSelectedRow(); //Obtiene la fila seleccionada en la tabla de Vehiculos
        if(fila==-1){
            //aviso=new VAviso(this,true, "No has seleccionado ningun vehiculo");
            //aviso.setVisible(true);
            AvisoVehiculo.setText("No has seleccionado ningun vehiculo.");
            return;
        }
            AvisoVehiculo.setText("");

        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //formato para la fecha
        String strDateF=formatter.format(jDateChooserFin.getDate()); //obtener FechaFin del jcalendar
        String strDateI=formatter.format(new Date());//obtener fechaActual
        String fFin=strDateF+" "+String.valueOf(9)+":00:00"; //Sumar a ese dia 9 horas (todas las reservas empiezan a las 9 de la mañana)
        String fIni=strDateI+" "+String.valueOf(9)+":00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Inicio = dateFormat.parse(fIni); //guardar fechas como Date
            Fin=dateFormat.parse(fFin);
        } catch (ParseException e) {
            aviso=new VAviso(this,true, "Fecha no valida");
            aviso.setVisible(true);
            return;
        }

        String dni=tablaVehiculos.getValueAt(fila, 5).toString();
        
        
        if(Inicio.compareTo(Fin)>=0){ //si la fecha de fin es anterior a la de inicios
            AvisoFFin.setText("Fecha de fin no valida");
            return;
        }    
        AvisoFFin.setText("");
        
        if("noUSC".equals(fa.obtenerRol(dni))) //si el usuario NO es de la USC
            if( estanSeparadosPorMasDe7Dias(Inicio,Fin)){
                AvisoFFin.setText("Usuario NoUSC: maximo 7 dias");
                return;
            }
            
        AvisoFFin.setText("");

        String matricula=tablaVehiculos.getValueAt(fila, 0).toString();
        boolean exito=fa.hacerReserva(matricula, plaza,aparcamiento,Inicio,Fin);
                
        if(exito){//reserva exitosa
            JOptionPane.showMessageDialog(this,"Reserva realizada con éxito.");
            this.dispose();
        }    
    }//GEN-LAST:event_hacerReservaButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label AvisoFFin;
    private java.awt.Label AvisoVehiculo;
    private javax.swing.JButton BuscarButton;
    private javax.swing.JButton hacerReservaButton;
    private javax.swing.JTextPane hora9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextPane jDNInput;
    private com.toedter.calendar.JDateChooser jDateChooserFin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label8;
    private javax.swing.JTable tablaVehiculos;
    // End of variables declaration//GEN-END:variables
}
