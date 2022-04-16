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
        if(mcc.get(String.valueOf(id)) != null)
        System.out.println(mcc.get(String.valueOf(id)) + " sacado del cache */*/*/*/*/*/*/*/*/*/*/*//*/");

        return mcc.get(String.valueOf(id)) == null ? null : (String) mcc.get(String.valueOf(id));
    }

    public boolean agregar(Persona persona,int tiempo){

        System.out.println(mcc.add(String.valueOf(persona.getId()), tiempo, persona.getJson()) + " guardado en el cache */*/*/*/*/*/*/*/*/*/*/*//*/");

        return  mcc.add(String.valueOf(persona.getId()), tiempo, persona).isDone();
    }


    public boolean actualizar(Persona persona,int tiempo){
        return  mcc.set(String.valueOf(persona.getId()), tiempo, persona.getJson()).isDone();
    }


    public boolean eliminar(Long id){
        return mcc.delete(String.valueOf(id)).isDone();
    }
}