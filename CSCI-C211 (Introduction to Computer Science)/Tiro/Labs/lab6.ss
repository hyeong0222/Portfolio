;; 5. CODING & DEBUGGING
(define digits->number
  (lambda (ls)
    (digits-help 0 ls)))
(define digits-help
    (lambda (acc ls)
        (cond
            [(null? ls) acc]
            (else (digits-help (+ (* 10 acc) (car ls)) (cdr ls))))))

(define price-is-right
  (lambda (price guesses)
    (cond
      [(null? guesses) #f]
      [(> (digits->number (car guesses)) price)
       (price-is-right price (cdr guesses))]
      [else (pir-help price (cdr guesses) (digits->number (car guesses)))])))

(define pir-help
  (lambda (price guesses best)
    (cond
      [(null? guesses) best]
      [(> (digits->number (car guesses)) price)
       (pir-help price (cdr guesses) best)]
      [(> (digits->number (car guesses)) best)
       (pir-help price (cdr guesses) (digits->number (car guesses)))]
      [else (pir-help price (cdr guesses) best)])))

#|
6. SUMMARY
I have learned a bit more about using tail recursive procedure's in practice
today.
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab Hellyeah
Sang Hyeong Woo (sangwoo)
Kyle Nealy (knealy)
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
(digit->number)
If there is a list of integers, you append all the numbers to create one integer
(price-is-right)
You compare a given price with a list of different integers and find the closest
one. If a given integer is over the given price, it does not count. If all the
given integers exceed the given price, the function returns #f.
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define digits->number
    (lambda (ls)
        (digits-help 0 ls)))
(define digits-help
    (lambda (acc ls)
        (cond
            [(null? ls) acc]
            (else (digits-help (+ (* 10 acc) (car ls)) (cdr ls))))))

(define price-is-right
    (lambda (num ls)
        (price-is-right-help 0 num ls)))
(define price-is-right-help
    (lambda (acc num ls)
        (cond
            [(null? ls) #f]
            [(> (car (car ls)) acc) (price-is-right-help (+ acc (car (car ls))
                                                           num (cdr ls))]
            [(<= (car (car ls)) num)
             (price-is-right-help (+ acc (car (car ls))) num (cdr ls))]
            (else (price-is-right-help acc num (cdr ls))))))


-------------------------------------------------------------------
4. HAND TRACE
~ (digits->number '(1 2 5))
== (digits-help 0 '(1 2 5))
== (digits-help (+ (* 10 0) 1) '(2 5))
== (digits-help (+ 0 1) '(2 5))
== (digits-help 1 '(2 5))
== (digits-help (+ (* 10 1) 2) '(5))
== (digits-help (+ 10 2) '(5))
== (digits-help 12 '(5))
== (digits-help (+ (*10 12) 5) '())
== (digits-help (+ 120 5) '())
== (digits-help 125 '())
== 125

~ (price-is-right 100 '((1 0 0)(1 0 0)(1 0 1)(9 9)))
== (> 100 100)
== (#f)
== (pir-help 100 '((1 0 0) (1 0 1) (9 9)) 100)
== (pir-help 100 ((1 0 0) (1 0 1) (9 9)) 100)
== (pir-help 100 ((9 9)) 100)
== (pir-help 100 () 100)
== 100

-------------------------------------------------------------------
|#