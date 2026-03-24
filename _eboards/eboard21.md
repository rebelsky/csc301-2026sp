---
title: "EBoard 21: Rebooting the Class"
number: 21
section: eboards
held: 2026-03-23
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

TESTING

_Approximate overview_

* Administrative stuff
* About Assessment 2
* Notes on problem set 2.1 (Rice)
* Notes on project 2
* Notes on Assessment 1.3

Administrative stuff
--------------------

* I've taken over the class. See the email for more details.
* Note that we've already had one student reported for AI use; please do
  not use AI in this class.
    * More importantly, you shouldn't use AI because you probably
      won't learn as much.
* I attempt to record and transcribe my classes.
* Your work from the first half of the semester should be returned on
  Gradescope.
    * If you did not turn in work, you should have received an email from me.
    * Please check comments from graders and instructors. (I left comments
      on many assessments, even when they got credit.)
    * Please do not put your name on assessments. I'd prefer to grade
      semi-anonymously.
* Warning! It may take me some time to learn names (even if I should know
  your names from prior classes).
* We need to talk about resubmission policies. I was going by what was
  on the syllabus, which suggests that there was only one resubmission
  available (at the end of the semester). What did you hear?
    * It appears the policy was "As long as it's graded, you can resubmit it."
    * The grading was a bit iffier.
    * Our policy will be: If you resubmit within a week after receiving
      the work, we'll (almost certainly) grade it within the next week.
      If you don't resubmit within a week, we may take longer, but will
      do our best.
* Your resubmissions for things from the first half of the semester
  DO NOT have to be LaTeXed.
* Resubmissions are done through the Gradescope "Resubmit" feature.
* The assessments will still be take-home.
* Could we have a LaTeX template for the class? I will think about it.
  I could ask the other Prof. Rebelsky.
* You may eventually end up with reading journals.

### Upcoming events

* Monday, 2026-03-23, 7:00 p.m., Noyce 3819, _Mentor Session_ (LaTeX)
* Tuesday,2026-03-24, noon, _CS Table_ (Unknown Topic)
* Thursday, 2026-03-26, 4:15 p.m., _CS Extras_
  The Quest for Transparency and Accountability in the Online Data Ecosystem
     * "Tea" at 4pm in the commons.
     * Chat about grad school afterwards 
* Thursday, 2026-03-26, 7:00 p.m., _Mentor Session_ (LaTeX)

### Upcoming deadlines

* Wednesday, 2026-03-25: Read CLRS 604--611 (Shortest Path) and 
  620--624 (Dijkstra's).
    * Please don't read about Bellman-Ford.
* Monday, 2026-03-30: Resubmissions of Assessment 1, Problem Set 1, Problem
  Set 2, Project 1, and Project 2 due.
    * These are all _optional_. You may choose to wait until May 15.
      However, if you resubmit now, you'll get one more chance and/or
      cross it off your list early.
    * Those who did not turn in initial submissions of these assignments
      (or who turned in partial submissions) may also turn in resubmissions.
    * The LaTeX policy does not apply to these.
* Wednesday, 2026-04-01: Assessment 2 due
    * The LaTeX policy also does not apply to assessment 2.

### The course web site

* I've set up a second course web site for the course. 
   * Partially so that I can post eboards. 
   * Also because I don't have rights to change the syllabus PDF.
* Things you should know
   * Easy links elsewhere (I hope)
   * Eboards can be found in schedule
   * If all goes well, eboards are updated "live"

### The course Teams team

* I've set up a Microsoft Teams team for this course
* There's an area for Q&A that I'll try to track
* There's an area for recordings that I'll try to make
* I think those are the only purposes I'll use it for

Assessment 2
------------

* Assessment 2 should be live on Canvas
* Problem 1: Sum of salaries
* Problem 2: Click distance
* Sam may write test code for the people who are particularly 
  obsessive (or want tests).

Notes on problem 1 from problem set 2
-------------------------------------

The problem: Find a loop invariant for the following algorithm and use it
to prove that the algorithm correctly finds the smallest element in a
non-empty array.

```
def find-min(arr):
    currMin = infinity
    // Loop invariant goes here
    for i = 1, 2, ..., length(arr):
        if currMin > arr[i-1]:
             currMin = arr[i-1]
    // Loop invariant still holds here
    return currMin
```

* For clarity, it's a zero-based array.

An insufficiently helpful invariant: 

> `currMin` is less than every element of `arr`

Why is it insufficiently helpful? 

* It won't always be true (e.g., if we've only looked at some of the
  elements). We need to talk about it it relative to i.
* We probably also want "less than or equal to"

Also an insufficiently helpful invariant:

> For all j, 0 <= j < i - 1, currMin <= arr[j] 

* Note: After the loop, we know that For all j, 0 <= j < length(arr),
  currMin <= arr[j].  
* That is, "currMin is less than equal to all of the elements of
  the array."

```
def find-min(arr):
    currMin = infinity
    // Loop invariant goes here
    for i = 1, 2, ..., length(arr):
        if currMin > arr[i-1]:
             currMin = arr[i-1]
    // currMin <= arr[j] for every valid index in arr.
    return currMin
```

