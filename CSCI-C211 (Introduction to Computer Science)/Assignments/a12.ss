#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked with JiHoon Ban (jiban) and got help during
the office hours
|#

(import (c211 image) (c211 color) (c211 tree))

;;1-1.
;;a. returns the #t if pt1 is to the right of pt2
(define right
  (lambda (pt1 pt2)
    (if (> (car pt1) (car pt2))
        #t
        #f)))

;;b. returns the right-most point
(define right-most
  (lambda (ls)
    (cond
      [(null? ls) ls]
      [(null? (cdr ls)) (car ls)]
      [(> (caar ls) (caadr ls)) (right-most (cons (car ls) (cddr ls)))]
      [else (right-most (cdr ls))])))


;;1-2. returns the number of nodes in tr that are not leaves
(define count-non-leaves
  (lambda (tr)
    (cond
      [(empty-tree? tr) 0]
      [(leaf? tr) 0]
      [else
       (+ (count-non-leaves (left-subtree tr))
         (count-non-leaves (right-subtree tr))
         1)])))


;;1-3. returns a tree similar to tr but every node that has one leaf with value
;;     val in tr has two leaves with value val.
(define two-leaves
  (lambda (tr)
    (cond
      [(empty-tree?) (leaf tr)]
      [else (tree (root-value tr)
              (two-leaves (left-subtree tr))
              (two-leaves (right-subtree tr)))])))


;;2.
;;a. returns the sum of the elements in vec with the indicies in ls
(define sum-index-list
  (lambda (vec ls)
    (cond
      [(null? ls) 0]
      [else (+ (vector-ref vec (car ls)) (sum-index-list vec (cdr ls)))])))

;;b. returns the sum of the elements in vec with the indicies in v
(define sum-index-vector
  (lambda (vec v)
    (define help
      (lambda (vec v index-vec index-v sum)
        (cond
          [(= (vector-length v) index-v) sum]
          [(= (vector-ref v index-v) index-vec)
           (help vec v (add1 index-vec) (add1 index-v)
             (+ (vector-ref vec index-vec) sum))]
          [else (help vec v (add1 index-vec) index-v sum)])))
    (help vec v 0 0 0)))


;;3. returns the number of occurrences of the character char in the string
(define char-count
  (lambda (str char)
    (cond
      [(= (string-length str) 0) 0]
      [(char=? (string-ref str 0) char)
       (add1 (char-count (substring str 1 (string-length str)) char))]
      [else (char-count (substring str 1 (string-length str)) char)])))


;;4. updates v1 to contain the results of applying proc to v1 and v2
(define vector-combine!
  (lambda (v1 proc v2)
    (define help
      (lambda (index v1 proc v2)
        (cond
          [(= (vector-length v1) index) (void)]
          [else (vector-set! v1 index (proc (vector-ref v1 index)
                                        (vector-ref v2 index)))
           (help (add1 index) v1 proc v2)])))
    (help 0 v1 proc v2)))


;;5.
;;a. returns an exact copy of the given image
(define image-copy
  (lambda (img)
    (image-map (lambda (x) x) img)))

;;b. returns the image created by interchanging the rows and columns
(define image-transpose
  (lambda (img)
    (make-image (image-cols img) (image-rows img) (lambda (row col)
                                                    (image-ref img col row)))))

;;c. returns the image that results from cropping the rightmost n columns
(define image-crop
  (lambda (img n)
    (make-image (image-rows img)
      (if (> n (image-cols img)) 0 (- (image-cols img) n))
      (lambda (row col) (image-ref img row col)))))


;;6.
;;a returns the image formed by concatenating the two images from left to right
(define concatenate-h
  (lambda (im1 im2)
    (make-image (image-rows im1) (+ (image-cols im1) (image-cols im2))
      (lambda (r c) (if (< c (image-cols im1))
                        (image-ref im1 r c)
                        (image-ref im2 r (- c (image-cols im1))))))))

;;b. returns the image formed by concatenating the two images from top to bottom
(define concatenate-v
  (lambda (im1 im2)
    (make-image (+ (image-rows im1) (image-rows im2))
      (min (image-cols im1) (image-cols im2))
      (lambda (r c) (if (< r (image-rows im1))
                        (image-ref im1 r c)
                        (image-ref im2 (- r (image-rows im1)) c))))))

;;c. returns a reduced image of the given original image
(define reduce
  (lambda (img)
    (make-image (div (image-rows img) 2) (div (image-cols img) 2)
      (lambda (r c) (image-ref img (* 2 r) (* 2 c))))))

;;d. Concatenating the given image each time to the right top portion
(define image-fractal
  (lambda (img n)
    (let loop ([img img] [n n])
      (if (zero? n) img
          (concatenate-v (concatenate-h (reduce img)
                           (loop (reduce img) (sub1 n)))
            (concatenate-h (reduce img) (reduce img)))))))


;;7.
;;a. returns the sum of the values on the three channels
(define brightness
  (lambda (col)
    (+ (color-ref col 'red) (color-ref col 'green) (color-ref col 'blue))))

;;b. returns the energy of the indicated pixel in a given image
(define energy
  (lambda (img r c)
    (cond
      [(and (zero? r) (zero? c))
       (sqrt (+ (expt (- (* -2 (brightness (image-ref img r (add1 c))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (* -2 (brightness (image-ref img (add1 r) c)))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(and (equal? r (sub1 (image-rows img))) (zero? c))
       (sqrt (+ (expt (- (* -1 (brightness (image-ref img (sub1 r) (add1 c))))
                        (* 2 (brightness (image-ref img r (add1 c))))) 2)
               (expt (+ (* 2 (brightness (image-ref img (sub1 r) c)))
                       (brightness (image-ref img (sub1 r) (add1 c)))) 2)))]
      [(and (zero? r) (equal? c (sub1 (image-cols img))))
       (sqrt (+ (expt (+ (* 2 (brightness (image-ref img r (sub1 c))))
                        (brightness (image-ref img (add1 r) (sub1 c)))) 2)
               (expt (- (* -1 (brightness (image-ref img (add1 r) (sub1 c))))
                       (* 2 (brightness (image-ref img (add1 r) c)))) 2)))]
      [(and (equal? r (sub1 (image-rows img))) (equal? c (sub1
                                                           (image-cols img))))
       (sqrt (+ (expt (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                        (* 2 (brightness (image-ref img r (sub1 c))))) 2)
               (expt (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                       (* 2 (brightness (image-ref img (sub1 r) c)))) 2)))]
      [(zero? r)
       (sqrt (+ (expt (- (- (+ (* 2 (brightness (image-ref img r (sub1 c))))
                              (brightness (image-ref img (add1 r) (sub1 c))))
                           (* 2 (brightness (image-ref img r (add1 c)))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (- (* -1 (brightness (image-ref img (add1 r) (sub1 c))))
                          (* 2 (brightness (image-ref img (add1 r) c))))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(zero? c)
       (sqrt (+ (expt (- (- (* -1 (brightness (image-ref img (sub1 r)
                                                (add1 c))))
                           (* 2 (brightness (image-ref img r (add1 c)))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (- (+ (* 2 (brightness (image-ref img (sub1 r) c)))
                             (brightness (image-ref img (sub1 r) (add1 c))))
                          (* 2 (brightness (image-ref img (add1 r) c))))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(equal? c (sub1 (image-cols img)))
       (sqrt (+ (expt (+ (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                           (* 2 (brightness (image-ref img r (sub1 c)))))
                        (brightness (image-ref img (add1 r) (sub1 c)))) 2)
               (expt (- (- (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                             (* 2 (brightness (image-ref img (sub1 r) c))))
                          (brightness (image-ref img (add1 r) (sub1 c))))
                       (* 2 (brightness (image-ref img (add1 r) c)))) 2)))]
      [(equal? r (sub1 (image-rows img)))
       (sqrt (+ (expt (- (- (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                              (* 2 (brightness (image-ref img r (sub1 c)))))
                           (brightness (image-ref img (sub1 r) (add1 c))))
                        (* 2 (brightness (image-ref img r (add1 c))))) 2)
               (expt (+ (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                          (* 2 (brightness (image-ref img (sub1 r) c))))
                       (brightness (image-ref img (sub1 r) (add1 c)))) 2)))]
      [else (sqrt (+ (expt (- (- (- (+ (+ (brightness (image-ref img
                                                        (sub1 r) (sub1 c)))
                                         (* 2 (brightness (image-ref img r
                                                            (sub1 c)))))
                                      (brightness (image-ref img (add1 r)
                                                    (sub1 c))))
                                   (brightness (image-ref img (sub1 r)
                                                 (add1 c))))
                                (* 2 (brightness (image-ref img r (add1 c)))))
                             (brightness (image-ref img (add1 r) (add1 c)))) 2)
                    (expt (- (- (- (+ (+ (brightness (image-ref img (sub1 r)
                                                       (sub1 c)))
                                        (* 2 (brightness (image-ref img
                                                           (sub1 r) c))))
                                     (brightness (image-ref img (sub1 r)
                                                   (add1 c))))
                                  (brightness (image-ref img (add1 r)
                                                (sub1 c))))
                               (* 2 (brightness (image-ref img (add1 r) c))))
                            (brightness (image-ref img (add1 r)
                                          (add1 c)))) 2)))])))


;;8. mutates the vector by caving out the ith element
(define vector-carve!
  (lambda (vec i)
    (if (= i (- (vector-length vec) 1))
        (vector-set! vec i 0)
        (begin
          (vector-set! vec i (vector-ref vec (+ i 1)))
          (vector-carve! vec (+ i 1))))))


;;9. returns the sum of all pixel energies in the given column
(define column-energy
  (lambda (img c)
    (let loop ([r 0] [sum 0])
      (cond
        [(= r (image-rows img)) sum]
        [else (loop (add1 r) (+ sum (energy img r c)))]))))

#|
;;10. computes the column with minimal energy
(define least-energy-column
  (lambda (img)
    (let loop ([d
                 |#