#|
NAME : Sang Hyeong Woo
E-MAIL : sangwoo@indiana.edu
LAB SEC NAME : Hellyeah
COLLABORATION STATEMENT : I worked alone and went to an office hour
|#

;;1.
;;a. add two values you get from rolling two dices
(define two-dice-add
  (lambda ()
    (+ (+ (random 6) 1) (+ (random 6) 1))))

;;b. determine if a list is containing even number of unary
(define u-even?
  (lambda (ls)
    (cond
      [(null? ls) #t]
      [(null? (cdr ls)) #f]
      [else (u-even? (cddr ls))])))

;;c. determine if a list is containing odd number of unary
(define u-odd?
  (lambda (ls)
    (cond
      [(null? ls) #f]
      [(null? (cdr ls)) #t]
      [else (u-odd? (cddr ls))])))


;;2.
;;a. Combine two lists into one list, and make sure the first element of the
;;   left list comes beforehand of the first element of the right list, and the
;;   second element of the list comes afterwards and then the second element of
;;   the right element, and so on.
(define interleave
  (lambda (left-ls right-ls)
    (cond
      [(null? left-ls) right-ls]
      [else (equal? (equal? (car left-ls) (car right-ls)) #f)
       (append (cons (car left-ls) (cons (car right-ls) '()))
         (interleave (cdr left-ls) (cdr right-ls)))])))

;;b. Create a list that contains the cubes of all the integers between 0 and n
(define cubes
  (lambda (n)
    (cond
      [(equal? n 0) '(0)]
      [else (append (cubes (- n 1)) (cons (* (* n n) n) '()))])))

;;c. Create a list containing the inverse of all the integers between 0 and n
(define inverses
  (lambda (n)
    (cond
      [(equal? n 1) '(1)]
      [else (append (inverses (- n 1)) (cons (/ 1 n) '()))])))


;;3. You trim elements in a list if an element is equal to the given procedure
;;   until you encounter something that is different to the given procedure.
(define trim-when
  (lambda (procedure ls)
    (if (null? ls)
        '()
        (if (equal? (procedure (car ls)) #t)
            (trim-when procedure (cdr ls))
            ls))))


;;4.
;;a. List an adjacency list of the given graph
(define www '((a b c d) (b e) (c e f) (d f h) (e f) (f g h) (g h i j) (h o)
              (i j) (j h k l m n) (k o) (l o) (m o) (n o) (o p) (p a)))

;;b. Lookup an element of an index if given symbol matches with the symbol
;;   of an index
(define lookup
  (lambda (symbol index)
    (cond
      [(null? index) '()]
      [(equal? symbol (caar index)) (cdar index)]
      [else (lookup symbol (cdr index))])))

;;c. Take a digraph and returns the list of vertices in the graph
(define get-vertices
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [else (cons (caar ls) (get-vertices (cdr ls)))])))

;;d. Take a digraph and returns the number of vertices in the graph
(define num-vertices
  (lambda (ls)
    (cond
      [(null? ls) 0]
      [else (+ 1 (num-vertices (cdr ls)))])))


;;5.
;;a. Take a vertex and a digraph, and return the next page that is randomly
;;   visited. 15% of time the surfer will return a random page, and 85% of times
;;   it will return an adjacent page.
(define index-maker
  (lambda (ls n)
    (cond
      [(null? ls) ls]
      [else (cons (list n (car (get-vertices ls)))
              (index-maker (cdr ls) (add1 n)))])))

(define next-page-randhelp
  (lambda (ls)
    (car (lookup (random (num-vertices ls)) (index-maker ls 0)))))

(define single-list-index
  (lambda (ls n)
    (cond
     [(null? ls) '()]
     [else
      (cons (list n (car ls)) (single-list-index (cdr ls) (add1 n)))])))

(define next-page
  (lambda (s ls)
    (cond
      [(null? ls) ls]
      [(<= (random 100) 15) (next-page-randhelp ls)]
      [else
      (car (lookup (random (length (lookup s ls)))
             (single-list-index (lookup s ls) 0)))])))

;;b. Take a web graph and a non-negative integer of a length of the path, and
;;   return a list of pages visited.
(define random-surfer-helper
  (lambda (ls n acc)
    (cond
      [(zero? n) acc]
      [else
       (random-surfer-helper ls (sub1 n)
         (cons (next-page (next-page-randhelp ls) ls) acc))])))

(define random-surfer
  (lambda (ls n)
    (random-surfer-helper ls n '())))


;;6.
;;a. Takes web graph and returns a list of two-element sublists that each
;;   contains the value of 0
(define make-counters
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [else (cons (cons (caar ls) (cons 0 '())) (make-counters (cdr ls)))])))

;;b. Takes a vertex and a list of visit counters, and returns the list of
;;   counters that results from incrementing the counter associated with the
;;   given vertex's page.
(define visit-page
  (lambda (n ls)
    (cond
      [(null? ls) '()]
      [(equal? n (caar ls))
       (cons (list n (+ (cadar ls) 1)) (visit-page n (cdr ls)))]
      [else (cons (car ls) (visit-page n (cdr ls)))])))

;;c. Take a web graph and a non-negative integer n, and run the random surfer to
;;   construct a path of length n and count the web pages visited.
(define get-raw-authorities-helper
  (lambda (ls n acc)
    (cond
      [(zero? n) acc]
      [else
        (get-raw-authorities-helper ls (sub1 n)
          (visit-page (car (random-surfer ls 1)) acc))])))

(define get-raw-authorities
  (lambda (ls n)
    (get-raw-authorities-helper ls n (make-counters ls))))

;;d. Take a web graph and a non-negative integer n, run the random surfer
;;   algorithm to construct a path of length n, and return the result of
;;   authority values in percentage.
(define get-authorities
  (lambda (ls n)
    (cond
      [(null? ls) '()]
      [else (cons (list (caar ls)
                        (round (* 100 (/
                                        (cadar (get-raw-authorities ls n))n))))
              (get-authorities (cdr ls) n))])))