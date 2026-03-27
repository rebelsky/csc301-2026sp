---
title: "EBoard 23: From Dijkstra's to Bellman-Ford"
number: 23
section: eboards
held: 2026-03-27
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Reading questions
* Dijkstra's algorithm
* Proving Dijkstra's algorithm correct
* Running time of Dijkstra's
* Expanding the problem: Shortest paths on graphs with negative weights
* The Bellman-Ford algorithm
* Practice on Bellman-Ford

Administrative stuff
--------------------

* I'm told that Prof. Autry wasn't putting due dates on Canvas, so I'm
  assuming Autry's Syllabus + Sam's Syllabus + Announcements + Gradescope
  suffice.
    * If you need me to add canvas to the mix, let me know.
* I'm still working to catch up on things for the class. Stay tuned.
* As usual, today's outline likely has more than we can cover.

### Assessment redo policy

* The CSC-301 team discussed what we know of / understand about
  Prof. Autry's approach, our grading styles (particularly with
  regards to comments), and, most importantly, what we think is
  best for you.
* We will require two parts to a resubmission:
    * A set of notes on what is wrong (in your own words) and, if possible,
      how you can avoid making that kind of mistake in the future
    * A corrected answer
* The set of notes encourages you to think more generally about the issues
  at play.
* The corrected answer shows that you can solve the problem.
* I'll be distributing an example over the weekend.

### Upcoming events

* Monday, 2026-03-30, 7:00 p.m., _Mentor Session_ 
  (Resubmissions, Project 3, Problem Set 4)
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)

### Upcoming deadlines

* Tonight: Problem Set 3
* Monday, 2026-03-30: Read CLRS 22.1 (Bellman-Ford)
* Monday, 2026-03-30: Resubmissions of Assessment 1, Problem Set 1, Problem
  Set 2, Project 1, and Project 2 due.
    * These are all _optional_. You may choose to wait until May 15.
      However, if you resubmit now, you'll get it graded promptly and
      can cross it off your list.
    * Those who did not turn in initial submissions of these assignments
      (or who turned in partial submissions) may also turn in resubmissions.
    * The LaTeX policy does not apply to these.
* Wednesday, 2026-04-01: Assessment 2 due
    * The LaTeX policy also does not apply to assessment 2, but you can
      certainly use LaTeX.
