;;Name: Sang Hyeong Woo
;;E-Mail: sangwoo@indiana.edu
;;Lab Section: hellyeah
;;Collaboration Statement: I worked alone

;;Question 1.
;;a. Spin-roulette procedure would return a random value between 1 and 5.
(define spin-roulette
  (lambda ()
    (+ 1 (random 5))))

;;b. Pick-ball procedure would pick either red or blue randomly.
(define pick-ball
  (lambda ()
    (if (equal? (random 2) 0)
        (quote red)
        (quote blue))))


;;Question 2. Echo procedure would return the output
;;exactly same whatever the input is.
(define echo
  (lambda (x)
    x))


;;Question 3.
(define toggle
  (lambda (bit)
    (cond
      [(equal? bit 'o) 'l]
      [(equal? bit 'l) 'o]
      [else (quote Error)])))


;;Question 4.
;;a. bit1 and bit 2 cannot be identical in order to ignore the carry.
(define add-bit-no-carry
  (lambda (bit1 bit2)
    (if (equal? bit1 bit2)
        '0
        '1)))

;;b. the carry will be dependant to bit3.
(define add-bit-carry
  (lambda (bit1 bit2 bit3)
    (if (equal? bit3 'o)
        '0
        '1)))


;;Question 5. The procedure will only be true if bit 2 is greater than bit 1.
(define lt-bit?
  (lambda (bit1 bit2)
    (cond
      [(equal? bit1 bit2) #f]
      [(equal? bit1 'l) #f]
      [(equal? bit2 'l) #t]
      [else 'Error])))


;;Question 6. The procedure will be true only if the sum of
;;the square of a and the square of b equals the square of c.
(define pythagorean?
  (lambda (a b c)
    (equal? (+ (* a a) (* b b)) (* c c))))


;;Question 7. Each of the two coordinate values of x and y are less than 1.
(define in-circle?
  (lambda (x y)
    (if (< (+ (* x x) (* y y)) 1) #t #f)))


;;Question 8.
;;a. Smaller procedure would return the smaller one among two values.
(define smaller
  (lambda (x y)
    (if (>= x y) y x)))

;;b. The smallest value among 3 different integers will be returned.
(define smallest
  (lambda (x y z)
    (smaller (smaller x y) z)))


;;Question 9. The median value among the 3 different integers will be returned.
(define median
  (lambda (x y z)
    (if (> x y)
        (if (> z x) x
            (if (> y z) y z)) (if (> z y) y (if (> z x) z x)))))


;;Question 10. The procedure will return the original value if it is already
;;an odd number, and if it is an even number, the procedure will add
;;one to its value
(define make-odd
  (lambda (n)
    (if (equal? (odd? n) #t) n (+ n 1))))


;;Question 11.
;;a. It will return true if one or the other or the both are odd integers.
(define either-odd?
  (lambda (x y)
    (if (integer? x) (if (odd? x) #t (if (integer? y) (if (odd? y) #t #f) #f))
        (if (integer? y) (if (odd? y) #t #f) #f))))


;;b. It will return true if both values are odd integers.
(define both-odd?
  (lambda (x y)
    (if (integer? x) (if (odd? x)
                         (if (integer? y)
                                      (if (odd? y) #t #f) #f) #f) #f)))


;;Question 12. The next number of Collatz sequence will be determined
;;in this procedure.
(define next-collatz
  (lambda (n)
    (cond
      [(equal? (odd? n) #t) (+ (* 3 n) 1)]
      [else (/ n 2)])))