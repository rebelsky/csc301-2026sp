---
title: "EBoard 24: Greed (and a bit of Bellman-Ford)"
number: 24
section: eboards
held: 2026-03-30
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

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

* Monday, 2026-03-30, 6:00-7:00 p.m., 3819, _Mentor Session_ 
  (Resubmissions, Project 3, Problem Set 4)
* Tuesday, 2026-03-31, Noon, _CS Table_ (Web Bloat)
* Thursday Extra, 2026-04-02, 4:00 p.m.: ????
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)

### Upcoming deadlines

* Tonight: Resubmissions of Assessment 1, Problem Set 1, etc.
* Wednesday, 2026-04-01: Assessment 2 due
* No reading for Wednesday! You have enough other work
* Monday, 2026-04-06: Project 3 due (does not match Autry's syllabus)

### Policy/administrative/assignment questions

Will we continue to use Autry's grading system?

> Yes, that's the current plan.

Where can I view changes to the late policy?

> They are probably on some random eboard, but I'll try to get them
  posted, too.

Bellman-Ford
------------

A shortest path algorithm that handles negative edge weights

The algorithm:

```
Reset distances, previous, etc.
distance(s) = 0
previous(s) = s
for i = 1 to n-1
    for each v in V   
        foreach n in G.neighbors(v): 
            if distance(v) + G.weight(v,n) < distance(n)
                distance(n) = distance(v) + G.weight(v,n)
                previous(n) = v
```

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

Please try the worksheet, feeling free to discuss it with your neighbor.

Whoops! It's the same example. Try changing EB to -4.

Questions (TPS)
---------------

What did you discover after changing EB to -4?

* There is a negative weight cycle. Precondition to BF: No negative weight
  cycles. (We do not usually verify this precondition.)

What do we know about distances after $$k$$ rounds? Does it make a difference
if we assume all edges are updated simultaneously?

* The longest a path can be after k rounds with the simultaneous update
  is k edges.
* The algorithm may still produce paths of more than $$k$ edges in
  round $$k$$
* So we know that at round $$k$$ we have the shortest path to any vertex
  that uses no more than $$k$$ edges (simultaneous update)
* We know the minimum possible value after a path of $$k$$ edges
* Hand waving proof of optimality.

What is the running time of BF? (You can give a rough estimate.)

* Version with triply-nested loop: $$O(|V|^3)$$ because each loop can
  take as many as $$O(|V|)$$ repetitions.
* Version with doubly-nested loop: $$O(|V|)$$ for outer loop, $$O(|E|)$$
  for inner loop (with the right graph representation), so $$O(|V|\times |E|)$$
* What assumptions are you making about graph representation in each case?
    * In the first case, adjacency matrices and adjancency lists work well
    * In the second case, we need an edge list

Are there "natural" improvements you could make (they may not affect
the worst-case running time, but they could affect average case or
"real" running time).

* We can mark vertices when we update them; we only need to check
  marked vertices when we are looking for what to update.
* We can also stop when all the vertices are unmarked (or if you don't
  update any vertices in a round).
* It might be faster if we had a queue of vertices to update
* You need to start at S
* You need to keep checking S to see if you have negative cycles.
    * I suppose discovering that you can get from S to S with a negative
      total distance also suffices for a negative loop.

How could we use Bellman-Ford to identify negative weight cycles?

* After we've run the inner stuff $$|V|-1$$ times, we run it one more
  time. Since there shouldn't be a path of length > $$|V|-1$$, if we
  see an improvement, it suggests there's a loop.

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
* Note that those dollar signs are for LaTeX and not to indicate currencies.

If our prices are 1, 5, 10, 25, what is the greedy algorithm and does
it seem to work?

* Greed: Take as many of the highest value as you can, then as many of the
  next highest value, and so on and so forth.

Are there sets of prices for which the greedy algorithm does not seem
to work?

* That is, find a set of prices and a total price for which the greedy 
  algorithm fails.
* The algorithm is pointless if they are all the same price.
* It may not be possible to make some total prices if we choose badly
  (e.g., all stamp prices are even, but the total is odd)
* We will assume there is a one cent stamp and that there are at least
  two stamp values.
* I imagine that somewhere, over the rainbow, there's a case in which
  not taking the bigger stamp is the right thing.
* Stamps: 1, 14, 25; Total of 28
    * Greed: 25, 1, 1, 1
    * Optimal: 14, 14
* We will return to this problem when we get to dynamic programming

The event sequencing problem
----------------------------

Suppose you are at a movie theatre (multiplex) and you want to maximize
the number of movies you see. Each movie has a start time and an end
time (implicitly a length).  

* We assume that you stay through each movie for the whole time.
* You must follow normal movie theatre protocol.
* You cannot run between theatres (or walk or crawl or teleport or ...)

What greedy options are there?

* Shortest movie
     * A: 10:00-10:30
     * B: 8:00-10:05
     * C: 10:15-12:00
* Earliest start time
     * A: 8:00-12:00
     * B: 8:15-8:30
     * C: 8:45-9:00
* Non greedy: Start with the shortest movie and just stay in that theatre
     * Theatre 1: 11:45-12:00
     * Theatre 2: 8:00-8:45, 9:00-9:45, 10:00-10:45
* Alphabetical
     * See earliest start time
* Earliest end time
     * It turns out that this is the correct greedy choice

Can we find counter-examples to these?

* See above!
