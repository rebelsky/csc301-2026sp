import java.lang.Comparable;
import java.io.PrintWriter;

/**
 * Binary search trees.
 */
public class BST<K extends Comparable<K>, V> 
{
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  BSTNode<K,V> root = null;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Insert a new key/value pair in the tree (or replace an existing
   * one.
   */
  public void insert(K key, V value) {
    this.root = insert(this.root, key, value);
  } // insert(K, V)

  /**
   * Remove a key/value pair from the tree.
   */
  public void remove(K key) {
    this.root = remove(this.root, key);
  } // remove(K)

  /**
   * Dump the tree.
   */
  public void dump(PrintWriter pen) {
    dump(this.root, pen, "");
  } // dump

  // +----------------+----------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Insert a key/value into a BST.
   */
  BSTNode<K,V> insert(BSTNode<K,V> node, K key, V value) {
    // If the node is empty, build a new node
    if (null == node) {
      return new BSTNode<K,V>(key, value);
    }
    else {
      // If the key is already in the current place, replace it
      if (key.equals(node.key())) {
        node.setValue(value);
      } 
      // If the key is less than the key of the current place, work on the left
      else if (key.compareTo(node.key()) < 0) {
        node.setLeft(insert(node.left(), key, value));
      } 
      // If the key is greater than the key of the current place, work on the left
      else {
        node.setRight(insert(node.right(), key, value));
      } // inner if/else
    } // if/else
    return node;
  } // insert

  /**
   *  Remove the element with a particular key from a tree.
   */
  BSTNode<K,V> remove(BSTNode<K,V> node, K key) {
    return node;        // STUB
  } // remove(BSTNode<K,V>)
  
  /**
   * Remove the largest (rightmost) element from a BST.
   *
   * @return a pair that contains (a) the BST without the node
   *         that held the largest key and (b) that node.
   */
  Pair<BSTNode<K,V>,BSTNode<K,V>> removeLargest(BSTNode<K,V> node) {
    if (null == node.right()) {
      BSTNode<K,V> newTree = node.left();
      node.setLeft(null);
      return new Pair<BSTNode<K,V>,BSTNode<K,V>>(newTree, node);
    } else {
      Pair<BSTNode<K,V>,BSTNode<K,V>> tmp = removeLargest(node.right());
      node.setRight(tmp.first());
      return new Pair<BSTNode<K,V>,BSTNode<K,V>>(node, tmp.second());
    } // if/else
  } // removeLargest(BSTNode<K,V>)

  /**
   * Dump a tree.
   */
  void dump(BSTNode<K,V> node, PrintWriter pen, String prefix) {
    if (node != null) {
      pen.println(prefix 
          + node.key().toString() + ":" + node.value().toString()
          + " [" + node.height + "]");
      dump(node.left, pen, prefix + " l ");
      dump(node.right, pen, prefix + " r ");
    } // if
  } // dump
} // class BST
