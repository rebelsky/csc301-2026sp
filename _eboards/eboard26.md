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

Prim's Algorithm
----------------

```
Unmark all vertices
Choose a vertex and mark it
Repeat (|V| - 1) times
    Find the shortest edge from a marked vertex to an unmarked vertex
    Add that edge to the MST
    Mark the vertex
```

With a priority queue

```
Unmark all vertices
Create a new priority queue, pq
Choose a vertex and mark it
Add all of its edges to the priority queue
Repeat until we've marked very vertex
    e = pg.get()
    if e connects a marked vertex, m, to an unmarked vertex, u
        Add e to the MST
        Mark u
        For each edge from u to an unmarked vertex
            Add that edge to pq
```

Running time of Prim's
----------------------

* Marking a vertex is: 
* Adding an edge to the MST is:
* Finding/removing the smallest edge is: 
* Adding a new edge is: 

How many times might we repeat the loop?

So our running time is ...

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

* 
* 
* 

How many times might we run the loop?

So our running time is ...

Detour: Data structures/algorithms for Kruskal's
-------------------------------------------------

Analyzing Kruskal's algorithm, revisited
----------------------------------------

With this new data structure, our running time is ...

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

**Theorem**: If $$e$$ is the smallest edge that connects a vertex in
$$S$$ to a vertex in $$T$$ ("crosses the cut""), then $$e$$ is in
some MST of $$G$$.

What technique might you use to prove this?

* 

**Proof:**

A proof by APPROACH.

Proof of Prim's
---------------

**Corollary:** Prim's Algorithm is correct

A proof by APPROACH

Proof of Kruskal's
------------------

**Corollary:** Kruskal's Algorithm is correct

A proof by APPROACH

Heaps (if time)
---------------

A heap is a data structure used to implement priority queues.

Our other MST algorithm (if time - hah!)
----------------------------------------

While more than $$|V|-1$$ edges remain, remove the highest-weight
edge that does not disconnect the graph.
