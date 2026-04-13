---
title: "EBoard 29: Greed, Revisited"
number: 29
section: eboards
held: 2026-04-10
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Questions on and implications of Gale-Shapley
* The Knapsack problem
    * 0-1 Knapsack
    * Fractional Knapsack
* Huffman codes

Administrative stuff
--------------------

* I'll be late for class today. See [the prior eboard](../eboards/eboard28)
  for an explanation.
* Don't forget the ISO Cultural Event on Saturday!
* I could not find a counter-example for "greedy unweighted maximal
  bipartite matching", but I was too lazy to come up with a proof.
  Perhaps one of you did better.

### Upcoming events

* Monday, 2026-04-13, 7:00 p.m.ish, 3819, _Mentor Session_
* Tuesday, 2026-04-14, Noon, Visit with an alum.
* Thursday Extra, 2026-04-09, 4:15-5:15pm, _???_
* Thursday, 2026-04-16, 7:00 p.m., _Mentor Session_

### Upcoming deadlines

* Monday, 2026-04-13: Assessment 2.2 Resubmissions
* Monday, 2026-04-13: Problem Set 3 Resubmissions
* Whenever, 2026-04-??: Assessment 2.1 Resubmissions
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due

### Policy/administrative/assignment questions

If I write the following, what value does `d` have?

```
c = 3;
d = c++;
cout << d << endl;
```

The answer is : 3

Gale-Shapley, Revisited
-----------------------

TPS: Remind yourself how G-S works.

Mitch: Given two independent sets, match people across sets according
to their rankings. (Note: Each member of each set has ranked the members
of the other set.)

Pick one set as the offerers and one set as the offerees.

* Run through the list of offerers. Each offers to their first choice.
* When an offeree receives an offer, they can either
    * Conditionally accept "I will say yes if I do not get a better offer"
        * If they get a better offer than they currently have. That is,
          any offer if they have none; one higher on their list if they
          already have one.
    * Deny if they have an offer and the new one is lower on their list.
* All of the conditional yeses are marked as a match.

Do this again for any unmatched offerers remaining with the second choice, then the third choice, then the fourth choice, etc.

Observations on Gale-Shapley
----------------------------

Does it prioritize the offerer or the offeree? (TPS)

* The person making the offer: 10
* The person receiving the offer: 4
* It depends on the day:

Here's one case

* A: PQR
* B: QRP
* C: RPQ
* P: BCA
* Q: CAB
* R: ABC

What happens?

* A makes an offer to P. "It's better to have your last choice than
  no one at all."
* B makes an offer to Q. "It's better to have your last choice than
  no one at all."
* C makes an offer to R. "It's better to have your last choice than
  no one at all."

The algorithm prioritizes the offerer. (We can prove this, but won't.)

Can lying/cheating help? How about collaborative lying/cheating? (TPS)

> Sam recalls that all of the evidence says "Not really".

How might we prove Gale-Shapley correct? (TPS)

* Why can we treat the "Good enough" as a "Match" at the end of each round?
    * The offeree could still get a better offer
    * The offerer cannot get someone they prefer more, so they won't switch.
    * If none of our offerers want to switch, there will be no swaps.
* Why does it terminate with enough matches?
    * Since unmatched offerees need to accept their first offer, we can
      guarantee that there's at least one "Good Enough" ("Match") in each
      round.
* How do we know it's stable at the end?
    * See above.

Applications of Gale-Shapley
----------------------------

These notes include both real applications and potential applications.

* Residencies!
    * Historically prioritized residencies over physicians
    * Switched to prioritizing physicians over residencies
    * 90% the same. Is that good?
* MAP students and MAP faculty (TPS)
    * Strengths and weaknesses?
    * Who would you make the offerer? We should prioritize faculty.
      We should prioritize students, especially if we can take off
      people (see next). Students know better than faculty what they
      want to do.
    * Process: We should permit both students and faculty to say (implicitly
      or explicitly). "I'd rather not match than match with this person".
      (We may not achieve a full matching.)
    * How does the algorithm work if there aren't equal numbers on
      both sides? Do you want the smaller group to be the offerers
      in that case?
    * Faculty are often looking for a set of skillsets, so not everyone
      will work together.
    * Is it ethical to use software to do a human process.
    * Since the algorithm is sequential, the people higher on the 
      offerer list will be more likely to get their top choice.
    * If there are two people which are equal preferences, what do we
      do?
* CS students and CS Classes (at least before the crash)
    * What does a preference mean for a class?

Pause
-----

* Visitor announcements
    * Reminder: Ajuna K '17 is holding "office hours" today from 2:00-4:30
      in the commons.
    * Lots of activities with cool alum visitor.
* The sock question
    * You have a vat of infinite red, infinite white, infinite grey, and
      infinite black socks. How many must you pull out before you get a
      pair of matched-color socks?

The Knapsack Problem
--------------------

You have a bunch of items, each of which has a value and a weight.
We can represent these as $$(v_i, w_i)$$ pairs.  You also have
a designated total weight, $$W$$, that you can carry. Which items
should you grab?

* In *0-1 Knapsack*, you either grab an item or you don't.
* In *Fractional Knapsack*, you can grab part of an item.

Does either seem to admit a greedy solution? (TPS) 

Think about

* What is (a) greedy solution?
* Can you come up with a case in which it doesn't work?
* If not, what might give you confidence that it works correctly?

Random solution: Grab whatever you see first.

Greedy (0-1): Grab the most expensive thing you see, then the next
most expensive, then the next, until their total weight will break
the knapsack.

Counter-example: W = 4, Items = (100,4), (90,2), (90,2). If we
grab the most valuable, we have 100 value. If we skip over it,
we get the next two, and it's 180.

Greedy (0-1): Grab things from smallest to largest weight.

Counter-example: W = 4, Items = (100, 4), (1, 2), (1, 2).

Unfortunately, there is no known greedy solution to 0-1 knapsack.

How about fractional knapsack.

Greedy (Fractional): Greedy approach using value/weight . That is,
take as much of the highest value/weight, then the next highest,
and so on and so forth.

* Total of 4 ounces
* Platinum: 4 ounces, 1000/ounce
* Gold: 100 ounces, 500/ounce
* Silver: 50 ounces, 100/ounce
* Grinnell water, 100 gallons, .01/ounce

Counter-example: 

* None. The greedy approach works.

Huffman Codes
-------------

* You a bunch of data (say, letters of the alphabet) you want to store 
  in binary. 
* You want to use as little space as possible.
* You know the approximate frequency of each character.
* What might you do?

Example

* P: 100
* Q:  23
* R: 215
* S: 144
* T:  60
* U:  30

Option 1: Use $$\ceil(log_2(n))$$ bits for each character.

* P: 000
* Q: 001
* R  010
* S: 011
* T: 100
* U: 101

With that option, we need 572 x 3 bits = 1716 bits.

Can we do better? Strategy: Use fewer bits for the characters that
appear more frequently, more bits for the characters that appear
less frequently.

* R: 10
* S: 11
* P: 001
* T: 010
* U: 0110
* Q: 0111

Is this better?

* P: 100 x 3 = 300
* Q:  23 x 4 =  92
* R: 215 x 2 = 430
* S: 144 x 2 = 288
* T:  60 x 3 = 180
* U:  30 x 4 = 120
* Total: 1410

I saved 300 bits with a this encoding (15%)

Question: How can we design the best encoding?

Greedy approach, starting from the least frequent ones. Build a 
tree.

* Take the two least frequently appearing "letters", combine them.
