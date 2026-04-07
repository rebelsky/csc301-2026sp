---
title: "EBoard 28: Bipartite and Stable Matching"
number: 28
section: eboards
held: 2026-04-08
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Bipartite matching, revisited
* Greedy approaches to bipartite matching
* Stable matching
* Greedy approaches to stable matching

Administrative stuff
--------------------

* I'm teaching CSC-151 8:30--9:50 on Friday morning. I may be a few
  minutes late in starting class while I get the recordings set up.
* Does anyone use the recordings, or can I stop making them?
    * I'll keep up the transcription.
* Just so you know, I write most of my board "live" rather than using 
  slides for a number of reasons.
    * It makes it easier to adapt to student questions.
    * It keeps the pace down, so that it's easier for you to take notes.
      (I realize that you probably can't write down everything at the
      speed at which I type, it's close.
    * It reminds you that eveyrone can make mistakes.
    * It lets me record the results of TPS activities.
    * ...
* For clarity: 
    * The only times you should be using Web search in this class are ...
        * To find information on how to do something in Python
        * To find information on how to do something in C++
        * To find information on how to do something in LaTeX
    * The only time you should be using AI in this class is ...
        * When Web search gives you an AI summary of those results
    * Don't ask the Web or AI for help on assignments
    * Don't ask the Web or AI to translate something into LaTeX

### Upcoming events

* Wednesday Extra, 2026-04-08, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday Extra, 2026-04-09, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday, 2026-04-09, 7:00 p.m., _Mentor Session_
* Friday, 2026-04-10, 5:30--7:00 p.m., _Keynote with Ajuna Kyaruzi '17_.
  Sign up in advance by Wednesday.
* Monday, 2026-04-13, 7:00 p.m.ish, 3819, _Mentor Session_
* Tuesday, 2026-04-14, Noon, _**NO* CS Table_

### Upcoming deadlines

* Friday, 2026-04-10: Read CLRS 25.2 on Stable Marriage (skimming is okay)
* Monday, 2026-04-13: Assessment 2 Resubmissions
* Monday, 2026-04-13: Problem Set 3 Resubmissions
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Policy/administrative/assignment questions

Bipartite Matching, revisited
-----------------------------

Definition: Given a (weighted) bipartite graph, 
$$G = (L,R,E\subset L\times R,w)$$, a _matching_ in $$G$$ is
a set of edges $$M \subseteq E$$ s.t., $$\forall l \in L, r \in R$$,
there is at most one edge of the form $$[l,\star]$$ and at most one
edge of the form $$[\star,r]$$ ,in $$M$$

Definition: $$matchings(G)$$ is the set of all matchings of $$G$$.

### Maximal Unweighted Bipartite Matching

Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O \in matchings(G), |M| \ge |O|$$

### Maximal Weighted Bipartite Matching

Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O \in matchings(G), \sum_{m\in M}w(m) \ge \sum_{o\in O}w(o)$$

Greedy Approaches to Bipartite Matching
---------------------------------------

### What greedy approaches to bipartite matching might we try? (TPS)

Unweighted:

* 

Weighted:

* 

### Do they work / can we find counter-examples? (TPS)

Unweighted:

* 

Weighted:


The Stable Matching Problem
---------------------------

This isn't quite a bipartite-matching problem, but it's fairly close.

We have two sets of people. People in the first set have matching
preferences for people in the second set, and people in the second
set have matching preferences for people in the first set.

For example, if our first set contains A, B, C, and D and our second
set contains P, Q, R, and S, A might rank the members of the second
set as SPQR, B might rank them as QPRS, and so on and so forth.
Similarly, P might rank the members of the first set as ABCD, Q
might rank them as DCBA, and so on and so forth.

A _match_ is a pairing of elements of the first set with elements of
the second set (or vice versa). Each element in each set is paired
with exactly one element. (It's almost bipartite matching.)

A _stable_ match is one in which there is no unmatched pair of people 
who would trade their current match for a better one.

For example, if we matched A with Q and B with P, the match is _unstable_
because A would drop Q to be with P (still preferring S) and P would drop
B to be with A (top choice!).

Historically, this is called _The Stable (Heterosexual) Marriage_ problem.

We'll try some examples in class.

Solving the Stable Matching Problem
-----------------------------------

Is there a deterministic algorithm that we can use to do the matching?

What is a greedy approach? (TPS)

What are the flaws in that approach? (TPS)

Can we correct those flaws? (TPS)

The Gale-Shapley Algorithm
--------------------------

Running Gale-Shapley
--------------------

Example 1

* A: PQRS
* B: PSRQ
* C: PRQS
* D: PQSR
* P: DCBA
* Q: CBAD
* R: BADC
* S: ABCD


