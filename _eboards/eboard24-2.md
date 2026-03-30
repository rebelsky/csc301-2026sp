---
title: "EBoard 24: Greed (and a bit of Bellman-Ford)"
number: 24.2
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

* Monday, 2026-03-30, 7:00 p.m., _Mentor Session_ 
  (Resubmissions, Project 3, Problem Set 4)
* Tuesday, 2026-03-31, Noon, _CS Table_ (Web Bloat)
* Thursday, 2026-04-02, 7:00 p.m., _Mentor Session_ (Project 3, Problem Set 4)

### Upcoming deadlines

* Tonight: Resubmissions of Assessment 1, Problem Set 1, Problem
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

Questions
---------

What do we know after $$k$$ rounds?

What is the running time?

Are there "natural" improvements you could make (they may not affect
the worst-case running time, but they could affect average case or
"real" running time).

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

Are there sets of prices for which the greedy algorithm does not seem
to work?

The event sequencing problem
----------------------------

Suppose you are at a movie theatre (multiplex) and you want to maximize
the number of movies you see. Each movie has a start time and an end
time. 

What greedy options are there?

* 
* 
* 
* 
* 

Can we find counter-examples to these?
