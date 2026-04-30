---
title: "EBoard 37: Balanced Search Trees (1)."
number: 37
section: eboards
held: 2026-04-29
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Review of binary search trees
* AVL trees: The key idea
* Insertion in AVL trees
* Looking ahead: Deletion in AVL trees

Administrative stuff
--------------------

* The rest of Problem Set 4 is now graded, as is Project 4. Our graders
  plan to start on regrades over the weekend.
* Someone has already booked my Thursday Office Hours. I'll still try to
  be around from about 9:00 am to 10:45 am and in the afternoon. I should
  also be around this afternoon.
* Assessment 4 has been released.

### Upcoming events

* Thursday, 2026-04-30, 4:15--5:15pm, _Thursday Extra_ 
* Thursday, 2026-04-30, 7:00 p.m., _Mentor Session_
* Thursday, 2026-04-30, _Trustees on Campus_ (dessert with students?)
* Friday, 2025-05-01, 5:00 p.m.: _CS Picnic_
* Monday, 2026-05-04, 7:00 p.m., 3819, _Mentor Session_

### Upcoming deadlines

* Friday, 2026-05-01: _Early Deadline_ for Problem Set 5
* Friday, 2026-05-01: _Early Deadline_ for Project 5
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  **No extensions. This deadline is _not_ a lie!**

### Other upcoming dates

* TODAY, 2026-04-29: Assessment 4 distributed (see below)

### Assessment 4

* One problem.
* Up to **two** hours.
* Clarified policies.
* Dynamic programming!
* More string alignment
* No proofs!
* No memoizing subproblem!

### Policy/administrative/assignment questions

Binary Search Trees---An interactive review
-------------------------------------------

_I'll ask questions, you do your best to answer. Feel free to say "I'm
not sure." Note that not all of the questions are pre-populated._

What is a binary search tree?

> "It's a sorted binary tree."

> A binary tree is an acyclic graph in which each vertex (node) has at
  most two "children".

> A binary tree is a tree (a hierarchical structure) in which each vertex
  (node) has at most two "children" (hence "binary").

> The children (and descendenants) are organized in a certain way relative
  to the key in the parent node.

> Usually: All the smaller elements are in the left subtree and all the
  larger elements are in the right subtree.

What are the key properties of a binary search tree?

> At every vertex, we have the BST property (invariant): All the elements
  in the left subtree are smaller, all the elements in the right subtree
  are larger.
  
How do we insert a key/value pair into a BST?

> Starting at the root, traverse down the tree, following the BST
  property (going left if you're smaller, to the right if you're
  larger).

> If you fall off the tree, insert the pair there.

> If you see the key in the tree, update that.

How do we remove a key/value pair from a BST?

> If it's a leaf, just remove it.

> If it has just one child, move the child up.

> If it has two children, either find the largest element in the left
  subtree or the smallest element in the right subtree. Remove that element
  and put it at the root.

What is the running time of insertion?

> $$O(log(n))$$ because you might have traverse all the levels of the
  tree and there are $$O(log(n))$$ levels. [Incorrect]

> Unfortunately, if we build our trees poorly, the can have $$\Theta(n)$$ 
  levels.

> So this is $$O(n)$$ in the worst case. 

What is the running time of deletion?

> $$O(n)$$, for similar reasons. 

_Side note: We should look at "Can we do better?", but not yet._

What Abstract Data Type (ADT) do Binary Search Trees (BSTs) implement?

> Generally: Dictionaries, Maps, Lookup Tables, Hashes (all the same)
  (Things that store key/value pairs and let us look up things by key.)

> Also: Sets

What other mechanisms do you know for implementing dictionaries?

> * Hash table

> * A linked list of key/value pairs (association lists).
    $$O(n)$$ insert (or maybe $$O(1)$$, if we permit duplicates).
    $$O(n)$$ find.
    $$O(n)$$ delete.

> * An array of key/value pairs (associative array)
    $$O(n)$$ insert (or maybe $$O(1)$$, if we permit duplicates).
    $$O(n)$$ find.
    $$O(n)$$ delete.
 
> * A sorted array of key/value pairs
    $$O(n)$$ insert (or maybe $$O(1)$$, if we permit duplicates).
    $$O(log(n))$$ find.
    $$O(n)$$ delete.

> * A sorted list of key/value pairs
    $$O(n)$$ insert (or maybe $$O(1)$$, if we permit duplicates).
    $$O(n)$$ find.
    $$O(n)$$ delete.

What are the advantages of using BSTs to implement that ADT?

> * Faster than all the lists/arrays.

> * Can get us other interesting bits of information, like "all elements
    smaller than this element"

> * Easy to iterate in order (compared to hash tables and unordered
    structures).

> * BSTs have consistent time for insert vs. hash tables and arrays
    which may have to pause and allocate and COPY.

> * You can make BSTs that are immutable (and that efficiently make
    new copies when you insert or delete an element).

What are the disadvantages of using BSTS?

> * Slower than expected amortized time of hash tables. Especially
    slower if the tree is not balanced.

> * Harder to delete than in a linked list.

> * Sorted arrays with complete sets of keys give you $$O(1)$$ lookup.

> * Sorted arrays give guaranteed $$O(logn)$$ lookup, even if you don't
    have all the keys.

> * Requires that we have elements we can compare to each other for
    order. Not all things are naturally comparable.

> * BSTs generally don't give you the locality benefits that hash tables
    (or arrays) give.

What are other issues?

> * How much memory do we use?

Why do we study BSTs?

> * It helps us understand algorithms (e.g., turning the binary search
    algorithm into a data structure).

> * They are easier to understand than hash tables.

> * We like you to practice working with nonlinear data structures.

### Insertion, revisited

Let's look at the details in Java (approximate). We'll write our algorithms
recursively.

```
BSTnode insert(BSTnode tree, String key, String value) {
} // insert
```

### Removal, revisited

```
??? remove(BSTnode tree, String key) {
} // remove
```
AVL Trees
---------

To ensure near-optimal running time for BSTs, computer scientists have
designed a variety of mechanisms for keeping BSTs balanced (or at least
sufficiently balanced), including 

* 2-3 trees (nodes have two or three children). Also m:n.
* Red-black trees (nodes are colored red or black to indicate certain 
  properties)
* AVL trees (subtrees can differ in height by at most one)

AVL trees were invented in 1962 by Georgy Adelson-Velsky and Evgenii Landis

### Key issues in AVL trees

* We keep track of the height of each subtree.
* Invariant: At any point, the subtrees differ in height by at most one.
* We need to ensure that we restore the invariant after any mutating operation.
    * We hope to be able to fix things in constant time (or perhaps
      logarithmic time)

### Questions (TPS)

* How few vertices can an AVL tree of height $$h$$ have?
* How much taller can an AVL tree be than a perfectly balanced tree?

Analysis

* See whiteboard.
* This grows like Fibonacci (more or less).
* People who are better mathematicians than I am say that if it grows
  like Fib instead of powers of two, the tree can be no higher than
  approximately 1.44 x the height of a complete tree.

Insertion in AVL Trees
----------------------

How might insertion in an AVL tree violate the AVL invariant?


```
        A
       / \
      B   n
     / \
   n+1  n
```

```
        A
       / \
      B   n
     / \
    n  n+1
```

How can we restore the tree afterwards?
