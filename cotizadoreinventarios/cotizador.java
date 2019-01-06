/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizadoreinventarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;


/**
 *
 * @author estrella
 */
public class cotizador extends javax.swing.JFrame {

     private int cuenta,conta;
    public final double a=1,b=6.10,doce=12.20,h=100,j=254;
    public double constante=a/b,p=h/j;
    public double uno=1,tres=3.05;
     public      String    id,aux,estado,medida,espesor;
     public int k;
     public     String descripcion;
    public     double precio,precioAux;
    public double sumatoriaIva,sumatoriaTotal,sumatoriaSub;
     public      double    cantidad,metros,ivas;   
                  File   FicheroProducto= new File("productos.txt");
                    File archivo =new File("cotizacion.txt");
                 ArrayList<TxtEnJava> productos = new ArrayList<TxtEnJava>();
                 ArrayList<Cotizacion> cotizar = new ArrayList<Cotizacion>();
                 ArrayList<Auxiliar> temporal=new ArrayList<>();
                DefaultTableModel modelo= new DefaultTableModel(); 
               
    public cotizador() {
         
        initComponents();
        setLocationRelativeTo(null);
        comprobarBd();
        DetxtAObjeto();
        texto();
       funcion();
       na.setVisible(false);
        setTitle("COTIZADOR");
        cuenta=0;
        conta=0;
        estado="";
        medida="";
        ivas=1.16;
       abrasivos.setVisible(false);
        
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
        }else{System.out.println(constante);}
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
        
     }
     public void InsertarProductos(String nombre,String descripcion,String espesor,double precio,double cantidad)
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
      public boolean validarNumero(String dato){
        
            try{
                Double.parseDouble(dato);
                return true;
            }catch(NumberFormatException e){
        
        return false;
            }
    }
       public boolean validarEntero(String dato){
        
            try{
                Integer.parseInt(dato);
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
        
         //tablaInventarios.setModel(modelo);
        
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
            TxtEnJava objeto = new TxtEnJava(id, descripcion,espesor, Double.parseDouble(peso),Double.parseDouble(precio), Double.parseDouble(aux),end,sum); 
            
            productos.add(objeto);
            
             cuenta++;
          
         }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
     public void texto()
     {
        
        try
        {
         if(!archivo.exists()){
             archivo.createNewFile();
         }
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(archivo));
        
         //tablaInventarios.setModel(modelo);
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MEDIDA");
        modelo.addColumn("ESPESOR");
        modelo.addColumn("METROS/KG");
        modelo.addColumn("PIEZAS/TRAMOS");
        modelo.addColumn("SUBTOTAL");
        modelo.addColumn("IVA");
        modelo.addColumn("PRECIO");
         while( (linea = leerFichero.readLine()) != null)
         {
          
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
            StringTokenizer mistokens = new StringTokenizer(linea, "|");
            String           id =  mistokens.nextToken().trim();
            String  descripcion =  mistokens.nextToken().trim();
             String  espesor =  mistokens.nextToken().trim();
             String  peso =  mistokens.nextToken().trim();
           
            String     cantidad =  mistokens.nextToken().trim();
            String     sub=mistokens.nextToken().trim();
            String     iva=mistokens.nextToken().trim();
             String       precio =  mistokens.nextToken().trim();
            String    auxiliar=mistokens.nextToken().trim();
           
            Cotizacion objeto = new Cotizacion(id, descripcion,espesor, Double.parseDouble(peso),Double.parseDouble(cantidad), Double.parseDouble(sub),
                    Double.parseDouble(iva),Double.parseDouble(precio),Double.parseDouble(auxiliar)); 
            
            cotizar.add(objeto);
            
             cuenta++;
          
         }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
        String [] datos =new String[8];
         for(int i=0;i<cotizar.size();i++)
          {
        
       
        datos[0]=cotizar.get(i).nombre;
        datos[1]=cotizar.get(i).medida;
        datos[2]=cotizar.get(i).espesor;
        datos[3]=String.valueOf(cotizar.get(i).precio);
        datos[4]=String.valueOf(cotizar.get(i).cantidad);
        datos[5]=String.valueOf(Math.round(cotizar.get(i).metros*100d)/100d);
        datos[6]=String.valueOf(Math.round(cotizar.get(i).subtotal*100d)/100d);
        datos[7]=String.valueOf(Math.round(cotizar.get(i).iva*100d)/100d);
        modelo.addRow(datos);
       
         }
       
         
         tablaInventarios.setModel(modelo);
      } 
   
    public void estadoMedida(){
         if(pulgadaMedida.isSelected()){
            medida="1";
           
         }else if(trescuartosmedida.isSelected()){
             medida="3/4";
         } else if(trespulgadasmedida.isSelected()){
               medida="3";
              
           }else if(cuatromedida.isSelected()){
               medida="4";
           }else if(cincomedida.isSelected()){
               medida="5";
           }else if(seispulgadasmedida.isSelected()){
               medida="6";
           }else if(ochomedida.isSelected()){
               medida="8";
           }else if(diezmedida.isSelected()){
               medida="10";
           }else if(docemedida.isSelected()){
               medida="12";
           }else if(tresoctavosMedida.isSelected()){
               medida="3/8";
           }else if(trespiesmedida.isSelected()){
               medida="3";
           }else if(cuatropiesmedida.isSelected()){
               medida="4";
           }else if(trespordosmedida.isSelected()){
               medida="3X2";
           }else if(tresporunaymedia.isSelected()){
               medida="3X1 1/2";
           }else if(tresymedidamedida.isSelected()){
               medida="3 1/2";
           }else if(dosporunamedida.isSelected()){
               medida="2X1";
           }else if(dospulgadasmedida.isSelected()){
               medida="2";
           }else if(dosymediamedida.isSelected()){
               medida="2 1/2";
           }else if(cuatropordosmedida.isSelected()){
               medida="4X2";
           }else if(cuatroporunaymediamedida.isSelected()){
               medida="4X1 1/2";
           }else if(mediaMedida.isSelected()){
               medida="1/2";
           }else if(cincooctavosmedida.isSelected()){
               medida="5/8";
           }else if(sieteoctavosmedida.isSelected()){
               medida="7/8";
           }else if(unauncuartomedida.isSelected()){
               medida="1 1/4";
           }else if(unaymediamedida.isSelected()){
               medida="1 1/2";
           }
    }
    public void estadoEspesor(){
         if(unoctavo.isSelected()){
                espesor="1/8";
            }else if(C30.isSelected()){
               espesor="C30";
           }else if(C40.isSelected()){
               espesor="C40";
           }else if(tresdiesiceisespesor.isSelected()){
               espesor="3/16";
           }else if(uncuartoespesor.isSelected()){
               espesor="1/4";
           }else if(cincodiesiceisespesor.isSelected()){
               espesor="5/16";
           }else if(tresoctavosespesor.isSelected()){
               espesor="3/8";
           }else if(mediaespesor.isSelected()){
               espesor="1/2";
           }else if(cincooctavosespesor.isSelected()){
               espesor="5/8";
           }else if(trescuartosespesor.isSelected()){
               espesor="3/4";
           }else if(sieteoctavosespesor.isSelected()){
               espesor="7/8";
           }else if(unapulgadaespesor.isSelected()){
               espesor="1";
           }else if(unauncuartoespesor.isSelected()){
               espesor="1 1/4";
           }else if(unaymediaespesor.isSelected()){
               espesor="1 1/2";
           }else if(dospulgadasespesor.isSelected()){
               espesor="2";
           }else if(cal10.isSelected()){
               espesor="CAL10";
           }else if(cal12.isSelected()){
               espesor="CAL12";
           }else if(cal14.isSelected()){
               espesor="CAL14";
           }else if(cal16.isSelected()){
               espesor="CAL16";
           }else if(cal18.isSelected()){
               espesor="CAL18";
           }else if(cal20.isSelected()){
               espesor="CAL20";
           }else if(cal26.isSelected()){
               espesor="CAL26";
           }else if(opcionCanal.isSelected()){
           
           espesor="N/A";
           }else if(cuadrado.isSelected()){
           
           espesor="N/A";
       }else if(VIGAIPR.isSelected()){
           
           espesor="N/A";
           
           
          
       }else if(viga.isSelected()){
           
           espesor="N/A";
           
           
           
          
       }
         else if(coldroll.isSelected()){
           espesor="N/A";
           
       }else if(pulido.isSelected()){
           espesor="N/A";
             
       }else if(redondo.isSelected()){
           espesor="N/A";
          
       }else if(na.isSelected()){
           espesor="N/A";
       }else if(blanco.isSelected()){
           espesor="BLANCO";
       }else if(azul.isSelected()){
           espesor="AZUL";
       }else if(rojo.isSelected()){
           espesor="ROJO";
       }
    }
    public void estado(){
        if(angulo.isSelected()){
           estado="ANGULO";
        
       }else if(opcionCanal.isSelected()){
           estado="CANAL";
           
           
           
          
       }else if(cuadrado.isSelected()){
           estado="CUADRADO";
         
       }else if(VIGAIPR.isSelected()){
           estado="VIGA IPR";
           
           
           
          
       }else if(viga.isSelected()){
           estado="VIGA IPS";
          
           
           
           
          
       }else if(coldroll.isSelected()){
           estado="COLD ROLL";
           
       }else if(lamina.isSelected()){
           estado="LAMINA LISA";
       }else if(laminaAnt.isSelected()){
           estado="LAMINA ANTIDERRAPANTE";
       }else if(pulido.isSelected()){
           
           estado="PULIDO";    
       }else if(ptr.isSelected()){
           estado="PTR";
       }else if(redondo.isSelected()){
           
           estado="REDONDO";
       }else if(solera.isSelected()){
           estado="SOLERA";
       }else if(tubo.isSelected()){
           estado="TUBO";
       }else if(tubular.isSelected()){
           estado="TUBULAR";
       }else if(monten.isSelected()){
           estado="POLIN MONTEN";
       }else if(varilla.isSelected()){
           espesor="N/A";
           estado="VARILLA CORRUGADA";
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        angulo = new javax.swing.JRadioButton();
        cuadrado = new javax.swing.JRadioButton();
        coldroll = new javax.swing.JRadioButton();
        lamina = new javax.swing.JRadioButton();
        laminaAnt = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ptr = new javax.swing.JRadioButton();
        pulido = new javax.swing.JRadioButton();
        redondo = new javax.swing.JRadioButton();
        solera = new javax.swing.JRadioButton();
        tubo = new javax.swing.JRadioButton();
        tubular = new javax.swing.JRadioButton();
        viga = new javax.swing.JRadioButton();
        monten = new javax.swing.JRadioButton();
        varilla = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTramos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMetros = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        tresoctavosMedida = new javax.swing.JRadioButton();
        mediaMedida = new javax.swing.JRadioButton();
        trescuartosmedida = new javax.swing.JRadioButton();
        pulgadaMedida = new javax.swing.JRadioButton();
        unauncuartomedida = new javax.swing.JRadioButton();
        unaymediamedida = new javax.swing.JRadioButton();
        dospulgadasmedida = new javax.swing.JRadioButton();
        dosymediamedida = new javax.swing.JRadioButton();
        tresymedidamedida = new javax.swing.JRadioButton();
        cuatromedida = new javax.swing.JRadioButton();
        cincomedida = new javax.swing.JRadioButton();
        seispulgadasmedida = new javax.swing.JRadioButton();
        trespulgadasmedida = new javax.swing.JRadioButton();
        ochomedida = new javax.swing.JRadioButton();
        diezmedida = new javax.swing.JRadioButton();
        docemedida = new javax.swing.JRadioButton();
        C30 = new javax.swing.JRadioButton();
        C40 = new javax.swing.JRadioButton();
        tresdiesiceisespesor = new javax.swing.JRadioButton();
        uncuartoespesor = new javax.swing.JRadioButton();
        cal10 = new javax.swing.JRadioButton();
        cal12 = new javax.swing.JRadioButton();
        cal14 = new javax.swing.JRadioButton();
        cal16 = new javax.swing.JRadioButton();
        cal18 = new javax.swing.JRadioButton();
        cal20 = new javax.swing.JRadioButton();
        cal26 = new javax.swing.JRadioButton();
        azul = new javax.swing.JRadioButton();
        blanco = new javax.swing.JRadioButton();
        verde = new javax.swing.JRadioButton();
        rojo = new javax.swing.JRadioButton();
        trespiesmedida = new javax.swing.JRadioButton();
        cuatropiesmedida = new javax.swing.JRadioButton();
        unoctavo = new javax.swing.JRadioButton();
        sieteoctavosespesor = new javax.swing.JRadioButton();
        dosporunamedida = new javax.swing.JRadioButton();
        trespordosmedida = new javax.swing.JRadioButton();
        cuatropordosmedida = new javax.swing.JRadioButton();
        tresporunaymedia = new javax.swing.JRadioButton();
        cuatroporunaymediamedida = new javax.swing.JRadioButton();
        cincooctavosmedida = new javax.swing.JRadioButton();
        opcionCanal = new javax.swing.JRadioButton();
        sieteoctavosmedida = new javax.swing.JRadioButton();
        unatrescuartos = new javax.swing.JRadioButton();
        cincodiesiceisespesor = new javax.swing.JRadioButton();
        tresoctavosespesor = new javax.swing.JRadioButton();
        mediaespesor = new javax.swing.JRadioButton();
        cincooctavosespesor = new javax.swing.JRadioButton();
        unapulgadaespesor = new javax.swing.JRadioButton();
        unauncuartoespesor = new javax.swing.JRadioButton();
        unaymediaespesor = new javax.swing.JRadioButton();
        dospulgadasespesor = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventarios = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        trescuartosespesor = new javax.swing.JRadioButton();
        cerrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        VIGAIPR = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtImpuesto = new javax.swing.JTextField();
        pulgadas = new javax.swing.JCheckBox();
        imprimir = new javax.swing.JButton();
        imprimible = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton18 = new javax.swing.JRadioButton();
        jRadioButton19 = new javax.swing.JRadioButton();
        jRadioButton20 = new javax.swing.JRadioButton();
        jRadioButton21 = new javax.swing.JRadioButton();
        na = new javax.swing.JRadioButton();
        fac = new javax.swing.JCheckBox();
        abrasivos = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(angulo);
        angulo.setText("ANGULO");
        angulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anguloActionPerformed(evt);
            }
        });

        buttonGroup1.add(cuadrado);
        cuadrado.setText("CUADRADO");
        cuadrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadradoActionPerformed(evt);
            }
        });

        buttonGroup1.add(coldroll);
        coldroll.setText("COLD ROLL");
        coldroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coldrollActionPerformed(evt);
            }
        });

        buttonGroup1.add(lamina);
        lamina.setText("LÁMINA");
        lamina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laminaActionPerformed(evt);
            }
        });

        buttonGroup1.add(laminaAnt);
        laminaAnt.setText("LÁMINA ANT");
        laminaAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laminaAntActionPerformed(evt);
            }
        });

        jLabel1.setText("      NOMBRE");

        jLabel2.setText("     MEDIDA");

        buttonGroup1.add(ptr);
        ptr.setText("PTR");
        ptr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ptrActionPerformed(evt);
            }
        });

        buttonGroup1.add(pulido);
        pulido.setText("PULIDO");
        pulido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulidoActionPerformed(evt);
            }
        });

        buttonGroup1.add(redondo);
        redondo.setText("REDONDO");
        redondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redondoActionPerformed(evt);
            }
        });

        buttonGroup1.add(solera);
        solera.setText("SOLERA");
        solera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soleraActionPerformed(evt);
            }
        });

        buttonGroup1.add(tubo);
        tubo.setText("TUBO");
        tubo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuboActionPerformed(evt);
            }
        });

        buttonGroup1.add(tubular);
        tubular.setText("TUBULAR");
        tubular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tubularActionPerformed(evt);
            }
        });

        buttonGroup1.add(viga);
        viga.setText("VIGA");
        viga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vigaActionPerformed(evt);
            }
        });

        buttonGroup1.add(monten);
        monten.setText("POLÍN MONTÉN");
        monten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montenActionPerformed(evt);
            }
        });

        buttonGroup1.add(varilla);
        varilla.setText("VARILLA CORR");
        varilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varillaActionPerformed(evt);
            }
        });

        jLabel3.setText("  ESPESOR");

        jLabel4.setText("TRAMOS");

        jLabel5.setText("METROS");

        btnCalcular.setText("CALCULAR");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel6.setText("PESO");

        jLabel7.setText("PRECIO");

        jLabel8.setText("IVA");

        buttonGroup2.add(tresoctavosMedida);
        tresoctavosMedida.setText("3/8");
        tresoctavosMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresoctavosMedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(mediaMedida);
        mediaMedida.setText("1/2");

        buttonGroup2.add(trescuartosmedida);
        trescuartosmedida.setText("3/4");
        trescuartosmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trescuartosmedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(pulgadaMedida);
        pulgadaMedida.setText("1");

        buttonGroup2.add(unauncuartomedida);
        unauncuartomedida.setText("1 1/4");
        unauncuartomedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unauncuartomedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(unaymediamedida);
        unaymediamedida.setText("1 1/2");
        unaymediamedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unaymediamedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(dospulgadasmedida);
        dospulgadasmedida.setText("2");
        dospulgadasmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dospulgadasmedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(dosymediamedida);
        dosymediamedida.setText("2 1/2");

        buttonGroup2.add(tresymedidamedida);
        tresymedidamedida.setText("3 1/2");
        tresymedidamedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresymedidamedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(cuatromedida);
        cuatromedida.setText("4");
        cuatromedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatromedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(cincomedida);
        cincomedida.setText("5");
        cincomedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincomedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(seispulgadasmedida);
        seispulgadasmedida.setText("6");
        seispulgadasmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seispulgadasmedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(trespulgadasmedida);
        trespulgadasmedida.setText("3");
        trespulgadasmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trespulgadasmedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(ochomedida);
        ochomedida.setText("8");
        ochomedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochomedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(diezmedida);
        diezmedida.setText("10");

        buttonGroup2.add(docemedida);
        docemedida.setText("12");

        buttonGroup3.add(C30);
        C30.setText("C30");

        buttonGroup3.add(C40);
        C40.setText("C40");
        C40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C40ActionPerformed(evt);
            }
        });

        buttonGroup3.add(tresdiesiceisespesor);
        tresdiesiceisespesor.setText("3/16");

        buttonGroup3.add(uncuartoespesor);
        uncuartoespesor.setText("1/4");

        buttonGroup3.add(cal10);
        cal10.setText("CAL 10");

        buttonGroup3.add(cal12);
        cal12.setText("CAL 12");

        buttonGroup3.add(cal14);
        cal14.setText("CAL 14");
        cal14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cal14ActionPerformed(evt);
            }
        });

        buttonGroup3.add(cal16);
        cal16.setText("CAL 16");

        buttonGroup3.add(cal18);
        cal18.setText("CAL 18");

        buttonGroup3.add(cal20);
        cal20.setText("CAL 20");
        cal20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cal20ActionPerformed(evt);
            }
        });

        buttonGroup3.add(cal26);
        cal26.setText("CAL 26");

        buttonGroup3.add(azul);
        azul.setText("AZUL");

        buttonGroup3.add(blanco);
        blanco.setText("BLANCO");

        buttonGroup3.add(verde);
        verde.setText("VERDE");

        buttonGroup3.add(rojo);
        rojo.setText("ROJO");
        rojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rojoActionPerformed(evt);
            }
        });

        buttonGroup2.add(trespiesmedida);
        trespiesmedida.setText("3 PIES");

        buttonGroup2.add(cuatropiesmedida);
        cuatropiesmedida.setText("4 PIES");
        cuatropiesmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatropiesmedidaActionPerformed(evt);
            }
        });

        buttonGroup3.add(unoctavo);
        unoctavo.setText("1/8");

        buttonGroup3.add(sieteoctavosespesor);
        sieteoctavosespesor.setText("7/8");

        buttonGroup2.add(dosporunamedida);
        dosporunamedida.setText("2 X1");
        dosporunamedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosporunamedidaActionPerformed(evt);
            }
        });

        buttonGroup2.add(trespordosmedida);
        trespordosmedida.setText("3X2");

        buttonGroup2.add(cuatropordosmedida);
        cuatropordosmedida.setText("4X2");

        buttonGroup2.add(tresporunaymedia);
        tresporunaymedia.setText("3X1 1/2");

        buttonGroup2.add(cuatroporunaymediamedida);
        cuatroporunaymediamedida.setText("4X1 1/2");

        buttonGroup2.add(cincooctavosmedida);
        cincooctavosmedida.setText("5/8");

        buttonGroup1.add(opcionCanal);
        opcionCanal.setText("CANAL");
        opcionCanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionCanalActionPerformed(evt);
            }
        });

        buttonGroup2.add(sieteoctavosmedida);
        sieteoctavosmedida.setText("7/8");
        sieteoctavosmedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteoctavosmedidaActionPerformed(evt);
            }
        });

        buttonGroup1.add(unatrescuartos);
        unatrescuartos.setText("1 3/4");

        buttonGroup3.add(cincodiesiceisespesor);
        cincodiesiceisespesor.setText("5/16");

        buttonGroup3.add(tresoctavosespesor);
        tresoctavosespesor.setText("3/8");

        buttonGroup3.add(mediaespesor);
        mediaespesor.setText("1/2");

        buttonGroup3.add(cincooctavosespesor);
        cincooctavosespesor.setText("5/8");
        cincooctavosespesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincooctavosespesorActionPerformed(evt);
            }
        });

        buttonGroup3.add(unapulgadaespesor);
        unapulgadaespesor.setText("1");

        buttonGroup3.add(unauncuartoespesor);
        unauncuartoespesor.setText("1 1/4");

        buttonGroup3.add(unaymediaespesor);
        unaymediaespesor.setText("1 1/2");

        buttonGroup3.add(dospulgadasespesor);
        dospulgadasespesor.setText("2");

        tablaInventarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaInventarios);

        jLabel9.setText("TOTAL");

        buttonGroup3.add(trescuartosespesor);
        trescuartosespesor.setText("3/4");

        cerrar.setText("CERRAR");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        buttonGroup1.add(VIGAIPR);
        VIGAIPR.setText("VIGA IPR");
        VIGAIPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIGAIPRActionPerformed(evt);
            }
        });

        jLabel10.setText("I. C");

        pulgadas.setText("P\"");
        pulgadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulgadasActionPerformed(evt);
            }
        });

        imprimir.setText("SUMAR");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });

        imprimible.setText("IMPRIMIR");
        imprimible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimibleActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("C999");

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("C555");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("C556");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("C3871");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setText("C758");

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("C560");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setText("C778");

        buttonGroup2.add(jRadioButton8);
        jRadioButton8.setText("C710");

        buttonGroup2.add(jRadioButton9);
        jRadioButton9.setText("C2004");

        buttonGroup2.add(jRadioButton10);
        jRadioButton10.setText("C744");

        buttonGroup2.add(jRadioButton11);
        jRadioButton11.setText("C742 FAST CUT");

        buttonGroup2.add(jRadioButton12);
        jRadioButton12.setText("PFERD 4 1/2");

        buttonGroup2.add(jRadioButton13);
        jRadioButton13.setText("PFERD 14");

        buttonGroup2.add(jRadioButton14);
        jRadioButton14.setText("SOLDADURA");
        jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton14ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton15);
        jRadioButton15.setText("6013 1/8");
        jRadioButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton15ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton16);
        jRadioButton16.setText("6013 1/8 PN");

        buttonGroup2.add(jRadioButton17);
        jRadioButton17.setText("GUANTE");
        jRadioButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton17ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton18);
        jRadioButton18.setText("LARGO");
        jRadioButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton18ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton19);
        jRadioButton19.setText("GIS");

        buttonGroup3.add(jRadioButton20);
        jRadioButton20.setText("CORTO");
        jRadioButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton20ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton21);
        jRadioButton21.setText("ALAMBRE");

        buttonGroup3.add(na);
        na.setText("N/A");

        fac.setText("F");
        fac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facActionPerformed(evt);
            }
        });

        buttonGroup1.add(abrasivos);
        abrasivos.setText("ABRASIVOS");
        abrasivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrasivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cuadrado, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(coldroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(754, 754, 754)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(docemedida)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cuatroporunaymediamedida)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(trespiesmedida)
                                            .addComponent(trespulgadasmedida)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(diezmedida)
                                        .addGap(18, 18, 18)
                                        .addComponent(cuatropiesmedida))
                                    .addComponent(dosymediamedida))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(cincooctavosmedida)
                                                .addGap(55, 55, 55))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(dosporunamedida)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tresporunaymedia)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cuatropordosmedida)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(cincomedida)
                                                .addGap(12, 12, 12))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tresymedidamedida)
                                        .addGap(34, 34, 34)
                                        .addComponent(cuatromedida)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seispulgadasmedida)
                            .addComponent(ochomedida, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(428, 428, 428)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(opcionCanal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(trespordosmedida)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(azul)
                                                .addGap(18, 18, 18)
                                                .addComponent(blanco))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(unoctavo)
                                                    .addComponent(trescuartosespesor))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tresdiesiceisespesor)
                                                    .addComponent(sieteoctavosespesor))
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(uncuartoespesor)
                                                    .addComponent(unapulgadaespesor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jRadioButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jRadioButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(175, Short.MAX_VALUE)
                                                .addComponent(unatrescuartos))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(abrasivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(angulo, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                                .addComponent(tresoctavosMedida)))
                                        .addGap(18, 18, 18)
                                        .addComponent(mediaMedida)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(trescuartosmedida)
                                                .addGap(18, 18, 18)
                                                .addComponent(sieteoctavosmedida)
                                                .addGap(18, 18, 18)
                                                .addComponent(pulgadaMedida)))
                                        .addGap(18, 18, 18)
                                        .addComponent(unauncuartomedida)
                                        .addGap(18, 18, 18)
                                        .addComponent(unaymediamedida)
                                        .addGap(18, 18, 18)
                                        .addComponent(dospulgadasmedida, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                        .addComponent(C30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(C40)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cal10, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cal18, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(laminaAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton11)
                                .addGap(91, 91, 91)
                                .addComponent(jRadioButton17)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cal12)
                                .addGap(18, 18, 18)
                                .addComponent(cal14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cal16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cal20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cal26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(na))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(verde)
                                        .addGap(18, 18, 18)
                                        .addComponent(rojo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cincodiesiceisespesor)
                                        .addGap(18, 18, 18)
                                        .addComponent(tresoctavosespesor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(mediaespesor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cincooctavosespesor))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(unauncuartoespesor)
                                        .addGap(18, 18, 18)
                                        .addComponent(unaymediaespesor)
                                        .addGap(10, 10, 10)
                                        .addComponent(dospulgadasespesor))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jRadioButton20)
                                        .addGap(31, 31, 31)
                                        .addComponent(jRadioButton18)
                                        .addGap(41, 41, 41))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lamina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pulido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ptr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(redondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(solera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tubo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tubular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(monten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(varilla)
                            .addComponent(VIGAIPR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton14)
                                        .addGap(6, 6, 6)
                                        .addComponent(jRadioButton21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButton13)
                                        .addGap(60, 60, 60)
                                        .addComponent(jRadioButton19)))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtImpuesto, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMetros, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                            .addComponent(txtTramos)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))))
                            .addComponent(btnCalcular)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pulgadas)
                            .addComponent(fac)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imprimible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(C40)
                            .addComponent(C30)
                            .addComponent(cal10)
                            .addComponent(cal12)
                            .addComponent(cal14)
                            .addComponent(cal16)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTramos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tresoctavosMedida)
                                    .addComponent(mediaMedida)
                                    .addComponent(trescuartosmedida)
                                    .addComponent(sieteoctavosmedida)
                                    .addComponent(pulgadaMedida)
                                    .addComponent(unauncuartomedida)
                                    .addComponent(unaymediamedida)
                                    .addComponent(dospulgadasmedida)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(abrasivos)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(angulo)
                                    .addComponent(unatrescuartos))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(opcionCanal)
                                    .addComponent(trespordosmedida))
                                .addGap(18, 18, 18)
                                .addComponent(cuadrado)
                                .addGap(18, 18, 18)
                                .addComponent(coldroll)
                                .addGap(18, 18, 18)
                                .addComponent(laminaAnt)
                                .addGap(18, 18, 18)
                                .addComponent(lamina)
                                .addGap(18, 18, 18)
                                .addComponent(pulido)
                                .addGap(18, 18, 18)
                                .addComponent(ptr)
                                .addGap(18, 18, 18)
                                .addComponent(redondo)
                                .addGap(18, 18, 18)
                                .addComponent(solera)
                                .addGap(18, 18, 18)
                                .addComponent(tubo)
                                .addGap(18, 18, 18)
                                .addComponent(tubular)
                                .addGap(18, 18, 18)
                                .addComponent(viga)
                                .addGap(18, 18, 18)
                                .addComponent(VIGAIPR)
                                .addGap(18, 18, 18)
                                .addComponent(monten)
                                .addGap(20, 20, 20)
                                .addComponent(varilla))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(cuatroporunaymediamedida)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(docemedida)
                                        .addGap(21, 21, 21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(cincomedida)
                                                .addGap(18, 18, 18)
                                                .addComponent(cuatropordosmedida))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addComponent(trespulgadasmedida)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(tresporunaymedia)
                                                            .addComponent(trespiesmedida)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(tresymedidamedida)
                                                            .addComponent(cuatromedida)
                                                            .addComponent(dosymediamedida))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(dosporunamedida)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(cincooctavosmedida)
                                                    .addComponent(cuatropiesmedida))))
                                        .addGap(5, 5, 5)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(207, 207, 207)
                                        .addComponent(btnCalcular)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLimpiar)
                                        .addGap(18, 18, 18)
                                        .addComponent(cerrar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(btnActualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(imprimible)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(imprimir))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(100, 100, 100)
                                                        .addComponent(diezmedida))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(na)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jRadioButton1)
                                                    .addComponent(jRadioButton4)
                                                    .addComponent(jRadioButton7)
                                                    .addComponent(jRadioButton10)
                                                    .addComponent(jRadioButton13)
                                                    .addComponent(jRadioButton16)
                                                    .addComponent(jRadioButton19)
                                                    .addComponent(jRadioButton20)
                                                    .addComponent(jRadioButton18))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jRadioButton2)
                                                    .addComponent(jRadioButton5)
                                                    .addComponent(jRadioButton8)
                                                    .addComponent(jRadioButton11)
                                                    .addComponent(jRadioButton17))
                                                .addGap(1, 1, 1))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRadioButton15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton3)
                                            .addComponent(jRadioButton6)
                                            .addComponent(jRadioButton9)
                                            .addComponent(jRadioButton12)
                                            .addComponent(jRadioButton21)
                                            .addComponent(jRadioButton14)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(txtMetros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cal18)
                                                .addComponent(cal20)
                                                .addComponent(cal26)
                                                .addComponent(pulgadas))
                                            .addComponent(seispulgadasmedida, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(blanco)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(azul)
                                                        .addComponent(verde)
                                                        .addComponent(rojo)
                                                        .addComponent(ochomedida)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(unoctavo)
                                                    .addComponent(tresdiesiceisespesor)
                                                    .addComponent(uncuartoespesor)
                                                    .addComponent(cincodiesiceisespesor)
                                                    .addComponent(tresoctavosespesor)
                                                    .addComponent(mediaespesor)
                                                    .addComponent(cincooctavosespesor))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(unapulgadaespesor)
                                                    .addComponent(unauncuartoespesor)
                                                    .addComponent(sieteoctavosespesor)
                                                    .addComponent(unaymediaespesor)
                                                    .addComponent(dospulgadasespesor)
                                                    .addComponent(trescuartosespesor)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel7)
                                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8)
                                                    .addComponent(fac))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9))))))
                                .addGap(17, 17, 17)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void redondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redondoActionPerformed
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(true);
        cincooctavosmedida.setVisible(true);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(true);
        unatrescuartos.setVisible(true);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
        na.setVisible(true);
    }//GEN-LAST:event_redondoActionPerformed

    private void vigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vigaActionPerformed
        na.setVisible(true);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false); 
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(true);
        diezmedida.setVisible(true);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_vigaActionPerformed

    private void varillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varillaActionPerformed
        na.setVisible(true);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(true);
        cincooctavosmedida.setVisible(true);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        seispulgadasmedida.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_varillaActionPerformed
