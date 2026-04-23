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
    * Building the table
    * Rewriting the recursive approach with the table
    * Making it iterative
    * Revising the running time
    * Extracting the LCS
* And one more problem: Edit distance
    * The problem
    * A recursive approach

Administrative stuff
--------------------

* I keep wondering what would happen if no one showed up for class.
  Would I still teach and record?
* Sorry about missing office hours this a.m. The extra hour of sleep was
  necessary. I should be in my office from about 1--4pm if you want to chat.

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

Will we have a chance to redo Assessment 4?

> Yes. Our goal is to grade that on the weekend of the 8th.

What about PS 5 and Project 5?

> We're less certain about those. We would hope to have those back to you
  by Wednesday the 13th.

> Self Gov says that you should finish those early so that those who can't
  finish them early get theirs graded more quickly.

Sam's Opening Questions
-----------------------

* Design the LCS table (one-dimensional, two-dimensional, etc.; what are
  the axes?)
* Write the algorithm to fill it out iteratively.

Review
------

### Longest common subsequences

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

### Two recursive formulations

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

* Three [Nope]
* Two [x2]

What are they?

* The index into S
* The index into T

A recursive formulation, revisited
----------------------------------

How do we update this to use the table?

```
// Find the length of the longest common substring between S and T.
// Note: 
lcs(S, T)
  return lcs_kernel(S, 0, T, 0 )

lcs_kernel(S, s, T, t) 
  if (table[s,t] is empty)
    if (s >= S.length) or (t >= T.length)
      table[s,t] = 0
    else if S[s] == T[t]
      table[s,t] = 1 + lcs_kernel(S, s+1, T, t+1)
    else
      table[s,t] = max(lcs_kernel(S, s+1, T, t)         // Drop in S
                       lcs_kernel(S, s, T, t+1))        // Drop in T
  return table[s,t]
```

* What do you do now that you have `s` and `t` (and don't change `S` and `T`)
* When should you look in the table?
* When should you store in the table?

An example
----------

`defeat` vs. `fate`

```
              S (indexed by s)
     0:D 1:E 2:F 3:E 4:A 5:T 6:  
    +---+---+---+---+---+---+---+
0:F | M |   | 3 |   |   |   |   |
    +---+---+---+---+---+---+---+
1:A | 2 | 2 | 2 | 2 | 2 |   |   |
    +---+---+---+---+---+---+---+
2:T | 1 | 1 | 1 | 1 | 1 | 1 |   |
    +---+---+---+---+---+---+---+
3:E | 1 | 1 | 1 | 1 | 0 | 0 | 0 |
    +---+---+---+---+---+---+---+
4:  | 0 |   | 0 |   | 0 | 0 |   |
    +---+---+---+---+---+---+---+

Stack: 
max(0,0) k(1,0) 
max(0,0) k(1,0) max(0,1) 
max(0,0) k(1,0) max(0,1) max(1,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) inc(4,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) inc(4,1) k(5,2)
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) k(4,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) k(4,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) max(3,1) k(4,1) k(3,2)
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) k(3,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) k(3,1) 
max(0,0) k(1,0) max(0,1) max(1,1) max(2,1) k(3,1) k(2,2)
max(0,0) k(1,0) max(0,1) max(1,1) k(2,1) 
max(0,0) k(1,0) max(0,1) k(1,1) 
max(0,0) k(1,0) max(0,1) k(1,1) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) max(4,3) max(5,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) max(4,3) max(5,3) k(6,3) k(5,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) max(4,3) k(5,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) max(4,3) k(5,3) k(4,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) max(4,2) k(5,2) k(4,3)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) k(4,2)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) max(3,2) k(3,3) k(4,2)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) max(2,3) inc(3,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) max(2,3) inc(3,3) k(4,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) max(2,3) k(3,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) max(2,3) k(3,3) k(2,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) max(2,2) k(3,2) k(2,3)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) max(1,2) k(2,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) max(0,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) max(0,3) inc(1,3) k(2,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) max(0,3) k(1,3) 
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) max(0,3) k(1,3) k(0,4)
max(0,0) k(1,0) max(0,1) k(1,1) max(0,2) k(1,2) k(0,3)
max(0,0) k(1,0) max(0,1) k(1,1) k(0,2)
max(0,0) k(1,0) k(0,1)
k(0,0)
```

Stack notation:

* `max(i,j)` - do the maximum computation in cell `i`, `j`.
* `inc(i,j)` - the increment computation in cell `i`, `j`.
* `k(i,j)` - do the kernel computation for cell `i`, `j`.
* The "state" of the stack will be a row of calls
* We'll keep track of the full history, oldest at bottom.

Notes:

* What is `lcs("E", "DEFEAT")` is `"E"` or length 1

Our DP solution
---------------

Which of the approaches do you want to use (left to right or right to left)?

* The recursive left-to-right will result in filling the table right-to-left
  [THIS WINS]
* The recursive right-to-left will result in filling the table left-to-right

How might we initialize parts of the table?

How do we fill in the rest of the table?

```
lcs(S,T)
  lcs_table = fill_lcs(S, T)
  return lcs_table[0,0]

fill_lcs(S, T)
  // Initialize

  // Fill the rest
  for ? = ? to ?
     for ? = ? to ?
       ?
  return table
```  

WE STOPPED HERE!

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
