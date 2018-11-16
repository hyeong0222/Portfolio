#|
NAME : Sang Hyeong Woo
E-MAIL : sangwoo@indiana.edu
LAB SEC NAME : Hellyeah
COLLABORATION STATEMENT : I got some helps from my friend Jihoon Ban.
;;                        jiban@indiana.edu
|#

;;1.If symbol equals a word in a list, the symbol will appear
;;  after the word in a list
(define double-symbol
  (lambda (symbol ls)
    (cond
      [(null? ls) '()]
      [(equal? symbol (car ls)) (cons (car ls)
                                  (cons symbol
                                    (double-symbol symbol (cdr ls))))]
      [else (cons (car ls) (double-symbol symbol (cdr ls)))])))


;;2. If f value appears more number of times than s value in a list,
;;   s values in a list will be replaced by f values, and vice versa
(define find-replace
  (lambda (f s ls)
    (cond
      [(null? ls) '()]
      [(equal? f (car ls))(cons s (find-replace f s (cdr ls)))]
      [else (cons (car ls) (find-replace f s (cdr ls)))])))


;;3.
;;a. Subtract ls2 from ls1 and represent the result in unary
(define u-subtract
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) ls1]
      [(equal? (car ls1) (car ls2))(u-subtract (cdr ls1) (cdr ls2))]
      [else ls1])))

;;b. If the value represnted in unary in list1 is greater than the value in
;;   list2, it is true
(define u-gte?
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) #t]
      [(null? ls1) #f]
      [else (equal? (car ls1) (car ls2)) (u-gte? (cdr ls1) (cdr ls2))])))


;;4. Add one until you find the symbol in a list
(define index-of
  (lambda (symbol ls)
    (cond
      [(null? ls) 0]
      [(equal? symbol (car ls)) 0]
      [else (+ 1 (index-of symbol (cdr ls)))])))


;;5. If given symbol appears in a list, remove all of them from the list
(define remove-symbol
  (lambda (symbol ls)
    (cond
      [(null? ls) '()]
      [(equal? symbol (car ls)) (remove-symbol symbol (cdr ls))]
      [else (cons (car ls) (remove-symbol symbol (cdr ls)))])))


;;6.
;;a. Compare two lists and pick one that has larger number of c's
(define pick-pile-helper
  (lambda (ls)
    (if (null? ls)
        0
        (+ 1 (pick-pile-helper (cdr ls))))))

(define pick-pile
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2]
      [(< (pick-pile-helper ls1) (pick-pile-helper ls2)) ls2]
      [else ls1])))

;;b. To compare and choose larger between ls1 and ls2
(define u-add
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2 ]
      [else (cons (car ls1) (u-add (cdr ls1) ls2))])))

(define u-add-helper
  (lambda (ls)
    (if (null? ls)
        0
        (+ 1 (u-add-helper (cdr ls))))))

(define pick-tray
  (lambda (ls1 ls2)
    (cond
      [(and (null? (car ls1)) (null? (cadr ls1))) ls2]
      [(< (u-add-helper (u-add (car ls1) (cadr ls1)))
         (u-add-helper (u-add (car ls2) (cadr ls2)))) ls2]
      [else ls1])))


;;7.
;;a. Combining two lists and make as one list
(define append
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls2]
      [else (cons (car ls1) (append (cdr ls1) ls2))])))

;;b. Remove the first element in each lists then appen the lists to create one
;;   list.
(define www
  '((1 the dog chewed the rug)
    (2 on the rug slept the dog)
    (3 the rug rats slept on)))

(define get-words
  (lambda (ls)
    (cdr ls)))

(define all-words
  (lambda (ls)
    (if (null? ls)
        '()
        (append (get-words (car ls)) (all-words (cdr ls))))))


;;8.
;;a. Find out whether s is included in a list
(define member?
  (lambda (s ls)
    (cond
      [(null? ls) #f]
      [(equal? s (car ls)) #t]
      [else (member? s (cdr ls))])))

;;b. Determine which numbers or characters are in both ls1 and ls2
(define intersection
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) '()]
      [(null? ls2) '()]
      [(member? (car ls1) ls2) (cons (car ls1) (intersection (cdr ls1) ls2))]
      [else (intersection (cdr ls1) ls2)])))

;;c. Returns numbers indicating the location of a symbol within the index
(define lookup
  (lambda (symbol index)
    (cond
      [(null? index) '()]
      [(equal? symbol (caar index)) (cdar index)]
      [else (lookup symbol (cdr index))])))

;;d. Returns the location where all of elements in ls1 appear
(define index
  '((a 3) (cat 1 3) (dog 2 3) (mat 1 2) (on 1 2) (sat 1 3) (stood 2 3)
    (the 1 2 3) (while 3)))

(define match-multi-word-query
  (lambda (ls1 ls2)
    (if (null? (cdr ls1))
        (lookup (car ls1) ls2)
        (intersection (lookup (car ls1) ls2)
          (match-multi-word-query (cdr ls1) ls2)))))


;;9.
;;a. Returns #t if the first list is the prefix of the second list
(define prefix?-helper
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) #f]
      [(equal? (car ls1) (car ls2)) #t]
      [else (prefix?-helper ls1 (cdr ls2))])))

(define prefix?
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) #t]
      [(equal? (prefix?-helper ls1 ls2) #t) (prefix? (cdr ls1) ls2)]
      [else #f])))

;;b. As the number intersects between seq1 and seq2 it combines and adds
;;   car seq1 and cdr seq2
(define fuse-fragments
  (lambda (seq1 seq2)
    (cond
      [(null? seq2) '()]
      [(null? seq1) (cons (car seq2) (fuse-fragments seq1 (cdr seq2)))]
      [(prefix? seq1 seq2) (cons (car seq1)
                             (fuse-fragments (cdr seq1) (cdr seq2)))]
      [else (cons (car seq1) (fuse-fragments (cdr seq1) seq2))])))


;;10. Simplify a list by eliminating all the repeating alphabets and listing
;;    them only once
(define unique
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [else (cons (car ls) (remove-symbol (car ls) (unique (cdr ls))))])))

;;11.
;;a. Calculate the distance of a point from the origin
(define distance-to-origin
  (lambda (x y)
    (sqrt (+ (* x x) (* y y)))))

;;b. Randomly give n amount of coordinates that of distance does not exceed 1
(define make-points
  (lambda (n)
    (if (equal? n 0)
        '()
        (cons (cons (random 1.0) (cons (random 1.0) '()))
          (make-points (- n 1))))))

;;c. Analyze a list containing different coordinates and return the number if
;;   the distance is less or equal to 1
(define count-in-circle
  (lambda (ls)
    (cond
      [(null? ls) 0]
      [(<= (distance-to-origin (caar ls) (cadar ls)) 1)
       (+ 1 (count-in-circle (cdr ls)))]
      [else (count-in-circle (cdr ls))])))


;;d.
(define calculate-pi
  (lambda (n)
    (/ (* 4.0 (count-in-circle (make-points n))) n)))

#|
You make n amount of random points that lies within a square of 1x1, and you
count how many of them lies within the circle. The reason you multiply by 4.0
is because only quarter size of an actual circle lies in the square of 1x1. You
divide the whole thing by n in the end to calculate the ratio of how many poitns
lie within the circle.
|#