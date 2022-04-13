package ar.edu.iua.iw3.modelo.cache;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheService.IdentifiableValue;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Memcache extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        String path = req.getRequestURI();
        if (path.startsWith("/favicon.ico")) {
            return; // ignore the request for favicon.ico
        }

        String key = "count-concurrent";
        // Using the synchronous cache.
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

        // Write this value to cache using getIdentifiable and putIfUntouched.
        for (long delayMs = 1; delayMs < 1000; delayMs *= 2) {
            IdentifiableValue oldValue = syncCache.getIdentifiable(key);
            byte[] newValue = oldValue == null ? BigInteger.valueOf(0).toByteArray() : increment((byte[]) oldValue.getValue()); // newValue depends on old value
            resp.setContentType("text/plain");
            resp.getWriter().print("Value is " + new BigInteger(newValue).intValue() + "\n");
            if (oldValue == null) {
                // Key doesn't exist. We can safely put it in cache.
                syncCache.put(key, newValue);
                break;
            } else if (syncCache.putIfUntouched(key, oldValue, newValue)) {
                // newValue has been successfully put into cache.
                break;
            } else {
                // Some other client changed the value since oldValue was retrieved.
                // Wait a while before trying again, waiting longer on successive loops.
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    throw new ServletException("Error when sleeping", e);
                }
            }
        }
    }

    /**
     * Increments an integer stored as a byte array by one.
     * @param oldValue a byte array with the old value
     * @return         a byte array as the old value increased by one
     */
    private byte[] increment(byte[] oldValue) {
        long val = new BigInteger(oldValue).intValue();
        val++;
        return BigInteger.valueOf(val).toByteArray();
    }

}
