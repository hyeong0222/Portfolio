#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked with one of classmate (Jihoon Ban).
                         jiban@indiana.edu
|#


;;1. Replace all "molehill" in a list to "mountain" using map function
(define molehill->mountain
  (lambda (ls)
    (map (lambda (x)
           (if (equal? x 'molehill) 'mountain x)) ls)))


;;2. Build a new list that has same length as the original list but instead
;;   replace all the elements by "duck" and the last element by "goose"
(define duck-duck-goose
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(null? (cdr ls)) (cons 'goose '())]
      [else (cons (car (cons 'duck (car ls))) (duck-duck-goose (cdr ls)))])))


;;3.
;;a. Takes a list of unary numbers and return the list of unary that represents
;;   twice more than the original
(define u-double
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [else (cons (car ls) (cons (car ls) (u-double (cdr ls))))])))

;;b. Takes two unary lists and multiply representing numbers and return the
;;   result as the unary number system
(define u-multiply
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) '()]
      [else (append ls1 (u-multiply ls1 (cdr ls2)))])))

;;c. Takes a list of unary number system, divide the number by two and then
;;   represent the result in a unary system
(define u-half
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(equal? (cdr ls) '()) '()]
      [else (cons 'l (u-half (cddr ls)))])))

;;d. Takes two lists and get the quotient of the unary lists, and then represent
;;   the result in a unary system
(define u-quotient
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2]
      [(null? ls2) ls1]
      [else (uq-help ls1 ls2 '())])))
(define u-subtract
  (lambda (ls1 ls2)
    (if (null? ls2)
        ls1
        (if (equal? (car ls1) (car ls2))
            (u-subtract (cdr ls1) (cdr ls2))
            ls1))))
(define uq-help
  (lambda (ls1 ls2 count)
    (cond
      [(null? ls1) count]
      [else (uq-help (u-subtract ls1 ls2) ls2 (cons 'l count))])))

;;e. Calculate the differences between two unary lists and represent them in
;;   a new list with a unary system
(define u-abs-dif
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2]
      [(null? ls2) ls1]
      [else (u-abs-dif (cdr ls1) (cdr ls2))])))


;;4.
;;a. Find the midpoint coordinate between two given coordinates
(define midpoint
  (lambda (ls1 ls2)
    (cons (/ (+ (+ 0.0 (car ls1)) (+ 0.0 (car ls2))) 2)
      (cons (/ (+ (+ 0.0 (cadr ls1)) (+ 0.0 (cadr ls2))) 2) '()))))

;;b. Find the midpoint of each side of triangles
(define mid-triangle
  (lambda (ls1 ls2 ls3)
    (cons (midpoint ls1 ls2) (cons (midpoint ls1 ls3)
                               (cons (midpoint ls2 ls3) '())))))

;;c. Repeat the mid-triangle function n amount of times
(define mid-n-triangle
  (lambda (ls n)
    (cond
      [(zero? n) ls]
      [else (mid-n-triangle
              (mid-triangle (car ls) (cadr ls) (caddr ls)) (- n 1))])))


;;5.
;;a. List of random outcomes from tossing a dice n number of times
(define die-toss
  (lambda (n)
    (cond
      [(zero? n) '()]
      [else (cons (+ 1 (random 6)) (die-toss (- n 1)))])))


;;b. Check to see how many times the element n appears in the list ls
(define count-face
  (lambda (ls n)
    (cond
      [(null? ls) 0]
      [(equal? (car ls) n) (+ 1 (count-face (cdr ls) n))]
      [else (+ 0 (count-face (cdr ls) n))])))


;;6. Create a list from 1 to n and apply the procedure proc to each elements
(define first-n-numbers
  (lambda (n proc)
    (cond
      [(equal? n 0) '()]
      [else (append (first-n-numbers (- n 1) proc) (cons (proc n) '()))])))


;;7. Determine if the first list is the suffix of the second list
(define suffix?-r
  (lambda (ls1 ls2)
    (map (lambda (x y) (if (> y x)
                           #t
                           #f)) ls1 ls2)))
(define suffix?
  (lambda (ls1 ls2)
    (if (null? ls1)
        #t
        (if (< (s-h ls1) (s-h ls2))
            #t
            #f))))
(define s-h
  (lambda (ls)
    (if (null? ls)
        0
        (add1 (s-h (cdr ls))))))


;;8. List all the possible addition that would return the result of n
(define addend-h
  (lambda (n count)
    (if (equal? n count)
        '()
        (cons (list count (- n count)) (addend-h n (add1 count))))))
(define addends
  (lambda (n)
    (addend-h n 1)))


;;9.
;;a. Check to see if any of the elements in a list is equal to the predicate
;;   pred
(define any?
  (lambda (pred ls)
    (cond
      [(null? ls) '#f]
      [(pred (car ls)) '#t]
      [else (any? pred (cdr ls))])))

;;b. Return #t if there are at least two unequal items in a list
(define diverse?
  (lambda (ls)
    (cond
      [(null? ls) '#f]
      [(null? (cdr ls)) '#f]
      [(equal? (car ls) (cadr ls)) (diverse? (cddr ls))]
      [else '#t])))


;;10.
;;a. find the rightmost element in a list that matches the predicate, and return
;;   its index
(define find-last
  (lambda (proc ls)
   (if (any? proc ls)
       (find-last1-help proc ls 0 0)
       #f)))

(define find-last1-help
  (lambda (proc ls index count)
    (cond
      [(null? ls) count]
      [(proc (car ls)) (find-last1-help proc (cdr ls) (add1 index) index)]
       (else (find-last1-help proc (cdr ls) (add1 index) count)))))

;;b. Find the index of the rightmost "straw" in a list
(define the-last-straw
  (lambda (ls)
    (find-last (lambda (x) (if (equal? x 'straw)
                               #t
                               #f))
      ls)))