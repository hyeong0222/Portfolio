;; 5. CODING & DEBUGGING
;; Pad-front takes a number (n), symbol (s), and list (ls)
;; and then adds n s symbols to ls.
(define pad-front
    (lambda (n s ls)
        (cond
            [(= n 1) (cons s ls)]
            [else (cons s (pad-front (- n 1) s ls))]
            )))

;; Decode takes a list of two  element lists and makes one bigger list of
;; sequences from these  sublists. For example, given
;; (decode '((1 a) (5 b) (2 c))) would output  (a b b b b b c c).
(define decode
    (lambda (ls)
        (cond
            [(null? (cdr ls)) (pad-front (caar ls) (cadar ls) '())]
            [else (pad-front (caar ls) (cadar ls) (decode (cdr ls)))]
            )))
#|
6. SUMMARY
We learned how to use recursion function to append a list with other element.
We also learned how to use a helper function, and the shortcut for car and cdr.

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Hellyeah
Sang Hyeong Woo (sangwoo)
Curtis Bitner (cebitner)
Taylor Ball (tarball)
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Pad-front takes a number (n), symbol (s), and list (ls)
and then adds n s symbols to ls.
Decode takes a list of two element lists and makes one bigger list of sequences
from these sublists. For example,
given (decode '((1 a) (5 b) (2 c))) would output (a b b b b b c c).
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define pad-front
    (lambda (n s ls)
        (cond
            [(= n 1) (cons s ls)]
            [else (cons s (pad-front (- n 1) s ls))]
            )))
(define decode
    (lambda (ls)
        (cond
            [(null? (cdr ls)) (pad-front (caar ls) (cadar ls) '())]
            [else (pad-front (caar ls) (cadar ls) (decode (cdr ls)))]
            )))
-------------------------------------------------------------------
4. HAND TRACE
~(pad-front 3 'a '(b c))
== (cons a (pad-front (- 3 1) 'a '(b c)))
== (cons a (pad-front 2 'a '(b c)))
== (cons a (cons a (pad-front (- 2 1) 'a '(b c))))
== (cons a (cons a (pad-front 1 'a '(b c))))
== (cons a (cons a (cons 'a '(b c))))
== (cons a (cons a '(a b c)))
== (cons a '(a a b c))
== (a a a b c)
~(decode '((3 a) (2 b)))
== (pad-front (caar '((3 a) (2 b))) (cadar '((3 a) (2 b)))
     (decode (cdr '((3 a) (2 b)))))
== (pad-front 3 'a (pad-front (caar '((2 b))) (cadar '((2 b)))
                     (decode (cdr '((2 b))))))
== (pad-front 3 'a (pad-front 2 'b '()))
== (pad-front 3 'a (cons 'b (pad-front (- 2 1) 'b '())))
== (pad-front 3 'a (cons 'b (cons 'b '())))
== (pad-front 3 'a (cons 'b '(b)))
== (pad-front 3 'a '(b b))
== (cons 'a (pad-front (- 3 1) 'a '(b b)))
== (cons 'a (cons 'a (pad-front (- 2 1) 'a '(b b))))
== (cons 'a (cons 'a (cons 'a '(b b))))
== (cons 'a (cons 'a '(a b b)))
== (cons 'a '(a a b b))
== (a a a b b)
-------------------------------------------------------------------
|#