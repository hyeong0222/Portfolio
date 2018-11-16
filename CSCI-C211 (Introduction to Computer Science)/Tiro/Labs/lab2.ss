;; 5. CODING & DEBUGGING
;; Complete Steps 1 through 4 below with your partner and then replace
;; this text with a comment generated from Step 2 of your
;; brainstorming session.  Paste your code draft from Step 3 into the
;; space below. Use the IDE to debug your code here. Do not make *any*
;; changes to your original draft code below in Step 3. Just leave it
;; alone, even if there are mistakes! We mean it. Really.
(define peel-first-character
    (lambda (str)
        (substring str 1 (string-length str))))
(define capitalize-first-character
    (lambda (str)
        (string-upcase (substring str 0 1))))
(define strsent
    (lambda (str)
        (string-append (string-append(capitalize-first-character str) (peel-first-character str))".")))
#|
6. SUMMARY
-Learned about proper parenthesization and how to combine multiple functions within the same function.
-Too many parenthesis is counter productive.
-Basic Scheme syntax and semantics.
-How prefix works in coding.

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab 2
Hellyeah
Cory & Jeff
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
<Give a clear, complete description of the problem(s) at hand. Include
several test cases and their expected outputs.>
Three different programs.  The first removes the first character in a given string input.  The sceond Capitalizes the first character in a given string input, and inally using the first two programs we will call them in a third program that will make the given input into a sentence.
-------------------------------------------------------------------
3. DRAFT SOLUTION
<Devise Scheme-like code to solve the problem(s).>
(define peel-first-character
    (lambda (str)
        (substring 1 (string-length str))))


(define capitalize
    (lambda (str)
        (string-upcase (substring str 0 1))))
(define strsent
    (lambda (str)
        (string-append (string-append(capitalize-first-character str) (peel-first-character str))".")))
-------------------------------------------------------------------
4. HAND TRACE
<Choose a non-trivial, medium-size test case from (2) and write out a
detailed hand trace of the application evaluation.>
(strsent "i am")
==(string-append (string-append (capitalize-first-character "i am") (peel-first-character "i am") ".")
==(string-append (string-append "I" "am") ".")
=="I am."
-------------------------------------------------------------------
|#