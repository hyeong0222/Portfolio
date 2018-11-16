;;Name: Samg Hyeong Woo
;;Email: sangwoo@indiana.edu
;;Lab name: Hellyeah
;;Collaboration Statement: I worked with JiHoon Ban (jiban)

;;1. Find the difference between two integers on a clock in both clockwise and
;;   anti-clockwise direction
(define clock-distances
  (lambda (n m)
    (let ([x (- m n)] [y (- n m)])
      (cond
        [(< n m) (cons x (cons (- 12 x) '()))]
        [else (cons (- 12 y) (cons y '()))]))))


;;2. Takes a string and convert all s's to 5, e's to 3, l's to 1, and o's to 0
(define leet-speak
  (lambda (str)
    (list->string (map (lambda (x) (cond
                                     [(equal? x #\s) #\5]
                                     [(equal? x #\e) #\3]
                                     [(equal? x #\l) #\1]
                                     [(equal? x #\o) #\0]
                                     [else x])) (string->list str)))))


;;3. Takes a message string and encrypt it by switching every regular alphabet
;;   into encrypted alphabet
(define encrypt
  (lambda (message regular encrypted)
    (define e-h
      (lambda (str1 str2 str3)
        (cond
          [(null? str2) str1]
          [(equal? str1 (car str2)) (car str3)]
          [else (e-h str1 (cdr str2) (cdr str3))])))
    (let ([str1 (string->list message)]
          [str2 (string->list regular)]
          [str3 (string->list encrypted)])
      (list->string (map (lambda (x) (e-h x str2 str3)) str1)))))


;;4.
;;a. Takes an integer and determine if it is a prime number
(define prime?
  (lambda (n)
    (define p-h
      (lambda (x y)
        (cond
          [(equal? x y) '#t]
          [(zero? (remainder x y)) '#f]
          [else (p-h x (+ y 1))])))
    (p-h n 2)))

;;b. Takes an integer n and return the first n prime numbers
(define primes
  (lambda (n)
    (define p-h2
      (lambda (x y)
        (cond
          [(zero? x) '()]
          [(prime? y) (cons y (p-h2 (- x 1) (+ y 1)))]
          [else (p-h2 x (+ y 1))])))
    (p-h2 n 2)))


;;5.
;;a. Returns #t if two clock arithmetic numbers are equal
(define cong?
  (lambda (n m clock-size)
    (cond
      [(equal? n m) '#t]
      [(equal? (- m clock-size) n) '#t]
      [else '#f])))

;;b. returns the result of raising the integer base to the power n in a clock
;;   of size clock-size
(define exp-clock
  (lambda (base n clock-size)
    (remainder (expt base n) clock-size)))

;;c. Tests whether a number is prime or not
(define fermat-test
  (lambda (base p)
    (if (equal? base (remainder (expt base p) p))
        '#t
        '#f)))


;;6. Takes a three-element list of sensor data and returns the corresponding
;;   barcode
(define recover-barcode
  (lambda (ls)
    (recover-barcode-h (car ls) (cadr ls) (caddr ls))))

(define recover-barcode-h
  (lambda (ls1 ls2 ls3)
    (cond
      [(and (null? ls1) (null? ls2)) ls3]
      [(equal? (car ls1) (car ls2))
       (cons (car ls1) (recover-barcode-h (cdr ls1) (cdr ls2) (cdr ls3)))]
      [(equal? (car ls2) (car ls3))
       (cons (car ls2) (recover-barcode-h (cdr ls1) (cdr ls2) (cdr ls3)))]
      [else (cons (car ls1) (recover-barcode-h (cdr ls1) (cdr ls2) (cdr ls3)))]
      )))

;;7. Takes a list of bits and add 1 if the existing number of 1's in the list
;;   is odd, and 0 if it is even
(define add-parity-bit
  (lambda (ls)
    (define apb-h
      (lambda (x y)
        (cond
          [(and (null? x) (odd? y)) '(1)]
          [(and (null? x) (even? y)) '(0)]
          [(equal? (car x) 1) (cons (car x) (apb-h (cdr x) (+ y 1)))]
          [else (cons (car x) (apb-h (cdr x) y))])))
    (apb-h ls 0)))


;;8. Takes a relation predicate and two numbers, and returns one of the numbers
;;   based on the given predicate
(define pick-one
  (lambda (rel? x y)
    (if (rel? x y)
        x
        y)))


;;9.
;;a. add a number x into the given list based on the relational predicate given
(define insert-in-order
  (lambda (proc n ls)
    (define help
      (lambda (ls)
        (cond
          [(null? ls) (cons n ls)]
          [(proc n (car ls)) (cons n ls)]
          [else (cons (car ls) (help (cdr ls)))])))(help ls)))

;;b. returns the result of sorting the list according to the given relation
(define insertion-sort
  (lambda (rel? ls)
    (define is-h
      (lambda (rel? ls acc)
        (cond
          [(null? ls) acc]
          [else (is-h rel? (cdr ls) (insert-in-order rel? (car ls) acc))])))
    (is-h rel? ls '())))