---
title: "EBoard 27: Bipartite Matching"
number: 27
section: eboards
held: 2026-04-06
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* The Hanoi problem
* Bipartite graphs
* Bipartite matching
* Greedy approaches to bipartite matching
* Stable matching
* Greedy approaches to stable matching

Administrative stuff
--------------------

* I know that there were many things to resubmit (and submit), but
  I find myself concerned at how few of you attempted resubmissions of
  Assessement 1.
* I realize that some languages suggest otherwise, but `push` is a 
  stack operation (and `pop` should also be).
    * For queues, `enqueue` and `dequeue` are preferred.
    * If you want to be generic `put` and `get` are appropriate.
* There's a lot of administrative stuff today (or perhaps just two
  long administrative things).
* The stable matching problem will almost certainly continue into
  next class.
* I forgot my charger; there's a small chance that the recording will
  end early.

### Upcoming events

* Monday, 2026-04-06, 7:00 p.m.ish, 3819, _Mentor Session_
* Tuesday, 2026-04-07, Noon, _CS Table_ (Age Verification)
* Tuesday, 2026-04-07, 5:30 p.m., JRC 101, _InnovateGC Kickoff_
* Wednesday Extra, 2026-04-08, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday Extra, 2026-04-09, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday, 2026-04-09, 7:00 p.m., _Mentor Session_
* Friday, 2026-04-10, 5:30--7:00 p.m., _Keynote with Ajuna Kyaruzi '17_.
  Sign up in advance by Wednesday.

### Upcoming deadlines

