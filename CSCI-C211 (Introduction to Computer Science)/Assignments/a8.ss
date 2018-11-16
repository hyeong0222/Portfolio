#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked alone and got help during the office hours
|#

(import (c211 image))
(import (c211 color))

;;1.
;;a. Return #t is unary number in ls2 is representing greater value than ls1
(define u-lt?
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) '#f]
      [(null? ls1) '#t]
      [(equal? ls1 ls2) '#f]
      [else (u-lt? (cdr ls1) (cdr ls2))])))

;;b. Return the list that represents smaller value
(define u-min
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls1]
      [(null? ls2) ls2]
      [else (cons (car ls1) (u-min (cdr ls1) (cdr ls2)))])))

;;c. Takes a list that represents the number two to the power of the number,
;;   and return the resulting value in a unary
(define u-multiply
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) '()]
      [else (append ls1 (u-multiply ls1 (cdr ls2)))])))

(define u-pow-two
  (lambda (ls)
    (cond
      [(null? ls) '(l)]
      [else (u-multiply '(l l) (u-pow-two (cdr ls)))])))

;;d. Returns a random value between 1 and 64
(define single-grain-toss
  (lambda ()
    (+ 1 (random 64))))

;;e. Returns a list of random values that are between 1 and 64
(define grains-toss
  (lambda (n)
    (cond
      [(zero? n) '()]
      [else (cons (single-grain-toss) (grains-toss (- n 1)))])))

;;f. Takes a number n and returns true if n is not in the list
(define empty-square?
  (lambda (n ls)
    (cond
      [(null? ls) '#t]
      [(equal? n (car ls)) '#f]
      [else (empty-square? n (cdr ls))])))

;;g. Returns a list of all values between 1 and 64 excluding the numbers in the
;;   list
(define empty-squares
  (lambda (ls)
    (es-h 1 ls)))

(define es-h
  (lambda (n ls)
    (cond
      [(> n 64) '()]
      [(equal? (empty-square? n ls) '#t) (cons n (es-h (add1 n) ls))]
      [else (es-h (add1 n) ls)])))

;;h. Calculate the area of trapezoid
(define area-trapezoid
  (lambda (a b h)
    (* (/ (+ a b) 2) h)))

;;i. Find the y-coordinate of a point on a unit circle
(define circle-y
  (lambda (x)
    (sqrt (- 1 (* x x)))))

;;j. Returns a partition of (0 1) in the n number of parts
(define se-h
  (lambda (n x ls)
    (cond
      [(equal? n 0) (cons 0 ls)]
      [else (se-h (- n 1) x (cons (/ (* n 1.0) x) ls))])))

(define split-equally
  (lambda (n)
    (se-h n n '())))

;;k. Returns an approximation of Pi value by calculating 4 times by the total
;;   trapezoid areas calculated under a unit circle
(define area-circle-helper
  (lambda (n ls)
    (cond
      [(null? (cdr ls)) 0]
      [else (+ (area-trapezoid (circle-y (car ls)) (circle-y (cadr ls)) (/ 1 n))
              (area-circle-helper n (cdr ls)))])))

(define area-circle
  (lambda (n)
    (* 4 (area-circle-helper n (split-equally n)))))


;;2.
;;a. Takes a list of numbers and returns a new list with squared values
(define squares
  (lambda (ls)
    (map (lambda (x) (* x x)) ls)))

;;b. Returns a list formed by taking the additions of the two numbers
(define pair-additions
  (lambda (ls)
    (map (lambda (x) (+ (car x) (cadr x))) ls)))

;;c. Returns a list consisting only the value elements
(define values-only
  (lambda (ls)
    (map (lambda (x) (cadr x)) ls)))

;;d. Returns a list of powers of two
(define list-pow2
  (lambda (ls)
    (map (lambda (x) (expt 2 x)) ls)))

;;e. Returns the list of the strings without the first three characters of each
;;   words
(define prefix-3
  (lambda (ls)
    (map (lambda (x) (substring x 3 (string-length x))) ls)))


;;3. Apply proc1 to the elements of a list by the probability of p and proc2 by
;;   the rest probability
(define map-ran
  (lambda (proc1 proc2 ls p)
    (map (lambda (x) (if (> (random 1.0) (- 1 p))
                         (proc1 x)
                         (proc2 x))) ls)))


;;4. Takes an image and inverts each color value by subtracting from 255
(define photo-negative
  (lambda (img)
    (image-map
      (lambda (c)
        (color (- 255 (color-ref c 'red))
          (- 255 (color-ref c 'green))
          (- 255 (color-ref c 'blue)))) img)))


;;5. Takes an image and modify the image where each pixel would have a new color
;;   based on the sum of the RGB components in a given pixel
(define obamicon
  (lambda (img)
    (image-map
      (lambda (c)
        (if (>= 181 (+ (color-ref c 'red)
                      (color-ref c 'green)
                      (color-ref c 'blue)))
            (color 0 51 76)
            (if (>= 363 (+ (color-ref c 'red)
                          (color-ref c 'green)
                          (color-ref c 'blue)))
                (color 217 26 33)
                (if (>= 545 (+ (color-ref c 'red)
                              (color-ref c 'green)
                              (color-ref c 'blue)))
                    (color 112 150 158)
                    (color 252 227 166)))))img)))