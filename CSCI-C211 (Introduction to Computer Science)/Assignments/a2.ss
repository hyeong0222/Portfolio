;;Name: Sang Hyeong Woo
;;E-Mail: sangwoo@indiana.edu
;;Lab Section: hellyeah
;;Collaboration Statement: I worked alone

;;Question 1
;;a. to add 3 to the input variable x
(define add3
  (lambda (x)
    (+ x 3)))

;;b. to subtract 3 to the input variable x
(define subtract3
  (lambda (x)
    (- x 3)))

;;Question 2

;;a. return thousands number of the input
(define thousands
  (lambda (x)
    (remainder (quotient x 1000) 10)))

;;b. absolute the input value and multiply by 10
(define ten-magnitude
  (lambda (x)
    (* (abs x) 10)))

;;Question 3

;;a. Squaring the input variable x
(define square
  (lambda (x)
    (* x x)))

;;b. Squaring each input variables x and y, and then add those two values
(define add-squares
  (lambda (x y)
    (+ (* x x)(* y y))))

;;Question 4

;;a. Calculate the length of diagonal of a square by multiplying square root of 2 to an input variable x
(define face-diagonal
  (lambda (x)
    (* x(sqrt 2))))

;;b. Calculate the body-diagonal length using input value a and the face-diagonal length
(define body-diagonal
  (lambda (a)
    (sqrt (+ (* a a)(* (face-diagonal a)(face-diagonal a))))))

;;Question 5

;;a. translate celsius to fahrenheit
(define c->f
  (lambda (c)
    (+ (* (/ 9 5) c) 32)))

;;b. translate fahrenheit to celsius
(define f->c
  (lambda (f)
    (* (- f 32)(/ 5 9))))

;;c. If I continuously repeat the procedure, it will eventually reach around -40.

;;Question 6

;;a. translate yards to inches
(define yd->in
  (lambda (x)
    (* x 36)))

;;b. translate inches to centimeters
(define in->cm
  (lambda (x)
    (* x 2.54)))

;;c. translate yards to centimeters
(define yd->cm
  (lambda (x)
    (in->cm (yd->in x))))

;;Question 7

;;a remove the right-part and giving the output of left-part of a string
(define left-part
  (lambda (str)
    (substring str 0 (quotient (string-length str) 2))))

;;b remove the left-part and giving the output of right-part of a string
(define right-part
  (lambda (str)
    (if (even? (string-length str))
        (substring str (quotient (string-length str) 2) (string-length str))
        (substring str (+ (quotient (string-length str) 2) 1) (string-length str)))))

;;c remove both left and right part of a string, and give the output of the middle character
(define middle
  (lambda (str)
    (if (even? (string-length str))
        (substring str 0 0)
        (substring str (quotient (string-length str) 2) (+ (quotient (string-length str) 2)1)))))

;;Question 8

;;a gives the output of the letter that is located x th in a string
(define char-at
  (lambda (str x)
    (substring str x (+ x 1))))

;;b gives the output of a random character within a string
(define random-char
  (lambda (str)
    (let ([index (random (string-length str))])
      (substring str index (+ index 1)))))

;;c puts two given words by inserting a word "as" in between them
(define make-simile
  (lambda (str1 str2)
    (string-append str1 (string-append " " (string-append "as" (string-append " " (string-append str2)))))))

;;Question 9 make a number by selecing random values in between the range of n and n+p
(define random-in-range
  (lambda (n p)
    (+ (random (+ p 1)) n)))

;;Question 10 make a number by selecing random values in between n-p and n+p
(define jitter
  (lambda (n p)
    (+ n (- (random (+ (* p 2) 1)) p))))