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
  be around from about 9:00a.m. to 10:45a.m. and then again in the afternoon.
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

I'll ask questions, you do your best to answer. Feel free to say "I'm
not sure." Note that not all of the questions are pre-populated.

What is a binary search tree?

What are the key properties of a binary search tree?

How do we insert a key/value pair into a BST?

How do we remove a key/value pair from a BST?

What is the running time of insertion?

What is the running time of deletion?

What ADT do BSTs implement?

What other mechanisms do you know for implementing that ADT?

What are the advantages of using BSTs to implement that ADT?

What are the disadvantages of using BSTS?

What are the advantages of using X?

What are the disadvantages of using X?

What are the advantages of using X?

What are the disadvantages of using X?

Why do we study BSTs?

AVL Trees
---------

To ensure near-optimal running time for BSTs, computer scientists have
designed a variety of mechanisms for keeping BSTs balanced (or at least
sufficiently balanced), including 

* 2-3 trees (nodes have two or three children)
* Red-black trees (nodes are colored red or black to indicate certain 
  properties)
* AVL trees (subtrees can differ in height by at most one)

AVL trees were invented in 1962 by Georgy Adelson-Velsky and Evgenii Landis

Can you guess why they are named AVL trees?

### Key issues in AVL trees

* We keep track of the height of each subtree.
* Invariant: At any point, the subtrees differ in height by at most one.
* We need to ensure that we restore the invariant after any mutating operation.

### Questions (TPS)

How few vertices can an AVL tree of height $$h$$ have?

How much taller can an AVL tree be than a perfectly balanced tree?

Insertion in AVL Trees
----------------------

How might insertion in an AVL tree violate the AVL invariant?
