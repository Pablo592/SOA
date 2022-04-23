package ar.edu.iua.iw3.modelo.cache;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;


@SuppressWarnings("serial")
public class Memcache{

private   MemcachedClient mcc;

    public Memcache() throws IOException {
        this.mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        // Connecting to Memcached server on localhost
}

    public String buscar(Long id){
        String dato = "";
        dato = (String) mcc.get(String.valueOf(id));
        return mcc.get(String.valueOf(id)) == null ? null : dato;
    }

    public String buscarLetras(String letras){
        String dato = "";
        dato = (String) mcc.get(letras);
        return dato == null ? null : dato;
    }



    public boolean agregar(Persona persona,int tiempo){
        return  mcc.add(String.valueOf(persona.getId()), tiempo, persona.getJson()).isDone();
    }

    public boolean agregarLista(String clave,String valor,int tiempo){
        boolean guardo = mcc.add(clave, tiempo,valor).isDone();
        return  guardo;
    }



    public boolean actualizar(Persona persona,int tiempo){
        return  mcc.set(String.valueOf(persona.getId()), tiempo, persona.getJson()).isDone();
    }


    public boolean eliminar(Long id){
        return mcc.delete(String.valueOf(id)).isDone();
    }
}