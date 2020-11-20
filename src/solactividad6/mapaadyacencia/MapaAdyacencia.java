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
    public boolean estaVertice(Vertice<E> v) {
        if (esVacio()) {
            return false;
        } else {
            VerticeConMap<E, T> aux;
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                aux = itr.next();
                if (aux.getVertice().equals(v)) {
                    return true;
                }
            }
            return false;
        }
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
                    if (aux.getAdy().get(a.getDestino())!=null && aux.getAdy().get(a.getDestino()).equals(a.getEtiqueta())) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        List<Vertice<E>> vertices = new LinkedList<>();
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        while (itr.hasNext()) {
            vertices.add(itr.next().getVertice());
        }
        return vertices.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        Set<Arco<E, T>> toret = new HashSet<>();
        for (VerticeConMap<E, T> vm : listVertices) {
            Vertice<E> inicio = vm.getVertice();
            Map<Vertice<E>, T> adyacentes = vm.getAdy();
            Iterator<Vertice<E>> it = adyacentes.keySet().iterator();
            while (it.hasNext()) {
                Vertice<E> fin = it.next();
                T etiqueta = adyacentes.get(fin);
                toret.add(new Arco<E, T>(inicio, fin, etiqueta));
            }
        }
        return toret.iterator();
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

    @Override
    public void insertarVertice(Vertice<E> v
    ) {
        if (!estaVertice(v)) {
            listVertices.add(new VerticeConMap<E, T>(v));
            numVertices++;
        } else {
            System.err.println("Ya existe");
        }
    }

    @Override
    public void insertarArco(Arco<E, T> a
    ) {
        if (!estaArco(a)) {
            if (!estaVertice(a.getOrigen())) {
                insertarVertice(a.getOrigen());
            }
            if (!estaVertice(a.getDestino())) {
                insertarVertice(a.getDestino());
            }
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                VerticeConMap<E, T> aux = itr.next();
                if (a.getOrigen() == aux.getVertice()) {
                    Map<Vertice<E>, T> map = aux.getAdy();
                    map.put(a.getDestino(), a.getEtiqueta());
                }
            }
        } else {
            System.err.println("Error");
        }

    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        if (estaVertice(v)) {
            while (itr.hasNext()) {
                if (itr.next().equals(v)) {
                    itr.remove();
                    numVertices--;
                } else {
                    Map<Vertice<E>, T> m = itr.next().getAdy();
                    m.remove(v);
                }
            }
        } else {
            System.err.println("No se encuentra");
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        if (estaArco(a)) {
            while (itr.hasNext()) {
                if (itr.next().getVertice().equals(a.getOrigen())) {
                    Map<Vertice<E>, T> m = itr.next().getAdy();
                    m.remove(a.getDestino());
                    numVertices--;
                }
            }
        } else {
            System.err.println("No se encuentra");
        }

    }

}
