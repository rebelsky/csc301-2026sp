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
        * Repacement version of Problem 21 posted
        * You are now on our class Team
        * Scheduling Meetings with Me
        * Please fill out the course survey
    * Consistent capitalization is for hobgoblins.
* I'll try to keep whiteboards and markers at the front of the room.
  Feel free to grab them when you come in for when we do algorithms
  exercises.

### Upcoming events

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
      However, if you resubmit now, you'll get one more chance and/or
      cross it off your list early.
    * Those who did not turn in initial submissions of these assignments
      (or who turned in partial submissions) may also turn in resubmissions.
    * The LaTeX policy does not apply to these.
* Wednesday, 2026-04-01: Assessment 2 due
    * The LaTeX policy also does not apply to assessment 2, but you can
      certainly use LaTeX.

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
total weight. (Alternately, find the weight of that path.) You may 
assume that all weights are positive.

Formalization question: What "types" are $$V$$, $$E$$, and $$w$$?

* $$V$$ is
* $$E$$ is
* $$w$$ is 

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

*

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

QUestion: What is the shortest path from V0 to V4?
     
Dijkstra's algorithm
--------------------

_Many of you have seen Dijkstra's algorithm before. We'll split the class
into those who remember it and those who do not. We'll then split those into
smaller groups_

Those who don't remember it: How would you approach the problem? (TPS)

Those who do remember it: How would you prove it correct? (TPS)

Those who already remember how to prove it correct: Listen in to
discussions but try not to help.

```
shortestPath(V, E, w, s, t):
    // Initialization
    // Loop 
    // Whatever else
```

Proving Dijkstra's algorithm correct
------------------------------------

* 
