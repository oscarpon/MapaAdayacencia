/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solactividad6.mapaadyacencia;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author oscar
 */
public class MapaAdyacencia<E,T> implements Grafo<E,T> {
  
    private List<VerticeConMap<E, T>> listVertices;
    public int numVertices;

    public MapaAdyacencia() {
        listVertices = new LinkedList<>();
        numVertices = 0;
    }

    
    @Override
    public boolean esVacio() {
        if (numVertices == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        if (esVacio()) {
            return false;
        } else {
            VerticeConMap<E, T> aux;
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                aux = itr.next();

                if ((aux.getVertice().equals(a.getOrigen()))) {
//                    Map<Vertice<E>,T> aux2 = aux.getAdy();
                    if (aux.getAdy().get(a.getDestino())!=null && aux.getAdy().get(a.getDestino()).equals(a.getEtiqueta())) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v
    ) {
        Iterator toret = null;
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        VerticeConMap<E, T> aux;
        while (itr.hasNext()) {
            aux = itr.next();
            if (aux.getVertice().equals(v)) {
                toret = aux.getAdy().keySet().iterator();
            }
        }
        return toret;
    }

}
