package me.shubhamjain.cachemanager.type;

public class CacheFactory {

    public static Cache getInstance(String cacheType, int size) throws InvalidCacheTypeException {
        if (cacheType.toLowerCase().equals("fifo"))
            return new FifoCache(size);
        else if (cacheType.toLowerCase().equals("lifo"))
            return new LifoCache(size);
        else if (cacheType.toLowerCase().equals("lru"))
            return new LruCache(size);
        else if (cacheType.toLowerCase().equals("lfu"))
            return new LfuCache(size);
        else
            throw new InvalidCacheTypeException("Please provide cache types among fifo, lifo and lru");
    }

}
