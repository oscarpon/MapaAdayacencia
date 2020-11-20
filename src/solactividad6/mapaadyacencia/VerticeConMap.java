/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solactividad6.mapaadyacencia;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oscar
 */
public class VerticeConMap<E, T> {
    private Vertice<E> origen;
    private Map<Vertice<E>, T> mapAdy;

    VerticeConMap(Vertice<E> v)
    {
        origen=v;
        mapAdy = new HashMap<>();
    }
    Vertice<E> getVertice(){
        return origen;
    }
    Map<Vertice<E>, T> getAdy(){
           return mapAdy;
    }   
}
