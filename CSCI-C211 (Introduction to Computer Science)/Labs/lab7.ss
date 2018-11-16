;; 5. CODING & DEBUGGING
;; Complete Steps 1 through 4 below with your partner and then replace
;; this text with a comment generated from Step 2 of your
;; brainstorming session.  Paste your code draft from Step 3 into the
;; space below. Use the IDE to debug your code here. Do not make *any*
;; changes to your original draft code below in Step 3. Just leave it
;; alone, even if there are mistakes! We mean it. Really.
(define nest
  (lambda (ls)
    (map (lambda (x) (list x)) ls)))

(define unnest
    (lambda (ls)
        (map (lambda (x) (car x)) ls)))

(define reciprocals
  (lambda (ls)
    (map (lambda (x) (if (equal? 0 x)
                         #f
                         (/ 1 x))) ls)))

(define flip-bits
  (lambda (ls)
    (map (lambda (x) (if (equal? 0 x)
                         1
                         0)) ls)))

(define swap
 (lambda (a b ls)
  (map (lambda (x) (cond ((equal? x a) b)
                         ((equal? x b) a)
                          (else x))) ls)))

(define splatter
  (lambda (img)
    (image-map (lambda (clr)
                 (color (random 256) (random 256) (random 256))) img)))

(define invert
  (lambda (img)
    (image-map (lambda (clr) (color (- 255 (color-ref clr 'red))
                               (- 255 (color-ref clr 'green))
                               (- 255 (color-ref clr 'blue)))) img)))

(define grayscale
  (lambda (img)
    (image-map (lambda (clr)(color
          (quotient (+ (color-ref clr 'red)
               (color-ref clr 'green)
               (color-ref clr 'blue)) 3)
          (quotient (+ (color-ref clr 'red)
               (color-ref clr 'green)
               (color-ref clr 'blue)) 3)
          (quotient (+ (color-ref clr 'red)
               (color-ref clr 'green)
               (color-ref clr 'blue)) 3))) img)))

#|
6. SUMMARY
In this class, I have learned how map function shortens the usual scheme
function. Also, I learned how images on any computer is formed with many
numbers of pixels.


7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab Hellyeah
Sang Hyeong Woo (sangwoo)
Yueyang Wu (yueywu)
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Part I: map
a) nest: nest a list into each list
b) unnest: take a list and unlist each element in it
c) reciprocals: modify a list to turn all the positive integers
   into a reciprocal, and return 0 as #f
d) flip-bits: change 0's to 1's and 1's to 0's
e) swap:  swap 2 elements in a list
Part II: image-map
a) splatter: takes an image and replaces every pixel with a random color
b) invert: takes an image and inverts all three of the channels
c) grayscale: takes an image and averages all three of the channels and
   then sets each channel to that average
-------------------------------------------------------------------
3. DRAFT SOLUTION
Part 1: map
a. nest:
(define nest
  (lambda (ls)
    (map (lambda (ls) list) ls)))
b. unnest:
(define unnest
    (lambda (ls)
        (map (lambda (ls) (car ls)) (cdr ls))))
c. reciprocals
(define reciprocals
    (lambda (ls)
        (map (lambda (ls) (/ 1 n)) ls)))
d. flip-bits
(define flip-bits
    (lambda (ls)
        (map (lambda x) (if (equal? x 0) 0 1))))

e.swap
(define swap
 (lambda (a b ls)
  (map (lambda (a b ls)
Part II: image-map
a. splatter
(define splatter
    (lambda (img)
        (color (random 256) (random 256) (random 256))))

b. invert
(define invert-helper
    (lambda (n)
        (- 255 n)))
(define invert
    (lambda (x y z)
        (invert-helper x y z)))

c. grayscale
(define grayscale
    (lambda (x y z)
        (round (/ (+ (+ x y) z) 3))))

-------------------------------------------------------------------
4. HAND TRACE
<Choose a non-trivial, medium-size test case from (2) and write out a
detailed hand trace of the application evaluation.>

(define nest
  (lambda (ls)
    (map (lambda (x) (list x)) ls)))

~ (nest '(x y z))
    x -> (lambda (x) (list x)) ls)))
    (list x)
    (x)
    y -> (lambda (y) (list y)) ls)))
    (list y)
    (y)
    z -> (lambda (z) (list z)) ls)))
    (list z)
    (z)
    ((x) (y) (z))
-------------------------------------------------------------------
|#