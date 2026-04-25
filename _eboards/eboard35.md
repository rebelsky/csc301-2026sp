---
title: "EBoard 35: Dynamic Programming (5)"
number: 35
section: eboards
held: 2026-04-24
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Opening questions
* Our current problem: Longest common subsequence
    * Review
    * Making it iterative
    * Revising the running time
    * Extracting the LCS
* And one more problem: Edit distance
    * The problem
    * A recursive approach
    * Designing the table
    * Making it iterative
    * Extracting the actions
* Notes on Project 5

Administrative stuff
--------------------

* It has been suggested that enough crud is going around campus that
  you should consider wearing masks.
* Please fill out the AI use survey at
  <https://grinnell.co1.qualtrics.com/jfe/form/SV_88Md19LJ12t6kFU>
    * Do you think PM intentionally made the link end in FU?
* Problem Set 4 (problems 1 and 2) returned.
    * Many issues, esp. with 4.2.
* We should wrap up Dynamic Programming on Monday. Then we're in the
  "downhill stretch" (or something like that).
* Consider the CS picnic next Friday. Sign up at
  <https://grinnell.co1.qualtrics.com/jfe/form/SV_6D9DakDp7we662i>
* Voting is open for next year's SEPC.

### Upcoming events

* Monday, 2026-04-27, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-30, 4:15--5:15pm, _Thursday Extra_ (?)
* Thursday, 2026-04-30, 7:00 p.m., _Mentor Session_
* Thursday, 2026-04-30, _Trustees on Campus_ (dessert with students?)
* Friday, 2025-05-01, 5:00 p.m.: _CS Picnic_

### Upcoming deadlines

* TODAY, 2026-04-24: Slightly late Assessment 3
    * Sam plans to start grading Saturday morning.
* Friday, 2026-05-01: _Early Deadline_ for Problem Set 5
* Friday, 2026-05-01: _Early Deadline_ for Project 5
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  **No extensions. This deadline is _not_ a lie!**

### Other upcoming dates

* TODAY, 2026-04-24: Project 5 distributed (end of class)
* Friday, 2026-05-01: Assessment 4 distributed (only one problem, but long)
    * I may distribute it earlier.

### About PS 4.1 (Counter-Examples)

* Many of the issues seemed to stem from a misreading of the problem.
  For example, for greedy coat assignment, you want to minimize the
  largest disparity, not the sum of the disparities.
    * These days, getting the goal wrong also has a problematic implication.

### About PS 4.2 (Greedy Well Placement)

* Many people stated the algorithm informally. That's a nice start, but
  something with a bit more details (such as pseudocode) helps, particularly 
  if you want to prove something about it.
    * I saw loop invariants without explicit loops.
* If you're doing a proof by induction, please make sure to state your
  inductive claim clearly. Not "the algorithm works", but rather what
  it means that the algorithm works.
* When you're doing a proof by induction (or the variant of using a loop
  invariant), all the information you want to rely on needs to be in
  the inductive hypothesis (or the loop invariant).
    * "The greedy algorithm has created a minimal well placement for
      $$i$$ houses" may not be enough. For example, if $$h_1 = 0$$,
      the placement $$w_1 = 0$$ is a minimal placement for one house. 
      However, it would not be an optimal placement for two hosues
      if $$h_2 = 4$$.
    * Similarly, "After $$i$$ iterations of the outer loop, we have
      optimally placed the first $$i$$ wells" is insufficient.
      
### Policy/administrative/assignment questions

_None_

Sam's Opening Question
----------------------

* Write the algorithm to fill out the LCS table iteratively.

Review of LCS to date
-------------

### The table

* Two dimensions
* One for $$S$$, one for $$T$$
* Cell `(s,t)` represents "number of characters in the LCS between
  `S[s:]` and `T[t:]`.
* An extra row and column at the end for "this portion is empty"

