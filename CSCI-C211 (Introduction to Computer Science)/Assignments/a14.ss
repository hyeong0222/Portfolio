#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked with JiHoon Ban (jiban) and got help during
                         the office hours
|#


(import (c211 color) (c211 image) (c211 tree) (c211 matrix))

;;1. Change the each pixel in a rectangular subimage of img to black and white
(define on-the-dark-side?
  (lambda (clr)
    (< (+ (color-ref clr 'red)
         (color-ref clr 'green)
         (color-ref clr 'blue)) 400)))

(define b&w-subimage!
  (lambda (img start-row start-col h w)
    (let rloop ([r start-row] [h h])
      (when (not (= h 0))
        (let cloop ([c start-col] [w w])
          (unless (= w 0)
            (begin
              (image-set! img r c (if (on-the-dark-side?
                                        (image-ref img r c))
                                      black
                                      white))
              (cloop (add1 c) (sub1 w)))))
          (rloop (add1 r) (sub1 h))))))


;;2.
;;a. Returns a list representing the remainder of two lists in unary
(define u-lt?
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) #f]
      [(null? ls1) #t]
      [(equal? ls1 ls2) #f]
      [else (u-lt? (cdr ls1) (cdr ls2))])))

(define u-sub
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) ls1]
      [else (u-sub (cdr ls1) (cdr ls2))])))

(define u-rem
  (lambda (ls1 ls2)
    (cond
      [(u-lt? ls1 ls2) ls1]
      [else (u-rem (u-sub ls1 ls2) ls2)])))

;;b. returns the list representing the integer part of the logarithm in base
;;   two of the number ls
(define u-half
  (lambda (ls)
    (cond
      [(null? ls) '()]
      [(equal? (cdr ls) '()) '()]
      [else (cons 'l (u-half (cddr ls)))])))

(define u-log
  (lambda (ls)
    (cond
      [(null? (u-half ls)) '()]
      [else (cons 'l (u-log (u-half ls)))])))

;;c. returns the sub-list that represents the largest in the given list
(define u-max
  (lambda (ls)
    (let loop ([lsx (cdr ls)][acc (car ls)])
      (if (null? lsx)
          acc
          (if (u-lt? acc (car lsx))
              (loop (cdr lsx) (car lsx))
              (loop (cdr lsx) acc))))))


;;3. returns the index in index-vectors at which vec has the largest value
(define index-of-max
  (lambda (vec index-vector)
    (let loop ([i 0] [m '-oo] [i-m -1])
      (if (= i (vector-length index-vector))
          i-m
          (let* ([vr1 (vector-ref index-vector i)]
                 [vr2 (vector-ref vec vr1)])
            (if (or (equal? m '-oo)
                    (> vr2 m)
                    (and (= vr2 m) (< vr1 i-m)))
                (loop (add1 i) vr2 vr1)
                (loop (add1 i) m i-m)))))))


;;4. Takes a matrix and returns its transpose, which rows become cols.
(define transpose
  (lambda (mat)
    (let ([r (matrix-rows mat)] [c (matrix-cols mat)])
      (matrix-generator c r (lambda (r c) (matrix-ref mat c r))))))


;;5,
;;a. Returns a list of the sums of each row in the matrix
(define sum-rows
  (lambda (mat)
    (let ([row (matrix-rows mat)] [col (matrix-cols mat)])
      (let rloop ([r (sub1 row)] [ls '()])
        (if (>= r 0)
            (let cloop ([c 0] [acc 0])
              (if (< c col)
                  (cloop (add1 c) (+ acc (matrix-ref mat r c)))
                  (rloop (sub1 r) (cons acc ls))))
            ls)))))

;;b. Returns a list of the sums of each column in the matrix
(define sum-cols
  (lambda (mat)
    (let ([row (matrix-rows mat)] [col (matrix-cols mat)])
      (let cloop ([c (sub1 col)] [ls '()])
        (if (>= c 0)
            (let rloop ([r 0] [acc 0])
              (if (< r row)
                  (rloop (add1 r) (+ acc (matrix-ref mat r c)))
                  (cloop (sub1 c) (cons acc ls))))
            ls)))))

;;c. returns the sum of each element in the main diagonal of the matrix
(define sum-diag+
  (lambda (mat)
    (let ([row (matrix-rows mat)] [col (matrix-cols mat)])
      (let rloop ([r 0] [acc 0])
        (if (< r row)
            (rloop (add1 r)(+ acc (matrix-ref mat r r)))
            acc)))))

;;d. returns the sum of each element in the non-main diagonal of the matrix
(define sum-diag-
  (lambda (mat)
    (let ([row (matrix-rows mat)] [col (matrix-cols mat)])
      (let rloop ([r 0] [acc 0])
        (if (< r row)
            (rloop (add1 r)(+ acc (matrix-ref mat r (- col 1 r))))
            acc)))))

;;e. returns #t if and only if the matrix is a magic square
(define ms-h
  (lambda (ls)
    (cond
      [(or (null? ls) (null? (cdr ls))) #t]
      [(not (equal? (car ls) (cadr ls))) #f]
      [else (ms-h (cdr ls))])))

(define magic-square?
  (lambda (mat)
    (and (= (matrix-cols mat) (matrix-rows mat))
         (ms-h (sum-rows mat))
         (ms-h (sum-cols mat))
         (equal? (sum-rows mat) (sum-cols mat))
         (equal? (car (sum-rows mat)) (sum-diag+ mat))
         (equal? (car (sum-rows mat)) (sum-diag- mat)))))


;;6.
;;a. returns the tree that results by removing the leaves from tr
(define lose-leaves
  (lambda (tr)
    (cond
      [(empty-tree? tr) (empty-tree)]
      [(leaf? tr) (empty-tree)]
      [else (tree (root-value tr)
              (lose-leaves (left-subtree tr))
                 (lose-leaves (right-subtree tr)))])))

;;b. returns the tree that results by adding 0-valued leaves to tr
(define get-leaves
  (lambda (tr)
    (if (empty-tree? tr)
        (leaf '0)
        (tree (root-value tr)
          (get-leaves (left-subtree tr))
          (get-leaves (right-subtree tr))))))


;;7. returns a quadstring encoding of the image
(define (solid-region? img r c n)
    (let ([clr (image-ref img r c)]
          [rend (+ r (expt 2 n))]
          [cend (+ c (expt 2 n))])
      (let rloop ([r r])
        (or (= r rend)
            (and (let cloop ([c c])
                   (or (= c cend)
                       (and (color-equal?
                              (image-ref img r c) clr)
                            (cloop (+ c 1)))))
                 (rloop (+ r 1)))))))

(define (D n)
  (define ^2 (lambda (x) (* x x)))
  (let* ([a (expt 2 n)]
         [b (/ a 2)]
         [c (^2 b)])
    (make-image a a
      (lambda (x y)
        (if (< (+ (^2 (- x b)) (^2 (- y b))) c)
            black
            white)))))

;;a. returns a quadstring enconding of an image, which returns either 10 or 11
;;   depends on the color at the given pixel, and 0 if the picutre needs to
;;   be splited into 4 regions.
(define image->string
  (lambda (img)
    (if (solid-region? img 0 0 (log (image-rows img) 2))
          (if (color-equal? (image-ref img 0 0) black)
              "10"
              "11")
        (let ([n (inexact->exact (log (image-rows img) 2))])
          (string-append "0"
            (image->string (make-image (expt 2 (sub1 n)) (expt 2 (sub1 n))
                             (lambda (r c) (image-ref img r c))))
            (image->string (make-image (expt 2 (sub1 n)) (expt 2 (sub1 n))
                             (lambda (r c) (image-ref img
                                             r (+ (expt 2 (sub1 n)) c)))))
            (image->string (make-image (expt 2 (sub1 n)) (expt 2 (sub1 n))
                             (lambda (r c) (image-ref img
                                             (+ (expt 2 (sub1 n)) r) c))))
            (image->string (make-image (expt 2 (sub1 n)) (expt 2 (sub1 n))
                             (lambda (r c) (image-ref img
                                             (+ (expt 2 (sub1 n)) r)
                                             (+ (expt 2 (sub1 n)) c))))))))))


;;8. returns a list consisting of the data in the tree along the
;;   longest root to leaf path
(define lp-h
  (lambda (tr)
    (cond
      [(empty-tree? tr) '(0)]
      [else (let ([left (lp-h (left-subtree tr))]
                  [right (lp-h (right-subtree tr))])
              (if (>= (car left) (car right))
                  (cons (add1 (car left)) (cons (root-value tr) (cdr left)))
                  (cons (add1 (car right)) (cons (root-value tr) (cdr right)))))
       ])))


(define longest-path
  (lambda (tr)
    (cdr (lp-h tr))))


;;9. takes an energy matrix, a start column, and a list of displacements, and
;;   returns the cost of the described seam
(define seam-cost
  (lambda (em c ls)
    (let ([rows (matrix-rows em)] [cols (matrix-cols em)])
      (let loop ([row 0] [start c] [col ls])
        (if (not (null? col))
            (+ (matrix-ref em row start)
              (loop (add1 row) (+ start (car col)) (cdr col)))
            (matrix-ref em row start))))))