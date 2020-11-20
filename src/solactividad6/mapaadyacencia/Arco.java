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
public class Arco<E,T> {
    private Vertice<E> origen;
    private Vertice<E> destino;
    private T etiqueta;

    public Arco(Vertice<E> vo, Vertice<E> vd, T etiq)
    {
        origen = vo;
        destino = vd;
        etiqueta = etiq;
    }

    
    public Vertice<E> getOrigen()
    {
        return origen;
    }
    public Vertice<E> getDestino()
    {
        return destino;
    }
    public T getEtiqueta()
    {
        return etiqueta;
    }
    public void setOrigen(Vertice<E> vo)
    {
        origen = vo;
    }
    public void setDestino (Vertice<E> vd)
    {
        destino = vd;
    }
    public void setEtiqueta(T etiq)
    {
        etiqueta = etiq;
    }
    public String toString(){
        return ""+origen+"-"+destino+": "+etiqueta;
    }
}
