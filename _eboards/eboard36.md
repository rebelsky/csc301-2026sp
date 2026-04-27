---
title: "EBoard 36: Dynamic Programming (6)"
number: 36
section: eboards
held: 2026-04-27
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Assessment 5
* Opening questions
* Our current problem: Edit distance
    * Designing the table
    * Making it iterative
    * Extracting the actions
* Looking ahead to Assessment 4
* Work time

Administrative stuff
--------------------

* Assessment 4 returned over the weekend.
* PM wrote code for LCS. I've posted it to our Handouts folder.
* It has been suggested that enough crud is going around campus that
  you should consider wearing masks.
* Please fill out the AI use survey at
  <https://grinnell.co1.qualtrics.com/jfe/form/SV_88Md19LJ12t6kFU>
* We're in the downhill stretch! Our last few classes will cover topics
  you will benefit from learning, but which you will not be tested on. 
  Please attend anyway! (And definitely try to attend the last class.)
* Advance warning: I will not be on campus tomorrow (Tuesday).

## Some notes from grading

* **Do not put your name on assessment submissions!** I will return without
  grading submissions with names.
* I've seen some problem sets that look strangely similar (not copied,
  but seemingly done together). **If you work with others, please make 
  sure to cite your colleagues!**

### Upcoming events

* Monday, 2026-04-27, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-30, 4:15--5:15pm, _Thursday Extra_ 
* Thursday, 2026-04-30, 7:00 p.m., _Mentor Session_
* Thursday, 2026-04-30, _Trustees on Campus_ (dessert with students?)
* Friday, 2025-05-01, 5:00 p.m.: _CS Picnic_

### Upcoming deadlines

* Friday, 2026-05-01: _Early Deadline_ for Problem Set 5
* Friday, 2026-05-01: _Early Deadline_ for Project 5
* Friday, 2026-05-08: Problem Set 5 due
* Friday, 2026-05-08: Project 5 due
* Friday, 2026-05-08: Assessment 4 due
* Friday, 2026-05-15 (5pm): All resubmissions/late work due. 
  **No extensions. This deadline is _not_ a lie!**

### Other upcoming dates

* Wednesday, 2026-04-29: Assessment 4 distributed (only one problem, but long)

### Policy/administrative/assignment questions

_None_

Sam's Opening Question
----------------------

* Write the algorithm to fill out the edit distance table iteratively

Edit distance: Review
---------------------

Given two strings, $$S$$ and $$T$$, what is the fewest number of changes
needed to convert $$S$$ to $$T$$?

Valid changes: 

* `delete(i)` - delete the letter at position `i` in `S`.
* `insert(i,ch)` - insert the letter `ch` immediately before position `i`.
* `replace(i,ch)` - replace the letter at position `i` with `ch`.
  (This is often called `substitute`.)

A recursive formulation
-----------------------

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
     return min(// Delete 1st character of S
                1 + ed(S.substring(1), T), 
                // insert 1st character of T at start of S
                1 + ed(S, T.substring(1)), 
                // replace 1st character of S with 1st of T
                1 + ed(S.substring(1), T.substring(1)))    
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

Example
-------

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

Assessment 4
------------

Work time
---------

Time to work on Project 5, Problem Set 5, or whatever. 
