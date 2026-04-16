---
title: "EBoard 30: O(n) sorting algorithms"
number: 30
section: eboards
held: 2026-04-13
link: true
---
# {{ page.title }}

**Warning! You are _probably_ being recorded** (and transcribed).

_Approximate overview_

* Administrative stuff
* Introduction to O(n) sorting algorithms
* Sample O(n) sorting algorithm
* Radix sort
* Bucket
* Looking ahead to the next class

Administrative stuff
--------------------

* Assessment 2.1 returned

### Upcoming events

* Monday, 2026-04-13, 7:00 p.m.ish, 3819, _Mentor Session_
* Tuesday, 2026-04-14, Noon, Visit with an alum, See email for space. 
  Free pizza!
* Thursday, 2026-04-16, 11:00-noon, _Convocation with CS alum_
* Thursday, 2026-04-16, 4:15--5:15pm, _Thursday Extra_
* Thursday, 2026-04-16, 7:00 p.m., _Mentor Session_

### Upcoming deadlines

* TONIGHT, 2026-04-13: Assessment 2.2 Resubmissions
* TONIGHT, 2026-04-13: Problem Set 3 Resubmissions
* Wednesday, 2026-04-15: Skim CLRS 8.3 (Radix Sort) and 8.4 (Bucket Sort)
* Friday, 2026-04-17: Problem set 4 due
* Friday, 2026-04-17: Project 4 due
* Monday, 2026-04-20: Assessment 2.1 Resubmissions
* Wednesday, 2026-04-22: Assessment 3 due

### Assessment 3 released

* Assessment 3.1: Greedy knapsack with uniform value. Differs in that we
  want the maximum _number_ of items rather than the maximum _value_.
* Assessment 3.2: Greedy gas station visits

### Policy/administrative/assignment questions

Important: In C++, the for-each loop you are tempted to write grabs
copies of objects, rather than the objects themselves.

```
for Verbex v : adjList {
  v.visited = false;
  v.previous = -1;
} 
```

How do you fix that? You can index into `adjList` or you can grab
pointers to objects rather than objects. (Look up how to do that.)

Remember to test your code.

Introduction to O(n) sorting algorithms
---------------------------------------

* As you know, one of the last steps you do after designing an algorithm
  is to ask yourself "Can I do better?"
* What do you know about sorting algorithms? (TPS)
    * We've seen three O(nlogn) algorithms:
        * Merge sort
        * Quick sort
        * Heap sort. 
        * Tim sort.
    * About heap sort
        * A heap is at tree structure that implements two
          key operations: add to heap, and remove from heap, which
          removes the highest priority element. (Heaps implement
          priority queues.) 
        * Sorting: Put all the elements in the heap, repeatedly
          grab the largest element and put it at the end of the "array".
        * You can sort using *any* priority queue, but heaps are nice
          because O(logn) to add, O(logn) to get, and they fit in
          arrays.
        * Heap sort is an O(nlogn) _in place_ sorting algorithm.
    * What is a "stable" sorting algorithm? Preserves the order of
      equal elements.
    * Which of merge sort and Quicksort are stable? (at least in
      the normal formulations)
        * Merge sort, as normally phrased, is stable because
          when you merge, you grab from the left side before
          the right side, which preserves ordering
        * Quick sort, as normally phrased, is not stable because
          we often swap elements from left to right and vice versa
        * The other sort, as normally phrased, is not stable
    * What other O(nlogn) sorting algorithm do you know?
        * See above.
    * Can we do better?
        * We proved that we can't do better than O(nlogn) for
          sorting algorithms in which you have no prior information
          about the contents AND you sort by comparing values
          for smaller/larger.
* So how could we do better? (TPS)
    * We could restrict the contents in some way
    * We could not compare values

A simple O(n) sorting algorithm
-------------------------------

* Suppose we know that we are sorting a list of the letters of the
  alphabet and every letter appears exactly once.
* We know there are twenty-six elements at least in US Enlgish.
* Create an array of 26 spots, go through the unsorted list and
  put each one into its correct spot.
* We already know what the result looks like, so we can just 
  generate it

```
int pos = 0;
for (char ch = 'a'; ch <= ''; ch++) {
  arr[pos++] = ch;
}
```

Radix sort
----------

### Demo of parallel radix sort

* Sam has a stack of cards with letters and binary encodings, in order.
    * Some cards are missing.
    * Some are repeated.
* Each card also has a physical representation of the 0's and 1's,
  making it easy to split the cards into those with a 0 in each spot
  and those with a 1 in each spot.
* In only five steps, Sam was able to sort the cards.
    * More precisely, if Sam had done the encoding correctly, he would
      have been able to sort the cards in only five steps.

### How did that work? (TPS)

* What did Sam do?
    * Progressed through the bits (in what order?)
    * Split the cards based on 0's and 1's
    * Put the 0's in front of the 1's
* Why did that work?
    * After the first round, we had all the xxxx0s in front of all the xxxx1s.
    * After the second round, we had them in the order xxx00, xxx01, xxx10,
      xxx11.
    * After the third round, we have them in the order xx000, xx001, xx010,
      xx011, xx100, xx101, xx110, xx111

### How might we implement it sequentially?

* Set up two lists, one for the eleemnts with zero in the spot, one for
  the elements with one in the spot. 
* Go through and append to the appropriate list.
* Concatenate the two lists
* Do it all over again.

### What's the running time?

If done well, this appears to be O(#bits * n). (The number of bits is
independent of the number of values.)

This is an O(n) algorithm!

### What was required

We needed to be able to encode the values as bit strings.

If we were doing people's names, the #bits might be large enough to
dissuade us from using this algorithm.

Bucket sort
-----------

Suppose we wanted to sort numeric grades (whole numbers, on a 0--100 scale).
In a really large class (e.g., with 1000 people). And we want to keep
identifying information with each grade.

### What's an approach we might take? (TPS)

* Use a hash table and hash them by grade.
* Create an array with one bucket for each value from 0 to 100.
    * Each bucket is a list.
    * Run through the values, adding them to the end of the list
      in the appropriate bucket.
    * Go through the buckets, putting them back into the main list.
      They will end up in sorted order.
* This is bucket sort.
* Is Bucket sort stable? Yes, if we add to the end of the list in
  each bucket.

### What's the running time? (TPS)

O(n + number of buckets)

Bucket sort works well if we have a limited range of valeus.

### Can we do it with fewer "buckets"?

* If our value breaks up into "digits" (e.g., literal digits, letters,
  ...), we can use bucket sort plus the ideas of radix sort.

For our problem above.

* Start with 10 buckets, numbered 0 through 9.
* Put things in the correct bucket based on the last digit
* Merge the buckets
* Put things in the correct bucket based on the first digit
* Merge the buckets
* Things from 00 to 99 are in correct order.
* You can use this to sort strings.

Looking ahead
-------------

On Wednesday, we'll be returning to binary search trees. Please review
your notes (mental or physical) from CSC-207 on BSTs

* What ADT do BSTs implement?
* What requirements to BSTs put on their data?
* How do you add to a BST?
* How do you remove from a BST?

