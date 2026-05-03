---
title: "EBoard 39: BSTs (3) + Ethics (1)"
number: 39
section: eboards
held: 2026-05-04
link: false
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff

Administrative stuff
--------------------

* Wednesday will be our last class for the semester. Please attend to fill
  out the End-of-Course Survey.

### Upcoming events

* Monday, 2026-05-04, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-05-07, 4:15--5:15pm, _Thursday Extra_ (maybe)
* Thursday, 2026-05-07, 7:00 p.m., _Out last Mentor Session_

### Upcoming deadlines

* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  No extensions.

### Policy/administrative/assignment questions

Did you miss anything about the grading policy?

> Unfortunately, yes. In order to pass the class, you must receive a
  satisfactory on at least one assessment and at least one project.

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

```
/**
 * Remove a key/value pair from the tree.
 *
 * @return
 */
remove(BSTnode tree, K key) {
} // remove
```

Ethics
------
