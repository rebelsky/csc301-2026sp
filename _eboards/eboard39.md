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
* Ethics, initiated

Administrative stuff
--------------------

* I've graded many of the assessments resubmissions (all of #3) and plan 
  to work on the rest tomorrow.
     * Email me if you have other things you want graded.
     * When you resubmit an assessment, please make sure to include a reflection!
       (What did you do wrong? Why? How did you fix it? How can you avoid 
        similar problems in the future? Etc.)
* Wednesday will be our last class for the semester. Please attend to fill
  out the End-of-Course Survey. And please bring a device.
* I figured out the problem with our code: I wasn't flushing the buffer. Duh.

### Upcoming events

* Monday, 2026-05-04, 7:00 p.m., 3819, _Mentor Session_
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

Review: Insertion in BSTs
-------------------------

Our code seems to work.

What are some experiments you'd like to carry out?

Insertion in AVL Trees
----------------------

How would you update this code to make it an AVL tree?

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
    return root;
  } // insert
```

Removal in BSTs
---------------

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

Ethics
------

We will use "ethics" fairly broadly in this class. While ethics is a
philosophical discipline, we also use it to mean "consideration of the
impact of the work we do, particularly with regards to bias".

_Background reflection_

What is an algorithm?

Can algorithms be biased?

Can the kinds of algorithms we write in this class be biased?

What kinds of "algorithms" are discussed in the readings?

Are these algorithms?

Ideas from the Readings
-----------------------

What did you see as the "big picture" ideas from the readings?
