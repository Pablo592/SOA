package ar.edu.iua.iw3.modelo.cache;

import ar.edu.iua.iw3.modelo.persistencia.Persona;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Memcache{

private  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();



    public Object buscar(Long id){
        MemcacheService.IdentifiableValue datoBuscado = syncCache.getIdentifiable(id);
        return datoBuscado.getValue();
    }

    public void agregar(Persona persona,int tiempo){
        syncCache.put(persona.getId(), persona, Expiration.byDeltaMillis(tiempo));
        return;
    }

    public void actalizar(Persona persona,int tiempo){
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

    }

    /**
     * Increments an integer stored as a byte array by one.
     * @param oldValue a byte array with the old value
     * @return         a byte array as the old value increased by one
     */
   /* private byte[] increment(byte[] oldValue) {
        long val = new BigInteger(oldValue).intValue();
        val++;
        return BigInteger.valueOf(val).toByteArray();
    }
*/
}
