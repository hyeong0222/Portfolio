;; 5. CODING & DEBUGGING
;; Complete Steps 1 through 4 below with your partner and then replace
;; this text with a comment generated from Step 2 of your
;; brainstorming session.  Paste your code draft from Step 3 into the
;; space below. Use the IDE to debug your code here. Do not make *any*
;; changes to your original draft code below in Step 3. Just leave it
;; alone, even if there are mistakes! We mean it. Really.

(define percent->grade
    (lambda (grade)
        (cond
            [(>= grade 90) "A"]
            [(>= grade 80) "B"]
            [(>= grade 70) "C"]
            [(>= grade 60) "D"]
            [else "F"])))
(define grade-advice
    (lambda (grade)
        (cond
            [(equal? grade "A") "Outstanding!"]
            [(equal? grade "B") "Nice job!"]
            [(equal? grade "C") "With a little studying, you can master the material."]
            [(equal? grade "D") "You still have a chance to improve next exam"]
            [else "Better luck next time!"])))
(define letter-grade-advice
    (lambda (grade)
        (string-append "Your grade: " (percent->grade grade) ". " (grade-advice (percent->grade grade)))))

(define letter-grade-advice-curved
  (lambda (grade)
    (letter-grade-advice (* (/ grade (- 100 (* (+ 1 (random 3)) 5))) 100))))




#|
6. SUMMARY
During this lab, I learned how to apply different predicates to create procedures, and how confusing the usage of "random" can be.

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab Team Hellyeah
Sang Hyeong Woo - sangwoo
Tyler Leeth - tjleeth
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
The goal of these procedures is to tell you what letter grade you recieve via
percentage, and give you a message based on that grade. Lastly it will give a
letter grade and advice for a curved grade.
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define percent->grade
    (lambda (x)
        (cond
            [(>= x 90) ("A")]
            [(>= x 80) ("B")]
            [(>= x 70) ("C")]
            [(>= x 60) ("D")]
            [else ("F")])))
(define grade-advice
    (lambda (grade)
        (cond
            [(equals? "A") ("Outstanding!")]
            [(equals? "B") ("Nice job!")]
            [(equals? "C") ("With a little studying, you can master the material.")]
            [(equals? "D") ("You still have a chance to improve next exam")]
            [else ("Better luck next time!")])))
(define letter-grade-advice
    (lambda (grade)
        (string-append "Your grade:" (percent->grade grade) "," (grade-advice grade))))

-------------------------------------------------------------------
4. HAND TRACE
~(letter-grade-advice 78)
        =(string-append "Your grade:" (percent->grade grade) "," (grade-advice grade))))

        =(string-append "Your grade:" ( (cond
            [(>= x 90) ("A")]
            [(>= x 80) ("B")]
            [(>= x 70) ("C")]
            [(>= x 60) ("D")]
            [else ("F")])))
) "," ( (cond
            [(equals? "A") ("Outstanding!")]
            [(equals? "B") ("Nice job!")]
            [(equals? "C") ("With a little studying, you can master the material.")]
            [(equals? "D") ("You still have a chance to improve next exam")]
            [else ("Better luck next time!")])))
))))
        =(string-append "Your grade:" "C" "," "With a little studying...")))
        ="Your grade: C, With a little studying, you can master the material"
-------------------------------------------------------------------
|#