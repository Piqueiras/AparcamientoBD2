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

import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 *
 * @author basesdatos
 */
public class VPrincipal extends javax.swing.JFrame {

    aplication.FachadaAplicacion fa;

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal(aplication.FachadaAplicacion fa) {
        this.fa = fa;
        initComponents();
        getContentPane().setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confPlazas = new javax.swing.JDialog();
        addPlazabtn = new javax.swing.JButton();
        elimPlazabtn = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        addUsuario = new javax.swing.JButton();
        registrarinfrBtn = new javax.swing.JButton();
        ButtonReservar = new javax.swing.JButton();
        btnGestionPagos = new javax.swing.JButton();
        confPlazasbtn = new javax.swing.JButton();
        stats = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        etiquetaBienvenida = new javax.swing.JLabel();

        confPlazas.setLocationByPlatform(true);
        confPlazas.setLocationRelativeTo(null);
        confPlazas.setMaximumSize(new java.awt.Dimension(400, 80));
        confPlazas.setMinimumSize(new java.awt.Dimension(400, 80));

        addPlazabtn.setBackground(Color.white);
        addPlazabtn.setText("Añadir Plaza");
        addPlazabtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        addPlazabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlazabtnActionPerformed(evt);
            }
        });

        elimPlazabtn.setText("Eliminar Plaza");
        elimPlazabtn.setBackground(Color.white);
        elimPlazabtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        elimPlazabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elimPlazabtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confPlazasLayout = new javax.swing.GroupLayout(confPlazas.getContentPane());
        confPlazas.getContentPane().setLayout(confPlazasLayout);
        confPlazasLayout.setHorizontalGroup(
            confPlazasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confPlazasLayout.createSequentialGroup()
                .addComponent(addPlazabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(elimPlazabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        confPlazasLayout.setVerticalGroup(
            confPlazasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confPlazasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(confPlazasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addPlazabtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(elimPlazabtn, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aparcamientos USC");
        setName("vPrincipal"); // NOI18N
        setResizable(false);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APARCAMIENTO");
        jLabel1.setBackground(Color.white);
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 255, 255), java.awt.Color.white, new java.awt.Color(102, 153, 255), new java.awt.Color(153, 204, 255)));

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        addUsuario.setText("Añadir usuario");
        addUsuario.setBackground(Color.WHITE);
        addUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addUsuarioMouseExited(evt);
            }
        });
        addUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUsuarioActionPerformed(evt);
            }
        });

        registrarinfrBtn.setText("Registrar infracción");
        registrarinfrBtn.setBackground(Color.white);
        registrarinfrBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        registrarinfrBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarinfrBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrarinfrBtnMouseExited(evt);
            }
        });
        registrarinfrBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarinfrBtnActionPerformed(evt);
            }
        });

        ButtonReservar.setText("Reservar/Aparcar");
        ButtonReservar.setBackground(Color.white);
        ButtonReservar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ButtonReservar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonReservarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonReservarMouseExited(evt);
            }
        });
        ButtonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonReservarActionPerformed(evt);
            }
        });

        btnGestionPagos.setText("Historial de Pagos");
        btnGestionPagos.setBackground(Color.white);
        btnGestionPagos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGestionPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGestionPagosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGestionPagosMouseExited(evt);
            }
        });
        btnGestionPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionPagosActionPerformed(evt);
            }
        });

        confPlazasbtn.setText("Configurar plazas");
        confPlazasbtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        confPlazasbtn.setBackground(Color.white);
        confPlazasbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confPlazasbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confPlazasbtnMouseExited(evt);
            }
        });
        confPlazasbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confPlazasbtnActionPerformed(evt);
            }
        });

        stats.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        stats.setBackground(Color.white);
        stats.setLabel("Estadísticas");
        stats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                statsMouseExited(evt);
            }
        });
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGestionPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registrarinfrBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addComponent(confPlazasbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(addUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registrarinfrBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confPlazasbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGestionPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaBienvenida)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(etiquetaBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(20, 20, 20))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(btnSalir)
                        .addGap(15, 15, 15))))
        );

        getAccessibleContext().setAccessibleName("Biblioteca Informática");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionPagosActionPerformed
        fa.historialPagos();
    }//GEN-LAST:event_btnGestionPagosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void ButtonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonReservarActionPerformed
        VAviso aviso;
        if (fa.getUsuario().getNumeroInfracciones() >= 5) {
            aviso = new VAviso(this, true, "Tienes demasiadas infracciones como para reservar o aparcar.");
            aviso.setVisible(true);
            aviso.setLocationRelativeTo(null);
            return;
        }
        VReservarAparcar vreservar = new VReservarAparcar(this, true, fa);
        vreservar.setLocationRelativeTo(null);
        vreservar.setVisible(true);
    }//GEN-LAST:event_ButtonReservarActionPerformed


    private void registrarinfrBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarinfrBtnActionPerformed
        VRegistroInfracciones vregistroinfracciones = new VRegistroInfracciones(fa);
        vregistroinfracciones.setLocationRelativeTo(null);
        vregistroinfracciones.setVisible(true);
    }//GEN-LAST:event_registrarinfrBtnActionPerformed

    private void addUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUsuarioActionPerformed
        VNuevoUsuario vNuevoUsuario = new VNuevoUsuario(this, true, fa);
        vNuevoUsuario.setLocationRelativeTo(null);
        vNuevoUsuario.setVisible(true);
    }//GEN-LAST:event_addUsuarioActionPerformed

    private void statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsActionPerformed
        VEstadisticas vEstadisticas = new VEstadisticas(this, true, fa);
        vEstadisticas.setLocationRelativeTo(null);
        vEstadisticas.setVisible(true);
    }//GEN-LAST:event_statsActionPerformed

    private void confPlazasbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confPlazasbtnActionPerformed
        confPlazas.getContentPane().setBackground(Color.white);
        confPlazas.setLocationRelativeTo(null);
        confPlazas.setVisible(true);
    }//GEN-LAST:event_confPlazasbtnActionPerformed

    private void addPlazabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlazabtnActionPerformed
        
        VAnadir vanadir = new VAnadir(this, true, fa);
        vanadir.setLocationRelativeTo(null);
        vanadir.setVisible(true);
    }//GEN-LAST:event_addPlazabtnActionPerformed

    private void elimPlazabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimPlazabtnActionPerformed
                VEliminar veliminar = new VEliminar(this, true, fa);
                veliminar.setLocationRelativeTo(null);
        veliminar.setVisible(true);
    }//GEN-LAST:event_elimPlazabtnActionPerformed

    private void addUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addUsuarioMouseEntered
      this.addUsuario.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_addUsuarioMouseEntered

    private void addUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addUsuarioMouseExited
      this.addUsuario.setBorder(null);
    }//GEN-LAST:event_addUsuarioMouseExited

    private void registrarinfrBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarinfrBtnMouseEntered
        this.registrarinfrBtn.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_registrarinfrBtnMouseEntered

    private void ButtonReservarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonReservarMouseEntered
      this.ButtonReservar.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_ButtonReservarMouseEntered

    private void confPlazasbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confPlazasbtnMouseEntered
       this.confPlazasbtn.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_confPlazasbtnMouseEntered

    private void btnGestionPagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionPagosMouseEntered
       this.btnGestionPagos.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_btnGestionPagosMouseEntered

    private void statsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statsMouseEntered
        this.stats.setBorder(new LineBorder(Color.BLUE));
    }//GEN-LAST:event_statsMouseEntered

    private void registrarinfrBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarinfrBtnMouseExited
        this.registrarinfrBtn.setBorder(null);
    }//GEN-LAST:event_registrarinfrBtnMouseExited

    private void ButtonReservarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonReservarMouseExited
        this.ButtonReservar.setBorder(null);
    }//GEN-LAST:event_ButtonReservarMouseExited

    private void confPlazasbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confPlazasbtnMouseExited
        this.confPlazasbtn.setBorder(null);
    }//GEN-LAST:event_confPlazasbtnMouseExited

    private void btnGestionPagosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionPagosMouseExited
       this.btnGestionPagos.setBorder(null);
    }//GEN-LAST:event_btnGestionPagosMouseExited

    private void statsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statsMouseExited
       this.stats.setBorder(null);
    }//GEN-LAST:event_statsMouseExited

    
    /**
     * @param args the command line argumentss
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonReservar;
    private javax.swing.JButton addPlazabtn;
    private javax.swing.JButton addUsuario;
    private javax.swing.JButton btnGestionPagos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JDialog confPlazas;
    private javax.swing.JButton confPlazasbtn;
    private javax.swing.JButton elimPlazabtn;
    protected javax.swing.JLabel etiquetaBienvenida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Panel panel1;
    private javax.swing.JButton registrarinfrBtn;
    private javax.swing.JButton stats;
    // End of variables declaration//GEN-END:variables

}
