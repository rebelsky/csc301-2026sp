---
title: Analysis of Algorithms
permalink: /syllabus/
---
# Analysis of Algorithms

<dl class="dl-horizontal">
  <dt>Instructor</dt>
  <dd>
    <p><a href="{{ site.instructor_homepage }}">{{ site.instructor }}</a></p>
  </dd>

  <dt>Meeting Times</dt>
  <dd>
    <ul class="list-unstyled">
      {% for time in site.meeting_times %}
        <li>{{ time | markdownify | remove: "<p>" | remove: "</p>" }}</li>
      {% endfor %}
    </ul>
  </dd>

  <dt>Office Hours</dt>
  <dd>
    <ul class="list-unstyled">
      {% for item in site.office_hours %}
        <li>{{ item | markdownify | remove: "<p>" | remove: "</p>" }}</li>
      {% endfor %}
    </ul>
  </dd>

  {% if site.review_sessions %}
    <dt>Review Sessions</dt>
    <dd>
      <ul class="list-unstyled">
        {% for session in site.review_sessions %}
          <li>{{ session }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}

  {% if site.textbook %}
    <dt>Textbook</dt>
    <dd>
      {{ site.textbook | markdownify | remove: "<p>" | remove: "</p>" }}
    </dd>
  {% endif %}

  {% if site.mentor %}
    <dt>Class Mentor</dt>
    <dd>{{ site.mentor }}</dd>
  {% endif %}

  {% if site.mentors %}
    <dt>Class Mentors</dt>
    <dd>
      <ul class="list-unstyled">
        {% for mentor in site.mentors %}
          <li>{{ mentor }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}

  {% if site.mentor_sessions %}
    <dt>Mentor Sessions</dt>
    <dd>
      <ul class="list-unstyled">
        {% for session in site.mentor_sessions %}
          <li>{{ session | markdownify | remove: "<p>" | remove: "</p>" }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}

  {% if site.tutors %}
    <dt>CS Tutors</dt>
    <dd>
      <ul class="list-unstyled">
        {% for tutor in site.tutors %}
          <li>{{ tutor }}</li>
        {% endfor %}
      </ul>
    </dd>
  {% endif %}
</dl>

## About this course

Welcome to the Spring 2026 version of Grinnell College's CSC-301-01, *Analysis of Algorithms*.  CSC -301 is the department's advanced course in algorithms.  It serves as a successor to CSC-207, _Object-Oriented Design, Algorithms, and Data Structures_.  In this course, we will work to develop your skills in the design, implementation, analysis, and verification of algorithms, abstract data types, and data structures.  

Along the way, we will consider a variety of classic algorithms, ADTs, and data structures - the "literature" of CS, as it were.  Why do we read the literature?  Because knowing how problems have been solved in the past helps us solve future problems.  A not-so-recent article on mathematics suggests the value of knowing the literature.

> Yet, as they told me, the proof [of the Green-Tao Theorem] depended on the insights of many other mathematicians. In the game of devil's chess [mathematics], players have no real hope if they haven't studied the winning games of the masters. A proof establishes facts that can be used in subsequent proofs, but it also offers a set of moves and strategies that forced the devil to submit — a devious way to pin one of his pieces or shut down a counterattack, or an endgame move that sacrifices a bishop to gain a winning position. Just as a chess player might examine variations of the Ruy Lopez and King's Indian Defense, a mathematician might study particularly clever applications of the Chinese remainder theorem or the sieve of Eratosthenes. The wise player has a vast repertoire to draw on, and the crafty player intuits the move that suits the moment.

> Cook, Gareth (2015).  The Singular Mind of Terry Tao.  *The New York Times Magazine*.  26 July 2015.  Available online at <http://www.nytimes.com/2015/07/26/magazine/the-singular-mind-of-terry-tao.html>.

This course will expand your "repertoire to draw on".

As importantly, we will work on your skills related to algorithm design and analysis by asking you to design and reflect on algorithms. Even when we cover algorithms from the literature, I often will ask you to try to develop your own algorithm to solve a problem before we explore the known algorithm. In my experience, doing so not only helps you develop skills, it also helps you understand why the algorithm designer made certain decisions.

I expect that every Grinnell CS major should be able to master the material in this course and use a form of mastery grading for this course to help ensure that your grade depends on whether you demonstrate your mastery of the material by the end of the semester, rather than upon when in the semester you master it.

## On the mid-semester switch

