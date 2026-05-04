/**
 * AVL Trees. Like Binary search trees except they rebalance.
 */
public class AVL<K extends Comparable<K>,V> extends BST<K,V> {
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  BSTNode<K,V> insert(BSTNode<K,V> node, K key, V value) {
    node = super.insert(node, key, value);
    return node;
  } // insert

  BSTNode<K,V> remove(BSTNode<K,V> node, K key) {
    node = super.remove(node, key);
    return node;
  } // remove(BSTNode<K,V>, K)

  Pair<BSTNode<K,V>,BSTNode<K,V>> removeLargest(BSTNode<K,V> node) {
    Pair<BSTNode<K,V>,BSTNode<K,V>> tmp = super.removeLargest(node);
    return tmp;
  } // removeLargest(BSTNode<K,V>)

  BSTNode<K,V> rebalance(BSTNode<K,V> node) {
    return node;
  } // rebalance(BSTNode<K,V>)

  /**
   *     node            left
   *    /    \          /    \
   * left    t3  =>    t1   node
   *  / \                   /  \
   * t1 t2                 t2  t3
   */
  BSTNode<K,V> rotateRight(BSTNode<K,V> node) {
    BSTNode<K,V> left = node.left();
    node.setLeft(left.right());
    left.setRight(node);
    return left;
  } // rotateRight(BSTNode<K,V>)

  /**
   *     node            right
   *    /    \          /    \
   *   t1   right =>  node   t3
   *         / \      / \
   *        t2 t3    t1 t2
   */
  BSTNode<K,V> rotateLeft(BSTNode<K,V> node) {
    BSTNode<K,V> right = node.right();
    node.setRight(right.left());
    right.setLeft(node);
    return right;
  } // rotateLeft(BSTNode<K,V>)
} // class AVL<K,V>
