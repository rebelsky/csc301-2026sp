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
* The Gale-Shapley algorithm for stable matching
* Implications of Gale-Shapley

Administrative stuff
--------------------

* I'm teaching CSC-151 8:30--9:50 on Friday morning.
    * No office hours on Friday.
    * I may be a few minutes late in starting class while I get the 
      recordings set up.
* On that note: Does anyone use the recordings, or can I stop making them?
    * I'll keep up the transcription in any case.
    * I will continue making the recordings.
* Just so you know, I write most of my board "live" rather than using 
  slides for a number of reasons.
    * It makes it easier to adapt to student questions.
    * It keeps the pace down, so that it's easier for you to take notes.
      (I realize that you probably can't write down everything at the
      speed at which I type, it's close.
    * It reminds you that eveyrone can make mistakes.
    * It lets me record the results of TPS activities.
    * I've done it for about thirty years and I'm too old to change.
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
    * You _can_ use Pandoc.
* It's the time of year that Sarah Dahlby Albright is starting to
  consider our peer educators for next year and is meeting with peer
  educators to provide them with feedback. Please fill out the peer
  educator evaluation form at
  <https://grinnell.co1.qualtrics.com/jfe/form/SV_1zhhjZPAWe6qeRU>
  to help her with both tasks. Please and thank you!
* SDA is also hiring peer educators for next year. 
    * Here's the ADP job board: 
      <https://myjobs.adp.com/grinnellstudent/cx/job-listing>.
    * The jobs are STU-893 (Grader), STU-894 (Mentor), and STU-895 (Tutor). 
    * She claims that you only need fill out the annoying Qualtrics form once.
        * SDA used the term "embedded" rather than "annoying"
* Problem Set 3 is graded and returned.
* Assessment 2.1 should be graded by tomorrow.

### Upcoming events

* Wednesday Extra, 2026-04-08, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday Extra, 2026-04-09, 4:15--5:45 p.m.: _CS Poster Session_
* Thursday, 2026-04-09, 7:00 p.m., _Mentor Session_
* Friday, 2026-04-10, 5:30--7:00 p.m., _Keynote with Ajuna Kyaruzi '17_.
  Sign up in advance by TONIGHT.
* Monday, 2026-04-13, 7:00 p.m.ish, 3819, _Mentor Session_
* Tuesday, 2026-04-14, Noon, Visit with an alum.

### Upcoming deadlines

* Friday, 2026-04-10: Read CLRS 25.2 on Stable Marriage (skimming is okay)
* Monday, 2026-04-13: Assessment 2 Resubmissions
* Monday, 2026-04-13: Problem Set 3 Resubmissions
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Policy/administrative/assignment questions

Sam said that we didn't have to implement Union Find on Project 4. However, 
the assignment says that we *do* have to implement it. Which is it?

> You do not need to implement the full Union-Find data structure. There's
  enough on the assignment that I'm trying to reduce the work.

> However, it can be a good experience to implement it.

I learned better from listening to lecture and taking notes. Can you 
return to that model?

> Prof. Autry does a very nice job of preparing and giving lectures.

> My understanding of the research literature is that most people
  learn better when they participate in active learning activities,
  which is why I regularly ask you to think-pair-share (TPS).

> Remember that the processes can be as important as the algorithms.
  That is, I care as much (or more) that you learn how to design
  and query algorithms.

Bipartite Matching, revisited
-----------------------------

**Definition (matching)**: Given a (weighted) bipartite graph, 
$$G = (L,R,E\subset L\times R,w)$$, a _matching_ in $$G$$ is
a set of edges $$M \subseteq E$$ s.t., $$\forall l \in L, r \in R$$,
there is at most one edge of the form $$[l,\star]$$ and at most one
edge of the form $$[\star,r]$$ ,in $$M$$

**Definition (matchings)**: $$matchings(G)$$ is the set of all
matchings of $$G$$.

**Problem (Maximal Unweighted Bipartite Matching)**: 
Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O \in matchings(G), |M| \ge |O|$$

**Problem (Maximal Weighted Bipartite Matching)**:

Given a graph, $$G$$, find a matching, $$M$$ s.t., 
$$\forall O \in matchings(G), \sum_{m\in M}w(m) \ge \sum_{o\in O}w(o)$$

Greedy Approaches to Bipartite Matching
---------------------------------------

### What greedy approaches to bipartite matching might we try? (TPS)

Unweighted:

* Repeatedly pick the vertex (on the left) with the fewest edges to
  unconnected vertices.
    * If there are multiple such vertices? Pick one randomly.
    * If there are more than one edges from such vertices to unconnected
      vertices? Pick the one to the parter with the smallest. (Random
      if necessary.)

Weighted:

* Repeatedly take the edge with the greatest weight that connects
  two unconnected vertices.

### Do they work / can we find counter-examples? (TPS)

Check correctness or look for counter-examples

Unweighted:

_Maybe if we make everyone have the same number of edges, we'll make
bad choices._

* L = 
* R =
* E = 

_We'll return to this at some point._

Weighted:

* L = A, B, C
* R = R, Q, P
* w = 
    * w(AR) = 90
    * w(BQ) = 90
    * w(CP) = 90
    * w(CQ) = 100

Alternate

* L = A, C
* R = R, Q
* w = 
    * w(AR) = 2
    * w(CQ) = 2
    * w(CR) = 3

Greed sometimes fails!

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

We'll try some examples in class

* A: SQRP
* B: SPRQ
* C: RQPS
* D: QSPR
* P: CDAB
* Q: CABD
* R: BACD
* S: DCAB

Matching

* AS
* BR
* CP
* DQ

TPS: Is this matching stable?

* AS - A won't switch, S would prefer D or C
* BR - B would switch for S or P, R won't switch
* CP - C would prefer R or Q, P is good
* DQ - D won't switch, Q would prefer B A C

Whoops! C will drop P (sorry P), Q will drop D, C and Q will joni

* AS - A won't switch, S would prefer D or C
* BR - B would switch for S or P, R won't switch
* CQ - C would prefer R, Q won't switch
* D has no partner
* P has no partner

Solving the Stable Matching Problem
-----------------------------------

Is there a deterministic algorithm that we can use to do the matching?

What is a greedy approach (even if you can tell that it's wrong)? (TPS)

* Run through the people on one side, each picks the highest available
  on their preference list.

Let's try it

* A picks S
* B picks P
* C picks R
* D picks Q

Whoo! It worked. It's stable.

Can we find a counter-example?

* A: SPQR
* B: SRPQ
* C: ...
* D: ...
* P: ...
* Q: ...
* R: ...
* S: DCBA

We pair A with S.

We pair B with R

B would prefer to be with S. S would prefer to be with B.

Note that would have happened if PQRS had made the offers, rather than
ABCD.

The Gale-Shapley Algorithm
--------------------------

We run through one side. Each person makes an offer to their top preference.
The person receiving the offer either says 

* "No", if they already have an offer and the new offer is lower on their list.
* "Maybe", if the new offer is higher on the list or if they have no offer.
* "Yes", if the new offer is highest on the list.

Anyone on the first side who does not have a patner makes offers to their
second choice (with the partner following those policies).

Anyone on the first side who does not have a patner makes offers to their
third choice (with the partner following those policies).

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

Running

* A makes an offer to P, P says "Maybe"
* B makes an offer to P, P says "Maybe", A no longer has a partner
* C makes an offer to P, P says "Maybe', B no longer has a partner
* D makes an offer to P, P says "Yes", C no longer has a parther.
* After round D and P are matched
* A makes an offer to Q, Q says "Maybe"
* B makes an offer to S, S says "Maybe"
* C makes an offer to R, R says "Maybe"

A slight change

* C: PQRS
* Q: BCAD

Result

* C makes an offer to Q, Q dumps A and says "maybe"
* At the end of the round, BS paired at maybe, CQ paired at maybe.
* Those matchings are finalized. BS cannot improve, CQ cannot improve.
* The last round, A makes an offer to R, R has no other option, and says "Maybe"
* And we're done.

Observations on Gale-Shapley
----------------------------

* Does it prioritize the offerer or the offeree? (TPS)
* Can lying help? (TPS)
* How might we prove Gale-Shapley correct?

Applications of Gale-Shapley
----------------------------

These notes include both real applications and potential applications.

* Residencies!
    * Historically prioritized residencies over physicians
    * Switched to prioritizing physicians over residencies
    * 90% the same. Is that good?
* CS students and CS Classes (at least before the crash)
    * What does a preference mean for a class?
* MAP students and MAP faculty
    * Strengths and weaknesses?