I took over as instructor for this course in the middle of the semester. Among other things, this means that I will generally follow the policies and deadlines from the previous instructor. You can find those on [the CSC-301-02 2026Sp Canvas Site](https://grinnell.instructure.com/courses/1951) (which should only be accessible to students in the class and the class support staff).

Here are a few of the important changes.

* **Resubmissions**: You were previously permitted one resubmission per assignment (problem, programming project, or assessment), due at the end of the semester. I will do my best to permit an extra resubmission, generally due one week after I return the graded assignment. (You may not turn in late resubmissions.)
* **Attendance**: My attendance policies are different. I expect you to show up every day. If you can't make it to class, you must send me an email message. You can read more details below.
* **Structure of class sessions**: As you may have noted from the comments above or will note from the comments below, you are likely to find that I run class sessions a bit differently. Among other things, I regularly call on students randomly during class. If that causes undue stress, please let me know and I'll work out an alternative.
* **Academic alerts**: Although the course policies permit you to skip the initial submission of an assignment and submit only the "resubmission", I will submit an Academic Alert if you do not make the initial submission.
* **Textbook**: I will assign regular readings in [CLRS](https://ebookcentral-proquest-com.grinnell.idm.oclc.org/lib/grinnell-ebooks/detail.action?docID=6925615). In general, I will ask you to read about a topic _after_ we have discussed it in class.
* **LaTeX**: I expect you to do all Problem Sets in LaTeX rather than writing them by hand.

Expect to see this site evolve over time.

## Other class issues

### Textbook

Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein (2022). [_Introduction to Algorithms, 4th Edition__](https://ebookcentral-proquest-com.grinnell.idm.oclc.org/lib/grinnell-ebooks/detail.action?docID=6925615)

> One of the most comprehensive texts on algorithms. Often treated as the standard.

### Types of writing 

This course employs a variety of genres of writing typically used in the discipline.  In particular, students will write,

* _Proofs_ in mathematical English.
* _Program code_ in a variety of languages
* _Documentation_ that explains the goals and expectations of functions, data structures, and compositions of functions and data structures, such as in objects/classes or full programs.

They may also write

* _Unit tests_ that help verify the correctness of code.
* _Claim-based essays_ in English.

### Class Format

I firmly believe that you learn by doing.  While I do not expect to make this a full workshop-style course, akin to CSC 151 or CSC 161, I will do my best to regularly give you the opportunity to work together and individually on problems during class time.  I am also likely to conduct many class sessions using my standard "pseudo recitation" (Socratic lite?) style - I will randomly select students and ask a series of questions.  You should do your best to answer those questions, but you should feel free to say "I'm not sure" or to ask me your own questions.  I employ that style not only because I find that it helps model problem solving processes, but also because it exposes you to other ways of thinking about problems.

### Time and workload

Grinnell has indicated that a 4-credit course, like this one, should involve approximately 180 hours of work, which matches some guidance from the U.S. government.  Across a 14-week term (plus a week finals), that ends up being approximately 12 hours of work per week.  I have not been able to get anyone to answer the question of "180 hours *for whom* and *for what grade*?", I've been given the impression that I should strive for that workload for the "average" student in a course.  If you find yourself working much more than the expected amount in this course, please let me know.  It could be that other approaches, or better support resources, can help.

## Diversity and inclusion

I believe that any college-level course should challenge you and put you outside of your comfort zone.
My mission as an instructor is to help you manage that discomfort so that you can grow in knowledge and maturity.
Therefore, I strive to create a fully inclusive setting so that we all can ultimately succeed in the classroom.

### Learning needs

I welcome you to talk to me as early as possible about your distinctive learning needs.
I particularly encourage students with disabilities to meet with me and discuss how our classroom and course activities could impact their work and what accommodations would be essential.
I will also make adjustments for students without documented disabilities.  However, I do recommend that you seek official accommodations.
As part of the accommodations process, I recommend talking to our Coordinator for Student Disability Resources for guidance and further instructions:

+ Jae Baldree, Steiner Hall, 1st floor (x3089)

You can find some additional details in [my statement on accommodations and adjustments](../handouts/accommodations).

### Religious observance policy

I also support the class's religious diversity and anyone who needs to balance academic work with religious observations.
Please let me know by **week two** if you will need to be absent from class for any religious holidays this semester, and we can work out an appropriate schedule for making up absences or missed work.

### Community guidelines

Our class is a community.  We should behave as such.  Among other things, that means treating others with respect, not only in the language that we use (no slurs, please), but also in taking ideas, approaches, and perspectives seriously.  We will discuss appropriate guidelines for the class throughout the semester, developing those guidelines as a community.

### Attendance

As I noted, our class is a community. You will learn as much from your interactions with your peers as you will from me. At times, you will learn as you work together to solve problems. At others, you will learn by explaining something that you've already figured out but a colleague has not; when you explain something, you understand it better. And, at times, you'll learn something because a peer will find a way to explain it to you that works better for you than what I did.

You cannot gain these benefits if you are not here. And your classmates cannot benefit from your presence if you are not here. Hence, **I expect you to be here for each class session.**

At the same time, I understand that there are reasons that you will be unable to make it to class, including conflicting obligations (e.g., religious obligations, family obligations, obligations to teams or other courses) as well as health reasons, including mental-health reasons (even the need for a "mental health" day). Hence, I do not strictly require attendance. However, **You must send me an email message before any class session that you will miss.** You need not tell me why you are missing class, only that you are missing class.

If you miss class two or more days in a row without notifying me, I will submit an Academic Alert. I may also submit Academic Alerts or impose grade penalties if you regularly miss class without notifying me in advance.

### Title IX and Pregnancy-related conditions

Grinnell College is committed to compliance with Title IX and to supporting the academic success of pregnant and parenting students and students with pregnancy-related conditions. If you are a pregnant student, have pregnancy-related conditions, or are a parenting student (child under one-year needs documented medical care) who wishes to request reasonable related supportive measures from the College under Title IX, please email the Title IX Coordinator at titleix@grinnell.edu. The Title IX Coordinator will work with Disability Resources and your professors to provide reasonable supportive measures in support of your education while pregnant or as a parent under Title IX.

If you are a pregnant student, have pregnancy-related conditions, or are a parenting student (of any age child) and would prefer to work with me directly, rather than through our Title IX office, I will do my best to support you through appropriate measures. Please reach out!

### Basic needs security

Any student who has difficulty affording groceries or accessing sufficient food to eat every day, or who lacks a safe and stable place to live, should know that these difficulties are likely to affect their performance in the course. Such students are urged to contact the [Dean of Students](https://www.grinnell.edu/profiles/student-affairs/staff) or the [CRSSJ](https://www.grinnell.edu/about/offices-services/crssj) for support. You may also notify me, if you feel comfortable doing so, and I will do my best to help you identify and arrange other resources.

### Other accommodations and adustments

There are many other ways in which it may be appropriate to adjust course policies and it is not possible to attempt to address them all in a few policies.  These may include other issues best addressed earlier in the semester (*e.g.*, student-athlete scheduling) or as they arise (*e.g.*, chronic health flare-ups).  I will do my best to be flexible in these cases with the overall goal of facilitating your growth in this course.  Please keep me informed!

In some cases, I will recommend consulting with the [Academic Advising staff](http://www.grinnell.edu/about/offices-services/academic-advising). They are an excellent resource for developing strategies for academic success and can connect you with other campus resources as well. If I notice that you are encountering difficulty, in addition to communicating with you directly about it, I will also likely submit an academic alert via Academic Advising's SAL portal. This reminds you of my concern, and it notifies the Academic Advising team and your advisor(s) so that they can reach out to you with additional offers of support.

## Other issues

**Names**

I get names wrong.  I get names wrong all the time.  Evidence suggests that I am not just bad at remembering names, I also have some brain differencees that regularly lead me to mix up peoples' names.  I think, for example, of two of my favorite research students, one with blonde hair, from Minnesota, who also served as a teaching assistant for my software design course; the other with dark hair, from Massachusetts, who brought a wealth of background in education to my research projects.  I can tell you a lot about each student (although I wouldn't without their permission), including hobbies, where they studied abroad, what they are doing now (or at least what they were doing before the pandemic hit), and more.  But I inevitably reversed their names, calling Minnesota Massachusetts and Massachusetts Minnesota.

When I discussed this issue with my family, my sons laughed and said "Dad, you get *our* names backwards, too; we just ignore you when you do so." And I've heard from other students I value highly that I do this and don't always notice.  (The other day, I almost referred to an alum by a different name.)

If I use the wrong name for you, it is not a sign that I do not respect your or that I do not care about you.  It's a deficiency in my processing, and one that I seem unable to fix.  Please accept and understand that disability, just as I will do my best to accept and understand your own differences.

Questions and Answers
---------------------

_This section provides a space for me to answer class-wide questions as they arise._
