---
title: "EBoard 39: BSTs (3) + Ethics (1)"
number: 39
section: eboards
held: 2026-05-04
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* BSTs, concluded
    * Examine our insertion code
    * Extend insertion code to balance the tree (using AVL)
    * Write removal code
    * Extend removal code to balance the tree (using AVL)
* Ethics, initiated
    * Why we do this?
    * Relationship between algorithms and ethics
    * Initial conversations about readings

Administrative stuff
--------------------

* I've graded many of the assessments resubmissions (all of #3) and plan 
  to work on the rest tomorrow.
     * Email me if you have other things you want graded.
     * When you resubmit an assessment, please make sure to include a reflection!
       (What did you do wrong? Why? How did you fix it? How can you avoid 
        similar problems in the future? Etc.)
     * When you resubmit an assessment, you still have to give a correct
       solution.
     * When you resubmit an assessment, you do not have to record your time.
* Hints for Project 5
     * Keep whiteboards handy to diagram things
     * Be careful tracing
     * Make sure you understand what direction represents
     * Choose your direction for filling out the table carefully 
       **lower-right to upper-left** or upper-left to lower-right.
* Wednesday will be our last class for the semester. Please attend to fill
  out the End-of-Course Survey. And please bring a device.
* I figured out the problem with our code: I wasn't flushing the buffer. Duh.

### Upcoming events

* Monday, 2026-05-04, 7:00 p.m., 3819, _Mentor Session_
* Monday, 2026-05-04, 7:00 p.m., 3821, _Board Game Guided Reading Demo Night_
* Thursday, 2026-05-07, Noon, HSSC A1231, _Student Seminar on GenAI_
* Thursday, 2026-05-07, 4:15--5:15pm, _Thursday Extra_ (maybe)
* Thursday, 2026-05-07, 7:00 p.m., _Our last Mentor Session_

### Upcoming deadlines

* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  No extensions.

### Code files for today's class

* [BSTNode.java](../examples/avl/BSTNode.java)
* [BST.java](../examples/avl/BST.java)
* [BSTExpt.java](../examples/avl/BSTExpt.java)
* [AVL.java](../examples/avl/AVL.java)
* [AVLExpt.java](../examples/avl/AVLExpt.java)
* [Pair.java](../examples/avl/Pair.java)

### Readings for today's class (and Wednesday's)

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

Did you miss anything about the grading policy?

> Unfortunately, yes. In order to pass the class, you must receive a
  satisfactory on at least one assessment and at least one project.
  I hope that won't be an issue.

If I already have satisfactory on seven assessments, do I have to do
the 8th assessment?

> Yes, you must make a valiant attempt at everything!

Review: Insertion in BSTs
-------------------------

Our code seems to work.

What are some experiments you'd like to carry out?

Um We Are Talking About

```
U:Um [2]
 l A:About [1]
 l  r T:Talking [0]
 r W:We [0]
```

The entire alphabet in order: A B C D E F G H I J

```
A:A [9]
 r B:B [8]
 r  r C:C [7]
 r  r  r D:D [6]
 r  r  r  r E:E [5]
 r  r  r  r  r F:F [4]
 r  r  r  r  r  r G:G [3]
 r  r  r  r  r  r  r H:H [2]
 r  r  r  r  r  r  r  r I:I [1]
 r  r  r  r  r  r  r  r  r J:J [0]
```

Nothing

```
```

One thing

```
O:One [0]
```

Replacing one thing: Foo Fu

```
-------------------------
INSERTING F:Foo

F:Foo [0]
-------------------------
INSERTING F:Fu

F:Fu [0]
-------------------------
```

A tongue twister: She Sells Seashells By The Sea Shore

```
S:Shore [1]
 l B:By [0]
 r T:The [0]
```

Insertion in AVL Trees
----------------------

How would you update this code to make it an AVL tree?

* You have an `int BST.height(BSTNode<K,V> node)` method available to you.

