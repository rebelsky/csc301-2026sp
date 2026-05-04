import java.lang.Math;
import java.lang.Comparable;

/**
 * Nodes in a binary search tree.
 */
class BSTNode<K extends Comparable<K>,V> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The key stored in the node.
   */
  K key;

  /**
   * The value stored in the node.
   */
  V value;

  /**
   * The left subtree. Set to null if there is no left subtree.
   */
  BSTNode<K,V> left;

  /**
   * The right subtree. Set to null if there is no right subtree.
   */
  BSTNode<K,V> right;

  /**
   * The height of the tree rooted at this node.
   */
  int height;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new node with specified key, value, left, and right.
   */
  BSTNode(K _key, V _value, BSTNode<K,V> _left, BSTNode<K,V> _right) {
    this.key = _key;
    this.value = _value;
    this.left = _left;
    this.right = _right;
    this.height = 1 + Math.max(height(this.left), height(this.right));
  } // BSTNode(K, V, BSTNode, BSTNode)

  /**
   * Create a new node with specified key and value and empty left 
   * and right subtrees.
   */
  BSTNode(K _key, V _value) {
    this(_key, _value, null, null);
  } // BSTNode(K, V)

  // +---------+-----------------------------------------------------
  // | Getters |
  // +---------+

  /**
   * Get the key.
   */
  public K key() {
    return this.key;
  } // key()

  /**
   * Get the value.
   */
  public V value() {
    return this.value;
  } // value()

  /**
   * Get the left subtree.
   */
  public BSTNode<K,V> left() {
    return this.left;
  } // left()

  /**
   * Get the right subtree.
   */
  public BSTNode<K,V> right() {
    return this.right;
  } // right()

  // +---------+-----------------------------------------------------
  // | Setters |
  // +---------+

  /**
   * Set the value.
   */
  public void setValue(V newValue) {
    this.value = newValue;
  } // setValue(V)

  /**
   * Set the left subtree.
   */
  public void setLeft(BSTNode<K,V> newLeft) {
    this.left = newLeft;
    this.height = 1 + Math.max(height(this.left), height(this.right));
  } // setLeft(BSTNode<K,V>)

  /**
   * Set the right subtree.
   */
  public void setRight(BSTNode<K,V> newRight) {
    this.right = newRight;
    this.height = 1 + Math.max(height(this.left), height(this.right));
  } // setRight(BSTNode<K,V>)

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Return the height of the tree rooted at node. Return -1 if
   * the tree is empty.
   */
  static int height(BSTNode node) {
    if (null == node) {
      return -1;
    } else {
      return node.height;
    } // if/else
  } // height(BSTNode)

} // class BSTNode