* Tonight, 2026-04-06: Project 3 due (does not match Autry's syllabus)
* Friday, 2026-04-10: Read CLRS 25.2 on Stable Marriage (skimming is okay)
* Monday, 2026-04-13: Assessment 2 Resubmissions
* Monday, 2026-04-13: Problem Set 3 Resubmissions
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Project 4

* A chance to implement Prim's and Kruskal's. Yay!
    * Please use data structures/objects, even if not the optimal ones.
* Also a chance to use them to _approximate_ a variant of a difficult 
  problem.
* Traveling Salescritter Problem (TSP) takes as input a (complete)
  weighted graph with positive weights and finds the shortest path
  that visits each vertex exactly once, returning to the starting
  vertex.
* TSP is NP-complete. That means that no one has found a solution
  that is better than exponential. $$O(2^n)$$ or something like that.
* Solutions are not asymptotically better than "List every path
  in the graph and choose the smallest.
* However, we can find reasonable approximations to TSP if we place
  some limitations on the problem.
    * Assume that the graph is complete.
    * Assume that the triangle inequality holds for every triplet of
      vertices. (That is, any edge in a triangle is smaller than
      the sum of the neighboring edges.)
* Observation: The MST is closely related to possible solutions to TSP.
    * If you traverse the MST, you'll hit many vertices twice, but
      you will visit every vertex and return to the starting vertex.
    * If you have a solution to the TSP, it must be bigger than the
      weight of the MST. (Otherwise, we could chop out one edge,
      and we'd be left with a spanning tree.)
    * It is strictly larger, because it has one more edge than an
      MST, and removing one edge will (a) create a spanning tree
      and (b) give something with less weight.
* Followup: If the weight of the MST is less than the solution to the
  TSP, and the path is no more than double the weight to the MST,
  we know that that path is no more than the double the TSP.
* We can turn the "cycle through the MST" path into one that visits
  each vertex exactly once by "taking a shortcut" whenever we might
  return to a vertex.
    * We need for the graph to be complete to make this choice.
    * We also need the triangle inequality to hold.

### Policy/administrative/assignment questions

Can we resubit again for the assessments?

> Certainly. I'm not sure when I'll get to regrading, but I'll try.

On the Towers of Hanoi
----------------------

Many people struggled on Assessment 1.3, including on the resubmission.
Issues included incorrect (or hidden) assumptions as well as some
ambiguities. Enough people struggled, that I think it's worth
spending a bit of class time going over some of the key issues I
saw (and why they are issues).

Note that this can be a difficult problem to get your head around, as it
were, so we will run through an extended example after reviewing common
problems.

Assessment 1.3 asks you to prove the following "correct".

```
def Hanoi(k, src, dest, swap):
    if k==1:
        move(src, dest)
    else:
        Hanoi(k-1, src, swap, dest)
        move(src, dest)
        Hanoi(k-1, swap, dest, src)
```

* Unfortunately, attempts at the Towers of Hanoi proof still
  suffered from ambiguity and/or incorrect assumptions.
* "Prove this correct" is, of course, intentionally ambiguous. You are
  responsible for specifying that more clearly.
* Some of your proofs include the claim that the target peg is empty.
  At least in my experience, you cannot guarantee that any peg (prong)
  is empty.
* I'd be careful about using terms like "largest", since that's 
  ambiguous. Is that the largest one in the stack to be moved? The 
  largest overall? The largest on the peg?
* To show that it's safe, you need to ensure that the largest in the
  stack you are moving is smaller than the largest on the peg you are
  moving to.
    * This could be an invariant.
    * This could be a requirement in the theorem (or an implication
      thereof).
    * This could be ...
* Your theorem statement might also explain what happens to any blocks 
  already on the `dest` and `swap` pages.
    * In the ideal world, this would be necessary. I've been relatively
      lenient on you skipping this part.

### An extended examples

Let's consider an extended example.  We'll keep track of the stack
of procedure calls so that we can consider what happens in some of
the recursive calls.  **Please stop me if you get lost at any point.**

```
                    1
                    2
                    3
Hanoi(4, A, C, B)   4
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(4, A, C, B)`

```
                    1
Hanoi(3, A, B, C)   2
move(A, C)          3
Hanoi(3, B, C, A)   4
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(3, A, B, C)`

```
Hanoi(2, A, C, B)
move(A, B)          1
Hanoi(2, C, B, A)   2
move(A, C)          3
Hanoi(3, B, C, A)   4
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(2, A, C, B)`

```
Hanoi(1, A, B, C)
move(A, C)
Hanoi(1, B, C, A)
move(A, B)          1
Hanoi(2, C, B, A)   2
move(A, C)          3
Hanoi(3, B, C, A)   4
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(1, A, B, C)`

```
move(A, B)
move(A, C)
Hanoi(1, B, C, A)
move(A, B)          1
Hanoi(2, C, B, A)   2
move(A, C)          3
Hanoi(3, B, C, A)   4
-----------------   -------
Call Stack          A  B  C
```

Execute the `move(A,B)`. B is empty. Life is good.

```
move(A, C)
Hanoi(1, B, C, A)
move(A, B)
Hanoi(2, C, B, A)   2
move(A, C)          3
Hanoi(3, B, C, A)   4  1
-----------------   -------
Call Stack          A  B  C
```

Execute the `move(A,C)`. C is empty. Life remains good.

```
Hanoi(1, B, C, A)
move(A, B)
Hanoi(2, C, B, A)
move(A, C)          3
Hanoi(3, B, C, A)   4  1  2
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(1, B, C, A)`.

Note that *every peg has at least one block on it.*

If this had been `Hanoi(3, B, C, A)`, which we would have had if
our starting point was six blocks instead of four, you also couldn't
guarantee that the call of Hanoi using the inductive hypothesis
will move to an empty peg.

```
move(B, C)
move(A, B)
Hanoi(2, C, B, A)
move(A, C)          3
Hanoi(3, B, C, A)   4  1  2
-----------------   -------
Call Stack          A  B  C
```

Move from B to C. We can do so safely because C has a larger block than
B.

```
move(A, B)
Hanoi(2, C, B, A)
move(A, C)          3     1
Hanoi(3, B, C, A)   4     2
-----------------   -------
Call Stack          A  B  C
```

Move from A to B. In this case, B _is_ empty. Yay!


```
Hanoi(2, C, B, A)
move(A, C)                1
Hanoi(3, B, C, A)   4  3  2
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(2, C, B, A)`. Note that, once again, all of the pegs have at least one block on them. Hence, we need to be careful on what we claim. We know that we can do this safely because the largest block on C is smaller than the blocks on both A and B.

```
Hanoi(1, C, A, B)
move(C, B)
Hanoi(1, A, B, C)
move(A, C)                1
Hanoi(3, B, C, A)   4  3  2
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(1, C, A, B)`

```
move(C, A)
move(C, B)
Hanoi(1, A, B, C)
move(A, C)                1
Hanoi(3, B, C, A)   4  3  2
-----------------   -------
Call Stack          A  B  C
```

Move the top block from C to A.

Note that it would be a bit odd to call that block the "largest", even
though some of you suggest that move calls involve the largest block.
(It _is_ the largest of something.)

Note also that we end up with a non-contiguous "stack" on peg A afterwards.

```
move(C, B)
Hanoi(1, A, B, C)
move(A, C)          1
Hanoi(3, B, C, A)   4  3  2
-----------------   -------
Call Stack          A  B  C
```

We move from C to B. Another move onto a non-empty stack.

```
Hanoi(1, A, B, C)
move(A, C)          1  2
Hanoi(3, B, C, A)   4  3  
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(1, A, B, C)`.

```
move(A, B)
move(A, C)          1  2
Hanoi(3, B, C, A)   4  3  
-----------------   -------
Call Stack          A  B  C
```

Move from A to B.

```
                       1
move(A, C)             2
Hanoi(3, B, C, A)   4  3  
-----------------   -------
Call Stack          A  B  C
```

Move from A to C.  Whoo! We're halfway there. 

_I plan to stop going over this at this point in class unless multiple
people want to see the rest. I've included it in the eboard (and, perhaps,
an accompanying handout) for you to reference._

```
                       1
                       2
Hanoi(3, B, C, A)      3  4
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(3, B, C, A)`. 

```
Hanoi(2, B, A, C)      1
move(B, C)             2
Hanoi(2, A, C, B)      3  4
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(2, B, A, C)`

```
Hanoi(1, B, C, A)
move(B, A)
Hanoi(1, C, A, B)      1
move(B, C)             2
Hanoi(2, A, C, B)      3  4
-----------------   -------
Call Stack          A  B  C
```

Expand `Hanoi(1, B, C, A)`. Note that although we have an empty peg (A), 
that's not the one we're moving the stack to.

```
move(B,C)         
move(B, A)
Hanoi(1, C, A, B)      1
move(B, C)             2
Hanoi(2, A, C, B)      3  4
-----------------   -------
Call Stack          A  B  C
```

Move the top block B to C. 1 over 4 is clearly ok!

```
move(B, A)
Hanoi(1, C, A, B)       
move(B, C)             2  1
Hanoi(2, A, C, B)      3  4
-----------------   -------
Call Stack          A  B  C
```

Move the top block from B to A

```
Hanoi(1, C, A, B)       
move(B, C)                1
Hanoi(2, A, C, B)   2  3  4
-----------------   -------
Call Stack          A  B  C
```

Expand the call to `Hanoi(1, C, A, B)`. Once again, we are Hanoi-ing
onto a non-empty stack. Isn't that annoy-ing?

```
move(C, A)
move(B, C)                1
Hanoi(2, A, C, B)   2  3  4
-----------------   -------
Call Stack          A  B  C
```

We move a block from C to A.

```
move(B, C)          1      
Hanoi(2, A, C, B)   2  3  4
-----------------   -------
Call Stack          A  B  C
```

We move a block from B to C.

```
                    1     3   
Hanoi(2, A, C, B)   2     4
-----------------   -------
Call Stack          A  B  C
```

We expand the call to `Hanoi(2, A, C, B)`.

```
Hanoi(1, A, B, C)
move(A, C)          1     3   
Hanoi(1, B, C, A)   2     4
-----------------   -------
Call Stack          A  B  C
```

We expand the call to `Hanoi(1, A, B, C)`.

```
move(A, B)
move(A, C)          1     3   
Hanoi(1, B, C, A)   2     4
-----------------   -------
Call Stack          A  B  C
```

We move from A to B. The target peg is even empty!

```
move(A, C)                3   
Hanoi(1, B, C, A)   2  1  4
-----------------   -------
Call Stack          A  B  C
```

We move from A to C. The target peg isn't empty. That's okay, its
top piece is big enough.

```
                          2   
                          3   
Hanoi(1, B, C, A)      1  4
-----------------   -------
Call Stack          A  B  C
```

We expand the call to `Hanoi(1, B, C, A)`.

```
                          2   
                          3   
move(B, C)             1  4
-----------------   -------
Call Stack          A  B  C
```

And we make our final move, clearing the call stack.

```
                          1   
                          2   
                          3   
                          4
-----------------   -------
Call Stack          A  B  C
```

We're done! Aren't you glad we didn't go through an example with five
blocks?

### Another issue: Writing proofs by induction

* I would like you to follow the "standard form of a proof by induction".
  Among other things, that includes 
    * explicitly stating your theorem,
    * indicating that you're doing a proof by induction, 
    * labeling your inductive hypothesis, 
    * noting that "We will show that ...", 
    * and ending with a statement similar to "By the principle of 
      induction, we conclude ..."
* These cues help your reader.
* These cues help you. For example, I saw at least one proof that
  concluded the inductive section with "so all the moves are valid"
  but that was not the claim of their inductive hypothesis.

### And another: Reflections

* I realize that most resubmissions don't require reflections. However,
  the assessments are your sole individual work in the class, so it's
  particularly important to consider why you might have struggled.
* Every assessment resubmission requires a reflection.
* Every assessment resubmission also requires a correct/corrected 
  submission.
    * Understanding what you got wrong does not necessarily indicate
      that you can fix it.

Bipartite Graphs
----------------

What is a bipartite graph? 

A bipartite graph is a graph in which you can divide the vertices into
two sets (often $$L$$,$$R$$) such that all edges connect a vertex in
$$L$$ to a vertex in $$R$$.

Bipartite Matching
------------------

There are many models of "matching" in a bipartite graph. In most (all)
each element of each set can match at most one element of the other set.

* In an unweighted bipartite graph, we likely just want to maximize the 
  number of matching edges.
* In a weighted undirected bipartite graph, we likely want to maximize 
  the sum of the weights of the matching edges.
    * Perhaps the lowest total weight, but that's weird.

Let's try to phrase the first two problems a bit more formally. (TPS)

Definition: Given a (weighted) bipartite graph, 
$$G = (L,R,E\subset L\times R,w)$$, a _matching_ in $$G$$ is
a set of edges $$M \subseteq E$$ s.t., $$\forall l \in L, r \in R$$,
there is at most one edge of the form $$[l,\star]$$ and at most one
edge of the form $$[\star,r]$$ ,in $$M$$

Maximal Unweighted Bipartite Matching

Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O\subseteq E$$ where $$O is a matching of $$G$$, $$|M| \ge |O|$$

Maximal Weighted Bipartite Matching

Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O\subseteq E$$ where $$O$$ is a matching of $$G$$, 
$$\sum_{m\in M}w(m) \ge \sum_{o\in O}w(o)$$

**We stopped here!**

Greedy Approaches to Bipartite Matching
---------------------------------------

What greedy approaches to bipartite matching might we try? (TPS)

Do they work / can we find counter-examples? (TPS)

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


