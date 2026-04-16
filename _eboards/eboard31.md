---
title: "EBoard 31: Dynamic Programming (1)"
number: 31
section: eboards
held: 2026-04-15
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* The stamps problem, revisited
    * An exhaustive solution
    * The joy of caching
    * An iterative approach
* Dynamic programming
* The knapsack problem, revisited
    * Organizing the problem / designing the table
    * Writing a recursive solution
    * Making it iterative

Administrative stuff
--------------------

* Happy Scarlet and Give Back Day!
* Have I once again overplanned for today? We shall see.
* PM and I decided to rearrange the schedule a bit so that you'll have
  more of the background to do Problem Set 5 and Project 5 "early".
* Project 3 has been returned.
    * The graders say that the biggest issues were (a) lack of documentation
      and (b) inconsistent code formatting.
    * Fixing those two issues should be quick, so please do so quickly.
* On code formatting
    * I prefer GNU style (key idea: braces on separate lines, indented), 
      with a fallback preference of Google Java style (open brace on same
      line), but I'll accept anything consistent.
    * Inconsistency suggests that one of three (or more) things is happening
        * The partners are doing the work separately
        * Parts of the code are taken from somewhere else
        * You're not paying attention
        * Something else
    * Note that if you go on to a SWE or programmer job, you will be
      expected to follow "house style".
* We had an interesting conversation with our alum yesterday.
    * It's clear that you need to be able to have deep conversations
      about questions like "What are the advantages and disadvantages
      to using this data structure in this problem". (More generally,
      about comparing approaches or thinking deeply about implications.)
    * At the same time, you're not going to make it to the conversations 
      unless you can do the automatically-graded (or AI graded) coding 
      (and design) challenges.
    * And they want you to be ready to use AI coding tools from day one.
    * _I think of this class as mostly helping you with the first issue,
      but the projects should also help with the coding challenges._

### Upcoming events

* Thursday, 2026-04-16, 11:00-noon, _Convocation with CS alum_
* Thursday, 2026-04-16, 7:00 p.m., _Mentor Session_
* Monday, 2026-04-20, 7:00 p.m., 3819, _Mentor Session_
* Thursday, 2026-04-23, 4:15--5:15pm, _Thursday Extra_ (?)

### Upcoming deadlines

* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due
* Monday, 2026-04-20: Assessment 2.1 Resubmissions
* Wednesday, 2026-04-22: Assessment 3 due

### Policy/administrative/assignment questions

Can you show the different styles?

GNU Style

```
int
factorial (int n) 
{
  if (n <= 0) 
    {
      return 1
    }
  else 
    {
      return n * factorial (n - 1);
    }
} // factorial
```

Google Java Style

```
int factorial(int n) {
  if (n <= 0) {
    return 1;
  } else {
    return n * factorial(n-1);
  }
} 
```

Many of you use

```
int factorial(int n) {
  if (n <= 0)
  {
  } else
  {
  }
}
```

What are the policies for using LaTeX?

> You can look up the different LaTeX things, but you CANNOT use AI to
  generate your LaTeX or to translate from one format to LaTeX.

> You *can* use Pandoc to translate to LaTeX.

The stamps problem, revisited
-----------------------------

Given a set of stamp values $$V = { v_1, v_2, ... v_n }$$ and a total cost, 
$$C$$, find the fewest stamps that exactly sum to $$C$$.

A greedy solution can work.

Example: 1, 5, 10, 25, and any $$C$$.

But it won't always work.

Example: 1, 3, 8, 14 and $$C = 16$$

An exhuastive solution
----------------------

Find *every* way to make $$C$$, choose the one with the smallest number
of stamps.


_Also a recursive solution._

We'll design a function, `stamps(V,C)`, that tells you the minimum
number of stamps. (We can modify it to tell you what those stamps are.)

```
stamps(V,0) = 0
stamps(V,C) = min(1 + stamps(V, C-v1), 1 + stamps(V, C-v2), ...)
```

Refactor

```
stamps(V,0) = 0
stamps(V,C) = 1 + min(stamps(V, C-v1), stamps(V, C-v2), ...)
```

Hack to limit it to stamps that are less than or equal to the cost

```
stamps(V,negative) = infinity
stamps(V,0) = 0
stamps(V,C) = 1 + min(stamps(V, C-v1), stamps(V, C-v2), ...)
```

Observation: If there is no solution (e.g., because we have no one-cent stamp), we will likely get ininity as an answer.

For our example

