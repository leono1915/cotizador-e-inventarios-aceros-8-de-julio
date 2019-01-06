/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizadoreinventarios;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author estrella
 */

public class Placas extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    File fichero = new File("cotizacion.txt");
   
    ArrayList<Cotizacion> cotizador=new ArrayList<Cotizacion>();
    public String estadoFinal;
    public final double p1_8 =25;
    public final double p3_16= 37.5; 
    public final double p1_4 =50;
    public final double p5_16= 62.5;
    public final double p3_8 =75;
    public final double p7_16= 87.5;
    public final double p1_2 =100;
    public final double p5_8= 125; 
    public final double p3_4 =150;
    public final double p7_8= 175;
    public final double p1 =200;
    public final double p13_16= 237.5;
    public final double p11_4 =250;
    public final double p13_8 =275;
    public final double p11_2= 300;
    public final double p15_8=325;
    public final double p13_4 =350;
    public final double p2= 400;
    public final double p21_4 =450;
    public final double p21_2= 500;
    public final double p3=600;
    public Placas() {
        initComponents();
        setTitle("Cotización de placas");
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        opcionPulgadas = new javax.swing.JCheckBox();
        txtMedida1 = new javax.swing.JTextField();
        txtMedida2 = new javax.swing.JTextField();
        MEDIDA1 = new javax.swing.JLabel();
        MEDIDA2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        octavo25 = new javax.swing.JRadioButton();
        dieciseis375 = new javax.swing.JRadioButton();
        cuarto50 = new javax.swing.JRadioButton();
        cincodiesiceis625 = new javax.swing.JRadioButton();
        tresoctavos75 = new javax.swing.JRadioButton();
        sietediesiseis875 = new javax.swing.JRadioButton();
        media100 = new javax.swing.JRadioButton();
        cincooctavos125 = new javax.swing.JRadioButton();
        trescuartos150 = new javax.swing.JRadioButton();
        sieteoctavos175 = new javax.swing.JRadioButton();
        pulgada200 = new javax.swing.JRadioButton();
        unatresdiesiseis2375 = new javax.swing.JRadioButton();
        unacuarto250 = new javax.swing.JRadioButton();
        unacincodiesiseis2625 = new javax.swing.JRadioButton();
        unatresoctavos275 = new javax.swing.JRadioButton();
        unaymedia300 = new javax.swing.JRadioButton();
        unacincooctavos325 = new javax.swing.JRadioButton();
        unatrescuartos350 = new javax.swing.JRadioButton();
        dospulgadas400 = new javax.swing.JRadioButton();
        dospulgadascuarto450 = new javax.swing.JRadioButton();
        dosymedia450 = new javax.swing.JRadioButton();
        trespulgadas600 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorte = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        opcionDisco = new javax.swing.JRadioButton();
        opcionCartabon = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioPlaca = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioCorte = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        opcionFactura = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        brida = new javax.swing.JRadioButton();
        txtBrida = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        opcionPulgadas.setText("PULGADAS");
        opcionPulgadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionPulgadasActionPerformed(evt);
            }
        });

        MEDIDA1.setText("MEDIDA");

        MEDIDA2.setText("MEDIDA");

        jLabel1.setText("PESO");

        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });

        buttonGroup1.add(octavo25);
        octavo25.setText("1/8");

        buttonGroup1.add(dieciseis375);
        dieciseis375.setText("3/16");

        buttonGroup1.add(cuarto50);
        cuarto50.setText("1/4");

        buttonGroup1.add(cincodiesiceis625);
        cincodiesiceis625.setText("5/16");

        buttonGroup1.add(tresoctavos75);
        tresoctavos75.setText("3/8");

        buttonGroup1.add(sietediesiseis875);
        sietediesiseis875.setText("7/16");

        buttonGroup1.add(media100);
        media100.setText("1/2");

        buttonGroup1.add(cincooctavos125);
        cincooctavos125.setText("5/8");

        buttonGroup1.add(trescuartos150);
        trescuartos150.setText("3/4");

        buttonGroup1.add(sieteoctavos175);
        sieteoctavos175.setText("7/8");

        buttonGroup1.add(pulgada200);
        pulgada200.setText("1");

        buttonGroup1.add(unatresdiesiseis2375);
        unatresdiesiseis2375.setText("1 3/16");

        buttonGroup1.add(unacuarto250);
        unacuarto250.setText("1 1/4");

        buttonGroup1.add(unacincodiesiseis2625);
        unacincodiesiseis2625.setText("1 5/16");

        buttonGroup1.add(unatresoctavos275);
        unatresoctavos275.setText("1 3/8");

        buttonGroup1.add(unaymedia300);
        unaymedia300.setText("1 1/2");

        buttonGroup1.add(unacincooctavos325);
        unacincooctavos325.setText("1 5/8");

        buttonGroup1.add(unatrescuartos350);
        unatrescuartos350.setText("1 3/4");

        buttonGroup1.add(dospulgadas400);
        dospulgadas400.setText("2");

        buttonGroup1.add(dospulgadascuarto450);
        dospulgadascuarto450.setText("2 1/4");

        buttonGroup1.add(dosymedia450);
        dosymedia450.setText("2 1/2");

        buttonGroup1.add(trespulgadas600);
        trespulgadas600.setText("3");

        jLabel2.setText("CORTE");

        jLabel3.setText("SUBTOTAL");

        jLabel4.setText("IVA");

        jLabel5.setText("TOTAL");

        txtCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorteActionPerformed(evt);
            }
        });

        opcionDisco.setText("DISCO");

        opcionCartabon.setText("CARTABÓN");

        jLabel6.setText("PRECIO CORTE");

        txtPrecioPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioPlacaActionPerformed(evt);
            }
        });

        jLabel7.setText("PRECIO PLACA");

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnCalcular.setText("CALCULAR");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        opcionFactura.setText("SIN FACTURA?");

        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        brida.setText("BRIDA");
        brida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bridaActionPerformed(evt);
            }
        });

        txtBrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBridaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(sieteoctavos175)
                                    .addGap(85, 85, 85))
                                .addComponent(media100, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sietediesiseis875, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tresoctavos75, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cuarto50, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dieciseis375, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(octavo25, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cincooctavos125, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(trescuartos150, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cincodiesiceis625)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unacincodiesiseis2625)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pulgada200, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(unatresdiesiseis2375)
                                    .addComponent(unacuarto250, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trespulgadas600)
                                    .addComponent(dosymedia450)))
                            .addComponent(unatresoctavos275)
                            .addComponent(unaymedia300)
                            .addComponent(unacincooctavos325)
                            .addComponent(unatrescuartos350)
                            .addComponent(dospulgadas400, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dospulgadascuarto450)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MEDIDA1)
                            .addComponent(MEDIDA2))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMedida1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(txtMedida2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(opcionPulgadas, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(opcionDisco)
                        .addGap(34, 34, 34)
                        .addComponent(opcionCartabon)
                        .addGap(16, 16, 16)
                        .addComponent(brida, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBrida, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIva)
                            .addComponent(txtPrecio)
                            .addComponent(txtCorte)
                            .addComponent(txtPeso)
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(opcionFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcionPulgadas)
                    .addComponent(MEDIDA1)
                    .addComponent(opcionDisco)
                    .addComponent(opcionCartabon)
                    .addComponent(txtPrecioPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(brida)
                    .addComponent(txtBrida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MEDIDA2)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecioCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(octavo25)
                    .addComponent(pulgada200)
                    .addComponent(dosymedia450)
                    .addComponent(jLabel2)
                    .addComponent(txtCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dieciseis375)
                        .addComponent(unatresdiesiseis2375)
                        .addComponent(trespulgadas600)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuarto50)
                    .addComponent(unacuarto250)
                    .addComponent(jLabel4)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cincodiesiceis625)
                        .addComponent(unacincodiesiseis2625)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tresoctavos75)
                            .addComponent(unatresoctavos275))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sietediesiseis875)
                            .addComponent(unaymedia300))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(media100)
                            .addComponent(unacincooctavos325))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cincooctavos125)
                            .addComponent(unatrescuartos350))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(trescuartos150)
                            .addComponent(dospulgadas400))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sieteoctavos175)
                            .addComponent(dospulgadascuarto450)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(opcionFactura)
                        .addGap(37, 37, 37)
                        .addComponent(btnCalcular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(516, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionPulgadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionPulgadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionPulgadasActionPerformed

    private void txtCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorteActionPerformed
         // TODO add your handling code here:
       
         
    }//GEN-LAST:event_txtCorteActionPerformed

    private void txtPrecioPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioPlacaActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtPrecioPlacaActionPerformed

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        double medida1,medida2,precioCorte,peso,precioPlaca,total=0,corte=0,aux,aux2;
        String es="PLACA";
        if(txtMedida1.getText().isEmpty()||txtMedida2.getText().isEmpty()||txtPrecioCorte.getText().isEmpty()||txtPrecioPlaca.getText().isEmpty()){
           return; 
        }
        medida1=Double.parseDouble(txtMedida1.getText());
        medida2=Double.parseDouble(txtMedida2.getText());
        precioCorte=Double.parseDouble(txtPrecioCorte.getText());
        precioPlaca=Double.parseDouble(txtPrecioPlaca.getText());
        double iva,estado=0;
        if(opcionFactura.isSelected()){
            iva=1.08;
        }else{
            iva=1.16;
        }
          boolean bandera=false;
       
        
         if(pulgada200.isSelected()){
           estado=p1;
           estadoFinal="1";
        }else if(unaymedia300.isSelected()){
            estado=p11_2;
            estadoFinal="1 1/2";
        }else if(unatresoctavos275.isSelected()){
            estado=p13_8;
            estadoFinal="1 3/8";
        }else if(unatresdiesiseis2375.isSelected()){
            estado=p13_16;
            estadoFinal="1 3/16";
        }else if(unatrescuartos350.isSelected()){
            estado=p13_4;
            estadoFinal="1 3/4";
        }else if(unacuarto250.isSelected()){
            estado=p11_4;
            estadoFinal="1 1/4";
        }else if(unacincooctavos325.isSelected()){
            estado=p15_8;
            estadoFinal="1 5/8";
        }else if(unacincodiesiseis2625.isSelected()){
            estado=262.5;
            estadoFinal="1 5/16";
        }else if(octavo25.isSelected()){
            estado=p1_8;
            estadoFinal="1/8";
        }else if(trescuartos150.isSelected()){
            estado=p3_4;
            estadoFinal="3/4";
        }else if(tresoctavos75.isSelected()){
            estado=p3_8;
            estadoFinal="3/8";
        }else if(trespulgadas600.isSelected()){
            estado=p3;
            estadoFinal="3";
        }else if(dieciseis375.isSelected()){
            estado=p3_16;
            estadoFinal="3/16";
        }else if(dospulgadas400.isSelected()){
            estado=p2;
            estadoFinal="2";
        }else if(dospulgadascuarto450.isSelected()){
            estado=p21_4;
            estadoFinal="2 1/4";
        }else if(dosymedia450.isSelected()){
            estado=p21_2;
            estadoFinal="2 1/2";
        }else if(trescuartos150.isSelected()){
            estado=p3_4;
        }else if(tresoctavos75.isSelected()){
            estado=p3_8;
            estadoFinal="3/8";
        }else if(sietediesiseis875.isSelected()){
            estado=p7_16;
            estadoFinal="7/16";
        }else if(sieteoctavos175.isSelected()){
            estado=p7_8;
            estadoFinal="7/8";
        }else if(cuarto50.isSelected()){
           estado=p1_4;
           estadoFinal="1/4";
        }else if(cincodiesiceis625.isSelected()){
           estado=p5_16;
           estadoFinal="5/16";
        }else if(cincooctavos125.isSelected()){
            estado=p5_8;
            estadoFinal="5/8";
        }else if(media100.isSelected()){
            estado=p1_2;
            estadoFinal="1/2";
        }
            else{
            JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO ESPESOR");
            return;
        }
       if(opcionPulgadas.isSelected()){
            bandera=true;
            medida1=medida1*2.54;
            medida2=medida2*2.54;
            corte=((medida1+medida2)/2.54)*precioCorte;
            peso=((estado*medida1*medida2)/10000);
        }else{
            corte=((medida1+medida2)/2.54)*precioCorte;
            peso=(estado*medida1*medida2/10000);
        }
        total+=(peso*precioPlaca+corte)*iva;
        double bridaCorte;
        if(txtBrida.getText().isEmpty()){
            bridaCorte=0;
        }else{
             bridaCorte=Double.parseDouble(txtBrida.getText());
        }
        if(brida.isSelected()){
          
         
           if(bandera==false){
            aux=(medida1/2.54)*3.1416+(bridaCorte/2.54*3.1416);
            }else{
                aux=(medida1/2.54)*3.1416+(bridaCorte/2.54*3.1416);
            }
            corte+=aux;
            total+=aux;
            es="BRIDA";
        }
        else if(opcionDisco.isSelected()){
            if(bandera==false){
            aux=(medida1/2.54)*3.1416;
            }else{
                aux=(medida1/2.54)*3.1416;
            }
            corte+=aux;
            total+=aux;
            es="DISCO";
        }
        else if(opcionCartabon.isSelected()){
            if(bandera==false){
            aux2=(sqrt((medida1*medida1)+(medida2*medida2)))/2.54;
            }else{
                aux2=sqrt((medida1*medida1)+(medida2*medida2));
            }
            total+=aux2;
            total=total/2;
            corte+=aux2;
            es="CARTABON";
        }
        iva=total/iva;
        double corteA = Math.round(corte*100d)/100d;
        double pesoA = Math.round(peso*100d)/100d;
        double ivaA = Math.round(iva*100d)/100d;
        txtCorte.setText("$"+String.valueOf(corteA));
        
        double valorfinal = Math.round(total*100d)/100d;
        double au=valorfinal-ivaA;
        double subtotal = Math.round(au*100d)/100d;
        txtPrecio.setText("$"+String.valueOf(ivaA));
        txtPeso.setText("KG:"+String.valueOf(pesoA));
        txtIva.setText("$"+String.valueOf(subtotal));
        txtTotal.setText("$"+String.valueOf(valorfinal));
        try {
            BufferedWriter escribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero,true),"utf-8"));
         escribe.write(es+"|"+medida1+" x "+medida2+"|"+estadoFinal+"|"+pesoA+"|"+1+"|"+ivaA+"|"+subtotal+"|"+valorfinal+"|"+0+"|\r\n");
         escribe.close();
        } catch (Exception e) {
        }
       
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        txtCorte.setText(null);
        txtIva.setText(null);
        txtMedida1.setText(null);
        txtMedida2.setText(null);
        txtPeso.setText(null);
        txtPrecio.setText(null);
        txtPrecioCorte.setText(null);
        txtPrecioPlaca.setText(null);
        txtTotal.setText(null);
        opcionCartabon.setSelected(false);
        opcionDisco.setSelected(false);
        opcionFactura.setSelected(false);
        opcionPulgadas.setSelected(false);
        brida.setSelected(false);       
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        setVisible(false);
       
       new Menu().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBridaActionPerformed
        
    }//GEN-LAST:event_txtBridaActionPerformed

    private void bridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bridaActionPerformed
        
       
    }//GEN-LAST:event_bridaActionPerformed

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
            java.util.logging.Logger.getLogger(Placas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Placas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Placas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Placas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Placas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel MEDIDA1;
    public javax.swing.JLabel MEDIDA2;
    public javax.swing.JRadioButton brida;
    public javax.swing.JButton btnCalcular;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JRadioButton cincodiesiceis625;
    public javax.swing.JRadioButton cincooctavos125;
    public javax.swing.JRadioButton cuarto50;
    public javax.swing.JRadioButton dieciseis375;
    public javax.swing.JRadioButton dospulgadas400;
    public javax.swing.JRadioButton dospulgadascuarto450;
    public javax.swing.JRadioButton dosymedia450;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JRadioButton media100;
    public javax.swing.JRadioButton octavo25;
    public javax.swing.JRadioButton opcionCartabon;
    public javax.swing.JRadioButton opcionDisco;
    public javax.swing.JCheckBox opcionFactura;
    public javax.swing.JCheckBox opcionPulgadas;
    public javax.swing.JRadioButton pulgada200;
    public javax.swing.JRadioButton sietediesiseis875;
    public javax.swing.JRadioButton sieteoctavos175;
    public javax.swing.JRadioButton trescuartos150;
    public javax.swing.JRadioButton tresoctavos75;
    public javax.swing.JRadioButton trespulgadas600;
    public javax.swing.JTextField txtBrida;
    public javax.swing.JTextField txtCorte;
    public javax.swing.JTextField txtIva;
    public javax.swing.JTextField txtMedida1;
    public javax.swing.JTextField txtMedida2;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPrecio;
    public javax.swing.JTextField txtPrecioCorte;
    public javax.swing.JTextField txtPrecioPlaca;
    public javax.swing.JTextField txtTotal;
    public javax.swing.JRadioButton unacincodiesiseis2625;
    public javax.swing.JRadioButton unacincooctavos325;
    public javax.swing.JRadioButton unacuarto250;
    public javax.swing.JRadioButton unatrescuartos350;
    public javax.swing.JRadioButton unatresdiesiseis2375;
    public javax.swing.JRadioButton unatresoctavos275;
    public javax.swing.JRadioButton unaymedia300;
    // End of variables declaration//GEN-END:variables
}