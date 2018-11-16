;; 5. CODING & DEBUGGING
;; Complete Steps 1 through 4 below with your partner and then replace
;; this text with a comment generated from Step 2 of your
;; brainstorming session.  Paste your code draft from Step 3 into the
;; space below. Use the IDE to debug your code here. Do not make *any*
;; changes to your original draft code below in Step 3. Just leave it
;; alone, even if there are mistakes! We mean it. Really.
(define every-other-one
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(null? (cdr ls)) (cons (car ls) '())]
      [else (cons (car ls) (every-other-one (cdr (cdr ls))))])))

(define likenate
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(null? (cdr ls)) (cons (car ls) '())]
      [else
      (cons (car ls) (cons 'like (likenate (cdr ls))))])))
#|
6. SUMMARY
We learned how to use recursion, and how repeating of cdr could lead to an error without a proper condition listed.
7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab 4
<Hellyeah>
Sang Hyeong Woo - sangwoo
Bryan Galle bgalle
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
every-other-one: Takes a list and returns the list with every other element (starting with the first one) removed
likenate: Takes a list and returns the list with the symbol like inserted between each pair of adjacent elements in the given  list
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define every-other-one
    (lambda (ls)
        (cond
            [(null? ls) '()]
            [else ((car ls) (every-other-one (car (cdr (cdr ls)))))])))


(define likenate
    (lambda (ls)
        (cond
            [(null? ls) '()]
            [else (cons (car ls) (" like ") (likenate (car (ls)))])))
-------------------------------------------------------------------
4. HAND TRACE
(every-other-one '(lab today is really cool))
== (cons lab (every-other-one '(is really cool)))
== (cons lab (cons is)(every-other-one '(really cool)))
== (cons lab (cons is (every-other-one '(really cool))))
== (cons lab (cons is (cons cool (every-other-one '()))))
== (cons lab (cons is (cons cool '())))
== lab is cool
(likenate '(here be dragons))
==(cons (car ls) (cons 'like (likenate (cdr '(here be dragons)))))
==(cons 'here (cons 'like (likenate ('(be dragons)))))
==(cons 'here 'like (cons 'like (likenate ('(be dragons)))))
==(cons 'here 'like 'be (cons 'like (likenate ('(dragons)))))
==(cons 'here 'like 'be 'like (cons 'like (likenate ('(dragons)))))
==(cons 'here 'like 'be 'like 'dragons (cons 'like '()))
==(cons 'here 'like 'be 'like 'dragons '())
==(here like be like dragons)
-------------------------------------------------------------------
|#