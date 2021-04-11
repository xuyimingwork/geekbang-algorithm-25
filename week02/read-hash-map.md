# HashMap 小总结

hashtable java 10

java set classes
- hash set => 基于 hash map 实现
- tree set
  
java map classes
- hash map => hash node | tree node => put | get

---

直接在 vs code 中开了一个 `TestHashMap.java` 的文件，引入了 `HashMap`，点击跳转到了对应的源代码，貌似是 JDK 13，凑合看了。

那主要是看 `get` 和 `put` 方法。

## get

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}
```

这里就是对 `key` 进行了 `hash`，然后依据 `hash` 的值与 `key` 本身去获取节点。

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

这里 `hash` 函数返回了一个整型

```java
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

首先 `tab` 是一个 `Node<K,V>` 类型的数组，这里用 `tab` 的 `length` 减 `1` 和 `hash` 做了按位与后拿到了节点在 `tab` 中的下标。也就是这个 `hash` 值对应的第一个节点 `first`。

拿到了 `first` 后，如果 `first` 本身的 `key` 符合要求，那么 `first` 即为要寻找的节点，如果不是，则需要向后查找。

对于 `Node<K,V>` 节点本身，它可以是 `TreeNode<K,V>`，也可以就是 `Node<K,V>`。

对于前者，顾名思义，树节点寻找元素，调用对应的 `getTreeNode` 即可。后者则使用了链表的方式遍历寻找对应节点。

## put

`put` 相对复杂了一些

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

首先如果内部用于保存的数组 `table` 还未初始化，则进行初始化操作，即 `resize` 操作。然后，与 `get` 时相应的，用 `table` 的 `length` 减 `1` 与 `hash` 值做了按位与操作得到下标。

若下标对应的 `table[i]` 不存在，则直接放入新节点即可。若存在，说明出现了 `hash` 碰撞，则该位置已有计算的 `hash` 值，即要么本次设值为替换，要么为新增值时出现了 `hash` 碰撞。

这里的 `else` 部分大致可以分为两个部分，首先是拿到要插入节点的引用，然后将具体要插入的值放入。

```java
Node<K,V> e; K k;
if (p.hash == hash &&
    ((k = p.key) == key || (key != null && key.equals(k))))
    e = p;
else if (p instanceof TreeNode)
    e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
else {
    for (int binCount = 0; ; ++binCount) {
        if ((e = p.next) == null) {
            p.next = newNode(hash, key, value, null);
            if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                treeifyBin(tab, hash);
            break;
        }
        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k))))
            break;
        p = e;
    }
}
```

上面的代码获取要插入节点的引用：
- 如果首个节点是要找的节点，则拿到首个节点
- 如果是 `TreeNode` 节点，通过 `putTreeVal` 放置节点并拿到节点引用
- 对于普通的节点，则是链表形式在存储碰撞的 hash，当碰撞过多时，使用树处理碰撞节点