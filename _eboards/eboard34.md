---
title: "EBoard 34: Dynamic Programming (4)"
number: 34
section: eboards
held: 2026-04-22
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Opening questions
* Our next problem: Longest common subsequence
    * The problem
    * A recursive approach
    * Considering the running time
    * Building the table
    * Rewriting the recursive approach with the table
    * Making it iterative
    * Revising the running time
    * Extracting the LCS
* And one more problem: Approximate matching
    * The problem
    * A recursive approach

Administrative stuff
--------------------

### Upcoming events

* Thursday, 2026-04-23, 7:00 p.m., _Mentor Session_
* Monday, 2026-04-27, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-30, 4:15--5:15pm, _Thursday Extra_ (?)

### Upcoming deadlines

* TONIGHT, 2026-04-22: Assessment 3 due (try for Friday night)
* Friday, 2026-05-01: _Early Deadline_ for Problem Set 5
* Friday, 2026-05-01: _Early Deadline_ for Project 5
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  **No extensions. This deadline is _not_ a lie!**

### Other upcoming dates

* TODAY, 2026-04-22: Problem Set 5 distributed
* Friday, 2026-04-24: Project 5 distributed
* Friday, 2026-05-01: Assessment 4 distributed (only one problem!)

### Problem Set 5

* Problem 1: Dynamic Programming for Matching Coats
* Problem 2: Dynamic Programming for Finding Words

### Policy/administrative/assignment questions


Sam's Opening Questions
-----------------------

* Design the LCS table (one-dimensional, two-dimensional, etc.; what are
  the axes?)
* Write the algorithm to fill it out iteratively.

Longest common subsequences
---------------------------

Given two strings, $$S = s_1, s_2, ..., s_n$$ and $$T = t_1, t_2, ... t_m$$, 
find the longest possible matching substrings in $$S$$ and $$T$$, where
a substring is a sequence of elements _in order_, but possibly with gaps.
(You achieve a substring by crossing off elements.)

An example

```
    0 1 2 3 4 5 6 7 8
S   a b x g r y i p n 
T   g r x p i y n o
```

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

Designing the table
-------------------

How many dimensions?

What are they?

A recursive formlation, revisited
---------------------------------

```
// Find the length of the longest common substring between S and T.
// Note: 
lcs(S, T)
  return lcs_kernel(S, T, ...)

lcs_kernel(S, T, ...):
  if (S is empty) or (T is empty)
    return 0
  else if S[0] == T[0]
    return 1 + lcs_kernel(S, T, ...)
  else
    return max(lcs_kernel(S, T, ...)
               lcs_kernel(S, T, ...))
```

Our example, continued
----------------------

```
     0:  1:  2:  3:  4:  5:  6:  7:  8: 
    +---+---+---+---+---+---+---+---+---+
0:0 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
1:1 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
2:2 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
3:3 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
4:4 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
5:5 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
6:6 |   |   |   |   |   |   |   |   |   |
    +---+---+---+---+---+---+---+---+---+
7:7 |   |   |   |   |   |   |   |   |   |         
    +---+---+---+---+---+---+---+---+---+

Stack: 
```

Our DP solution
---------------

Which of the approaches do you want to use (left to right or right to left)?

How might we initialize parts of the table?

How do we fill in the rest of the table?

```
lcs(S,T)
  lcs_table = fill_lcs(S, T)

fill_lcs(S, T)
  for ? = ? to ?
     for ? = ? to ?
       ?
  return table
```  

Runtime
-------

Extracting the LCS
------------------

Once we've built the table, how can you extract the LCS?

Our next problem: Edit distance
-------------------------------

Given two strings, $$S$$ and $$T$$, what is the fewest number of changes
needed to convert $$S$$ to $$T$$?

Valid changes: 

* _Delete_ a letter at a position.
* _Insert_ a letter at a position.
* _Substitute_ a letter for another at a position.

### Example 1: Turn "samr" into "spam"

A bad approach

                // "samr"
remove(1)       // "smr"
remove(1)       // "sr"
remove(1)       // "s"
insert(1, 'p')  // "sp"
insert(2, 'a')  // "spa"
insert(2, 'm')  // "spam"

A less-bad approach

```
                // "samr"
replace(1, 'p') // "spmr"
replace(2, 'a') // "spar"
replace(3, 'm') // "spam"
```

Can we do better?

```
                // "samr"
```

### Example 2: Turn "kitten" into "spliting"

```
```

### Example 3: Turn "spliting" into "kitten"

```
```

A recursive formulation
-----------------------

Write a recursive procedure, `edit(S,T)` that returns the _minimum_ edit
distance between `S` and `T`.
