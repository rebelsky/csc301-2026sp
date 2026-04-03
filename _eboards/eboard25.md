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
* Running time: Kruskal's (skipped)
* Detour: Algorithms/Data Structures for Kruskal's (skipped)

Administrative stuff
--------------------

* You'll note that I tend to include $$\LaTeX$$ text between matched 
  double dollar signs when writing this eboards. There are two reasons:
    * They should help you get used to reading (and writing?) $$\LaTeX$$.
    * They make the corresponding Web page look nicer.
    * They make the math a bit less ambiguous.
* Note: When you're not coming to class, you don't need to tell me why.
  Just "I can't make it today." But please do let me know.
    * You are responsible for catching up on the missed material.
      You might use eboards, recordings, Autry's notes, your colleagues' notes,
      or any combination thereof you find helpful.
    * You can also chat with me, but I prefer that you make some
      attempt on your own first.

### Upcoming events

* Thursday Extra, 2026-04-02, 4:00 p.m.: ???? (perhaps none)
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)
* Monday, 2026-04-06, 7:00 p.m.ish, 3819, _Mentor Session_ 
  (Project 3, Project 4, Problem Set 4)
* Tuesday, 2026-04-07, Noon, _CS Table_ (TBD)

### Upcoming deadlines

* Tonight, 2026-04-01: Assessment 2 due (I plan to grade on Saturday)
* Friday, 2026-04-03: Read about Greed (CLRS pp. 417–424, 426–427)
* Monday, 2026-04-06: Read about MSTs (CLRS 22)
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

Do we have to use $$\LaTeX$$ on assessments?

> No! Only problem sets.

> If you want to use $$\LaTeX$$, you may.

### About Problem Set 4

* Problem 1: Counterexamples to greedy "solutions"
     * Cars on Ferries
     * Dijkstra's with negative weights
     * Assigning coats
* Problem 2: Optimizing well placement (with proof)
* Problem 3: Party planning (with proof)

The proofs are likely by induction (or perhaps contradiction). Sam
will get back to you on this.

Minimum spanning trees
----------------------

The problem: Given a connected, undirected, weighted graph, $$G = (V,E,w)$$, 
find a minimum spanning tree (MST), the smallest set of edges that
span the graph (ensures that it remains connected).

Reminder: A connected graph is one in which there is a path from each vertex
to every other vertex. 

Question: How many edges are there in an MST?

* $$|V|-1$$. Each vertex needs an edge, so about $$|V|$$, but that last
  edge will connect two vertices.

If we were being more formal, we might say that we want to find a set
$$M \subseteq E$$ such that 

* (a) $$M$$ spans $$G$$ and 
* (b) $$\forall S \subseteq E$$ that spans $$G$$ ,
  $$\sum_{m \in M} w(m) \le \sum_{s \in S} w(s)$$

Are there any parts of the formalization that don't make sense?

* The upside-down A is $$\forall$$
* The big E symbols are sum sybols, it means we are adding things
  up. In this case, the subsets all have a form like $$m \ in M$$

Questions

* Why did I use $$\forall$$ instead of $$\exists$$? - For a minimum,
  it must be less than or equal to all other things, not just one of 
  them.
* Why did I use $$\le$$ (less than or equal to) instead of `\<` (less than).
  That is, when would the $$S$$ sum ever equal the $$M$$ sum? - There may
  be multiple MSTs. It's also the case that S could literally equal M.
* Why did I use $$\subseteq$$ instead of $$\subset$$. The sets could
  be equal. If the graph were already a tree, it is its own MST.

Suppose we decide to try to solve MST with a greedy approach. How might
we arrange the problem and what greedy choices might we make? (There
should be at least four "obvious" greedy approaches.)

* Remove the greatest weight edge that does not disconnect the graph.
* Remove all the edges and add them in, smallest to largest, skipping
  any edge that creates a cycle. [Kruskal's]
* Pick a vertex and use Dijkstra's.
* We will separate the graph into two parts: An MST for some of the
  vertices and all the other vertices. (At any stage, we will have
  NO edges to the vertices in the "other" group.) At each step, you
  pick the smallest edge from the partial MST to to the "other group"
  and add it and it's vertex. [Prim's]
 
Prim's Algorithm
----------------

Idea: We divide the graph into two parts, an MST and and all the other
vertices.

Greedy approach: Pick the edge from the MST to the other vertices with
smallest weight.

Initialization: Pick one vertex and put it in the partial MST.

Sample graph (due to Autry):

```
   
A--C--E
|\/|\ |
B--D--F
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

We found the MST: AD (4), BD (2), BC (1), CF (3), EF (4) = 14

We also found the MST: AD (4), CD (2), BC (1), CF (3), EF (4) = 14

Kruskal's Algorithm
-------------------

Greedy approach: Just keep taking the shortest edge that doesn't create
a cycle.


Let's see how it works on the same graph.  (This part is on the chalkboard.)

Yay! We found one of the answers above.

Pause
-----

When you have two algorithms that seem to solve the same problem, what
do you do?

* Implement the one that you are more likely to get correct.
* Assess the runtime and implement the one that is more efficient.
* Assess the storage needs and implement the one that requires less
  storage.
* Implement them both and compare the real-world run-times.
* Consider whether there are particular sets of inputs that one or the
  other performs better on.
* Choose the easier to understand one in case someone else needs to
  deal with your code.
* Prove them correct!

Running time of Prim's
----------------------

Key activities:

* Keep track of what is in the partial MST and what is not.
    * Two lists. O(1) to add to a list. O(|V|) to remove. O(|V|)
      to determine if something is in the list.
    * We could have a flag on each vertex that indicates whether or
      not it's in the partial MST. O(1) (plus O(|V|) to initialize)
    * We could have a hash table indexed by vertex id. Expected amortized
      O(1) for all the operations.
* Find the smallest edge from partial MST to not partial MST
    * Iterate through all the neighbors of all the vertices in the
      partial MST. $$O(|E|)$$
    * Sort the edges by weight at the beginning $$O(|E|log|E|)$$
    * Keep a priority queue of edges from partial MST. Every time we add a
      vertex to the partial MST, we add all the edges from that vertex.
      If we implement the MST as a heap, this will be O(log|E|) for
      finding and removing the lowest weight edge.
    * If we can find a better way to implement a priority queue (e.g.,
      a Fibonacci heap), we can do even better.

Running time of Kruskal's
-------------------------

* What are the key operations that we'll have to figure out how to implement?
* How will we implement them?

Detour: Data structures/algorithms for Kruskal's
-------------------------------------------------
