---
title: "EBoard 32: Dynamic Programming (2)"
number: 32
section: eboards
held: 2026-04-17
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Dynamic programming, reviewed
* The stamps problem, revisited
* The knapsack problem, revisited
    * The DP approach
    * Writing a recursive solution
    * Making it iterative
* Our next problem: Longest common subsequence
    * The problem
    * A recursive approach
    * Considering the running time
    * Applying DP

Administrative stuff
--------------------

* As usual, I've almost certainly over-prepared for this class.
* In the last class, one of you asked about using generative AI to convert
  from some document format to LaTeX. I talked to a colleague about
  the idea, and they noted that they'd experimented with it and found that
  it not only converts the formatting instructions, but also some of the
  content. **You may not use generative AI to create LaTeX.**
* Some of you have indicated that you struggle to make my morning office
  hours. I am generally happy to meet with you at other times of the day; 
  you just have to reach out and request a time (preferably at least a
  day in advance).
* Problem set 4 has some overlap with Assessment 3. Hence, you'll benefit
  from getting PS4 in on time so that you get feedback before you do
  Assessment 3.
* Seniors: Please sign up for the graduation breakfast! You know you
  want to be there. We definitely want you to be there.
* Everyone: Please sign up for the CS picnic!

### Upcoming events

* Monday, 2026-04-20, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-23, 4:15--5:15pm, _Thursday Extra_ (?)
* Thursday, 2026-04-23, 7:00 p.m., _Mentor Session_

### Upcoming deadlines

* TONIGHT, 2026-04-17: Problem set 4 due (prioritize this)
* TONIGHT, 2026-04-17: Project 4 due
* Monday, 2026-04-20: Assessment 2.1 Resubmissions
* Monday, 2026-04-20: Project 3 resubmissions
* Wednesday, 2026-04-22: Assessment 3 due

### Policy/administrative/assignment questions

When are the SEPC elections?

> I don't know. Ask an SEPC member.

Sam's Three Questions
---------------------

* What are the basic principles of dynamic programming
* How do we modify the stamps solution to return the list of stamps?
* Can we find a recursive formulation for Knapsack?

Basic Principles of Dynamic Programming
---------------------------------------

* Come up with a recursive (often exhaustive) formulation
* Design a corresponding table
* Write an algorithm to fill the table iteratively


Revisiting the stamps problem
-----------------------------

_How can we modify the stamps solution to extract the list of stamps?_

### Current solution

```
fillTable(V, C)
  table[0] = 0
  for v in V
    table[v] = 1
  for c in 1:C
    if (table[c] == infy)
      table[c] = findMin(table, V, c)

// Return the smallest number of stamps needed to make c cents
findMin(table, V, c)
  minSoFar = infinity
  for v : V
    if (v == c)
      return 1
    else if c-v > 0
      if (table[c-v] < minSoFar)
        minSoFar = table[c-v]
  return 1 + minSoFar

stamps(V, C)
  table = fillTable(V, C)
  return table[C]
```

### Ideas

* In addition to the array of numbers, add an array of lists
  which represent the stamps.
* We'll call that array `um`
* We still want to use `findMin`, but we need it to return both 
  the number and the set of stamps.
* Everywhere we update `table` (or something similar), we should update `um`.

### An updated solution

```
fillTable(V, C)
  table = new array of integers of size (C+1)
  um = new array of lists of size (C+1)
  table[0] = 0
  um[0] = { }
  for v in V
    table[v] = 1
    um[v] = { v }
  for c in 1:C
    if (table[c] == infinity)
      (num,stamps) = findMin(table, V, c)
      table[c] = num
      um[c] = stamps

// Return the smallest number of stamps needed to make c cents PLUS
// the set of stamps that makes that
findMin(table, V, c)
  minSoFar = infinity
  minoListSoFar = { }
  for v : V
    if (v == c)
      return 1,{ v }
    else if c-v > 0
      if (table[c-v] < minSoFar)
        minSoFar = 1 + table[c-v]
        // QUESTION: WE UPDATE BECUAUSE table[c-v] HAS FEWER STAMPS
        // THAN WE'VE SEEN SO FAR. WHAT DOES THAT MEAN ABOUT UPDATING
        // minListSoFar?
        minListSoFar = { v } + um[c-v]
  return minSoFar,minListSoFar

stamps(V, C)
  table = fillTable(V, C)
  return um[C]
```

The knapsack problem, revisited
-------------------------------

Consider a set of items, which we represent as $$(v_i, w_i)$$ pairs
and a designated total weight, $$W$$, that you can carry. 
Find a set of items whose total weight is less than or equal to
$$W$$ and that maximizes the total value.

**Annoying rephrasing:**

Given a set
$$I = \{ (v_i, w_i) \} \subset N^+ \times N^+$$
(representing value/weight pairs) 
and an integer $$W \in N^+$$ 
(representing weight),
find a set $$M = \{ (u_j, x_j) \} \subseteq I$$ such that

* (a) $$\sum{x_j} \le W$$, and
* (b) $$\forall O = \{ (t_k, y_k) \} \subseteq I s.t. \sum{y_k} \le W$$, 
  $$\sum{u_j} \ge \sum{t_k}$$.

