---
title: "EBoard 22: The shortest-path problem and Dijkstra's algorithm"
number: 22
section: eboards
held: 2026-03-25
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Refresh: How do you approach an algorithms problem?
* The shortest path problem
* Dijkstra's algorithm
* Proving Dijkstra's algorithm correct

Administrative stuff
--------------------

* I'm new to Canvas, and have not yet figured out how to put due dates
  on there. Stay tuned!
* Although "Spam from Sam" is my traditional mechanism for communicating
  minor course issues to students, for this course, I'll be using the
  Canvas announcement system. Let me know if that doesn't work for you.
    * Do you get email when I post an announcement?
    * Recent posts:
        * Grades for Assessment 1.2 posted
        * Test for problem 2.4 posted
        * Notes on proof by induction posted
        * Repacement version of Problem 2.1 posted
        * You are now on our class Team
        * Scheduling meetings with me
        * Please fill out the course survey
    * Consistent capitalization is for hobgoblins.
* I'll try to keep whiteboards and markers at the front of the room.
  Feel free to grab them when you come in for when we do algorithms
  exercises.

### Upcoming events

* Wednesday, 2026-03-25, Noon, HSSC Auditorium.
     * "Legislative Update or How the Iowa Legislature is trying to
       screw Grinnell."
* Thursday, 2026-03-26, 4:15 p.m., _CS Extras_
  The Quest for Transparency and Accountability in the Online Data Ecosystem
     * "Tea" at 4pm in the commons.
     * Chat about grad school afterwards 
* Thursday, 2026-03-26, 7:00 p.m., _Mentor Session_ (LaTeX)

### Upcoming deadlines

* Friday, 2026-03-27: Read CLRS 604--611 (Shortest Path) and 
  620--624 (Dijkstra's). 
    * Please don't read about Bellman-Ford.
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

### Questions

What should a resubmission for an assessment look like?

> Stay tuned! I'll give an example on Friday (or, more likely, post
  one to Canvas).

Will the late policy remain?

> Modifications: Tokens are meaningless. If you turn it in more than a
  day late, you may not be graded in the first round. If you don't
  turn it in within three days and don't send me an email, I'll send 
  you an email and turn in an AA. 

Refresh: How do you approach an algorithms problem?
---------------------------------------------------

Since we're returning from break, it will be useful to consider some of
the key steps involved are in addressing an algorithms problem. (TPS)

* Read the question or the problem.
* Ensure that you understand the problem, including the expected inputs
  and outputs.
    * Try some examples (perhaps including edge cases)
    * Express it formally (UM: using math)
* 
* Consider possible ways to solve the algorithm
    * Use a technique that you used for a similar problem (or something
      you can pretend is similar).
    * Convert the problem to one we've already solved and use that
      solution. ("Reduction")
    * Consider decomposing the problem into subproblems and solve them
      separately.
    * Consider whether divide-and-conquer might work
    * See if it's been solved in the math literature
    * Use dynamic programming (a technique that we'll cover in a few
      weeks)
    * Look at how you solved your examples by hand and generalize
    * Will a greedy solution work?
    * Will exhaustive search work?
