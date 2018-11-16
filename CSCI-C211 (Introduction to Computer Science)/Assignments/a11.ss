#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked with JiHoon Ban (jiban) and got help during
                         the office hours
|#

(import (c211 image))

;;1. Takes two images and returns the distance between two.
(define img (make-image 2 3 black))
(image-set! img 0 0 red)
(image-set! img 1 2 green)

(define image-distance
  (lambda (img1 img2)
    (define image-distance-helper
      (lambda (imgls1 imgls2)
        (cond
          [(null? imgls1) 0]
          [(color-equal? (car imgls1) (car imgls2))
           (image-distance-helper (cdr imgls1) (cdr imgls2))]
          [else (+ 1 (image-distance-helper (cdr imgls1) (cdr imgls2)))])))
    (image-distance-helper (image->list img1)(image->list img2))))


;;2.
;;a. Takes a predicate and divide the list into one list with two sublists
(define divide
  (lambda (pred? ls)
    (let loop([ls ls] [ls1 '()] [ls2 '()])
      (cond
        [(null? ls) (list ls1 ls2)]
        [(pred? (car ls)) (loop (cdr ls) (cons (car ls) ls1) ls2)]
        [else (loop (cdr ls) ls1 (cons (car ls) ls2))]))))

;;b. Takes two lists and an item and join all three of them
(define join
  (lambda (ls1 goo ls2)
    (let loop([ans (cons goo ls2)] [newls ls1])
      (if (null? newls)
          ans
          (cons (car newls) (loop ans (cdr newls)))))))

;;c. Takes a list and sort it based on the given predicate
(define quicksort
  (lambda (rel? ls)
    (cond
      [(null? ls) ls]
      [(null? (cdr ls)) ls]
      [else (let ([pivot (car ls)])
             (let
                  ([ans (divide (lambda (x) (rel? x pivot)) (cdr ls))])
                  (let
                  ([left (car ans)]
                  [right (cadr ans)])
              (join (quicksort rel? left) pivot (quicksort rel? right)))))])))


;;3.
(define training-data
  '((d (1 8))(d (2 9))(d (8 10))(d (4 2))(r (1 3))(r (2 1))(r (4 8))(r (6 4))
    (d (7 3)) (r (1 5)) (d (1 9)) (d (6 2)) (r (10 9)) (d (7 7))
    (d (5 11)) (r (1 1)) (r (0 9)) (r (12 12)) (r (20 30))))

;;a. Takes two points and returns the sum of the absolute differences on the
;;   coordinate
(define euclidean-distance
  (lambda (p q)
    (sqrt
      (+ (expt (- (car p) (car q)) 2) (expt (- (cadr p) (cadr q)) 2)))))

(define taxicab-distance
  (lambda (p q)
    (+ (abs (- (car p) (car q))) (abs (- (cadr p) (cadr q))))))

;;b. Returns a list of k nearest neighbors to the given house
(define knn
  (lambda (k p func td)
    (list-head (quicksort (lambda (a b)
                            (< (func (cadr a) p) (func (cadr b) p))) td) k)))

;;c. Returns the label that occurs most frequently
(define count
  (lambda (x ls acc)
    (if (null? ls)
        acc
        (if (equal? x (caar ls))
            (count x (cdr ls) (add1 acc))
            (count x (cdr ls) acc)))))

(define majority
  (lambda (func ls)
    (caar (quicksort (lambda (x y) (> (cadr x) (cadr y)))
            (map (lambda (x) (list x (count x func 0))) ls)))))

;;d. Predicts the party affliation of the new house
(define classify
  (lambda (n coordinate proc ls td)
    (majority (knn n coordinate proc td) ls)))


;;4.
;;a. Returns the result of reading teh corresponding image in mnist
(define path "C:/Users/sangwoo/Downloads/mnist")

(define read-digit
  (lambda (path n)
    (read-image (string-append path "/" (number->string n) ".png"))))

;;b. Returns the list with the image number replaced by the corresponding mnist
;;   image
(define read-mnist
  (lambda (path str)
    (map (lambda (x) (cons (car x)(cons (read-digit path (cadr x)) '()))) str)))

(define tiny-set
       (read-mnist
         path
         '((9 10) (0 11) (6 12) (9 13) (0 14) (1 15) (5 16) (9 17) (7 18) (3 19)
           (4 20) (9 21) (6 22) (6 23) (5 24) (4 25) (0 26) (7 27) (4 28) (0 29)
           (1 30))))

;;(define small-set (read-mnist path mnist-training-data-small))

;;c. Returns the result of replacing the images with a list of grayscale values
(define new-case (read-digit path 8235))

(define reduce
  (lambda (ls)
    (map (lambda (x) (list (car x)
                           (map (lambda (clr) (color-ref clr 'red))
                             (image->list (cadr x))))) ls)))

;;(define tiny-set-r (reduce tiny-set))
;;(define small-set-r (reduce small-set))

;;d. Returns the sum of the absolute differences of their values
(define digit-distance
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) 0]
      [else (+ (abs (- (car ls1) (car ls2)))
                   (digit-distance (cdr ls1) (cdr ls2)))])))

;;e. Returns a list of two-element sublists where the first element is the known
;;   label and the second element is the result of the classification
;;(define test-set-r (reduce (read-mnist path
;;                               (list-head mnist-test-data 50))))

(define side-by-side
  (lambda (ls1 ls2)
    (map (lambda (x)
           (list (car x) (classify 3 (cadr x) digit-distance
                           (iota 10) ls2))) ls1)))

;;f. Calcaulates the percentage of matching entries
(define accuracy
  (lambda (ls)
    (define help
      (lambda (ls acc len)
        (cond
          [(null? ls) (* 100.0 (/ acc len))]
          [(equal? (caar ls) (cadar ls)) (help (cdr ls) (add1 acc) (add1 len))]
          [else (help (cdr ls) acc (add1 len))])))
    (help ls 0 0)))

;;g.