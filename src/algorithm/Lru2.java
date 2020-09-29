package algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2020/9/17
 * @description  LinkedHashMao实现  所以开发者需要实现LRU算法只需要继承LinkedHashMap并重写removeEldestEntry方法
 */


//public class Lru2 extends LinkedHashMap<K, V> {
//
//    K o;
//    V d;
//    private final int CACHE_SIZE;
//
//    public Lru2(int cacheSize){
//        // true基于访问排序，false基于插入排序
//        super((int) (Math.ceil(cacheSize / 0.75) + 1),0.75f,true);
//        CACHE_SIZE = cacheSize;
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
//        return size() > CACHE_SIZE;
//    }
//
//
//}
