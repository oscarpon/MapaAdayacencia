/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solactividad6.mapaadyacencia;

/**
 *
 * @author oscar
 */
public class Vertice<E> {
    private E etiqueta;
    public Vertice(E etiqueta){
        this.etiqueta = etiqueta;
    }
    public E getEtiqueta(){
        return etiqueta;
    }
   
    public boolean equals(Object o){
        Vertice<E> v = (Vertice<E>) o;
        return etiqueta.equals(v.getEtiqueta());
    }
    public String toString(){
        return " "+etiqueta;
      }
}