* Whoops. We should also specify that it's an element of the array.
* Otherwise, we could have the situation that arr = { 1, 2, 3, 4, 5 }
  and currMin = 0.

A potentially better invariant:

> `currMin` is the smallest element in the elements 0 (inclusive)
  through i-1 (exclusive) of the array.

Expressing it a bit more formally:

> For all j, 0 <= j < i - 1, currMin <= arr[j]  AND

> Exists k s.t. currMin == arr[k] (i.e., it's an element of the array).

Why is this invariant better?

* If we've shown it holds, concluding that we return the minimum
  value is trivial.
  

What problems might there be with it?

* It's really hard to show that it holds at the start.  In fact, it
  doesn't hold before the first iteration of the loop.

Rewriting `find-min`to make the problem easier

```
def find-min(arr):
    currMin = arr[0];
    // Loop invariant goes here
    for i = 1, 2, ..., length(arr) - 1:
        if currMin > arr[i]:
             currMin = arr[i]
    // Loop invariant still holds here. Use that to reach the conclusion.
    return currMin
```

Notes on project 2
------------------

Here's a sketch of a typical solution to project 2

```
/*
 * Breadth first search.
 */
BFS(adjList, start, finish):
    vertices = new Queue
    vertices.enqueue(start.label)
    while (!vertices.isEmpty())
        v = vertices.dequeue();         // Grab and remove
        mark(v)
        for n : neighbors(vertex)
            if (!marked(n))
                n.previous = vertex
                vertices.enqueue(n)
    path = new List
    v = finish.label
    while (v != start.label)
        path.addToFront(v)
        v = v.previous
    path.addToFront(v)
    return path

/*
 * Depth first search.
 */
DFS(adjList, start, finish):
    vertices = new Stack
    vertices.push(start.label)
    while (!vertices.isEmpty())
        v = vertices.pop();    
        mark(v)
        for n : neighbors(vertex)
            if (!marked(n))
                n.previous = vertex
                vertices.push(n)
    path = new List
    v = finish.label
    while (v != start.label)
        path.addToFront(v)
        v = v.previous
    path.addToFront(v)
    return path
```

That has a variety of problems, including at least one problem with
correctness, at least three stylistic issues, and at least two inefficient
approaches.  How many can you identify? (TPS)

_I don't guarantee that I'll remember all of them._

* Style: No comments. Add them.
* Correctness: Makes assumptions about the values of marked and previous.
    * Option 1: Reset the values of marked and previous at the start of 
      the function.
    * Option 2: Reset the values at the end of the function. [Less good
      option because we may have other functions that could also mark.]
    * Option 3: The procedure edits a copy of the list. [Same problem
      as Option 2.]
    * Option 4: Document the important precondition that all the vertices
      must be unmarked and all the previous fields must be -1.
    * THIS LOST MOST PEOPLE POINTS. FIX USING OPTION 1 OR 4.
* Style: Introductory documentation should have inputs, return value,
  exceptions, description of what it does.
    * FIX THIS!
* Correctness: Does not document what happens when there is no path
  from start to finish. (Currently crashes, too.)
    * FIX THIS
* Question: How do we mark? (In the code you wrote, v.visited = true.) 
* Efficiency issue: If we mark upon removing from the stack, we may end
  up pushing the same vertex more than once. Analysis: Suppose 
  a connects to b and c, b connects to d, c connects to d. We enqueue
  a, then b and c, then d and d.
    * OPTIONAL, BUT DO IT ANYWAY
* Style: The code is not DRY ("Don't Repeat Yourself"). If you factor
  out the common code, you need not repeat yourself as much.
     * In an object-oriented language, you could ensure that stack and
       queue match the same interface, and write the code once using
       that interface.

        list search(vertexStuff vertices, ....) {
        }

        list DFS(...) {
          return search(new Stack, ....);
        }

        list BFS(...) {
          return search(new Queue, ...);
        }
    * YOU DO NOT NEED TO DO THAT!
* Efficiency: We are adding to the front of a list. Most modern
  "list" implementations are actually arrayLists. Adding to the
  front is an O(n) operation. It takes O(n^2) time to build the
  path. If we add to the end, which is O(1), and then reverse,
  building the path is O(n).
* Efficiency: We run the loop until all vertices have been marked.
  We could stop when we hit the exit.

    while ((!vertices.isEmpty()) && (!exit.marked)):
* Style: We're using both vertex objects and vertex numbers.
* Style: How can we best mark vertices?
    * We used fields.
    * Given that verticies are numbered 0 to n-1, we could create
      a new vector of the marks (and predecessors)
    * YOU DON'T NEED TO FIX THIS..

Notes on Assessment 1.3
-----------------------

_These were not presented in class. A handout has been posted to Canvas._

Most of you struggled with this problem. I think it behooves us to go
over some key issues.

What is the typical "form" of a proof by induction? Note that we follow
the form not only because it makes things clearer for the reader but also
to ensure that we cover all the important parts.

Some important issues

* We may encounter the base case or even the inductive case as the result
  of a recursive call. Be careful to consider what you do and do not know.
* You may find it useful to give an invariant about the state of the
  puzzle at any time. For example, what can you say about the size of
  the discs in the substack you are moving as compared to the other
  discs? (That may also be part of your theorem statement.)

