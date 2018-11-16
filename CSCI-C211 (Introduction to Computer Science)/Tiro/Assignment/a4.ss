#|
NAME : Sang Hyeong Woo
E-MAIL : sangwoo@indiana.edu
LAB SEC NAME : Hellyeah
COLLABORATION STATEMENT : I worked alone.
|#

;;1.
;;a. Switch the place of the first and the third elements in a list
(define swap1-3
  (lambda (ls)
    (cons (car (cdr (cdr ls))) (cons (car (cdr ls)) (cons (car ls) '())))))

;;b. Bring the first element in the list to the last
(define rotate
  (lambda (ls)
    (cons (car (cdr ls)) (cons (car (cdr (cdr ls))) (cons (car ls) '())))))

;;c.Find (a b c), (a c b), (b c a), (b a c), (c a b), and (c b a)
;;(swap1-3 (swap1-3 '(a b c)))
;;==(a b c)
;;(swap1-3 (rotate '(a b c)))
;;==(a c b)
;;(rotate '(a b c))
;;==(b c a)
;;(swap1-3 (rotate (rotate '(a b c))))
;;==(b a c)
;;(rotate (rotate '(a b c)))
;;==(c a b)
;;(swap1-3 '(a b c))
;;==(c b a)

;;2.
;;ls1 Find (1 2)
(define ls1 (cons 1 (cons 2 '())))
;;ls2 Find (3 4 5)
(define ls2 (cons 3 (cons 4 (cons 5 '()))))
;;ls3 Find ((6) (7) (8))
(define ls3 (cons (cons 6 '()) (cons (cons 7 '()) (cons (cons 8 '()) '()))))
;;ls4 Find ((9 10) 11)
(define ls4 (cons (cons 9 (cons 10 '())) (cons 11 '())))
;;ls5 Find (12 (13 14))
(define ls5 (cons 12 (cons (cons 13 (cons 14 '())) '())))
;;ls6 Find (15 (16 (17)))
(define ls6 (cons 15 (cons (cons 16 (cons (cons 17 '()) '())) '())))
;;ls7 Find (((18) 19) 20)
(define ls7 (cons (cons (cons 18 '()) (cons 19 '()))(cons 20 '())))
;;ls8 (a b c d e f)
(define ls8 (cons 'a (cons 'b (cons 'c (cons 'd (cons 'e (cons 'f '())))))))
;;ls9 ((((g))))
(define ls9 (cons (cons (cons (cons 'g '()) '()) '()) '()))
;;ls10 ((h i) (j k) (m n))
(define ls10 (cons (cons 'h (cons 'i '()))
               (cons (cons 'j (cons 'k '()))
                 (cons (cons 'm (cons 'n '())) '()))))

;;3.
;;a. Put two words and make a list
(define couple
  (lambda (a b)
    (cons a (cons b '()))))

;;b. Role two dices and give 2 random numbers
(define two-dice
  (lambda ()
    (couple (+ 1 (random 6)) (+ 1 (random 6)))))

;;4. Dividing the given element into 3 equal length parts
(define trisect
  (lambda (ls)
     (cons (cons (car ls) (cons (/ (+ (* 2 (car ls)) (cadr ls)) 3) '()))
       (cons (cons (/ (+ (* 2 (car ls)) (cadr ls)) 3)
               (cons (/ (+ (car ls) (* 2 (cadr ls))) 3) '()))
         (cons (cons (/ (+ (car ls) (* 2 (cadr ls))) 3)
                 (cdr ls)) '())))))

;;5. Count how many minutes it takes to reach 10 min per km
(define walking-point
  (lambda (a)
    (if (>= a 10)
        0
        (+ 1 (walking-point (* 1.02 a))))))

;;6.
;;a. Count the number of elements in a list and convert them into unary system
(define u-count
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [else (cons (car (cons 'l (cons (car ls) '()))) (u-count (cdr ls)))])))

;;b. Add one extra unary to a given unary list
(define u-add-one
  (lambda (ls)
    (cond
      [(null? ls) '(l)]
      [else (cons 'l (cons (car (cons 'l (cons (car ls) '())))
                       (u-count (cdr ls))))])))

;;c. Add two lists that contain unary numbers
(define u-add
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2 ]
      [else (cons (car ls1) (u-add (cdr ls1) ls2))])))

;;7. For every 5 unary numbers, you convert to the symbol V
(define take-five
  (lambda (ls)
    (cond
      [(< (length ls) 5) ls]
      [(null? (cdr (cdr (cdr (cdr (cdr ls)))))) (cons 'v '())]
      [else (cons 'v (take-five (cdr (cdr (cdr (cdr (cdr ls)))))))])))

;;8. Determine if a list contains only zeroes
(define all-zeroes?
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(equal? (car ls) 'l) '#f]
      [(equal? (car ls) 'o) '#t]
      [else (all-zeroes? (cdr ls))])))

;;9.
;;a. Return the page number listed in a list
(define get-page-number
  (lambda (ls)
    (car ls)))

;;b. Return all the words listed in a list
(define get-words
  (lambda (ls)
    (cdr ls)))

;;c. Combine multiple pages to create one list that shows all words
(define page1 '(1 the dog chewed the rug))
(define page2 '(2 on the rug slept the dog))
(define page3 '(3 the rug rats slept on))
(define make-simple-web
  (lambda (page1 page2 page3)
    (cons page1 (cons page2 (cons page3 '())))))

;;d. Return the number of all the pages used in a World Wide Web
(define all-pages
  (lambda (ls)
    (if (null? ls)
        '()
        (cons (car (car ls)) (all-pages (cdr ls))))))

;;10.
;;a. Compare the first two elements of a list and remove the larger one
(define crunch-front
  (lambda (ls)
    (cond
      [(null? (cdr ls)) '()]
      [(< (car ls) (car (cdr ls))) (cons (car ls)(cdr (cdr ls)))]
      [else (cdr ls)])))

;;b. Find the minimum number listed in a list
(define find-min
  (lambda (ls)
    (if (equal? (length ls) 1)
        ls
        (find-min (crunch-front ls)))))

;;11.
;;a. Find out which hole the marble will fall into when it is dropped from two
;;   different locations
(define drop-marble
  (lambda (start ls)
    (if (equal? start 'a)
        (if (equal? (car ls) 'left)
            'c
            (if (equal? (cadr ls) 'left)
                'c
                'd))
        (if (equal? start 'b)
            (if (equal? (car (cdr (cdr ls))) 'right)
                'd
                (if (equal? (car (cdr ls)) 'right)
                    'd
                    'c))))))

;;b. Use the list of marble-dropping-points to determine the list of outcomes
;;   you would get
(define next-levers
  (lambda (chute levers)
    (if (equal? chute 'a)
        (if (equal? (car levers) 'left)
            (list 'right (cadr levers) (caddr levers))
            (if (equal? (cadr levers) 'left)
                (list 'left 'right (caddr levers))
                (list 'left 'left (caddr levers))))
        (if (equal? (caddr levers) 'right)
            (list (car levers) (cadr levers) 'left)
            (if (equal? (cadr levers) 'left)
                (list (car levers) 'right 'right)
                (list (car levers) 'left 'right))))))

(define play-game
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) '()]
      [else (cons (drop-marble (car ls1) ls2)
              (play-game (cdr ls1) (next-levers (car ls1)ls2)))])))