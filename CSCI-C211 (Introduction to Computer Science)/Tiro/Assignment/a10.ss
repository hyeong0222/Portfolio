#|
Name: Samg Hyeong Woo
Email: sangwoo@indiana.edu
Lab name: Hellyeah
Collaboration Statement: I worked with JiHoon Ban (jiban)
|#

(import (c211 tree))

;;1.
;;a. Returns a partition of 0 and 1 into n number of random parts
(define insert-in-order
  (lambda (x ls)
    (cond
      [(null? ls) (cons x ls)]
      [(< x (car ls)) (cons x ls)]
      [else (cons (car ls) (insert-in-order x (cdr ls)))])))

(define sr-h
  (lambda (n ls)
    (cond
      [(< n 0) ls]
      [else (let ((insert (random 1.0)))
              (sr-h (- n 1) (insert-in-order insert ls)))])))

(define split-randomly
  (lambda (n)
    (if (< n 1)
        '()
        (cons 0 (sr-h (cond
                        [(equal? n 1) -1]
                        [else (- n 2)]) '(1))))))

;;b. calculating the total area given in a circle by random number of tripezoids
(define area-trapezoid
  (lambda (a b h)
    (* (/ (+ a b) 2) h)))

(define circle-y
  (lambda (x)
    (sqrt (- 1 (* x x)))))

(define area-circle-helper
  (lambda (n ls)
    (cond
      [(null? (cdr ls)) 0]
      [else (+ (area-trapezoid (circle-y (car ls)) (circle-y (cadr ls)) (/ 1 n))
              (area-circle-helper n (cdr ls)))])))

(define area-circle-random
  (lambda (n)
    (* 4 (area-circle-helper n (split-randomly n)))))


;;2.
;;a. sums each digits of a number
(define sum-digits
  (lambda (n)
    (if (= n 0)
        0
        (+ (mod n 10) (sum-digits (/ (- n (mod n 10)) 10))))))

;;b. repeat sum-digits until eventually the final result number has only 1 digit
(define sum-digits-one
  (lambda (n)
    (if (equal? (quotient n 10) 0)
        n
        (sum-digits (sum-digits n)))))

;;c. Utilize error-correcting codes technique to check for correctness of the
;;   division
(define nine-test
  (lambda (a b c d)
    (if (= (sum-digits-one a) (sum-digits-one (+ (* b (sum-digits-one c))
                                                (sum-digits-one d))))
        #t
        #f)))


;;3.
;;a. Returns a list after merging two adjacent lists together according to given
;;   relation
(define mas-helper
  (lambda (rel? ls1 ls2)
    (cond
      [(null? ls1) ls2]
      [(null? ls2) ls1]
      [(rel? (car ls1) (car ls2)) (cons (car ls1)
                                    (mas-helper rel? (cdr ls1) ls2))]
      [else (cons (car ls2) (mas-helper rel? ls1 (cdr ls2)))])))

(define merge-adjacent-sequences
  (lambda (rel? ls)
    (cond
      [(null? ls) ls]
      [(null? (cdr ls)) ls]
      [else (cons (mas-helper rel? (car ls) (cadr ls))
              (merge-adjacent-sequences rel? (cddr ls)))])))

;;b. Returns a list created using a relation and Mergesort algorithm
(define group-sorted-sequences
  (lambda (rel? ls)
    (cond
      [(null? (cdr ls)) (cons ls '())]
      [else (let ([gss (group-sorted-sequences rel? (cdr ls))])
              (if (rel? (car ls) (caar gss))
                  (cons (cons (car ls) (car gss)) (cdr gss))
                  (cons (cons (car ls) '()) gss)))])))

(define merge-sort
  (lambda (rel? ls)
    (define m-h
      (lambda (rel? seqs)
        (cond
          [(null? (cdr seqs)) (car seqs)]
          [else (m-h rel? (merge-adjacent-sequences rel? seqs))])))
    (cond
      [(null? ls) ls]
      [else (m-h rel? (group-sorted-sequences rel? ls))])))


;;4. Compare three different sequences using time-it to compare each of
;;   efficiency
(define insert-in-order
  (lambda (proc n ls)
    (define help
      (lambda (ls)
        (cond
          [(null? ls) (cons n ls)]
          [(proc n (car ls)) (cons n ls)]
          [else (cons (car ls) (help (cdr ls)))])))(help ls)))

(define insertion-sort
  (lambda (rel? ls)
    (define is-h
      (lambda (rel? ls acc)
        (cond
          [(null? ls) acc]
          [else (is-h rel? (cdr ls) (insert-in-order rel? (car ls) acc))])))
    (is-h rel? ls '())))

(define time-it
  (lambda (thunk)
    (let ([start (cpu-time)])
      (let ([value (thunk)])
        (- (cpu-time) start)))))

(define random-list
  (lambda (n)
    (cond
      [(= n 0) '()]
      [else (cons (random 1000) (random-list (- n 1)))])))

(define ls1 (random-list 100))
(define ls2 (random-list 1000))
(define ls3 (random-list 5000))
(define ls4 (random-list 10000))

#|
insertion-sort   mergesort   sort
ls1        0              0         0
ls2        47             0         0
ls3        639            31        0
ls4        2527           47        15

After running the test, it is possible to conclude that the built-in-procedure
sort is much more efficient than insertion-sort and mergesort. The possible
reason is becuase those two procedures go through recursion which increases
the time it takes to go through the list of numbers one by one. It is also
clearly visiable that mergesort is more efficient than insertion-sort since
mergesort takes less amount of time than the insertion-sort to run.
|#


;;5.
;;a. Returns the approximation of pi with an error less than E
(define p-h
  (lambda (b answer e)
    (let ([x (/ 1 (+ 1 (* 2 b)))])
      (cond
        [(< x (/ e 4)) answer]
        [else (if (odd? b)
                  (p-h (add1 b) (- answer x) e)
                  (p-h (add1 b) (+ answer x) e))]))))

(define pi-leibniz
  (lambda (e)
    (* 4 (+ 1 (p-h 1.0 0 e)))))

;;b.
#|
# of Trial      Time
    1            0
    2            15
    3            16
    4            16
    5            0

average: 9.2

|#


;;6. Returns #t if the sum of some of subset elements of ls matches the target
;;   sum
(define subset-sum?
  (lambda (ls n)
    (cond
      [(equal? n 0) #t]
      [(null? ls) #f]
      [else (or (subset-sum? (cdr ls) (- n (car ls)))
       (subset-sum? (cdr ls) n))])))


;;7. Returns the number of distinct shortest paths between the indicated square
;;   and the upper left corner
(define count-shortest-paths
  (lambda (x y)
    (cond
      [(and (equal? x 1) (equal? y 1)) 0]
      [(or (equal? x 1) (equal? y 1)) 1]
      [else (+ (count-shortest-paths (- x 1) y)
              (count-shortest-paths x (- y 1)))])))


;;8. Define each given trees
(define tr0 (tree 'a (leaf 5) (empty-tree)))
(define tr1 (tree 4 (empty-tree) (leaf 'b)))
(define tr2 (tree 'd (leaf 'c) (leaf 'e)))
(define tr3 (tree '+ (leaf 7) (tree '- (tree '/ (leaf 'x) (leaf 5)) (leaf 'y))))
(define tr4 (tree 1 (tree 2 (empty-tree)
                      (tree 3 (tree 4 (empty-tree) (leaf 5)) (empty-tree)))
              (tree 2 (tree 3 (empty-tree) (leaf 4)) (empty-tree))))


;;9. Takes a tree and returns the height
(define height
  (lambda (tr)
    (cond
      [(empty-tree? tr) 0]
      [else (if (empty-tree? (left-subtree tr))
                (+ 1 (height (right-subtree tr)))
                (max (+ 1 (height (right-subtree tr)))
                  (+ 1 (height (left-subtree tr)))))])))


;;10. Check if a tree contains a given value of leaf
(define num-tree (tree 7 (tree 5 (leaf 3) (leaf 6))
                   (tree 9 (leaf 8) (empty-tree))))

(define leaf-member?
  (lambda (n tr)
    (cond
      [(empty-tree? tr) #f]
      [(and (equal? n (root-value tr)) (leaf? tr)) #t]
      [else (or (leaf-member? n (left-subtree tr))
                (leaf-member? n (right-subtree tr)))])))


;;11. Returns a list of values along some root to leaf path in the tree
(define random-walk
  (lambda (tr)
    (cond
      [(empty-tree? tr) '()]
      [(empty-tree? (left-subtree tr)) (cons (root-value tr)
                                         (random-walk (right-subtree tr)))]
      [(empty-tree? (right-subtree tr)) (cons (root-value tr)
                                          (random-walk (left-subtree tr)))]
      [(equal? (random 2) 0) (cons (root-value tr)
                               (random-walk (right-subtree tr)))]
      [else (cons (root-value tr)(random-walk (left-subtree tr)))])))


;;12. Takes two trees and compare if the shapes are identical
(define same-shape?
  (lambda (tr1 tr2)
    (cond
      [(and (empty-tree? tr1) (empty-tree? tr2)) #t]
      [(and (empty-tree? tr1) (not (empty-tree? tr2))) #f]
      [(and (empty-tree? tr2) (not (empty-tree? tr1))) #f]
      [else (and (same-shape? (left-subtree tr1) (left-subtree tr2))
                 (same-shape? (right-subtree tr1) (right-subtree tr2)))])))