* Monday, 2026-04-06: Project 3 due (does not match Autry's syllabus)

### Project 3: Arbitrage

* Project 3 asks you to find a negative weight cycle in the graph.
    * Although the assignment does not say so, you should return the
      empty path if there are no negative weight cycles.
* Prof. Autry did not share his program for pairing students, and I
  hadn't realized that there were preferences and anti-preferences
  available, so it will take me a bit longer to pair you off. I expect 
  to get pairings out tonight.
* Please read the assignment over the weekend and ask questions on our
   Q&A channel on Teams or via email.
* Reminders: 
    * You should work with your partner using a pair-programming model
    * You should document your code appropriately
    * You should try to write clean code

### Policy/administrative questions

Do we still have the extended deadlines?

> 24 hours are free. These will get graded on time.

> Another 48 hours require an imaginary token. These may not get graded
  on time.

Was this supposed to be on the screen?

> Yes. Sorry.

Questions from the reading
--------------------------

Dijkstra's algorithm
--------------------

In short: A greedy algorithm based on current known distance from $$s$$
to $$v$$.

```
// Find the weight of the shortest path from s to t in the weighted 
// graph G = (V,E,w), where w only returns positive integers.
//
// Inputs:
//   G: a graph
//   s: a vertex label
//   t: a vertex label
//
// Output:
//   The weight of the shortest path from s to t. If there is no path
//   from s to t, returns infinity.
//
// Note:
//   The code also collects enough information to permit reconstruction
//   of the shortest path, but does not return it.
shortestPath(G, s, t):
    // Initialization
    for (v : G.V):
        distance(v) = infinity
        predecessor(v) = -1
    distance(s) = 0
    predecessor(s) = s

    // Loop 
    while (!marked(t)):
        let v be the unmarked vertex with minimal distance
          // That is, for all u, u in unmarked, d(v) <= d(u)
        mark(v)
        foreach n in G.neighbors(v):
            if distance(v) + G.weight(v,n) < distance(n)
                 distance(n) = distance(v) + G.weight(v,n) 
                 predecessor(n) = v

    // And we are done
    return distance(t)
```

Proving Dijkstra's algorithm correct
------------------------------------

Theorem: Given a weighted graph, $$G$$, with positive edge weights
and designated vertices, $$s$$ and $$t$$, Dijkstra's returns the
weight of the shortest path from $$s$$ to $$t$$. If there is no
such path Dijkstra's returns $$\infty$$.

A proof by strong induction on the number of rounds.

After (and before) each round ...

At the start of each loop, we know that the current distance from the start 
vertex to all marked vertices is the minimum distande to each of those 
vertices.

$$Let D(v) be the minimum distance to that vertex. d(v) is the current
known distance, \forall u in M, D(u) = d(u).$$

Induction on the number of rounds = size of M.

Initialization/Base case:

Suppose $$|M| = 0$$, which means that we've marked no nodes. This holds
vacuously. There are no marked nodes, so the property holds for all none of 
them.

Suppose $$|M| = 1$$. If $$|M| == 1$$ then $$M = { s }$$. Since there are
no negative edge weights in the graph, any path from $$s$$ to $$s$$ is
at least 0. (We initialied $$d(s)$$ to 0.)

Invariant: M contains the $n$ values with shortest distance.

Inductive hypothesis: Suppose there is an $$n >= 1$$ s.t. $$|M| = n$$
and $$\forall u in M, D(u) = d(u)$$.

Goal: Show that when $$|M| = n+1$$, $$\forall u in M, D(u) = d(u)$$. That
is, for the vertex, $$v$$, we add in round $$n+1$$, $$D(v) = d(v)$$.

Inductive analysis: Consider the vertex immediately before $$v$$, which
we'll call $$p$$ (predecessor). $$p$$ must be in $$M$$. What do we know 
about $$d(p)$$ vs. $$d(v)$$?  $$d(v) > d(p)$$ because the edge $$(p,v)$$
has positive weight. If $$d(p) < d(v)$$, we would not be considering
$$v$$ in this round, we'd be considering $$p$$.

Since the claim holds for $$|M| = 0 and |M| = 1, and we've shown that
given that if the claim holds for $$|M| = n$$ it must also hold for
$$|M| = n + 1$$, by the principles of induction, the original claim holds.

Q.E.D.

Dijkstra's running time
-----------------------

What are some of the key operations we must consider?

The number of times we do the outer loop: $$n = |V|$$.

```
shortestPath(G, s, t):
    // Initialization
    for (v : G.V):
        distance(v) = infinity
        predecessor(v) = -1
    distance(s) = 0
    predecessor(s) = s

    // Loop 
    while (!marked(t)): // O(n) repetitions, where n = |V|
        let v be the unmarked vertex with minimal distance  // Depends on imp.
                                                            // List O(n)
                                                            // Heap O(logn)
                                                            // FibHeap O(1)
          // That is, for all u, u in unmarked, d(v) <= d(u)
        mark(v) // Constant time
        foreach n in G.neighbors(v):                        // O(n)
            if distance(v) + G.weight(v,n) < distance(n)    // O(1)
                 distance(n) = distance(v) + G.weight(v,n)  // O(1), maybe
                 predecessor(n) = v                         // O(1)

    // And we are done
    return distance(t)
```

Initial analysis: What's the running time? $$O(n^2)$$ because the
outer loop is O(n) and the inner loop is O(n) and we know that 
for nested loops we can do multiplication.

Revised analysis: Can we look at the problem differently to get a
more precise running time (perhaps a lower upper bound)?

We can look at the parts of the algorithm that we do for each vertex
and the parts of the algorithm that we do for each edge 

$$O(n \times  workpervertex + m \times workperedge)$$, where $$n$$ is the
number of vertices and $$m$$ is the number of edges.

For each vertex

* Some marking, unmarking, setting values: O(1)
* Finding the smallest distance vertex: O(logn) for "smallest" if we
  use a heap

For each edge

* Lots of O(1) work
* Perhaps a hidden update to the heap. O(logn)

So $$O(n \times log(n) + m \times log(n))$$

When the graph is sparse, $$m$$ is approximately $$n$$, so it's an
$$O(nlogn)$$ algorithm.

Unfortunately, when the graph is not sparse, $$m$$ is approximately $$n^2$$
and the algorithm is $$O(n^2 logn)$$.

What about negative edge weights?
---------------------------------

Will Dijkstra's work if there are negative edge weights in the graph?
Why or why not?

* The proof assumes that the distance to a vertex is greater than
  the distance to all previous vertices. That is no longer true.
  Whoops.

Assuming it doesn't work, how might you try to fix it?

Bellman-Ford
------------

An algorithm that handles negative edge weights

The algorithm:

```
for i = 1 to n
     for v: V   
        foreach n in G.neighbors(v): 
            if distance(v) + G.weight(v,n) < distance(n)
                 distance(n) = distance(v) + G.weight(v,n)
```

Sample graph (due to Autry)

```
w(S,A) = 10
w(S,G) = 8
w(A,E) = 2
w(B,A) = 1
w(B,C) = 1
w(C,D) = 3
w(D,E) = -1
w(E,B) = -2
w(F,E) = -1
w(G,F) = 1
```

We'll run the algorithm on the board. You can also look at Autry's notes
for details.

Notes for Sam
-------------

Claim: After $$n$$ rounds, the distances for the $$n$$ marked vertices
represent the shortest distance from $$s$$ to each of those vertices.

A slightly more precise claim: Let $$D(v)$$ be the length of the
shortest path from $$s$$ to $$v$$. After each round, $$\forall
v in M, distance(v) = D(v)$$.



