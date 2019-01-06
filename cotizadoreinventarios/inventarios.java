/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizadoreinventarios;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author estrella
 */

public class inventarios extends javax.swing.JFrame {
    private int cuenta;
    public final double a=1,b=6.10,uno=1,tres=3.05,doce=12.20;
    public double constante=a/b,peso;
     public      String    id,aux;
     public int k;
     public     String descripcion;
    public     double precio;
     public      double    cantidad,metros;   
                  File   FicheroProducto= new File("productos.txt");
                 ArrayList<TxtEnJava> productos = new ArrayList<TxtEnJava>();
                DefaultTableModel modelo= new DefaultTableModel(); 
    /**
     * Creates new form inventarios
     */
    public inventarios() {
        initComponents();
        comprobarBd();
        DetxtAObjeto();
        setLocationRelativeTo(null);
        setTitle("INVENTARIOS");
        cuenta=0;
        
    }
    public void comprobarBd(){
        try
      {
        //Varialble con la ruta donde esta el archivo de la bd de los productos  
        //File FicheroProducto= new File("./Bd/productos.txt");
        //crear el fichero de base de datos de productos   
        if(!FicheroProducto.exists())
        {
            FicheroProducto.createNewFile();
            System.out.println("Base de datos de productos creada se insertara el producto");
        }else{ }
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
        
     }
    public boolean validarRepeticion(String nombre,String descripcion,String espesor,double peso,double precio,double cantidad){
        boolean band=false;
        for(TxtEnJava n: productos){
          if(nombre.equals(n.nombre)&&descripcion.equals(n.medida)&&espesor.equals(n.espesor)){
              
              band=true;
              break;
          }  
        }
        return band;
    }
     public void InsertarProductos(String nombre,String descripcion,String espesor,double peso,double precio,double cantidad)
      {
         try
      {  
         this.id=nombre;
         this.descripcion=descripcion;
         this.precio=precio; 
         this.cantidad=cantidad; 
          this.peso=peso; 
        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8
         * con la clase BufferedWriter
         */  
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicheroProducto,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la función.  
           *el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
          Fescribe.write(nombre.toUpperCase()+"|\t"+descripcion.toUpperCase()+"|\t"+espesor.toUpperCase()+"|\t"+peso+"|\t"+precio+"|\t"+cantidad+"|\r\n");  
           JOptionPane.showMessageDialog(null,"El producto a sido insertado en la base de datos");           //Cierra el flujo de escritura  
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
      public void actualizarProductos(String nombre,String descripcion,String espesor,double precio,double cantidad)
      {
         try
      {  
         this.id=nombre;
         this.descripcion=descripcion;
         this.precio=precio; 
         this.cantidad=cantidad; 
           
        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8
         * con la clase BufferedWriter
         */  
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicheroProducto,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la función.  
           *el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
          Fescribe.write(nombre+"|\t"+descripcion+"|\t"+espesor+"|\t"+precio+"|\t"+cantidad+"|\r\n");  
           JOptionPane.showMessageDialog(null,"El producto a sido insertado en la base de datos");           //Cierra el flujo de escritura  
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
    public void limpiarTabla(){
        tablaInventarios.setVisible(false);
    }
    public boolean validarNumero(String dato){
        
            try{
                Double.parseDouble(dato);
                return true;
            }catch(NumberFormatException e){
        
        return false;
            }
    }
    public int dameCuenta(){
        return cuenta;
    }
    public void DetxtAObjeto()
     {
        
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(FicheroProducto));
        
         tablaInventarios.setModel(modelo);
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MEDIDA");
        modelo.addColumn("ESPESOR");
        modelo.addColumn("PESO KG");
        modelo.addColumn("PRECIO");
        modelo.addColumn("TRAMOS");
        modelo.addColumn("METROS");
        modelo.addColumn("KILOS");
         String [] datos=new String[8];
         while( (linea = leerFichero.readLine()) != null)
         {
          
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
            StringTokenizer mistokens = new StringTokenizer(linea, "|");
            String           id =  mistokens.nextToken().trim();
            String  descripcion =  mistokens.nextToken().trim();
             String  espesor =  mistokens.nextToken().trim();
             String  peso =  mistokens.nextToken().trim();
            String       precio =  mistokens.nextToken().trim();
            String     cantidad =  mistokens.nextToken().trim();
            String aux="";
            metros=Double.parseDouble(cantidad);
            TxtEnJava objeto;
            for(int a=0;a<cantidad.length();a++){
                if(cantidad.charAt(a)==46){
                break;
                }
                aux+=cantidad.charAt(a);
                
            }
             if(id.equals("LAMINA ANTIDERRAPANTE")||id.equals("LAMINA LISA")){
                constante=uno/tres;
            }else if(id.equals("VIGA IPS")||id.equals("VARILLA CORRUGADA")||id.equals("CANAL")){
                constante=1/doce;
                
            }else{
                constante=a/b;
            }
            metros-=Double.parseDouble(aux);
            metros=metros/constante;
            double end=Math.round(metros*100d)/100d;
            double sum=Double.parseDouble(aux)+end*constante;
            objeto = new TxtEnJava(id, descripcion,espesor, Double.parseDouble(peso),Double.parseDouble(precio), Double.parseDouble(aux),end,sum); 
            
            productos.add(objeto);
            
             cuenta++;
          
            //transformo los tipo de String a los tipos que hace falta int double 
            //int    idOperar=Integer.parseInt(id);
            /*double preciOperar=Double.parseDouble(precio);
            double    cantidadOperar=Integer.parseInt(cantidad);
            
            
            //lo paso al constructor para que me cree los objetos
            objeto= new TxtEnJava(id,descripcion,preciOperar,cantidadOperar);
            //lo añado al vecto para poder jugetear con el 
            cositas.add(objeto);*/
             /*String [] datos=new String[4];
        datos[0]=this.id;
        datos[1]=this.descripcion;
        datos[2]=String.valueOf(this.precio);
        datos[3]=String.valueOf(this.cantidad);*/
       /*datos[0]=id;
        datos[1]=descripcion;
        datos[2]=precio;
        datos[3]=cantidad;
            
        modelo.addRow(datos);*/
           
        
            }
            
           
         /*for(int f=1;f<cuenta;f++){
             for(int j=cuenta-1;j>=f;j--){
              if(i[j-1].aux.compareTo(i[j].aux)>0) {
               au=i[j];
               i[j]=i[j-1];
               i[j-1]=au;
              }  
             }
         }*/
        Collections.sort(productos, new Comparator<TxtEnJava>(){

			

             @Override
             public int compare(TxtEnJava o1, TxtEnJava o2) {
                 
                 return o1.nombre.compareTo(o2.nombre);
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
			
			
		});
         for(TxtEnJava n: productos){
       
        datos[0]=n.nombre;
        datos[1]=n.medida;
        datos[2]=n.espesor;
        datos[3]=String.valueOf(n.peso);
        datos[4]=String.valueOf(n.precio);
        datos[5]=String.valueOf(n.cantidad);
        datos[6]=String.valueOf(n.metros);
        datos[7]=String.valueOf(Math.round(n.sum*n.peso*100d)/100d);
        modelo.addRow(datos);
       
         }
         
          //JOptionPane.showMessageDialog(null,productos.lastIndexOf(n));
        
        /*for(int f=0;f<tablaInventarios.getRowCount();f++){
            if(Double.parseDouble(tablaInventarios.getValueAt(f, 4).toString())<3){
                tablaInventarios.setForeground(Color.red);
                
                        
            }
        }*/
        tablaInventarios.setModel(modelo);
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
    
    public void mostrarTabla(){
        DefaultTableModel modelo= new DefaultTableModel();
        tablaInventarios.setModel(modelo);
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MEDIDA");
        modelo.addColumn("PRECIO");
        modelo.addColumn("CANTIDAD");
        String [] datos=new String[1000];
        for(int i=0;i<cuenta;i++){
          datos[0]=null; 
        }
        
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
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtExistencias = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtEspesor = new javax.swing.JTextField();
        txtMedida = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtPeso = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        SALIR = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInventarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("NOMBRE");

        jLabel2.setText("MEDIDA");

        jLabel3.setText("ESPESOR");

        jLabel4.setText("PRECIO KG");

        jLabel5.setText("CANTIDAD");

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel6.setText("PESO");

        SALIR.setText("CERRAR");
        SALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SALIRActionPerformed(evt);
            }
        });

        tablaInventarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaInventarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SALIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(SALIR))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(521, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtEspesor.setText(null);
        txtExistencias.setText(null);
        txtMedida.setText(null);
        txtNombre.setText(null);
        txtPrecio.setText(null);
        txtPeso.setText(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(!validarNumero(txtPrecio.getText())||!validarNumero(txtExistencias.getText())){
            JOptionPane.showMessageDialog(null,"debe ingresar un valor numérico");
            return;
        } if(validarRepeticion(txtNombre.getText().toUpperCase(), txtMedida.getText(),txtEspesor.getText(),Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtPrecio.getText()), 
                Double.parseDouble(txtExistencias.getText()))){
             JOptionPane.showMessageDialog(null,"El producto ya está registrado");
            return;
        }
        InsertarProductos(txtNombre.getText(), txtMedida.getText(),txtEspesor.getText(),Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtPrecio.getText()), 
                Double.parseDouble(txtExistencias.getText()));
       
        String [] datos= new String[7];
        
       
        datos[0]=txtNombre.getText().toUpperCase();
        datos[1]=txtMedida.getText();
        datos[2]=txtEspesor.getText();
        datos[3]=String.valueOf(txtPeso.getText());
        datos[4]=String.valueOf(txtPrecio.getText());
        datos[5]=String.valueOf(txtExistencias.getText());
        datos[6]=String.valueOf("0.0");
        
        
         
         modelo.addRow(datos);
       // tablaInventarios.setModel(modelo);
       
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int i=0;
         try{
                      
                      if(tablaInventarios.getSelectedRow()==-1){
                          JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR LA FILA A MODIFICAR");
                        
                      }else{
                            BufferedWriter bw = new BufferedWriter(new FileWriter(FicheroProducto));
                              for(TxtEnJava n: productos)
                              {
                                  if(i==tablaInventarios.getSelectedRow()){
                                      n.nombre=tablaInventarios.getValueAt(i,0).toString();
                                      n.medida=tablaInventarios.getValueAt(i,1).toString();
                                      n.espesor=tablaInventarios.getValueAt(i,2).toString();
                                      n.peso=Double.parseDouble(tablaInventarios.getValueAt(i, 3).toString());
                                      n.precio=Double.parseDouble(tablaInventarios.getValueAt(i, 4).toString());
                                      double aux=Double.parseDouble(tablaInventarios.getValueAt(i, 5).toString());
                                      double a=Double.parseDouble(tablaInventarios.getValueAt(i, 6).toString());
                                      
                                      n.sum=aux+(a*constante);
                                      
                                      break;
                                  }
                                  i++;
                              }
                              
                              for(TxtEnJava n: productos)
                              {
                                  
                                  bw.write(n.nombre.toUpperCase()+"|\t"+n.medida.toUpperCase()+"|\t"+n.espesor.toUpperCase()+"|\t"+n.peso+"|\t"+n.precio+"|\t"+(n.sum)+"|\r\n");
                                 
                                  
                                  
                              }   
                          JOptionPane.showMessageDialog(null,"MODIFICADO CON EXITO");
                         
                           bw.close();
                      }
                      
                     }catch (Exception ex) 
                    {  
                      //Captura un posible error le imprime en pantalla   
                      System.out.println(ex.getMessage());  
                    } 
    }//GEN-LAST:event_btnModificarActionPerformed

    private void SALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SALIRActionPerformed
        setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_SALIRActionPerformed

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
            java.util.logging.Logger.getLogger(inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SALIR;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tablaInventarios;
    public javax.swing.JTextField txtEspesor;
    public javax.swing.JTextField txtExistencias;
    public javax.swing.JTextField txtMedida;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