Greed fails! E.g.,

```
(15, 15)
(14, 8)
(14, 8)
W = 16
```

Observation: In effect, we want our DP solution to try all the
"reasonable" sets of items.

Organizing the problem for DP
-----------------------------

We are computing `knapsack(I,W)`

Two key issues for DP:

* Recursive formulation (the recursive calls are of the same function with
  one or more parameters "smaller")
* We store the results in a table

What is your recursive formulation?

* How do we make `I` smaller? (I is a set of $$(v_i, w_i)$$ pairs.)
     * Remove the element with the smallest value in $$I$$ 
     * Remove the element with the largest value in $$I$$
     * Remove the element with the smallest weight in $$I$$ 
     * Remove the element with the largest weight in $$I$$
     * Look at all possible subsets
* How do we make `W` smaller? (W is a non-negative integer.)
     * Assume you place that item in your bag, so remove the weight
       of that item.
     * SKip the item, keeping W the same.
* As long as we always remove one item, we'll eventually reach 0 items.

````
knapsack(I, W)
  if (empty(I))
    return 0
  else if (W < 0)
    return negative infinity 
  else
    let i = (v,w) be an element of I 
       // heaviest, lightest, cheapest, expensiveist, ??
    max (knapsack(I - { i }, W),
         v + knapsack(I - { i }, W - w))
```

We'll try this for our counter-example above

```
a = (15, 15)
b = (14, 8)
c = (14, 8)
I = { a, b, c}
W = 16
```

```
knapsack({ a, b, c}, 16)
  max (knapsack({ b, c }, 16), // 28 (see below)
       15 + knapsack({ b, c }, 1)

knapsack({ b, c }, 16)
  max (knapsack({ c }, 16), 
       14 + knapsack({ c }, 8))

knapsack({ c }, 16)
  max (knapsack({ }, 16)
       14 + knapsack({ }, 8))

knapsack({ }, 16) = 0
knapsack({ }, 8) = 0

knapsack({ c }, 16)
  max (0, 14 + 0) = 14

knapsack({ c }, 8)
  max (knapsack({ }, 8)
       14 + knapsack({ }, 0))

knapsack({ }, 0) = 0

knapsack({ c }, 8) = max(0, 14 + 0) = 14

knapsack({ b, c }, 16)
  max (14, 14 + 14) = 28

knapsack({ b, c }, 1)
  max (knapsack({ c }, 1),
       14 + knapsack({ c }, -7))

// Note: When the remaining weight is negative, we skip the option
knapsack({ c }, 1)
  // Weight of c is bigger than 1, so this is not valid
```

What table might support that recursive formulation?
(What indices will our table have?)

```
                              Weight
                    0   1   2   3   4   5   6   7 
                  +---+---+---+---+---+---+---+---
                {}|   |   |   |   |   |   |   | 
S                 +---+---+---+---+---+---+---+---
U             {i1}|   |   |   |   |   |   |   | 
B                 +---+---+---+---+---+---+---+---
S          {i1,i2}|   |   |   |   |   |   |   | 
E                 +---+---+---+---+---+---+---+---
T       {i1,i2,i3}|   |   |   |   |   |   |   | 
                  +---+---+---+---+---+---+---+---
     {i1,i2,i3,i4}|   |   |   |   |   |   |   | 
                  +---+---+---+---+---+---+---+---
                  |   |   |   |   |   |   |   | 

i1 = (v1,w1), i2 = (v2, w2), i3 = (v3, w3), ...
The i's are items
Note that the last element of the set corresponds to the row of the set.
Assume $$n$$ rows and $$W$$ columns.
```

Algorithm

```
knapsack(I, W)
  if (empty(I))
    return 0
  if W == 0
    return 0
  else
    let i = (v,w) be an element of I
       // heaviest, lightest, cheapest, expensiveist, ??
    if (w <= W)
      return max (knapsack(I - { i }, W), v + knapsack(I - { i }, W - w))
    else
      return knapsack(I - { i }, W)
```

Making it iterative
-------------------

```
knapsack(I, W) {
  fillKnapsackTable(I, W)
  return table(|I|, W)
}

fillKnapsackTable(I, W) {
  // Initialization
  for col = 0 to W
    table[col, 0] = 0
  for row = 0 to |I|
    table[0, row] = 0

  // Fill the rest of the table
  for col = 1 to W
    for row = 1 to |I|
      // THINK ABOUT THIS FOR NEXT TIME!
}
```

Longest common subsequences
---------------------------

Given two strings, $$S = s_1, s_2, ..., s_n and $$T = t_1, t_2, ... t_m$$, 
find the longest possible matching substrings in $$S$$ and $$T$$, where
a substring is a sequence of elements _in order_, but possibly with gaps.

An example

```
    0 1 2 3 4 5 6 7 8
S   a b x g r y i p n 
T   g r x p i y n o
```

Solution?

THINK ABOUT THE SOLUTION FOR NEXT TIME

THINK ABOUT HOW YOU MIGHT WRITE THE RECURSIVE FORMULATION FOR NEXT TIME


Observations?

A recursive formulation
-----------------------

Runtime
-------
