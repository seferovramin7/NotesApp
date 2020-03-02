package notesapp.demo.Service;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheCleanerService {
    private final CacheManager cacheManager;
    public CacheCleanerService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }
}
