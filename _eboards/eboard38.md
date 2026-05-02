---
title: "EBoard 38: Balanced Search Trees (2)"
number: 38
section: eboards
held: 2026-05-01
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Quick review of AVL trees
* Insertion in AVL trees
* Detour: Insertion and Deletion in BSTs
* Deletion in AVL trees

Administrative stuff
--------------------

* Happy May Day! Celebrate workers!
* If you ask me code questions, please send me the full code file.
* We have readings for Monday.

### Upcoming events

* TODAY, 2025-05-01, 5:00 p.m.: _CS Picnic_
* Monday, 2026-05-04, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-05-07, 4:15--5:15pm, _Thursday Extra_ (maybe)
* Thursday, 2026-05-07, 7:00 p.m., _Out last Mentor Session_

### Upcoming deadlines

* TODAY, 2026-05-01: _Early Deadline_ for Problem Set 5
* TODAY, 2026-05-01: _Early Deadline_ for Project 5
* Monday, 2026-05-04: A bunch of readings
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  No extensions.

### Readings

Read the Munro piece. Skim the rest. 

* Munro, Dan. 2018. Feynman's Error.
  <https://www.danmunro.ca/blog/2018/11/29/feynmans-error-on-ethical-thinking-and-drifting-nbsp>
* Noble, Safiya Umoia. 2017. _Algorithms of Oppression_, Introduction.
  <https://www.jstor.org/stable/j.ctt1pwt9w5>
* Cahan, E.M., Hernandez-Boussard, T., Thadaney-Israni, S. et al. 2019.  Putting the data before the algorithm in big data addressing personalized healthcare. _npj Digit. Med._ 2, 78. <https://doi.org/10.1038/s41746-019-0157-2>.
  <https://www.nature.com/articles/s41746-019-0157-2>
* Barocas, S. and Selbst, Andrew D. 2016.  Big Data's Disparate Impact.  _California Law Review_, Vol. 104, No. 3, pp. 671-732. 
  <https://www.jstor.org/stable/24758720>
* Ledford, Heidi. 2019. Millions of black people affected by racial bias in health-care algorithms. _Nature_.
  <https://www.nature.com/articles/d41586-019-03228-6>
* Bolukbasi, Tolga _et al_. 2016. Man is to Computer Programmer as Woman is to
Homemaker? Debiasing Word Embeddings. In _Proceedings of the NIPS 2016 Conference on Neural Information Processing Systems_.
  <https://proceedings.neurips.cc/paper_files/paper/2016/file/a486cd07e4ac3d270571622f4f316ec5-Paper.pdf>
* Caliskan, Aylin _et al._ 2017. Semantics derived automatically from language corpora contain human-like biases. _Science_ 356, 183-186. DOI:10.1126/science.aal4230.
  <https://www.science.org/doi/full/10.1126/science.aal4230>

### Policy/administrative/assignment questions

Could you remind me of the grading policy?

> Sure!

> You need to make a reasonable attempt at **everything** in order to pass.

> You can earn up to three points for each category (Problem Sets, Projects,
  and Assessments).

> 3/5 projects: 1 point; 4/5 projects: 2 points; 5/5 projects: 3 points

> 3/8 assessments: 1 point; 5/8 assessments: 2 points; 7/8 assessments: 3 points

> See syllabus for PSets.

> You can pass with two points! But you should try to do better.

> You need at least one satisfactory assessment and one satisfactory
  project to pass.

> You need all nine points for an A. Each point below that drops your
  grade by 1/3 of a letter.

When do you expect to grade Project 5?

> Starting Sunday morning.

When do you expect the graders to grade PS 5?

> This weekend.

When do you expect the graders to grade PS and Project resubmissions?

> This weekend.

When do you expect to grade Assessment resubmissions?

> Starting Sunday morning. Maybe earlier.

Do you expect us to have time for re-resubmissions?

> Yes, that's my goal.

Review: AVL Trees
-----------------

* We keep track of the height of each subtree.
* Invariant: At any point, the subtrees differ in height by at most one.
* We need to ensure that we restore the invariant after any mutating operation.
    * We hope to be able to fix things in constant time (or perhaps
      logarithmic time)

Insertion in AVL Trees
----------------------