```
                    S (indexed by s)
           0:D 1:E 2:F 3:E 4:A 5:T 6:  
          +---+---+---+---+---+---+---+
      0:F |   |   |   |   |   |   | 0 |
          +---+---+---+---+---+---+---+
      1:A |   |   |   |   |   |   | 0 |
          +---+---+---+---+---+---+---+
   T  2:T |   |   |   |   |   |   | 0 |
          +---+---+---+---+---+---+---+
      3:E |   |   |   |   |   |   | 0 |
          +---+---+---+---+---+---+---+
      4:  | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
          +---+---+---+---+---+---+---+
```

### Our recursive formulation

```
// Find the length of the longest common substring between S and T.
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
  // Initialize (fill in the last row and column)
  for (col = 0; col <= S.length; col++)
    table[T.length, col] = 0
  
  for (row = 0; row <= T.length; row++) 
    table[row, S.length] = 0;

  // Fill the rest
  for (row = T.length-1; row >=0; row--) 
     for (col = S.length-1; col >= 0; col--) 
       if (S[col] == T[row])
         table[row,col] = 1 + table[row+1,col+1]
       else 
         table[row,col] = max(table[row+1, col], table[row, col+1])
  return table
```  

```
                    S (indexed by s)
           0:D 1:E 2:F 3:E 4:A 5:T 6:  
          +---+---+---+---+---+---+---+
      0:F | 3 | 3 | 3 | 2 | 2 | 1 | 0 |
          +---+---+---+---+---+---+---+
      1:A | 2 | 2 | 2 | 2 | 2 | 1 | 0 |
          +---+---+---+---+---+---+---+
   T  2:T | 1 | 1 | 1 | 1 | 1 | 1 | 0 |
          +---+---+---+---+---+---+---+
      3:E | 1 | 1 | 1 | 1 | 0 | 0 | 0 |
          +---+---+---+---+---+---+---+
      4:  | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
          +---+---+---+---+---+---+---+
```

Runtime
-------

The iterative algorithm should be $$O(|S|x|T|)$$ because the table is
that size and we can fill in each cell in constant time..

Extracting the LCS
------------------

Once we've built the table, how can you extract the LCS?

_Note that Problem Set 5 and Assessment 4 will ask a similar question._

Approach one: Walk the first row, any time you drop the value, add the character.

```
extractLCS(S,T,table)
  lcs = {};
  row = 0, col = 0
  while (row < S.length && col < T.length) 
    if (S[col] = T[row]) 
      lcs.push(S[col])
      row += 1
      col += 1
    else if (table[row,col] == table[row+1,col])
      row += 1
    else
      col += 1
  return lcs
```

Our next problem: Edit distance
-------------------------------

Given two strings, $$S$$ and $$T$$, what is the fewest number of changes
needed to convert $$S$$ to $$T$$?

Valid changes: 

* `delete(i)` - delete the letter at position `i` in `S`.
* `insert(i,ch)` - insert the letter `ch` immediately before position `i`.
* `replace(i,ch)` - replace the letter at position `i` with `ch`.
  (This is often called `substitute`.)

### Example 1: Turn "samr" into "spam"

A bad approach

                // "samr"
delete(1)       // "smr"
delete(1)       // "sr"
delete(1)       // "s"
insert(1, 'p')  // "sp"
insert(2, 'a')  // "spa"
insert(3, 'm')  // "spam"

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
insert(1, 'p')  // "spamr"
delete(3)       // "spam"
```

### Example 2: Turn "kitten" into "spliting"

```
                // "kitten"
delete(0)       // "itten"
insert(0,'s')   // "sitten"
insert(1,'p')   // "spitten"
insert(2,'l')   // "splitten"
insert(6,'i')   // "splittin"
insert(8,'g')   // "splitting"
                // "splitting"