```
  /**
   * Insert a key/value into a BST.
   */
  BSTNode<K,V> insert(BSTNode<K,V> root, K key, V value) {
    // If the root is empty, build a new node
    if (null == root) {
      return new BSTNode<K,V>(key, value);
    }
    else {
      // If the key is already in the current place, replace it
      if (key.equals(root.key)) {
        root.setValue(value);
      }
      // If the key is less than the key of the current place, work on the left
      else if (key.compareTo(root.key) < 0) {
        root.setLeft(insert(root.left(), key, value));
      }
      // If the key is greater than the key of the current place, work on the left
      else {
        root.setRight(insert(root.right(), key, value));
      } // inner if/else
    } // if/else
    return rebalance(root);
  } // insert

  BSTNode<K,V> rebalance(BSTNode node) {
    // If the left subtree is too tall
    if (BSTNode.height(node.left()) - BSTNode.height(node.right()) > 1) {
      BSTNode<K,V> left = node.left();
      // The easy case
      if (BSTNode.height(left.left()) > BSTNode.height(left.right()) {
        return rotateRight(node);
      }
      // The hard case
      else {
        node.setLeft(rotateLeft(node.left()));
        return rotateRight(node);
      }
    }
    // If the right subtree is too tall
    else if (BSTNode.height(node.right()) - BSTNode.height(node.left()) > 1) {
      BSTNode<K,V> right = node.right();
      // The easy case
      if (BSTNode.height(right.right()) > BSTNode.height(right.left()) {
        return rotateLeft(node);
      }
      // The hard case
      else {
        node.setRight(rotateRight(node.right()));
        return rotateLeft(node);
      }
    }
    // No rebalance needed
    else {
      return node;
    } 
  } // rebalance

  BSTNode<K,V> rotateRight(BSTNode<K,V> node) {
    BSTNode<K,V> sub = node.left();
    node.setLeft(sub.right());
    sub.setRight(node);
    return sub;
  } // rotateRight
```


Removal in BSTs
---------------

**Skipped**

```
  /**
   * Remove a key/value pair from the tree.
   */
  public void remove(K key) {
    this.root = remove(this.root, key);
  } // remove(K)

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
    // ...
  } // removeLargest(BSTNode<K,V>)
```

Removal in AVL Trees
--------------------

**Gilliganed**

Ethics
------

We will use "ethics" fairly broadly in this class. While ethics is a
philosophical discipline, we also use it to mean "consideration of the
impact of the work we do, particularly with regards to bias".

_Background reflection_

What is an algorithm?

> A set of unambiguous instructions that take an input and convert that 
  input into a desired output in finite time.

Can algorithms be biased?

> Yes. An algorithm might consider irrelevant knowledge.

```
boolean admit(Student s) {
  int score = evaluate(s);
  if (s.name.contains("y")) {
    score += 5;
  }
  return (score > 100);
}
```

> Not all biases are bad. Ones that consider "irrelevant" knowledge
  in making their decisions probably are.

Can the kinds of algorithms we write in this class be biased? 
E.g., Can a sorting algorithm be biased? Can a string matching
algorithm that uses dynamic programming be biased?

* A sorting algorithm may exhibit bias based on the comparison criteria.
* The use of a sorting algorithm may be biased (e.g., because of the
  comparison criteria).
* The original ordering of elements can affect the result of a sorting
  algorithm.
    * The choice of what to do when two elements or equal can bias a
      sorting algorithm.
* We can use sorting algorithms in ways that show bias (or are problematic)
  even if they don't seem to be.
    * UI design: Wouldn't it be nice if we provided an autocomplete function?
    * Suppose there are multiple completion options
    * We should provide them in sorted order
* The Gale-Shapley algorithm is biased towards those making offers.
* In dynamic programming, we're often maximizing (minimizing) between
  multiple choices. If two are equal, we make a choice.

What kinds of "algorithms" are discussed in the readings? 
E.g., the results of a machine learning algorithm, a large language model.
Why would Sam say "these aren't algorithms"?

* Most AI-related "algorithms" are non-deterministic. Is that the same
  as unambiguous? `boolean heads() {return Math.random() > 0.5;}`
* Most AI-related "algorithms" are "black boxes"; we can't see what the
  instructions are, or at least we can't comprehend the instructions.
* They have "algorithmic behavior" (JC); that is, they followo unamibiguous
  instructions to convert an input to an output.

Is there a difference between these "algorithms"

Ideas from the Readings
-----------------------

What did you see as the "big picture" ideas from the readings?
