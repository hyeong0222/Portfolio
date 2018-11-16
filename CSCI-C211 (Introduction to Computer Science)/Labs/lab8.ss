;; 5. CODING & DEBUGGING
(define alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(define ouija
  (lambda (ls alphabet)
    (list->string (help ls 0 alphabet))))


(define help
  (lambda (ls position alphabet)
    (cond
      [(null? ls) '()]
      [(eqv? (car ls) 'right) (help (cdr ls) (add1 position) alphabet)]
      [(eqv? (car ls) 'left) (help (cdr ls) (sub1 position) alphabet)]
      [else (cons (safe-string-ref alphabet position)
                                (help (cdr ls) position alphabet))]
      )))


(define safe-string-ref
  (lambda (str index)
    (string-ref str (min (sub1 (string-length str)) (max 0 index)))))
#|
6. SUMMARY
In this lab, we learned differnet built-in functions like 'string->list',
'char-upcase', 'string-ref' and so on. We utilized these built-in function to
create a ouija function which takes a list of upper case alphabet and returns
a new string based on a list of command that contains
'left', 'right' or 'hover'.

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Hellyeah
Sang Hyeong Woo (sangwoo)
Tyler Leeth (tjleeth)
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Define a procedure ouija that takes a list of commands to move the
planchette and returns a string representing the spelled-out message using
left, right, hover functions.
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(define ouija
  (lambda (ls alphabet)
    (list->string (help ls 0 '() alphabet))))


(define help
  (lambda (ls position out alphabet)
    (cond
      [(null? ls) out]
      [(eqv? (car ls) 'right) (help (cdr ls) (add1 position) out alphabet)]
      [(eqv? (car ls) 'left) (help (cdr ls) (sub1 position) out alphabet)]
      [(eqv? (car ls) 'hover)
       (help (cdr ls)
         position
         (cons (safe-string-ref alphabet position) out)
           alphabet)]
     )))

(define safe-string-ref
  (lambda (str index)
    (string-ref str (min (sub1 (string-length str)) (max 0 index)))))
-------------------------------------------------------------------
4. HAND TRACE
~ (ouija '(hover right) alphabet)
== (list->string (help '(hover right) 0 alphabet))
== (list->string (cons 'a (help '(right) 0 alphabet)))
== (list->string (cons 'a (help '() 1 alphabet))
== (list->string (cons #\a  '()))
== (list->string '(#\a))
== (a)
-------------------------------------------------------------------
|#