* Write your algorithm in pseudocode
* Test your algorithm on a few examples
* Implement the algorithm
* Reflect on edge cases
* Make sure it's correct (UM: Use math)
    * Proof
    * Exhaustive test suite (don't use math)
* Figure out the big-O run time
    * UM: Use math!
    * Graph running time
* Ask yourself "can I do better"?

Other things

* Don't spend too much time at once. If you are stuck, walk away from
  the problem (unless it's during class).
* Consider appropriate data structures

A shortest path problem
-----------------------

Given a weighted graph, $$G = (V, E, w)$$ and designated vertices
$$s,t \in V$$, find the path from $$s$$ to $$t$$ with the least
total weight. (Alternately, find the weight of that path.) You may 
assume that all weights are positive.

Formalization question: What "types" are $$V$$, $$E$$, and $$w$$?

* $$V$$ is a finite set of vertices (distinct values)
* $$E$$ is a fintte set of pair of vertices, $$E \subset VxV$$, 
  representing edges
* $$w$$ is a function from E to natural numbers (or integers, real numbers)
  that is, a property of edges

Side question: We normally limit ourselves to one directed edge between each 
pair of vertices. How does our world change if we want to permit multiple
edges?

* Usually, we treat $$E \subset VxVxN$$
* We've chosen this notation to ensure that there's only one weight per
  edge.

Formalization question: How do you express "the path from $$s$$ to
$$t$$ with the least total weight" in such a way that we could analyze
it mathematically? 

* _I realize that this is easy for some of you and perhaps too hard for 
  others of you. I'd like to make sure we practice precision in
  this class, which will require that we use some formal notation._
* _Formal notation also helps us think more carefully about what we
  want to achieve._
* _I should warn you that I may get things wrong on occasion. In such 
  case, I'll take of this course's generous resubmission policy._
     * _Or perhaps I'm writing them incorrectly to make sure that
       you are paying attention._

Answer to formalization question:

* A path from s to t is a sequence of vertices, U = { u1 ... un } 
  s.t. (a) For each pair, (ui,ui+1), that pair is in E, and 
  (b) u1 = s, and (c) un = t.
* Our goal is a path from s to t, U = { u1, ..., un }, s.t.
  For any path from s to t { n1, ..., nm }, Sum(w(nj,nj+1) >= Sum(w(ui,ui+1)

Note

* Our statement suggests one (possibly inefficient) algorithm: Find all the 
  (non-looping) paths and choose the shortest one.

Sample Graph

* V = { V0, V1, V2, V3, V4 }
* Weights:
    * w(V0,V1) = 3
    * w(V0,V3) = 5
    * w(V1,V2) = 6
    * w(V1,V3) = 1
    * w(V2,V4) = 2
    * w(V3,V1) = 1
    * w(V3,V2) = 3
    * w(V3,V4) = 4
    * w(V4,V2) = 1
* E is implicit from the weights

Question: What is the shortest path from V0 to V4?

* Approach one: Always choose the smallest edge (from a visited vertex)
  we haven't used. Whoops. Failed!
     
Dijkstra's algorithm
--------------------

_Many of you have seen Dijkstra's algorithm before. We'll split the class
into those who remember it and those who do not. We'll then split those into
smaller groups_

Those who don't remember it: How would you approach the problem? (TPS)

Those who do remember it: How would you prove it correct? (TPS)

Those who already remember how to prove it correct: Listen in to
discussions but try not to help.

Hint: This is also a greedy algorithm, but the greedy choice is *not*
"choose the smallest edge". What kinds of greedy choices can you make?

Dijkstra's idea(s):

* At every step, we keep track of the nearest "known" distance to
  each vertex (updating as we add more vertices or edges).
* We do greed based on known distrance.

Trace:

* See recording

Other versions:

* CHange the edge weights and see what happens.

Edge cases:

* Multiple paths of equal weights
* No path (when do we stop)

A question:

* How do you find the unmarked vertex with the shortest distance?
* We store the vertices in a priority queue (preferably, a priority
  queue that permits us to update values)
    * Unordered list O(n) to remove
    * A heap: O(logn) to add, O(logn) to remove
    * [It turns out we have things even better than heaps, but we'll
      stick with heaps]

The algorithm:

```
shortestPath(V, E, w, s, t):
    // Initialization
    // Loop 
    // Whatever else
```

Proving Dijkstra's algorithm correct
------------------------------------

Possible approaches

* Induction (weak and strong) with or without invariants
    * We will prove this by (weak) induction
* Brute force
* Contradiction
* Contrapositive
* Construction

Wrapup
------

* Read CLRS for the proof. Be prepared to discuss on Monday. We may
  start with questions.
* Apology: I know that we're changing teaching styles, I understand
  that it will take some time to adjust.
