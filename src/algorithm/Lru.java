package algorithm;

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Lru.
 *
 * @author yangxc27652
 * @date 2020 /9/9
 * @description LRU实现
 */
public class Lru {

    Map<String, LruNode> map = new HashMap<>();

    LruNode head;

    LruNode tail;

   // 缓存最⼤大容量量，我们假设最⼤大容量量⼤大于 1，
   // 当然，⼩小于等于1的话需要多加⼀一些判断另⾏行行处理理

    int capacity;

    /**
     * Instantiates a new Lru cache.
     * @param capacity the capacity
     */
    public Lru(int capacity) {
        this.capacity = capacity;
    }


    public void put(String key, Object value) {
        if (head == null) {
            head = new LruNode(key, value);
            tail = head;
            map.put(key, head);
        }
        LruNode node = map.get(key);
        if (node != null) {
            // 更更新值
            node.value = value;
            // 把他从链表删除并且插⼊入到头结点
            removeAndInsert(node);
        } else {
            LruNode tmp = new LruNode(key, value);
            // 如果会溢出
            if (map.size() >= capacity) {
                // 先把它从哈希表中删除这⾥里里需要提醒的是，对于链表这种数据结构，头结点和尾节点是两个⽐比较特殊的点，如果要删除的节点
              //  是头结点或者尾节点，我们⼀一般要先对他们进⾏行行处理理。
               // 这⾥里里放⼀一下单链表版本的吧
                map.remove(tail.key);
                // 删除尾部节点
                tail = tail.pre;
                tail.next = null;
            }
            map.put(key, tmp);
            // 插⼊
            tmp.next = head;
            head.pre = tmp;
            head = tmp;
        }
    }


    public Object get(String key) {
        LruNode node = map.get(key);
        if (node != null) {
            // 把这个节点删除并插⼊入到头结点
            removeAndInsert(node);
            return node.value;
        }
        return null;
    }
    private void removeAndInsert(LruNode node) {
        // 特殊情况先判断，例例如该节点是头结点或是尾部节点
        if (node == head) {
            return;
        } else if (node == tail) {
            tail = node.pre;
            tail.next = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        // 插⼊入到头结点
        node.next = head;
        node.pre = null;
        head.pre = node;
        head = node;
    }

    private void display(Lru lru){
        if (head == null) {return;}
        LruNode lruNode = head;
        while (lruNode!=null){
            System.out.println(lruNode.key + lruNode.value);
            lruNode = lruNode.next;
        }
    }
    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        // 指定初始容量是5
        Lru lru = new Lru(5);
        lru.put("aa","11");
        lru.put("bb","11");
        lru.put("cc","11");
        lru.put("dd","11");
        lru.get("bb");
        lru.display(lru);
    }
}

/**
 * 链表节点的定义
 */
class LruNode{

    String key;

    Object value;

    LruNode next;

    LruNode pre;

    public LruNode(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}