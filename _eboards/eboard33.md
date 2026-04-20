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
    * Example
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

* As usual, I've almost certainly prepared too much for today. We'll see
  how it goes.
* Note that you often see me revising what I've written as we go on. That's
  because I'm often re-deriving things "on the fly", partially to suggest
  to you that we often make mistakes and then find the need to correct them.
* We remain a bit behind in grading. Apologies.
* Don't forget to let me if the you don't see the eboard on the screen or
  if the formatting on your screen is wonky.

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
* Friday, 2026-05-15 (5pm): All resubmissions / late work due

### Other upcoming dates

* Wednesday, 2026-04-22: Problem Set 5 distributed
* Friday, 2026-04-24: Project 5 distributed
* Friday, 2026-05-01: Assessment 4 distributed (only one problem!)

### Policy/administrative/assignment questions

When is the SEPC election?

> Whoops. Soon.

Does the 24-hour deadline apply to projects?

> Yes, it applies to everything.

> "The deadline is a lie."

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
      return max (knapsack(I - { i }, W),         // Skip the item
                  v + knapsack(I - { i }, W - w)) // We grabbed the item
    else
      return knapsack(I - { i }, W) // Skip the item
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
S          {i1,i2}|   |   | x |   |   |   |   | 
E                 +---+---+---+---+---+---+---+---
T       {i1,i2,i3}|   |   |   |   | X |   |   | 
                  +---+---+---+---+---+---+---+---
     {i1,i2,i3,i4}|   |   |   |   |   |   |   | 
                  +---+---+---+---+---+---+---+---
                  |   |   |   |   |   |   |   | 

i1 = (v1,w1), i2 = (v2, w2), i3 = (v3, 2), ...
```

What is an entry in this table?  The entry at $$(r,c)$$ tells us
the best possible value you can get with the first $$r$$ items
and a weight of $$c$$.

```
knapsack(I, W) 
  kt = fillKnapsackTable(I, W)
  return table(|I|, W)

fillKnapsackTable(I, W) 
  // Initialization
  n = |I|
  for col = 0 to W
    table[col, 0] = 0
  for row = 0 to n
    table[0, row] = 0

  // Fill the rest of the table
  for col = 1 to W
    for row = 1 to n
      // NEED TO FILL THIS IN! Note that we can use the ideas from the
      // recursive version
      // Decide if it's better to take "the item" or leave "the item"
      // The item is the last item in the set from the current row
      (v,w) = I[row] // Assuming 1-based indexing in I
      // Assume row 3, column 4, and w = 2
      if (w <= col)
        table[row,col] = max(table[row-1,col]        // skip the item
                             v + table[row-1,col-w]) // grab the item
      else
        table[row,col] = table[row-1,col]            // skip the item

  // And we're done
  return table
```

Applying knapsack
-----------------

```
                              Weight
                    0   1   2   3   4   5   6  
                  +---+---+---+---+---+---+---+
   0            {}| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 
S                 +---+---+---+---+---+---+---+
U  1          {i1}| 0 | 0 | 4 | 4 | 4 | 4 | 4 | 
B                 +---+---+---+---+---+---+---+
S  2       {i1,i2}| 0 | 0 | 4 | 5 | 5 | 9 | 9 | 
E                 +---+---+---+---+---+---+---+
T  3    {i1,i2,i3}| 0 | 1 | 4 | 5 | 6 | 9 |10 | 
                  +---+---+---+---+---+---+---+
   4 {i1,i2,i3,i4}| 0 | 1 | 4 | 5 | 6 | 9 |10 | 
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
  return determineItems(kt, I, |I|, W)

determineItems(kt, I, n, W)
  if (n == 0) || (W == 0)
    return { }
  else if kt[n,W] == kt[n-1,W]
    return determineItems(kt, I, n-1, W)
  else
    return { I[n] } union determineItems(kt, I, n-1, W-I[n].weight)
```

Longest common subsequences
---------------------------

Given two strings, $$S = s_1, s_2, ..., s_n$$ and $$T = t_1, t_2, ... t_m$$, 
find the longest possible matching substrings in $$S$$ and $$T$$, where
a substring is a sequence of elements _in order_, but possibly with gaps.

An example

```
    0 1 2 3 4 5 6 7 8
S   a b x g r y i p n 
T   g r x p i y n o
```

Solution?

* 1: None
* 2: gr, xy, 
* 3: xyn
* 4: grin

Observations?

* Just because two things line up, it's not necessarily a good idea
  to take them.
  
A recursive formulation
-----------------------

Write `lcs(S,T)` which gives the length of the longest common substring
between S and T.

```
// Left to right formulation
lcs(S, T)
  if (S is empty) or (T is empty)
    return 0
  else if S[0] == T[0]
    return 1 + lcs(S.substring(1), T.substring(1))
  else
    return max(lcs(S.substring(1), T)
               lcs(S, T.substring(1)))
```

```
// Alternate formulation: Right to left
lcs(S,T)
  return lcsHelper(S, |S|-1, T, |T|-1)

lcsHelper(S, s, T, t)
  if (s < 0) or (t < 0)
    return 0
  else if S[s] == T[t]
    return 1 + lcsHelper(S, s-1, T, t-1)
  else
    return max(lcsHelper(S, s-1, T, t),
               lcsHelper(S, s, T, t-1))
```

Runtime
-------

There are potential *two* recursive calls for each character we remove,
so this is $$O(2^(|S|+|T|))$$.

We should not run this algorithm.

* Design the table (one-dimensional, two-dimensioinal, etc.; what are
  the axes)
* Write the algorithm to fill it out.

THINK ABOUT THOSE FOR WEDNESDAY!

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
