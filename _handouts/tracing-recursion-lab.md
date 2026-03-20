---
title: Tracing recursive calls from the basic recursion lab
section: handouts
---
# {{ page.title }}

## `func-1a`

Here's the function

```
;;; (func-1a x l) -> ??
;;;   x : any/c
;;;   l : list?
;;; ??
(define func-1a
  (lambda (x l)
    (if (null? l)
        (list x)
        (cons (car l) (func-1a x (cdr l))))))
```

Here's a trace. Here and elsewhere we've permitted the lines to go beyond 80 characters.

```
    (func-1a 9 '(1 8 2))
--> (if (null? '(1 8 2)) (list 9) (cons (car '(1 8 2)) (func-1a 9 (cdr '(1 8 2)))))
--> (if #f (list 9) (cons (car '(1 8 2)) (func-1a 9 (cdr '(1 8 2)))))
--> (cons (car '(1 8 2)) (func-1a 9 (cdr '(1 8 2))))
--> (cons 1 (func-1a 9 (cdr '(1 8 2))))
--> (cons 1 (func-1a 9 '(8 2)))
--> (cons 1 (if (null? '(8 2)) (list 9) (cons (car '(8 2)) (func-1a 9 (cdr '(8 2))))))
--> (cons 1 (if #f (list 9) (cons (car '(8 2)) (func-1a 9 (cdr '(8 2))))))
--> (cons 1 (cons (car '(8 2)) (func-1a 9 (cdr '(8 2)))))
--> (cons 1 (cons 8 (func-1a 9 (cdr '(8 2)))))
--> (cons 1 (cons 8 (func-1a 9 '(2))))
--> (cons 1 (cons 8 (if (null? '(2)) (list 9) (cons (car '(2)) (func-1a 9 (cdr '(2)))))))
--> (cons 1 (cons 8 (if #f (list 9) (cons (car '(2)) (func-1a 9 (cdr '(2)))))))
--> (cons 1 (cons 8 (cons (car '(2)) (func-1a 9 (cdr '(2))))))
--> (cons 1 (cons 8 (cons 2 (func-1a 9 (cdr '(2))))))
--> (cons 1 (cons 8 (cons 2 (func-1a 9 '()))))
--> (cons 1 (cons 8 (cons 2 (if (null? '()) (list 9) (cons (car '()) (func-1a 9 (cdr '())))))))
--> (cons 1 (cons 8 (cons 2 (if #t (list 9) (cons (car '()) (func-1a 9 (cdr '())))))))
--> (cons 1 (cons 8 (cons 2 (list 9))))
--> (cons 1 (cons 8 (cons 2 '(9))))
--> (cons 1 (cons 8 '(2 9)))
--> (cons 1 '(8 2 9))
--> '(1 8 2 9)
```

### 
