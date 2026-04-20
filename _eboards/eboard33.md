---
title: "EBoard 33: Dynamic Programming (3)"
number: 33
section: eboards
held: 2026-04-20
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Opening questions
* Knapsack
    * Review
    * Wrapping up the iterative solution
    * Extracting the items from the table
* Our next problem: Longest common subsequence
    * The problem
    * A recursive approach
    * Considering the running time
    * Applying DP
    * Revising the running time
    * Extracting the LCS

Administrative stuff
--------------------

* As usual, I've almost certainly over-prepared for this class.
* We remain a bit behind in grading. Apologies.

### Upcoming events

* Monday, 2026-04-20, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-23, 4:15--5:15pm, _Thursday Extra_ (?)
* Thursday, 2026-04-23, 7:00 p.m., _Mentor Session_

### Upcoming deadlines

* TONIGHT, 2026-04-20: Assessment 2.1 resubmissions
* TONIGHT, 2026-04-20: Project 3 resubmissions
* Wednesday, 2026-04-22: Assessment 3 due
* Friday, 2026-05-01: _Early Deadline_ for Problem Set 5
* Friday, 2026-05-01: _Early Deadline_ for Project 5
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions / lat4e work due

### Other upcoming dates

* Wednesday, 2026-04-22: Problem Set 5 distributed
* Friday, 2026-04-24: Project 5 distributed
* Friday, 2026-05-01: Assessment 4 distributed (only one problem!)

### Policy/administrative/assignment questions

Sam's Opening Questions
-----------------------

* How do we make Knapsack iterative? (We almost finished.)
* What is the best solution for the particular substring problem?
* What ideas does that suggest for the general case?
* How might we phrase our solution recursively?

Knapsack algorithm, revisited
-----------------------------

```
knapsack(I, W)
  if (empty(I))
    return 0
  if W == 0
    return 0
  else
    let i = (v,w) be an element of I
       // heaviest, lightest, cheapest, most expensive
    if (w <= W)
      return max (knapsack(I - { i }, W), v + knapsack(I - { i }, W - w))
    else
      return knapsack(I - { i }, W)
```

Making it iterative
-------------------

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
```

```
knapsack(I, W) 
  kt = fillKnapsackTable(I, W)
  return table(|I|, W)

fillKnapsackTable(I, W) 
  // Initialization
  for col = 0 to W
    table[col, 0] = 0
  for row = 0 to |I|
    table[0, row] = 0

  // Fill the rest of the table
  for col = 1 to W
    for row = 1 to |I|
      // NEED TO FILL THIS IN!

  // And we're done
  return kt
```

Applying knapsack
-----------------

```
                              Weight
                    0   1   2   3   4   5   6  
                  +---+---+---+---+---+---+---+
                {}| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 
S                 +---+---+---+---+---+---+---+
U             {i1}| 0 |   |   |   |   |   |   | 
B                 +---+---+---+---+---+---+---+
S          {i1,i2}| 0 |   |   |   |   |   |   | 
E                 +---+---+---+---+---+---+---+
T       {i1,i2,i3}| 0 |   |   |   |   |   |   | 
                  +---+---+---+---+---+---+---+
     {i1,i2,i3,i4}| 0 |   |   |   |   |   |   | 
                  +---+---+---+---+---+---+---+

i1 = (4,2), i2 = (5,3), i3 = (1,1), i4 = (3,3), W = 6
```

Extracting additional information
---------------------------------

Suppose you've built the basic knapsack table, which tells you the
value of the optimal solution. How can you use the table to determine
which items to select?

_Hint_: Think about how we built the table.

```
knapsackItems(I, W) 
  kt = fillKnapsackTable(I, W)
  return determineItems(kt, |I|, W)

determineItems(kt, n, W)
  ...
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

Observations?

A recursive formulation
-----------------------

Runtime
-------

Designing the table
-------------------

How many dimensions?

What are they?

Our DP solution
---------------

How might we initialize parts of the table?

How do we fill in the rest of the table?

Runtime
-------

Extracting the LCS
------------------

Once we've built the table, how can you extract the LCS?
