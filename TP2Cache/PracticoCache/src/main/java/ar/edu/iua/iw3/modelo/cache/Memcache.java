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


/*
    public Object buscar(Long id){
        MemcacheService.IdentifiableValue datoBuscado = syncCache.getIdentifiable(id);
        return datoBuscado.getValue();
    }

    public void agregar(Persona persona,int tiempo){
        syncCache.put(persona.getId(), persona, Expiration.byDeltaMillis(tiempo));
        return;
    }

    public void actualizar(Persona persona,int tiempo){
        syncCache.putIfUntouched(persona.getId(), syncCache.getIdentifiable(persona.getId()), persona, Expiration.byDeltaMillis(tiempo));
        return;
    }


    public void eliminar(Long id){
        syncCache.delete(id);
        return;
    }

/*
    public void doGet() throws IOException,
            ServletException {

        String key = "count-concurrent";
        // Using the synchronous cache.
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

        // Write this value to cache using getIdentifiable and putIfUntouched.

            IdentifiableValue oldValue = syncCache.getIdentifiable(key);
            byte[] newValue = oldValue == null ? BigInteger.valueOf(0).toByteArray() : increment((byte[]) oldValue.getValue()); // newValue depends on old value
            if (oldValue == null) {
                // Key doesn't exist. We can safely put it in cache.
                syncCache.put(key, "valor nuevo");

            } else if (syncCache.putIfUntouched(key, oldValue, "valor actualizado")) {
                // newValue has been successfully put into cache.
            }

    }*/
   /* private byte[] increment(byte[] oldValue) {
        long val = new BigInteger(oldValue).intValue();
        val++;
        return BigInteger.valueOf(val).toByteArray();
    }

}

        */
