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
  
  //Cremos una lista de vértices y la variable para saber el número de vértices.
    private List<VerticeConMap<E, T>> listVertices;
    public int numVertices;

//Constructor, donde inicializamos la lista vacía y el numero a 0
    public MapaAdyacencia() {
        listVertices = new LinkedList<>();
        numVertices = 0;
    }

/**Devuelve TRUE si la lista está vacia y FALSE en caso
contrario */
    @Override
    public boolean esVacio() {
        if (numVertices == 0) {
            return true;
        }
        return false;
    }

/**Devuelve TRUE si está el vértice que se le pasa como parámetro */
    @Override
    public boolean estaVertice(Vertice<E> v) {
        //Si está vacio retornamos false porque no está.
        if (esVacio()) {
            return false;
        } else {
            //Creamos un vértice auxiliar para poder recorrer.
            VerticeConMap<E, T> aux;
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                aux = itr.next();
                if (aux.getVertice().equals(v)) { //Si el vertice de aux es = a v erntonces devuelve true.
                    return true;
                }
            }
            return false;
        }
    }

/**Devuelve TRUE si está el arco y false en caso contrario */
    @Override
    public boolean estaArco(Arco<E, T> a) {
        if (esVacio()) {
            return false;
        } else {
            VerticeConMap<E, T> aux;
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                aux = itr.next(); //La creamos para recorrer el arco.
                //Comprobamosm que el vértice es igual al origen del arco a.
                if ((aux.getVertice().equals(a.getOrigen()))) {
                    //Si tiene un destino y además el destino del adyacente es igual al arco retorna true.
                    if (aux.getAdy().get(a.getDestino())!=null && aux.getAdy().get(a.getDestino()).equals(a.getEtiqueta())) {
                        return true;
                    }
                }

            }
            return false;
        }
    }

/**Devuelve un iterator de vértices */
    @Override
    public Iterator<Vertice<E>> vertices() {
        List<Vertice<E>> vertices = new LinkedList<>();
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        while (itr.hasNext()) {
            vertices.add(itr.next().getVertice());
        }
        return vertices.iterator();
    }

/**Devuelve un iterator de arcos */
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

/**Devuelve un iterator de adyacentes */
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

/**Inserta un vértice, si ya está ese vértice no deja insertar */
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

/**Inserta un arco. Si el arco ya existe devuelve error */
    @Override
    public void insertarArco(Arco<E, T> a
    ) {
        if (!estaArco(a)) {
            //Si no existe el origen, insertamos el origen del arco
            if (!estaVertice(a.getOrigen())) {
                insertarVertice(a.getOrigen());
            }
            //Si no existe el destino entonces insertamos el destino.
            if (!estaVertice(a.getDestino())) {
                insertarVertice(a.getDestino());
            }
            Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
            while (itr.hasNext()) {
                //Recorremos el iterator y una vez coincidan le asociamos una etiqueta.
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

/**Elimina un vértice si existe. */
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

/**Elimina un arco si existe. */
    @Override
    public void eliminarArco(Arco<E, T> a) {
        Iterator<VerticeConMap<E, T>> itr = listVertices.iterator();
        if (estaArco(a)) { //Primero comprobamos si está
            while (itr.hasNext()) {
                if (itr.next().getVertice().equals(a.getOrigen())) {//Una vez lo localizamos
                    Map<Vertice<E>, T> m = itr.next().getAdy();
                    m.remove(a.getDestino());//Eliminamos
                    numVertices--;
                }
            }
        } else {
            System.err.println("No se encuentra");
        }

    }

}
