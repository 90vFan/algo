
public class HashTable<K, V> {
    /**
     * 散列表默认长度
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * 初始化散列表数组
     */
    private Entry<K, V>[] table;
    // 散列表元素数量
    private int size = 0;
    // 散列表索引数量
    private int use = 0;

    public HashTable() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    static class Entry<K, V> {
        K key;
        V value;

        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value) {
        int index = hash(key);

        // 位置未被引用
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> tmp = table[index];
        // 新增节点
        if (tmp.next == null) {
            tmp.next = new Entry<>(key, value, null);
            size++;
            use++;
            // 动态扩容
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        }
        // 解决散列冲突，使用链表法。相同hash,插入链表
        else {
            // 遍历链表，有相同key则覆盖
            do {
                tmp = tmp.next;
                // key 相同，覆盖旧的数据
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);
            // 遍历至链表末尾，添加新的key-value
            Entry<K, V> temp = table[index].next;
            table[index].next = new Entry<>(key, value, temp);
            size++;
        }
    }

    /**
     * 散列函数
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }

    /**
     * 扩容
     */
    private void resize() {
        Entry<K, V>[] oldeTable = table;
        // 创建新的table,扩容2倍
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        use = 0;
        // 遍历oldTable, 复制元素
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null) {
                continue;
            }
            // 复制oldTable[i]
            Entry<K, V> e = oldTable[i];
            // 遍历链表e
            while (e.next != null) {
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null) {
                    // 新的散列table, 索引数量+1
                    use++;
                    table[index] = new Entry<>(null, null, null);
                }
                // 复制oldTable[i]元素e key-value
                table[index].next = new Entry<>(e.key, e.value, table[index].next);
            }
        }
    }

    /**
     * 删除
     */
    public void remove(K key) {
        int index = hash(key);
        Entry e = table[index];
        if (e == null || e.next == null) {
            return;
        }

        Entry pre;
        Entry<K, V> headNode = table[index];

        do {
            pre = e;
            e = e.next;
            if (key == e.key) {
                pre.next = e.next;                // 删除链表节点
                size--;                           // 元素总数-1
                if (headNode.next == null) use--; // 如果索引位置链表为空，散列表索引数量-1
                return;
            }
        } while (e.next != null);                 // 遍历链表e
    }

    /**
     * 获取
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return null;
        }
        while (e.next != null) {                  // 遍历链表e
            e = e.next;
            if (key == e.key) {                   // 找到链表e类key所在节点，取出value
                return e.value;
            }
        }
        return null;
    }


}
