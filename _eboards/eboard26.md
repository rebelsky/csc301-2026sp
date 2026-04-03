---
title: "EBoard 26: More on MSTs"
number: 26
section: eboards
held: 2026-04-03
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Review (mostly)
    * Prim's algorithm, revisited
    * Running time: Prim's
    * Kruskal's algorithm, revisited
* Running time: Kruskal's
* Detour: Helper algorithms/Data Structures for Kruskal's
* Running time: Kruskal's (revisited)
* Preparing for Proofs of Correctness: Cuts in a graph
* Proving Prim's correct
* Proving Kruskal's correct
* A review of the heap data structure (if time)
* Another MST algorithm (in the unlikely case we still have time)

Administrative stuff
--------------------

* The other Professor Rebelsky asked me to share a meme about Tornado
  Watches vs Tornado Warnings
* There was a question as to whether you have to implement heaps when you
  implement Prim's and whether you have to implement Kruskal's supporting
  data structure when you implement Kruskal's. 
    * The answer is _No_.
    * However, you should use a separate data structure so that you 
      can swap in a better implementation.
* We'll talk more about Project 4, in which you implement Prim's and
  Kruskal's, on Monday.
* I realize that there are a lot of resources for you to look at after
  class (e.g., eboard, Autry's notes, texbook), which might lead you
  to skip note-taking. **Remember that experiments suggest that taking 
  notes helps you learn!**
    * Hypothesis: Having to summarize activates brain cells
    * Hypothesis: It's harder to say "Oh, I understand that" when you
      write it down / summarize.
    * _You can also take notes while reading through resources._
* Please read your email about the SEPC and consider applying to join
  the SEPC. We have a lot of openings on the SEPC.
    * The SEPC serves as the liaison between the faculty and students.
    * The SEPC represents students in faculty reviews
    * The SEPC represents students in faculty hiring
    * The SEPC is responsible for the social life of the department
    * The SEPC gets to deal with SGA (no, not the guy from Oklahoma)

### Upcoming events

* Monday, 2026-04-06, 7:00 p.m.ish, 3819, _Mentor Session_ 
* Tuesday, 2026-04-07, Noon, _CS Table_ (TBD)
* Wednesday Extra, 2026-04-08, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday Extra, 2026-04-09, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday, 2026-04-09, 7:00 p.m., _Mentor Session_ 

### Upcoming deadlines

* Monday, 2026-04-06: Read about MSTs (CLRS 22)
* Monday, 2026-04-06: Project 3 due (does not match Autry's syllabus)
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Policy/administrative/assignment questions

Who votes for SEPC

> Traditionally, only students.

Prim's Algorithm
----------------

```
Unmark all vertices
Choose a vertex and mark it
Repeat (|V| - 1) times
    Find the shortest edge from a marked vertex to an unmarked vertex
    Add that edge to the MST
    Mark the unmarked vertex
```

With a priority queue

```
Unmark all vertices
Create a new priority queue, pq
Choose a vertex and mark it
Add all of its edges to the priority queue
Repeat until we've marked very vertex
    e = pg.get() // Gets the shortest edge from a marked vertex
    if e connects a marked vertex, m, to an unmarked vertex, u
        Add e to the MST
        Mark u
        For each edge from u to an unmarked vertex
            Add that edge to pq
```

Running time of Prim's
----------------------

* Marking a vertex is: O(1), which we can achieve with a flag on the vertex.
  Or by using a hash table.
* Adding an edge to the MST is: We'll use a list of edges, O(1)
* Finding/removing the smallest edge is: Depends on your implementation
  of the priority queue.
    * If we use a sorted list, O(1)
    * If we use a heap, O(logn)
* Adding a new edge is: 
    * If we use a sorted list, O(n), where n is the length of the list
    * If we use a heap, O(logn)

SO the body of our loop is O(logn) if we use a heap.

How many times might we repeat the loop?

* We might have to check every edge once.

So our running time is $$O(|E|\times log|E|)$$

* We may have to put every edge into the priority queue and grab every
  edge from the priority queue.
* Although the first version has a loop over the number of vertices,
  the time within the loop is harder to predict, so we don't use 
  that version.

(CLRS may do a slightly tighter bound.)

Kruskal's Algorithm
-------------------

```
Sort the edges from smallest to largest
Repeat until we've created the MST
    Grab the next smallest edge (from u to w)
    If adding the edge to the MST does not create a cycle
        Add it to the MST
```

Analyzing Kruskal's Algorithm
-----------------------------

What are the key operations?

* Sorting all the edges: $$O(|E|log|E|)$$
* Grab the next smallest edge : $$O(1)$$
* Determine if adding an edge creates a cycle: It depends on how we
  we check for cycles. $$O(cyclecheck)$$

How many times might we run the loop?

$$O(|E|)$$

So our running time is ...

$$O(|E|log|E| + |E| \times cyclecheck)$$

How would you check for a cycle?

* Use the marking idea from Prim's. Nope.
* Keep track of the number of edges from each vertex. Nope.
* Use a "Union Find"
* Topologically sort the graph; if you can't, there's a cycle.
  Topological sort can be expensive.

Detour: Data structures/algorithms for Kruskal's
-------------------------------------------------

Rethink the "are we creating a cycle" to 

* Keep track of all the connected components in the graph (each of
  those is "set")
* When you explore an edge, if both vertices are in the same set,
  adding the edge creates a loop. If both vertices are not in the
  same set, you can safely add the edge.
* We want a data structure that supports
    * Create a set
    * Check if two values are in the same set (Find)
    * Merge two sets (Union)
* One technique: Hash table. Hash each vertex to its own value. When
  we merge to sets, we pick a new set identifier and re-hash all of
  the vertices in the two sets. $$O(|V|)$$ to do the union,
  expected $$O(1)$$ to do the find.
* This would give us an overall running time of $$O(|E|log|E| + |E||V|)$$

We can do even better. 

* Key idea: Upside-down trees, in which the path from any vertex name
  in the tree to the root gives you the identifier for the set its
  in.
* When you union two trees, you make the parent of the smaller tree
  the root of the larger tree
* With a bit of work, you can show that the depth of the tree can't be 
  larger than $$log|V|$$.
* This would give us an overall running time of $$O(|E|log|E| + |E|log|V|)$$
  = $$O(|E|log|E|)$$
* Proof that an improved union/find algorithm is $$O(log^star|V|)$$
    * log-star is "the number of times you have to take the logarithm
      of the value to get to 1. Grows much slower than log.
* Proof that the amortized time for the improved union/find is
  "the inverse Ackermann function" (grows really really slowly)

Analyzing Kruskal's algorithm, revisited
----------------------------------------

With this new data structure, our running time is $$O(|E|log|E|)$$; that
is, the sorting dominates!

Preparing for Proofs: Cuts in a graph with a partial MST
--------------------------------------------------------

It turns out that the proofs of the correctness for Prim's depend upon
some additional infrastructure.

We will define a *cut* in a graph with a partially completed MST.

* Let set $$S \subset V$$ be a set of vertices
    * In our algorithms, it will contain all the edges in the partially 
      completed MST.
* The set $$T = V - S$$ contains all vertices not in S.
* This partitioning is called a *cut* of the graph.
* We'll do a few diagrams on the board. Hopefully, I'll remember to
  turn off screen sharing during that time.
* We will be considering the edges that cross the cut.
* Idea: In building an MST algorithm, we want to create repeated cuts
  and choose the smallest edge that crosses the cut. (Assuming that
  no edges in the MST cross the cut.)

**Theorem**: If $$e$$ is the smallest edge that connects a vertex in
$$S$$ to a vertex in $$T$$ ("crosses the cut""), then $$e$$ is in
some MST of $$G$$.

**Theorem** (restated): Given a weighted, undirected graph, $$G = (V,E,w)$$,
a cut $$S \subset V$$ and $$T = V - S$$, and an edge $$e$$ that is the
lowest weight edge that crosses the cut, $$e$$ is in some MST of $$G$$.

What technique might you use to prove this?

* Contradiction
* Contrapositive
* Indicution
* Construction

**Proof:**

A proof by contradiction.

Suppose $$e$$ is not in any MST of $$G$$.

Let $$M$$ be an MST of $$G$$. $$M$$ must contain an edge, $$m$$, that
crosses the cut between $$S$$ and $$T$$.

Now, consider set edge set $$M \union { e }$$. This set contains a loop.
Removing $$m$$ from that set will create a new MST.

What is the weight of $$M \union { e } - { m }$$.  Since $$e$$ is the
lowest weight edge that crosses the cut, 
$$W(M \union { e } - { m }) \le W(M)$$.

But that's an MST that contains $$e$$. That violates our assumption.
Our assumption must be wrong.

Therefore, $$e$$ is in some MST of $$G$$.

QED.

Note: Another way to think of why $$M \union { e } - { m }$$ is a
spanning tree.  We can think of $$M$$ as having three parts: The
edges in $$S$$, which we'll call $$M_S$$ the edges in $$T$$, which
we'll call $M_T$$, and ...

Proof of Prim's
---------------

**Corollary:** Prim's Algorithm is correct

A proof by construction

Proof of Kruskal's
------------------

**Corollary:** Kruskal's Algorithm is correct

A proof by construction

Our other MST algorithm 
-----------------------

While more than $$|V|-1$$ edges remain, remove the highest-weight
edge that does not disconnect the graph.

Why don't we use this approach?

* Perhaps it's not efficient.
* Perhaps it's not correct.
* Perhaps it's harder to prove correct.

Let's think about efficiency

Looking at up to $$O|E|$$ edges. Checking if a graph is disconnected does
not have an efficient solution.

Heaps (not covered)
-------------------

