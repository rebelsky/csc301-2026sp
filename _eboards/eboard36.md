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
* Opening questions
* Our current problem: Edit distance
    * Designing the table
    * Making it iterative
    * Extracting the actions
    * Example
* Looking ahead to Assessment 4
* Looking ahead to next class
* Work time

Administrative stuff
--------------------

* Masks in the back of the room.
* Assessment 4 returned over the weekend.
* PM wrote code for LCS. I've posted it to our Handouts folder.
    * PM built the table in the opposite way to how we built the table.
* Please fill out the AI use survey at
  <https://grinnell.co1.qualtrics.com/jfe/form/SV_88Md19LJ12t6kFU>
* We're in the downhill stretch! Our last few classes will cover topics
  you will benefit from learning, but which you will not be tested on. 
  Please attend anyway! (And definitely try to attend the last class.)
* Advance warning: I will not be on campus tomorrow (Tuesday).

## Some notes from grading

* **Do not put your name on assessment submissions!** I will return 
  without grading submissions with names.
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
  It's a two-hour assessment.

### Policy/administrative/assignment questions

When will the regrades get done?

> This coming weekend. (Maybe sooner.)

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
     return T.length    // Insert a bunch of characters
   else if (T.length == 0)
     return S.length    // Delete a bunch of characters

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
  // Initialize row 0
  for (int col = 0; col <= T.length; col++)
    table[0, col] = col 
  // Initialize column 0
  for (int row = 0; row <= S.length; row++)
    table[row, 0] = row
  // Loop
  for (row = 1; row <= S.length; row++)
    for (col = 1; col <= T.length; col++)
      if (S[row] == T[col])
        table[row,col] = table[row-1, col-1]
      else
        min(1 + table[row-1, col-1],    // Replace
            1 + table[row, col-1],      // Insert
            1 + table[row-1, col])      // Delete
```

```
                 TARGET   
                s   p   a   m
            0   1   2   3   4
          +---+---+---+---+---+
        0 | 0 | 1 | 2 | 3 | 4 |
  S       +---+---+---+---+---+
  O  s  1 | 1 | 0 | 1 | 2 | 3 |
  U       +---+---+---+---+---+
  R  a  2 | 2 | 1 | 1 | 1 | 2 |
  C       +---+---+---+---+---+
  E  m  3 | 3 | 2 | 2 | 1 | 1 |
          +---+---+---+---+---+
     r  4 | 4 | 3 | 3 | 2 | 2 |
          +---+---+---+---+---+
```

Running time
------------

$$O((|S|+1)\times(|T|+1))$$ because that's the size of the table and
each cell is constant amount of work to fill.

$$O(|S| + |T| + |S|\times|T|)$$


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
  // If the target is empty, we need to delete everything
  else if (col == 0)
    // Think carefully about this for Project 5
    for (r = row-1; r >= 0; r--)
      edits.append(Delete(r))
    return edits
  // If the source is empty, we need to ?
  else if (row == 0)
    for (c = col-1; c >= 0; c--)
      edits.append(Insert(0,T[c]))
    return edits
  // If the current characters match, we don't need an edit here
  else if S[row] == T[col]
    return editsHelper(S, T, edTable, col-1, row-1, edits)
  // At this point, we need an edit
  else 
    // Compute the best direction
    best = min(edTable[row-1, col-1],   // Represents Replace
               edTable[row-1, col],     // Represents Delete
               edTable[row, col-1])     // Represents Insert
    if (edTable[row-1, col-1] == best)
      edits.append(Replace(row-1, T[col-1])
      return editsHelper(S, T, edTable, row-1, col-1, edits)
    else if (edTable[row-1, col] == best)
      edits.append(Delete(row-1))
      return editsHelper(S, T, edTable, row-1, col, edits)
    else
      edits.append(Insert(row, T[col-1])) // Need to check!
      return editsHelper(S, T, edTable, row, col-1, edits)
```

We can also make this iterative.

```
edits(S,T)
  edTable = fillTableED(S,T)
  edits = {}
  col = T.length
  row = S.length
  while (col >= 0) && (row >= 0)
    if (row =- 0) 
      edits.append(Insert(0, T[col-1]))
      col = col - 1
    else if col == 0
      edits.append(Delete(row-1))
      row = row - 1
    else if S[row] == T[col]
      row = row - 1
      col = col - 1
    else
      // Compute the best direction
      best = min(edTable[row-1, col-1],   // Represents Replace
                 edTable[row-1, col],     // Represents Delete
                 edTable[row, col-1])     // Represents Insert
      if (edTable[row-1, col-1] == best)
        edits.append(Replace(row-1, T[col-1])
        row = row - 1
        col = col - 1
      else if (edTable[row-1, col] == best)
        edits.append(Delete(row-1))
        row = row - 1
      else  
        edits.append(Insert(row, T[col-1])) // Need to check! (looked right)
        col = col - 1
  return edits
```
Example
-------

```
                // samr
Delete(3)       // sam
Insert(1,'p')   // spam
```

Assessment 4
------------

String alignment! Given two strings $$S$$ and $$T$$. You can insert
spaces anywhere you want in $$S$$ and $$T$$. Goal: Minimize the mismatches
after inserting spaces.

```
S = actactact
T = tagtgcat
    ****** *
```

```
S = actactac_t
T = __tagtgcat
    **  * * *
```

Where do we put the spaces to minimize the total number of mismatches?

* Design recursive algorithm (Python-like pseudocode)
* Design table: Shape, Entries (count of mismatches)
* Make it iterative!
* Running time
* Extract the modified strings (with spaces)

Next class
----------

* Binary search trees, balanced.
* Please review insertion and deletion in BSTs.
