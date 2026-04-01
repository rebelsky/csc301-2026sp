---
title: "EBoard 25: Minimum spanning trees"
number: 25
section: eboards
held: 2026-04-01
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Minimum spanning trees
* Prim's algorithm
* Kruskal's algorithm
* Running time: Prim's
* Running time: Kruskal's
* Proofs (probably not)
  
Administrative stuff
--------------------

* You'll note that I tend to include $$\LaTeX$$ text between matched 
  double dollar signs when writing this eboards. There are two reasons:
    * They should help you get used to reading (and writing?) $$\LaTeX$$.
    * They make the corresponding Web page look nicer.

### Upcoming events

* Thursday Extra, 2026-04-02, 4:00 p.m.: ???? (perhaps none)
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)
* Monday, 2026-04-06, 7:00 p.m.ish, 3819, _Mentor Session_ 
  (Project 3, Project 4, Problem Set 4)
* Tuesday, 2026-04-07, Noon, _CS Table_ (TBD)

### Upcoming deadlines

* Tonight, 2026-04-01: Assessment 2 due (I plan to grade on Saturday)
* Friday, 2026-04-03: Read about Greed (CLRS pp. 417–424, 426–427)
* Monday, 2026-04-06: Read about MSTS (CLRS 22)
* Monday, 2026-04-06: Project 3 due (does not match Autry's syllabus)
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Policy/administrative/assignment questions

Where can I view changes to the late policy?

> They are now on [my syllabus](../syllabus/)

Why are we getting so many assessments, problem sets, and projects in
rapid succession?

> We are following the original plans for the class, more or less. Many
  of them are going out more than a week ahead of the due date.

### About Problem Set 4

* Problem 1: Counterexamples to greedy "solutions"
     * Cars on Ferries
     * Dijkstra's with negative weights
     * Assigning coats
* Problem 2: Optimizing well placement (with proof)
* Problem 3: Party planning (with proof)

Minimum spanning trees
----------------------

The problem: Given a connected, undirected, weighted graph, $$G = (V,E,w)$$, 
find a minimum spanning tree (MST), the smallest set of edges that
span the graph (ensures that it remains connected).

Reminder: A connected graph is one in which there is a path from each vertex
to every other vertex. 

Question: How many edges are there in an MST?

* 

If we were being more formal, we might say that we want to find a set
$$M \subseteq E$$ such that 

* (a) $$M$$ spans $$G$$ and 
* (b) $$\forall S \subseteq E$$ that spans $$G$$ ,
  $$\sum_{m \in M} w(m) \le \sum_{s \in S} w(s)$$

Are there any parts of the formalization that don't make sense?

* 
* 

Suppose we decide to try to solve MST with a greedy approach. How might
we arrange the problem and what greedy choices might we make? (There
should be at least four "obvious" greedy approaches.)

* 
* 
*
* 

Prim's Algorithm
----------------

Idea: We divide the graph into two parts, ??? and ???.

Greedy approach: 

Sample graph (due to Autry):

```
A  C  E

B  D  F
```

```
AB: 5
AC: 6
AD: 4
BC: 1
BD: 2
CD: 2
CE: 5
CF: 3
DF: 4
EF: 4
```

Let's see how it works. We'll start with A. (This part is on the chalkboard.)

Kruskal's Algorithm
-------------------

Greedy approach: 

Running time of Prim's
----------------------

Running time of Kruskal's
-------------------------

Detour: Data structures/algorithms for Kruskal's
-------------------------------------------------

Correctness (unlikely to cover today)
-------------------------------------
