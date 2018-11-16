;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Do not put your name in this file!                         ;;
;; Test your code and SAVE it often!                          ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 4a: u-lt?                                           ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define u-lt?
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) '#f]
      [(null? ls1) '#t]
      [(equal? ls1 ls2) '#f]
      [else (u-lt? (cdr ls1) (cdr ls2))])))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 4b: u-min                                          ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define u-min
  (lambda (ls1 ls2)
    (cond
      [(null? ls1) ls1]
      [(null? ls2) ls2]
      [else (cons (car ls1) (u-min (cdr ls1) (cdr ls2)))])))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 4c: u-pow-two                                       ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define u-multiply
  (lambda (ls1 ls2)
    (cond
      [(null? ls2) '()]
      [else (append ls1 (u-multiply ls1 (cdr ls2)))])))

(define u-pow-two
  (lambda (ls)
    (cond
      [(null? ls) 'l]
      [else (append (u-multiply (cons (car ls) '()) '(l l))
              (u-pow-two (cdr ls)))])))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 5a: single-grain-toss                               ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define single-grain-toss
  (lambda ()
    (+ 1 (random 64))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 5b: grains-toss                                     ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define grains-toss
  (lambda (n)
    (cond
      [(zero? n) '()]
      [else (cons (single-grain-toss) (grains-toss (- n 1)))])))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 5c: empty-square?                                   ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define empty-square?
  (lambda (n ls)
    (cond
      [(null? ls) '#t]
      [(equal? n (car ls)) '#f]
      [else (empty-square? n (cdr ls))])))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 5d: empty-squares                                   ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define es-h
  (lambda (n)
    (cond
      [(> n 64) '()]
      [else (cons n (es-h (+ n 1)))])))

#|
(define empty-squares
  (lambda (ls)
    (cond
      [(equal? (car ls) (car (es-h 1))) (cdr (es-h 1))]
      [(equal? (equal? (car ls) (car (es-h 1)) #f) (empty-squares (cdr ls))]
      [else (cons (car es-h) (empty-squares ls))])))
|#


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 6a: area-trapezoid                                  ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define area-trapezoid
  (lambda (a b h)
    (* (/ (+ a b) 2) h)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 6b: circle-y                                        ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define circle-y
  (lambda (x)
    (sqrt (- 1 (* x x)))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 6c: split-equally                                   ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#|
(define split-equally
  (lambda (n)
    (cond
      [(equal? n 1) '(0 1)]
      [else (cons 0 (cons (
|#



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Problem 6d: area-circle                                     ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define area-circle
  (lambda (n)
    (cond
      [(zero? n) 0])))