$$V = { 14, 8, 3, 1 }$$
```
stamps(V, 16)
  1 + min(stamps(V, 2), stamps(V, 8), stamps(V, 13), stamps(V, 15))

stamps(V, 2) = 1 + min(stamps(V, -12), stamps(V, -6), stamps(V, -3), stamps(V, 1)

stamps(V, -12) = infinity

stamps(V, -6) = infinity

stamps(V, -3) = infinity

stamps(V, 1) = 1 + min(...., stamps(V, 0)) = 1

stamps(V, 2) = 1 + 1 = 2

stamps(V, 8) = 1 + min(stamps(V, -6), stamps(V, 0), stamps(V, 5), stamps(V, 7))

stamps(V, 8) = 1 + min(infinity, 0, stamps(V, 5), stamps(V, 7))

stamps(V, 5) = 1 + min(stamps(V, -9), stamps(V, -3), stamps(V, 2), stamps(V, 4))

stamps(V, 5) = 1 + min(infinity, infinity, stamps(V, 2), stamps(V, 4))

stamps(V, 2) = 1 + min(stamps(V, -12), stamps(V, -6), stamps(V, -1), stamps(V, 1))

stamps(V, 2) = 1 + min(infinity, infinity, infinity, stamps(V, 1))

stamps(V, 1) = 1 + min(stamps(V, -13), stamps(V, -7), stamps(V, -2), stamps(V, 0))

stamps(V, 1) = 1 + min(infinity, infinity, infinity, 0) = 1

stamps(V, 2) = 1 + min(infinity, infinity, infinity, 1) = 2


stamps(V, 4) = 1 + min(stamps(V, -10), stamps(V, -4), stamps(V, 1), stamps(V, 3))
stamps(V, 4) = 1 + min(infinity, infinity, stamps(V, 1), stamps(V, 3))

stamps(V, 1) = 1 + min(stamps(V, -13), stamps(V, -7), stamps(V, -2), stamps(V, 0))

stamps(V, 1) = 1 + min(infinity, infinity, infinity, 0) = 1
```

How much work does this do?

* At each stage, we do $$|V|$$ recursive calls
* Given that one of stamps is a one-center, we can recurse $$C$$ times (in
  one recursive path).
* So this is approximately $$|V|^C$$

Can we do better?

Yes! Take advantage of our observation that we are recomputing the same
value again and again and again.

The joy of caching
------------------

Idea: Make a table of the values we've computed.

```
   0   1   2   3   4   5   6
+---+---+---+---+---+---+---+
|  0|inf|inf|inf|inf|inf|inf|
+---+---+---+---+---+---+---+
```

Rewrite our algorithm to use the table

```
stamps(V, C) 
  if (C < 0)
    return inf
  else (C == 0)
    return 0
  if table[C] < infy
    return table[C]
  else
    table[C] = 1 + min(stamps(v1, C-v1), stamps(v2, C-v2), ...)
    return table[C]
```

You never fill in a space more than once. There are $$C$$ spaces to fill
in. So, even with all the recursive calls, this is $$O(C \times |V|)$$.

An iterative approach
---------------------

Can we fill out the table iteratively, rather than recursively?


```
   0   1   2   3   4   5   6   7   8   9  10  11  12  13  14 15   16
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
|  0|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|inf|
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
```

```
fillTable(V, C)
  for c in 1:C
      table[c] = 1 + min(lookup(table,c-v1), lookup(table,c-v2), ...)

lookup(table, x)
  if (x < 0) 
    return infinity 
  else
    return table[x]

stamps(V, C)
  table = fillTable(V, C)
  return table[C]
```

Improving the algorithm

```
fillTable(V, C)
  for v in V
    table[v] = 1
  for c in 1:C
    if (table[c] == infy)
      table[c] = 1 + min(lookup(table,c-v1), lookup(table,c-v2), ...)
```

Dynamic programming
-------------------

An approach to certain kinds of optimization problems, originally
designed by Richad Bellman at Bell Labs in 1950's.

Given an optimization problem, do three basic things

* Frame it with a "recursive" way of computing the desired value (that
  is, computing it from smaller versions of the same problem)
* Use a table to make the computation more efficient
* (Fill in the table iteratively)

In our stamps problem, the following expression was our recursive way
of computing (or thinking about the issue)

```
stamps(V, C) = 1 + min(stamps(V, C-v1), stamps(V, C-v2), ...)
```

In our stamps problem, we made it more efficient by using a one-dimensional
table.

Note: We may move on to two-dimensional and three-dimensional tables.

Question
--------

How can we modify the stamps solution above to extract the list of
stamps?

```
fillTable(V, C)
  for v in V
    table[v] = 1
  for c in 1:C
    if (table[c] == infy)
      table[c] = 1 + min(lookup(table,c-v1), lookup(table,c-v2), ...)

define lookup(table, x)
  if (x < 0) 
    return infinity 
  else
    return table[x]

stamps(V, C)
  table = fillTable(V, C)
  return table[C]
```


The knapsack problem, revisited
-------------------------------

You have a bunch of items, each of which has a value and a weight,
both of which are positive integers.  

We can represent these as $$(v_i, w_i)$$ pairs.  

You also have a designated total weight, $$W$$, that you can carry. 

Which items should you grab to maximize the total value of grabbed items?

Organizing the problem for DP
-----------------------------

What is your recursive formulation? I.e, given a set of pairs, P, and a 
total weight, W, what does your computation depend upon?

Sam will ask you those questions at the start of next class. Be ready.

Two key issues for DP:

Writing a recursive solution
----------------------------

Making it iterative
-------------------