```

Iimprove by making the first step `replace(0, 's')`

A recursive formulation
-----------------------

Write a recursive procedure, `ed(S,T)` that returns the _minimum_ edit
distance between `S` and `T`.

Things to think about:

* What is our base case? (Are there base _cases_?)
* What choices should we minimize between?
* Is there a case in which we don't need to minimize? (Is it similar to LCS)

```
ed(S, T)
   // Base case(s)
   if (S == T)
     return 0
   else if (S.length == 0)
     return T.length
   else if (T.length == 0)
     return S.length

   // Easy case
   else if S[0] == T[0]
     return ed(S.substring(1), T.substring(1))
   // Minimize
   else
     return min(1 + ed(S.substring(1), T), // delete first character of S
                1 + ed(S, T.substring(1)), // insert first character of T at start of S
                1 + ed(S.substring(1), 
                       T.substring(1)))    // replace first character of S with 1st of T
```

The table
---------

* There are only two parameters, so we will use a two-dimensional table.
* We will put the source string on the left and the target along the top.
* A cell (row,col) represents `ed(S[0:row],T[0:col])`.
* That is, the the edit distance 
    * from the string consisting of the first `row` elements of `S`
    * to the string consisting of the first `col` elements of `T`.

```
                 TARGET   
                s   p   a   m
            0   1   2   3   4
          +---+---+---+---+---+
        0 |   |   |   |   |   |
  S       +---+---+---+---+---+
  O  s  1 |   |   |   |   |   |
  U       +---+---+---+---+---+
  R  a  2 |   |   |   |   |   |
  C       +---+---+---+---+---+
  E  m  3 |   |   |   |   |   |
          +---+---+---+---+---+
     r  4 |   |   |   |   |   |
          +---+---+---+---+---+
```

Note that we've flipped almost everything from our LCS solution.

* We're doing the substring up to the column/row, rather than
  starting at the column row.
* That means that we'll find the solution in the lower-right corner
  rather than the upper-left corner.
* We've also flipped which axis is associated with each parameter.

QUESTION: HOW DO WE DO THIS ITERATRIVELY

Making it iterative
-------------------

```
ed(S,T)
  edTable = fillTableED(S,T)
  return edTable[S.length, T.length]

fillTableED(S,T)
  // Initialize
  // Loop
  for (row = ?; row ? ?; row++)
    for (col = ?; col ? ?; col++)
      CODE
```

Running time
------------

Extracting the edits
--------------------

```
edits(S,T)
  edTable = fillTableED(S,T)
  return editsHelper(S, T, edTable, S.length, T.length, {})

editsHelper(S, T, edTable, col, row, edits)
  // If we reach the top left of the table, no edits are needed
  if (col == 0) && (row == 0)
    return edits
  // If the target is empty, we need to ?
  else if (col == 0)
    ?
  // If the source is empty, we need to ?
  else if (row == 0)
    ?
  // If the current characters match, we don't need an edit here
  else if S[row] == T[col]
    return editsHelper(S, T, edTable, col-1, row-1, edits)
  // At this point, we need an edit
  else 
    // Compute the best direction
    best = min(edTable[row-1, col-1],   // Represents ?
               edTable[row-1, col],     // Represents ?
               edTable[row, col-1])     // Represents ?
    if (edTable[row-1, col-1] == best)
      edits.append(?)
      return editsHelper(S, T, edTable, row-1, col-1, edits)
    else if (edTable[row-1, col] == best)
      edits.append(?)
      return editsHelper(S, T, edTable, row-1, col, edits)
    else
      edits.append(?)
      return editsHelper(S, T, edTable, row, col-1, edits)
```

We can also make this iterative.

```
```

Project 5
---------

* Edit distance! (Today's problem.)
* Four parts!
    * Generate the table iteratively.
    * Generate the table recursively (using memoization).
    * Extract the edits.
    * Solve a similar problem and gather data.
* Warning: More time consuming than Project 4.
* We've included a Q&A page, too. I'll try to let you know if I update it.