How might insertion in an AVL tree violate the AVL invariant? Here are
two examples in which we've inserted on the left.

```
   V01         V01
   / \  ins    / \
 V02  n  =>  V02  n
 / \         / \
n   n      n+1  n
```

```
   V01         V01
   / \  ins    / \
 V02  n  =>  V02  n
 / \         / \
n   n       n  n+1
```

How can we restore the tree afterwards? Note that the first is easier
than the second.

### Our first tree

```
   V01         V01         V02
   / \  ins    / \  fix    / \ 
 V02  n  =>  V02  n  =>  n+1 V01
 / \         / \             / \
n   n      n+1  n           n   n
```

### Our second tree

```
   V01         V01         V01          V03
   / \  ins    / \         / \  fix    /   \
 V02  n  =>  V02  n  ==  V02  n  =>  V01   V02
 / \         / \         / \         / \   / \
n   n       n  n+1      n  V03      n  n? n? n
                           / \
                          n? n?
```

Note that we could implement the fix by "rotate left" at V02, which puts
us in the state of the first tree, so we "rotate right" at V01.

Implementing BSTs and AVL Trees
--------------------------------

Let's look at the details in Java (approximate). We'll write our algorithms
recursively.

### Our nodes

```
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
  // | Helpers |
  // +---------+

  /**
   * Return the height of the tree rooted at node. Return -1 if
   * the tree is empty.
   */
  int height(BSTNode<K,V> node) {
    if (null == node) {
      return -1;
    } else {
      return node.height;
    } // if/else
} // class BSTNode
```

### Insertion

```
public class BST<K extends Comparable<K>, V> 
{
  BSTNode<K,V> root = null;

  public void insert(K key, V value) {
    this.root = insert(this.root, key, value);
  }
} // class BST
```

```
/**
 * Insert a key/value pair into the tree. If the key is already in
 * the tree, replaces the associated value.
 *
 * Returns the updated subtree.
 */
BSTnode insert(BSTNode<K,V> tree, K key, V value) {
  // If the tree is empty, build a new node
  if (null == tree) {
    return new BSTNode(key, value)
  }
  else {
    // If the key is already in the current place, replace it
    if (key.equals(tree.key)) {
      tree.value = value;
    } 
    // If the key is less than the key of the current place, work on the left
    else if (key.compareTo(tree.key) < 0) {
      tree.left = insert(tree.left, key, value);   
    } 
    // If the key is greater than the key of the current place, work on the left
    else {
      tree.right = insert(tree.right, key, value);   
    } // inner if/else
  } // if/else
  return tree;
} // insert
```

Style comment: Sam should be using getters and setters, not fields.

Test code

```
public class BSTExpt {
  public static void main(String[] args) {
    BST<String,String> tree;
    for (String s : args) {
      tree.insert(s.substring(0,1), s);
      tree.dump(System.stdout);
      System.stdout.println("-------------------------");
    } // for
  } // main(String[])
} // class BSTExpt
```

Insertion in AVL Trees
----------------------

How would you update this code to make it an AVL tree?

```
/**
 * Insert a key/value pair into the tree. If the key is already in
 * the tree, replaces the associated value.
 *
 * Returns the updated subtree.
 */
BSTnode insert(BSTNode<K,V> tree, K key, V value) {
  // If the tree is empty, build a new node
  if (null == tree) {
    tree = new BSTNode(key, value)
  }
  // If the key is already in the current place, replace it
  else if (key.equals(tree.key)) {
    tree.value = value;
  } 
  // If the key is less than the key of the current place, work on the left
  else if (key.compareTo(tree.key) < 0) {
    tree.left = insert(tree.left, key, value);   
  } 
  // If the key is greater than the key of the current place, work on the left
  else {
    tree.right = insert(tree.right, key, value);   
  } // if/else
  // Check if the height of the right tree equals the height of the left
  // tree. If so, we're lucky!
  if (height(tree.left) == height(tree.right)) {
    // Do nothing; we're okay
  }
  return tree;
} // insert
```
Removal in AVL Trees
--------------------
### Removal

```
/**
 * Remove a key/value pair from the tree.
 *
 * @return
 */
remove(BSTnode tree, K key) {
} // remove
```

