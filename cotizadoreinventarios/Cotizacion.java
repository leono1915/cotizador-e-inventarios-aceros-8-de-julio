/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizadoreinventarios;

/**
 *
 * @author estrella
 */
public class Cotizacion {
    public     String    nombre,medida, espesor;
    public double auxiliar;
     public      double precio,subtotal,iva;
     public     double    cantidad,metros;  
 public Cotizacion(){
     
 }
    public Cotizacion(String nombre, String medida, String espesor, double precio, double cantidad, double metros,double subtotal,double iva,double auxiliar) {
        this.nombre = nombre;
        this.auxiliar=auxiliar;
        this.medida = medida;
        this.espesor = espesor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.metros = metros;
        this.subtotal=subtotal;
        this.iva=iva;
    }
}