/////                                                                                                         AQUI MERO ESTA LO BUENO
    
    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        //inventarios i=new inventarios();
       
         String [] datos= new String[8];
       // if(!abrasivos.isSelected()){
        //tablaInventarios.setVisible(true);
      
        String nombre1="",medida1="",espesor1="";
       boolean bandera=false;
       
        estado();
        estadoMedida();
        estadoEspesor();
        if(lamina.isSelected()||laminaAnt.isSelected()){
            
           constante= uno/tres;
        }else if(viga.isSelected()||VIGAIPR.isSelected()||varilla.isSelected()||opcionCanal.isSelected()){
           
          constante=1/doce;
                
            
        }else{
            constante=a/b;
        }
        double metro;
        double cant;
        double impuesto=0;
         if(txtImpuesto.getText().isEmpty()){impuesto=0;}
        else{
         impuesto=Double.parseDouble(txtImpuesto.getText());
        }
        if(txtMetros.getText().isEmpty()){metro=0;}
        else{
         metro=Double.parseDouble(txtMetros.getText());
        }
        if(pulgadas.isSelected()){
            metro=metro/p/100;
        }
        if(txtTramos.getText().isEmpty()){cant=0;}
        else{
         cant=Double.parseDouble(txtTramos.getText());
        }
        if(!validarEntero(txtTramos.getText())&&!txtTramos.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"SOLO SE ACEPTAN TRAMOS ENTEROS");
           return;
        }
        if(estado.isEmpty()||medida.isEmpty()){
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR DATOS");
            return;
        }
       
      if(fac.isSelected()){
          ivas=1.08;
      }else{
          ivas=1.16;
      }
      double ant,lo;
      if(opcionCanal.isSelected()||viga.isSelected()||VIGAIPR.isSelected()||tubo.isSelected()){
          ant=0;
      }else{
          ant=.22;
      }
        lo=ant+ivas;
        try {
            if(!archivo.exists()){
            archivo.createNewFile();
        }
             BufferedWriter escribe= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo,true), "utf-8"));
        //JOptionPane.showMessageDialog(null,"DATOS SON"+estado+medida+espesor);
        for(TxtEnJava n: productos){
       /*
        Cotizacion co=new Cotizacion(nombre,medida,espesor,Double.parseDouble(precio),Double.parseDouble(cantidad),Double.parseDouble(metros));
         cotizar.add(co);*/
       
       if(n.nombre.equals(estado)&&n.medida.equals(medida)&&n.espesor.equals(espesor)){
          
           double auxi=metro*constante*n.peso;
           
           auxi=auxi*(n.precio+impuesto)*lo;
           precioAux=(n.precio+impuesto)*(n.peso*cant*ivas)+auxi;
           double iva=precioAux/ivas;
           txtIva.setText("$"+String.valueOf(Math.round((precioAux-iva)*100d)/100d));
           txtPrecio.setText("$"+String.valueOf(Math.round(iva*100d)/100d));
           txtTotal.setText("$"+String.valueOf(Math.round(precioAux*100d)/100d));
           txtPeso.setText(String.valueOf(Math.round(n.peso*(cant+(metro*constante))*100d)/100d)+ " KG");
           nombre1=n.nombre;
           medida1=n.medida;  
           espesor1=n.espesor;
           Cotizacion co=new Cotizacion(nombre1,medida1,espesor1,precioAux,cant,metro,iva,precioAux-iva,auxi); 
           cotizar.add(co);
           escribe.write(nombre1+"|"+medida1+"|"+espesor1+"|"+metro+"|"+cant+"|"+iva+"|"+(precioAux-iva)+"|"+precioAux+"|"+auxi+"|\r\n");
           bandera=true; 
           
           break;
              
          
       }
       
        }
         escribe.close();
        
       
       if(bandera==false){
           JOptionPane.showMessageDialog(null,"MATERIAL NO AGREGADO EN INVENTARIO");
          return;
       }
         
       /* else{
           String auxiliar="",nombre="";
         if(jRadioButton1.isSelected()){
            auxiliar="C999"; 
         } else if(jRadioButton2.isSelected()){
            auxiliar="C555"; 
         }  else if(jRadioButton3.isSelected()){
            auxiliar="C556"; 
         }  else if(jRadioButton4.isSelected()){
            auxiliar="C3871"; 
         }  else if(jRadioButton5.isSelected()){
            auxiliar="C758"; 
         }  else if(jRadioButton6.isSelected()){
            auxiliar="C560"; 
         }  else if(jRadioButton7.isSelected()){
            auxiliar="C778"; 
         }  else if(jRadioButton8.isSelected()){
            auxiliar="C710"; 
         }  else if(jRadioButton9.isSelected()){
            auxiliar="C2004"; 
         }  else if(jRadioButton10.isSelected()){
            auxiliar="C744"; 
         }  else if(jRadioButton11.isSelected()){
            auxiliar="C742"; 
         }  else if(jRadioButton12.isSelected()){
            auxiliar="PFERD4 1/2"; 
         }  else if(jRadioButton13.isSelected()){
            auxiliar="PFERD14"; 
         }  else if(jRadioButton14.isSelected()){
            nombre="SOLDADURA";
            
         }  else if(jRadioButton15.isSelected()){
            auxiliar="6013"; 
         }  else if(jRadioButton16.isSelected()){
            auxiliar="SOLDADURA60131/8PN"; 
         }  else if(jRadioButton17.isSelected()){
            
          
         }  else if(jRadioButton18.isSelected()){
            auxiliar="GUANTE LARGO"; 
         }  else if(jRadioButton19.isSelected()){
           
            auxiliar="GIS";
         }  else if(jRadioButton20.isSelected()){
            auxiliar="GUANTE CORTO"; 
         }  else if(jRadioButton21.isSelected()){
            auxiliar="ALAMBRE";
           
         }
         for(TxtEnJava n:productos){
             
             if(n.nombre.equals(auxiliar)){
                JOptionPane.showMessageDialog(null," encontrado"+n.nombre);
                        
                 break;
             }
         }
        }*/
       
      
       for(int i=0;i<cotizar.size();i++)
          {
        
       
        datos[0]=cotizar.get(i).nombre;
        datos[1]=cotizar.get(i).medida;
        datos[2]=cotizar.get(i).espesor;
        datos[3]=String.valueOf(cotizar.get(i).metros);
        datos[4]=String.valueOf(cotizar.get(i).cantidad);
        datos[5]=String.valueOf(Math.round(cotizar.get(i).subtotal*100d)/100d);
        datos[6]=String.valueOf(Math.round(cotizar.get(i).iva*100d)/100d);
        datos[7]=String.valueOf(Math.round(cotizar.get(i).precio*100d)/100d);
        
       
         }
       
         modelo.addRow(datos);
         tablaInventarios.setModel(modelo);
       
       
                  
        
        } catch (Exception e) {
            
        }
        
        
       //modelo.setValueAt(aux, tablaInventarios.getRowCount()+1,5);
        //cotizar.clear();
         
       
        
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void tresoctavosMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresoctavosMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tresoctavosMedidaActionPerformed

    private void seispulgadasmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seispulgadasmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seispulgadasmedidaActionPerformed

    private void trespulgadasmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trespulgadasmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trespulgadasmedidaActionPerformed

    private void ochomedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochomedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ochomedidaActionPerformed

    private void C40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C40ActionPerformed

    private void cal14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cal14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cal14ActionPerformed

    private void cal20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cal20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cal20ActionPerformed

    private void rojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rojoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rojoActionPerformed

    private void anguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anguloActionPerformed
       na.setVisible(false);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(true);
        cincodiesiceisespesor.setVisible(true);
        tresoctavosespesor.setVisible(true);
        mediaespesor.setVisible(true);
        cincooctavosespesor.setVisible(true);
        trescuartosespesor.setVisible(true);
        sieteoctavosespesor.setVisible(true);
        unapulgadaespesor.setVisible(true);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(true);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(true);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_anguloActionPerformed

    private void cuadradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadradoActionPerformed
        na.setVisible(true); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(true);
        cincooctavosmedida.setVisible(true);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_cuadradoActionPerformed

    private void coldrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coldrollActionPerformed
        na.setVisible(true); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(true);
        cincooctavosmedida.setVisible(true);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(true);
        
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_coldrollActionPerformed

    private void laminaAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laminaAntActionPerformed
        na.setVisible(false);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(true);
        cal12.setVisible(true);
        cal14.setVisible(true);
        cal16.setVisible(true);
        cal18.setVisible(true);
        cal20.setVisible(true);
        cal26.setVisible(true);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(true);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(true);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(true);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_laminaAntActionPerformed

    private void laminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laminaActionPerformed
        na.setVisible(false); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(true);
        cal12.setVisible(true);
        cal14.setVisible(true);
        cal16.setVisible(true);
        cal18.setVisible(true);
        cal20.setVisible(true);
        cal26.setVisible(true);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(true);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(true);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(true);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_laminaActionPerformed

    private void ptrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ptrActionPerformed
        na.setVisible(false); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(true);
        verde.setVisible(true);
        blanco.setVisible(true);
        rojo.setVisible(true);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(true);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(true);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(true);
        tresymedidamedida.setVisible(true);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(true);
        cuatroporunaymediamedida.setVisible(true);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_ptrActionPerformed

    private void pulidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulidoActionPerformed
        na.setVisible(true); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(true);
        cincooctavosmedida.setVisible(true);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_pulidoActionPerformed

    private void soleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soleraActionPerformed
        na.setVisible(false);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(true);
        cincodiesiceisespesor.setVisible(true);
        tresoctavosespesor.setVisible(true);
        mediaespesor.setVisible(true);
        cincooctavosespesor.setVisible(true);
        trescuartosespesor.setVisible(true);
        sieteoctavosespesor.setVisible(true);
        unapulgadaespesor.setVisible(true);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(true);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(true);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(true);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_soleraActionPerformed

    private void tuboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuboActionPerformed
        na.setVisible(false); 
        C30.setVisible(true);
        C40.setVisible(true);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(true);
        cal20.setVisible(true);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(true);
        diezmedida.setVisible(true);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(true);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_tuboActionPerformed

    private void tubularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tubularActionPerformed
        na.setVisible(false); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(true);
        cal18.setVisible(true);
        cal20.setVisible(true);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(true);
        unaymediamedida.setVisible(true);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(true);
        dospulgadasmedida.setVisible(true);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(true);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(true);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(true);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(true);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);

    }//GEN-LAST:event_tubularActionPerformed

    private void montenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montenActionPerformed
        na.setVisible(false); 
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(true);
        cal16.setVisible(true);
        cal18.setVisible(true);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
         unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_montenActionPerformed

    private void opcionCanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionCanalActionPerformed
        na.setVisible(true);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(true);
        uncuartoespesor.setVisible(true);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(true);
        diezmedida.setVisible(true);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(true);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_opcionCanalActionPerformed

    private void cincooctavosespesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincooctavosespesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cincooctavosespesorActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         int filas=tablaInventarios.getRowCount();
         conta=0;
        for(int i=0;filas>i;i++){
          modelo.removeRow(0);
        
        }
        cotizar.clear();
        archivo.delete();
       JOptionPane.showMessageDialog(null,"HISTORIAL ELIMINADO");
       // tablaInventarios.setVisible(false);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_cerrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
                                                                                                         //Actualizar está aquí
         int i;
         double a1,aux1,b1,l1,c1,f1;
        constante=a/b;
        
         try{
                     
                      if(tablaInventarios.getSelectedRow()==-1){
                          JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR LA FILA A MODIFICAR");
                         
                      }else{
                          
                         BufferedWriter bw = new BufferedWriter(new FileWriter(FicheroProducto));
                      i=tablaInventarios.getSelectedRow();
                      for(TxtEnJava n: productos)
                      {
                          if(n.nombre.contains(tablaInventarios.getValueAt(i, 0).toString())
                                  &&n.medida.equals(tablaInventarios.getValueAt(i, 1).toString())
                                  &&n.espesor.equals(tablaInventarios.getValueAt(i, 2).toString())){
                             
                             if(n.nombre.contains("LAMINA")){
                                 constante=uno/tres;
                                         
                             }else if(n.nombre.contains("VIGA")||n.nombre.contains("VARILLA")){
                constante=1/doce;
                
            }
                              aux1=Double.parseDouble(tablaInventarios.getValueAt(i, 4).toString());
                             
                              a1=Double.parseDouble(tablaInventarios.getValueAt(i, 3).toString());
                              
                               b1=n.cantidad+(n.metros*constante);
                               f1=a1*constante;
                             
                               l1=aux1+f1;
                              
                              c1=b1-l1;
                            
                              n.cantidad=c1;
                              n.sum=n.cantidad;
                           if(n.cantidad<=0){
                               JOptionPane.showMessageDialog(null,"inventario agotado");
                           }
                          break;
                          }
                          
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
        
                   constante=a/b;
        
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void VIGAIPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIGAIPRActionPerformed
       na.setVisible(true);
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        tresoctavosespesor.setVisible(false);
        mediaespesor.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trescuartosespesor.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unapulgadaespesor.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediaespesor.setVisible(false);
        dospulgadasespesor.setVisible(false); 
        unauncuartomedida.setVisible(false);
        unaymediamedida.setVisible(false);
        docemedida.setVisible(true);
        diezmedida.setVisible(true);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        cincooctavosmedida.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        cincomedida.setVisible(true);
        cuatromedida.setVisible(true);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(true);
        pulgadaMedida.setVisible(false);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        seispulgadasmedida.setVisible(true);
        sieteoctavosmedida.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
    }//GEN-LAST:event_VIGAIPRActionPerformed

    private void pulgadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulgadasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pulgadasActionPerformed
    public void generarPdf(){
     
    }
    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
       String [] dato=new String[8];
       if(conta>0){
           JOptionPane.showMessageDialog(null,"LA SUMA YA FUE HECHA");
           return;
       }
       int l=cotizar.size();
       sumatoriaIva=0;
       sumatoriaSub=0;
       sumatoriaTotal=0;
      double []dat = new double[8];
      double aux,a=0,f=0;
       double []auxo = new double[8];
        double []auxi = new double[8];
       
       if(cotizar.isEmpty()&&l==0){
           JOptionPane.showMessageDialog(null,"NO HAY DATOS");
           return;
       }
       boolean bandera=false;
      for(Cotizacion n: cotizar){
       
        Auxiliar an=new Auxiliar(n.nombre, n.medida, n.espesor, n.cantidad, n.metros,n.subtotal,n.iva,n.precio,n.auxiliar);
        temporal.add(an); 
      }
       cotizar.clear();
        conta++;
         String []datos = new String[8];
         
        for(int i=0; i<l;i++ ){
           id=(tablaInventarios.getValueAt(i,0).toString());
            medida=(tablaInventarios.getValueAt(i,1).toString());
            espesor=(tablaInventarios.getValueAt(i,2).toString());
            metros=Double.parseDouble(tablaInventarios.getValueAt(i,3).toString());
           cantidad=Double.parseDouble(tablaInventarios.getValueAt(i,4).toString());
          
            
          
            precio=Double.parseDouble(tablaInventarios.getValueAt(i,5).toString());
            uno=(Double.parseDouble(tablaInventarios.getValueAt(i,6).toString()));
            Double p=(Double.parseDouble(tablaInventarios.getValueAt(i,7).toString()));
          if(cantidad==temporal.get(i).cantidad){
              aux=1;
              f=cantidad;
          }else{
              aux=cantidad;
              f=1;
          }
          if(temporal.get(i).cantidad==0){
              
              a=1;
              }else if(temporal.get(i).cantidad>=1){
                  a=0;
              }
       
           p=(p-temporal.get(i).auxiliar);
           precio=(precio-temporal.get(i).auxiliar/1.16);
           uno=uno-((temporal.get(i).auxiliar/1.16-temporal.get(i).auxiliar)*-1);
         
          Cotizacion co= new Cotizacion(id, medida, espesor, (precio/(temporal.get(i).cantidad+a))*aux*f, cantidad, metros, (p/(temporal.get(i).cantidad+a))*aux*f,
                  (uno/(temporal.get(i).cantidad+a))*aux*f,temporal.get(i).auxiliar);
          //JOptionPane.showMessageDialog(null,(precio/(temporal.get(i).cantidad+a))*aux);
           //JOptionPane.showMessageDialog(null,(temporal.get(i).cantidad+a));
          cotizar.add(co);
        }
        temporal.clear();
         int filas=tablaInventarios.getRowCount();
        for(int i=0;filas>i;i++){
          modelo.removeRow(0);
        
        }
        
        for(int i=0; i<cotizar.size();i++ ){
            datos[0]=cotizar.get(i).nombre;
            datos[1]= cotizar.get(i).medida;
            datos[2]= cotizar.get(i).espesor;
            datos[3]=String.valueOf(cotizar.get(i).metros);
            datos[4]=String.valueOf(cotizar.get(i).cantidad);
            datos[5]=String.valueOf(Math.round((cotizar.get(i).precio+(cotizar.get(i).auxiliar/1.16))*100d)/100d);
            datos[6]=String.valueOf(Math.round((cotizar.get(i).iva+((cotizar.get(i).auxiliar/1.16-cotizar.get(i).auxiliar)*-1))*100d)/100d);
            datos[7]=String.valueOf(Math.round((cotizar.get(i).subtotal+cotizar.get(i).auxiliar)*100d)/100d);
            modelo.addRow(datos);
          
        } 
         tablaInventarios.setModel(modelo);
       
        
        for(int i=0;i<cotizar.size();i++){
         
           sumatoriaIva+=(Double.parseDouble(tablaInventarios.getValueAt(i,5).toString()));
            sumatoriaSub+=(Double.parseDouble(tablaInventarios.getValueAt(i,6).toString()));
            sumatoriaTotal+=(Double.parseDouble(tablaInventarios.getValueAt(i,7).toString()));
         
        }
       
           dato[5]="$"+String.valueOf(Math.round(sumatoriaIva*100d)/100d);
           dato[6]="$"+String.valueOf(Math.round(sumatoriaSub*100d)/100d);
           dato[7]="$"+String.valueOf(Math.round(sumatoriaTotal*100d)/100d);
        modelo.addRow(dato);
        tablaInventarios.setModel(modelo);
        conta++;
       
    }//GEN-LAST:event_imprimirActionPerformed

    private void imprimibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimibleActionPerformed
       
        try {
          
            MessageFormat cabeza= new MessageFormat("ACEROS 8 DE JULIO \r Av 8 de julio 1671 tel 36193663");
            
             MessageFormat foter= new MessageFormat("Su proovedor confiable, rapidez es nuestro compromiso");
             tablaInventarios.print(JTable.PrintMode.FIT_WIDTH,cabeza, foter);
           
        } catch (Exception e) {
        }
       

            

          

        
    }//GEN-LAST:event_imprimibleActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton15ActionPerformed

    private void jRadioButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton18ActionPerformed

    private void cuatromedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatromedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuatromedidaActionPerformed

    private void trescuartosmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trescuartosmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trescuartosmedidaActionPerformed

    private void sieteoctavosmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sieteoctavosmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sieteoctavosmedidaActionPerformed

    private void unauncuartomedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unauncuartomedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unauncuartomedidaActionPerformed

    private void unaymediamedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unaymediamedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unaymediamedidaActionPerformed

    private void dospulgadasmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dospulgadasmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dospulgadasmedidaActionPerformed

    private void tresymedidamedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresymedidamedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tresymedidamedidaActionPerformed

    private void cincomedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincomedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cincomedidaActionPerformed

    private void cuatropiesmedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatropiesmedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuatropiesmedidaActionPerformed

    private void dosporunamedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosporunamedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dosporunamedidaActionPerformed

    private void jRadioButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton20ActionPerformed

    private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton14ActionPerformed
         jRadioButton15.setVisible(true);
        jRadioButton16.setVisible(true);
        jRadioButton20.setVisible(false);
        jRadioButton18.setVisible(false);
    }//GEN-LAST:event_jRadioButton14ActionPerformed

    private void jRadioButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton17ActionPerformed
         jRadioButton20.setVisible(true);
        jRadioButton18.setVisible(true);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
    }//GEN-LAST:event_jRadioButton17ActionPerformed

    private void facActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facActionPerformed
        
    }//GEN-LAST:event_facActionPerformed

    private void abrasivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrasivosActionPerformed
       //funcion2();
    }//GEN-LAST:event_abrasivosActionPerformed
   public void funcion(){
        C30.setVisible(false);
        C40.setVisible(false);
        cal10.setVisible(false);
        cal12.setVisible(false);
        cal14.setVisible(false);
        cal16.setVisible(false);
        cal18.setVisible(false);
        cal20.setVisible(false);
        cal26.setVisible(false);
        azul.setVisible(false);
        verde.setVisible(false);
        blanco.setVisible(false);
        rojo.setVisible(false);
        tresdiesiceisespesor.setVisible(false);
        uncuartoespesor.setVisible(false);
        unoctavo.setVisible(false);
        cincodiesiceisespesor.setVisible(false);
        unauncuartomedida.setVisible(false);
        unauncuartoespesor.setVisible(false);
        unaymediamedida.setVisible(false);
        unaymediaespesor.setVisible(false);
        docemedida.setVisible(false);
        diezmedida.setVisible(false);
        dosporunamedida.setVisible(false);
        dospulgadasmedida.setVisible(false);
        dospulgadasespesor.setVisible(false);
        dosymediamedida.setVisible(false);
        trescuartosmedida.setVisible(false);
        trescuartosespesor.setVisible(false);
        cincooctavosmedida.setVisible(false);
        cincooctavosespesor.setVisible(false);
        trespiesmedida.setVisible(false);
        trespordosmedida.setVisible(false);
        trespulgadasmedida.setVisible(false);
        tresporunaymedia.setVisible(false);
        tresymedidamedida.setVisible(false);
        tresoctavosMedida.setVisible(false);
        tresoctavosespesor.setVisible(false);
        cincomedida.setVisible(false);
        cuatromedida.setVisible(false);
        cuatropordosmedida.setVisible(false);
        cuatroporunaymediamedida.setVisible(false);
        ochomedida.setVisible(false);
        pulgadaMedida.setVisible(false);
        unapulgadaespesor.setVisible(false);
        cuatropiesmedida.setVisible(false);
        mediaMedida.setVisible(false);
        mediaespesor.setVisible(false);
        seispulgadasmedida.setVisible(false);
        sieteoctavosmedida.setVisible(false);
        sieteoctavosespesor.setVisible(false);
        unatrescuartos.setVisible(false);
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jRadioButton8.setVisible(false);
        jRadioButton9.setVisible(false);
        jRadioButton10.setVisible(false);
        jRadioButton11.setVisible(false);
        jRadioButton12.setVisible(false);
        jRadioButton13.setVisible(false);
        jRadioButton14.setVisible(false);
        jRadioButton15.setVisible(false);
        jRadioButton16.setVisible(false);
        jRadioButton17.setVisible(false);
        jRadioButton18.setVisible(false);
        jRadioButton19.setVisible(false);
        jRadioButton20.setVisible(false);
        jRadioButton21.setVisible(false);
        
   }
   public void funcion2(){
       C30.setSelected(false);
        C40.setSelected(false);
        cal10.setSelected(false);
        cal12.setSelected(false);
        cal14.setSelected(false);
        cal16.setSelected(false);
        cal18.setSelected(false);
        cal20.setSelected(false);
        cal26.setSelected(false);
        azul.setSelected(false);
        verde.setSelected(false);
        blanco.setSelected(false);
        rojo.setSelected(false);
        tresdiesiceisespesor.setSelected(false);
        uncuartoespesor.setSelected(false);
        unoctavo.setSelected(false);
        cincodiesiceisespesor.setSelected(false);
        unauncuartomedida.setSelected(false);
        unauncuartoespesor.setSelected(false);
        unaymediamedida.setSelected(false);
        unaymediaespesor.setSelected(false);
        docemedida.setSelected(false);
        diezmedida.setSelected(false);
        dosporunamedida.setSelected(false);
        dospulgadasmedida.setSelected(false);
        dospulgadasespesor.setSelected(false);
        dosymediamedida.setSelected(false);
        trescuartosmedida.setSelected(false);
        trescuartosespesor.setSelected(false);
        cincooctavosmedida.setSelected(false);
        cincooctavosespesor.setSelected(false);
        trespiesmedida.setSelected(false);
        trespordosmedida.setSelected(false);
        trespulgadasmedida.setSelected(false);
        tresporunaymedia.setSelected(false);
        tresymedidamedida.setSelected(false);
        tresoctavosMedida.setSelected(false);
        tresoctavosespesor.setSelected(false);
        cincomedida.setSelected(false);
        cuatromedida.setSelected(false);
        cuatropordosmedida.setSelected(false);
        cuatroporunaymediamedida.setSelected(false);
        ochomedida.setSelected(false);
        pulgadaMedida.setSelected(false);
        unapulgadaespesor.setSelected(false);
        cuatropiesmedida.setSelected(false);
        mediaMedida.setSelected(false);
        mediaespesor.setSelected(false);
        seispulgadasmedida.setSelected(false);
        sieteoctavosmedida.setSelected(false);
        sieteoctavosespesor.setSelected(false);
        unatrescuartos.setSelected(false);
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        jRadioButton6.setSelected(false);
        jRadioButton7.setSelected(false);
        jRadioButton8.setSelected(false);
        jRadioButton9.setSelected(false);
        jRadioButton10.setSelected(false);
        jRadioButton11.setSelected(false);
        jRadioButton12.setSelected(false);
        jRadioButton13.setSelected(false);
        jRadioButton14.setSelected(false);
        jRadioButton15.setSelected(false);
        jRadioButton16.setSelected(false);
        jRadioButton17.setSelected(false);
        jRadioButton18.setSelected(false);
        jRadioButton19.setSelected(false);
        jRadioButton20.setSelected(false);
        jRadioButton21.setSelected(false);
   }
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
            java.util.logging.Logger.getLogger(cotizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cotizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cotizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cotizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cotizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton C30;
    public javax.swing.JRadioButton C40;
    public javax.swing.JRadioButton VIGAIPR;
    public javax.swing.JRadioButton abrasivos;
    public javax.swing.JRadioButton angulo;
    public javax.swing.JRadioButton azul;
    public javax.swing.JRadioButton blanco;
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnCalcular;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    public javax.swing.JRadioButton cal10;
    public javax.swing.JRadioButton cal12;
    public javax.swing.JRadioButton cal14;
    public javax.swing.JRadioButton cal16;
    public javax.swing.JRadioButton cal18;
    public javax.swing.JRadioButton cal20;
    public javax.swing.JRadioButton cal26;
    public javax.swing.JButton cerrar;
    public javax.swing.JRadioButton cincodiesiceisespesor;
    public javax.swing.JRadioButton cincomedida;
    public javax.swing.JRadioButton cincooctavosespesor;
    public javax.swing.JRadioButton cincooctavosmedida;
    public javax.swing.JRadioButton coldroll;
    public javax.swing.JRadioButton cuadrado;
    public javax.swing.JRadioButton cuatromedida;
    public javax.swing.JRadioButton cuatropiesmedida;
    public javax.swing.JRadioButton cuatropordosmedida;
    public javax.swing.JRadioButton cuatroporunaymediamedida;
    public javax.swing.JRadioButton diezmedida;
    public javax.swing.JRadioButton docemedida;
    public javax.swing.JRadioButton dosporunamedida;
    public javax.swing.JRadioButton dospulgadasespesor;
    public javax.swing.JRadioButton dospulgadasmedida;
    public javax.swing.JRadioButton dosymediamedida;
    public javax.swing.JCheckBox fac;
    public javax.swing.JButton imprimible;
    public javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JRadioButton jRadioButton10;
    public javax.swing.JRadioButton jRadioButton11;
    public javax.swing.JRadioButton jRadioButton12;
    public javax.swing.JRadioButton jRadioButton13;
    public javax.swing.JRadioButton jRadioButton14;
    public javax.swing.JRadioButton jRadioButton15;
    public javax.swing.JRadioButton jRadioButton16;
    public javax.swing.JRadioButton jRadioButton17;
    public javax.swing.JRadioButton jRadioButton18;
    public javax.swing.JRadioButton jRadioButton19;
    public javax.swing.JRadioButton jRadioButton2;
    public javax.swing.JRadioButton jRadioButton20;
    public javax.swing.JRadioButton jRadioButton21;
    public javax.swing.JRadioButton jRadioButton3;
    public javax.swing.JRadioButton jRadioButton4;
    public javax.swing.JRadioButton jRadioButton5;
    public javax.swing.JRadioButton jRadioButton6;
    public javax.swing.JRadioButton jRadioButton7;
    public javax.swing.JRadioButton jRadioButton8;
    public javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JRadioButton lamina;
    public javax.swing.JRadioButton laminaAnt;
    public javax.swing.JRadioButton mediaMedida;
    public javax.swing.JRadioButton mediaespesor;
    public javax.swing.JRadioButton monten;
    public javax.swing.JRadioButton na;
    public javax.swing.JRadioButton ochomedida;
    public javax.swing.JRadioButton opcionCanal;
    public javax.swing.JRadioButton ptr;
    public javax.swing.JRadioButton pulgadaMedida;
    public javax.swing.JCheckBox pulgadas;
    public javax.swing.JRadioButton pulido;
    public javax.swing.JRadioButton redondo;
    public javax.swing.JRadioButton rojo;
    public javax.swing.JRadioButton seispulgadasmedida;
    public javax.swing.JRadioButton sieteoctavosespesor;
    public javax.swing.JRadioButton sieteoctavosmedida;
    public javax.swing.JRadioButton solera;
    public javax.swing.JTable tablaInventarios;
    public javax.swing.JRadioButton trescuartosespesor;
    public javax.swing.JRadioButton trescuartosmedida;
    public javax.swing.JRadioButton tresdiesiceisespesor;
    public javax.swing.JRadioButton tresoctavosMedida;
    public javax.swing.JRadioButton tresoctavosespesor;
    public javax.swing.JRadioButton trespiesmedida;
    public javax.swing.JRadioButton trespordosmedida;
    public javax.swing.JRadioButton tresporunaymedia;
    public javax.swing.JRadioButton trespulgadasmedida;
    public javax.swing.JRadioButton tresymedidamedida;
    public javax.swing.JRadioButton tubo;
    public javax.swing.JRadioButton tubular;
    public javax.swing.JTextField txtImpuesto;
    public javax.swing.JTextField txtIva;
    public javax.swing.JTextField txtMetros;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPrecio;
    public javax.swing.JTextField txtTotal;
    public javax.swing.JTextField txtTramos;
    public javax.swing.JRadioButton unapulgadaespesor;
    public javax.swing.JRadioButton unatrescuartos;
    public javax.swing.JRadioButton unauncuartoespesor;
    public javax.swing.JRadioButton unauncuartomedida;
    public javax.swing.JRadioButton unaymediaespesor;
    public javax.swing.JRadioButton unaymediamedida;
    public javax.swing.JRadioButton uncuartoespesor;
    public javax.swing.JRadioButton unoctavo;
    public javax.swing.JRadioButton varilla;
    public javax.swing.JRadioButton verde;
    public javax.swing.JRadioButton viga;
    // End of variables declaration//GEN-END:variables

    
}
