---
title: "EBoard 24 (Section 2): Greed (and a bit of Bellman-Ford)"
number: 24.2
section: eboards
held: 2026-03-30
link: true
---
# {{ page.title }}

_Approximate overview_

* Administrative stuff
* Bellman-Ford 
    * The Bellman-Ford algorithm
    * Practice with Bellman-Ford
    * Bellman-Ford worksheet
* Greed
    * Basic principles
    * The stamp minimization problem
    * The movie scheduling problem

Administrative stuff
--------------------

* As usual, today's outline likely has more than we can cover.

### Upcoming events

* Monday, 2026-03-30, 6:00-7:00 p.m., _Mentor Session_ 
  (Resubmissions, Project 3, Problem Set 4)
* Tuesday, 2026-03-31, Noon, _CS Table_ (Web Bloat)
* Thursday, 2026-04-02, 4:00 p.m., _CS Extra_
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)

### Upcoming deadlines

* Tonight: Resubmissions of Assessment 1, Problem Set 1, Problem Set 2, etc.
* Wednesday, 2026-04-01: Assessment 2 due
* No reading for Wednesday! You have enough other work
* Monday, 2026-04-06: Project 3 due (does not match Autry's syllabus)

### Project 3: Arbitrage

* Project 3 asks you to find a negative weight cycle in the graph.
    * Although the assignment does not say so, you should return the
      empty path if there are no negative weight cycles.
* Reminders:
    * You should work with your partner using a pair-programming model
    * You should document your code appropriately
    * You should try to write clean code

### Assessment 2: Graph Algorithms (1)

* Problem 1: Summing budgets
* Problem 2: Counting clicks
* We will be generous on the due date, but try to get this in soon.

### Policy/administrative questions

What is the late policy?

> More or less as it has been.

Bellman-Ford
------------

A shortest path algorithm that handles negative edge weights (but no
negative cycles)

The algorithm:

```
Reset distances, previous, etc.
distance(s) = 0
previous(s) = s
for i = 1 to |V|-1
    for each v in V   
        foreach n in G.neighbors(v): 
            if distance(v) + G.weight(v,n) < distance(n)
                distance(n) = distance(v) + G.weight(v,n)
                previous(n) = v
```

* We sometimes think of this as processing all the vertices simultaneously.
  Otherwise, it's usually in some natural order (e.g., S and then alphabetical).

An alternate formulation

```
Reset distances, previous, etc.
distance(s) = 0
previous(s) = s
for i = 1 to n-1
    for each <v,n> in E
        if distance(v) + G.weight(v,n) < distance(n)
            distance(n) = distance(v) + G.weight(v,n)
            previous(n) = v
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

Worksheet
---------

How does the result change if EB is -4 instead of -2?

Questions
---------

What did we see if EB is -4 instead of -2?

* Two negative weight cycles.

What do we know after $$k$$ rounds? (Something about the current distances)

* `distance(v)` is <= the minumum distance you can get from a path of k edges.
* This can be used to show the algorithm works: Since you can't have a path
  of more than |V|-1 vertices, |V|-1 rounds gives you the best overall path.

What is the running time (casually)?

* Three nested loops: $$O(|V|^3)$$
* Two nested loops: $$O(|V| \times |E|)$$
* Assumptions about data structures?
     * To get th emore efficient running time bound, we need
       adjancency lists for algorithm 1 and edge lists for algorithm 2.
       In both cases, an adjacency matrix will give us the cubic running
       time.

Are there "natural" improvements you could make (they may not affect
the worst-case running time, but they could affect average case or
"real" running time).

* Stop when a round gives you no updates.
* Mark vertices when you decrease their distance. In the next round,
  you only need to look at marked vertices (unmarking them first)

How can we find negative weight loops?

* Observation: If you have a negative weight loop, you can keep improving
  the best path by increasing the number of vertices on the path. Run
  Bellman-Ford one extra time and see if anything decreases.
* If there are no negative loop cycles, the longest a path can be is
  $$|V|-1$$. If we can improve the distance to some vertex by increasing
  the path by one edge (to length $$|V|$$), that suggests that we negative
  loop.

Greed
-----

* An approach to the design of algorithms
* Most frequently used for optimization algorithms (e.g., minimization
  or maximization)
* Basic idea: To reach on overall optimum, choose the local optimum
* The challenge: What "local optimum" do you choose?
     * Dijkstra's: Not the edge with lowest weight, not the neighboring
       edge with lowest weight, but the vertex with lowest total distance
* Another challenge: Does it work? Not always

The stamp minimization problem
------------------------------

* Given an infinite supply of stamps of various prices, $$p1, p2, ... pn$$,
  with $$p1 < p2 < ... < pn$$ and a total price, $$T$$, find the smallest 
  total number of stamps needed to make exactly $$T$$.
* Example: Prices 1, 5, 10, 25, total price of 68: 
  $$2 x 25 + 1 x 10 + 1 x 5 + 3 x 1$$

If our prices are 1, 5, 10, 25, what is the greedy algorithm and does
it seem to work?

* Take the largest stamp if you can and recurse.
* Take as many of the 25's as you can, then as many of the 10's as you can
  then as many of 5's as you can, and then 1's for the remainder.

Are there sets of prices for which the greedy algorithm does not seem
to work? (E.g, 1, 2, 3)

* prices/values are 1, 12, 19, total is 24

Damn! The greedy algorithm is not universal. We'll design a universal
algorithm when we get to dynamic programming.

The event sequencing problem
----------------------------

Suppose you are at a movie theatre (multiplex) and you want to maximize
the number of movies you see. Each movie has a start time and an end
time (length).

What greedy options are there?

* Start with the shortest one
     * A: 10:00-10:30
     * B: 8:00-10:15
     * C: 10:16-11:59
* Start with the one that starts first and work forward
* Start with the one that starts last and work from back to front
* Start with the one that ends last and work from back to front
* Start with the one that ends first and work from front to back
* Alphabetical!

Can we find counter-examples to these?
