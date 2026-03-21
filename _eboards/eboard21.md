---
title: "EBoard 21: Shortest path and Dijkstra's algorithm"
number: 21
section: eboards
held: 2026-03-23
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* About assessment 2
* Notes on problem 1 from problem set 2
* Notes on project 2
* Refresh: How do you approach an algorithms problem?
* The shortest path problem
* Dijkstra's algorithm
* Proving Dijkstra's algorithm correct

Administrative stuff
--------------------

* I've taken over the class. See the email for more details.
* Note that we've already had one student reported for AI use; please do
  not use AI in this class.
    * More importantly, you shouldn't use AI because you probably
      won't learn as much.
* Your work from the first half of the semester should be returned on
  Gradescope.
    * If you did not turn in work, you should have received an email from me.
* Warning! It may take me some time to learn names (even if I should know
  your names from prior classes).

### Upcoming events

* Monday, 2026-03-23, 7:00 p.m., _Mentor Session_ (LaTeX)
* Thursday, 2026-03-23, 7:00 p.m., _Mentor Session_ (LaTeX)

### Upcoming deadlines

* Wednesday, 2026-03-25: Read CLRS 604--611 (Shortest Path) and 
  620--624 (Dijkstra's). 
    * Please don't read about Bellman-Ford.
* Monday, 2026-03-30: Resubmissions of Assessment 1, Problem Set 1, Problem
  Set 2, Project 1, and Project 2 due.
    * These are all _optional_. You may choose to wait until May 15.
      However, if you resubmit now, you'll get one more chance and/or
      cross it off your list early.
    * Those who did not turn in initial submissions of these assignments
      (or who turned in partial submissions) may also turn in resubmissions.
    * The LaTeX policy does not apply to these.
* Wednesday, 2026-04-01: Assessment 2 due
    * The LaTeX policy also does not apply to assessment 2.

### The course web site

* I've set up a second course web site for the course. 
   * Partially so that I can post eboards. 
   * Also because I don't have rights to change the syllabus PDF.
* Things you should know
   * Easy links elsewhere (I hope)
   * Eboards can be found in schedule
   * If all goes well, eboards are updated "live"

### The course Teams team

* I've set up a Microsoft Teams team for this course
* There's an area for Q&A that I'll try to track
* There's an area for recordings that I'll try to make
* I think those are the only purposes I'll use it for

Assessment 2
------------

* Assessment 2 should be live on Canvas
* Problem 1: Sum of salaries
* Problem 2: Click distance

Notes on problem 1 from problem set 2
-------------------------------------

The problem: Find a loop invariant for the following algorithm and use it
to prove that the algorithm correctly finds the smallest element in a
non-empty array.

```
def find-min(arr):
    currMin = infinity
    // Loop invariant goes here
    for i = 1, 2, ..., length(arr):
        if currMin > arr[i-1]:
             currMin = arr[i-1]
    // Loop invariant still holds here
    return currMin
```

An insufficiently helpful invariant: 

> `currMin` is less than every element of `arr`

Why is it insufficiently helpful? 

* 

A potentially better invariant:

> `currMin` is ...

Expressing it a bit more formally:

> ...

Why is this invariant better?

* 

What problems might there be with it?

* 

Rewriting `find-min`to make the problem easier

* 

Notes on project 2
------------------

Here's a sketch of a typical solution to project 2

```
BFS(adjList, start, finish):
    vertices = new Queue
    vertices.enqueue(start.label)
    while (!vertices.isEmpty())
        v = vertices.dequeue();    
        mark v
        for n : neighbors(vertex)
            if (!marked(n))
                n.previous = vertex
                vertices.enqueue(n)
    path = new List
    v = finish.label
    while (v != start.label)
        path.addToFront(v)
        v = v.previous
    path.addToFront(v)
    return path

DFS(adjList, start, finish):
    vertices = new Stack
    vertices.push(start.label)
    while (!vertices.isEmpty())
        v = vertices.pop();    
        mark v
        for n : neighbors(vertex)
            if (!marked(n))
                n.previous = vertex
                vertices.push(n)
    path = new List
    v = finish.label
    while (v != start.label)
        path.addToFront(v)
        v = v.previous
    path.addToFront(v)
    return path
```

That has a variety of problems, including at least one incorrectness
issue, at least two stylistic issues, and at least three efficiency issues.
How many can you identify? (TPS)

* 
* 
* 
* 
* 
* 

How would you address the following? (TPS)

Refresh: How do you approach an algorithms problem?
---------------------------------------------------

Since we're returning from break, it will be useful to consider what some of
the key steps involved are in addressing an algorithms problem. (TPS)

* 
* 
* 
* 
* 
*  

A shortest path problem
-----------------------

Given a weighted graph, $$G = (V, E, w)$$ and designated vertices
$$s,t \in V$$, find the path from $$s$$ to $$t$$ with the last
total weight. You may assume that all weights are positive.

Formalization question: What "types" are $$V$$, $$E$$, and $$w$$?

* $$V$$ is
* $$E$$ is
* $$w$$ is 

Formalization question: How do you express "the path from $$s$$ to
$$t$$ with the least total weight" in such a way that we could analyze
it mathematically?

Dijkstra's algorithm
--------------------

_Many of you have seen Dijkstra's algorithm before. We'll split the class
into those who remember it and those who do not._

Those who don't remembrer it: How would you approach the problem? (TPS)

Those who do remember it: How would you prove it correct? (TPS)

Those who already remember how to prove it correct?: Listen in to
discussions but don't help.

Proving Dijkstra's algorithm correct
------------------------------------
