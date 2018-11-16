;; 5. CODING & DEBUGGING
(define xor-encode
  (lambda (str1 str2)
    (let ((str3 (make-string (string-length str1))))
      (let loop ((index1 0) (index2 0))
        (cond
          [(= (string-length str1) index1) str3]
          [(= (string-length str2) index2) (loop index1 0)]
          [else (begin (string-set! str3 index1
                         (char-xor (string-ref str1 index1)
                           (string-ref str2 index2)))
                  (loop (add1 index1) (add1 index2)))])))))
(define char-xor
  (lambda (char1 char2)
    (let ((int1 (char->integer char1))
          (int2 (char->integer char2)))
      (let ((encoded-int (logxor int1 int2)))
        (integer->char encoded-int)))))

#|
6. SUMMARY
I learned logxor, string-set, string-ref, string-length, and how to combine
all these procedures to create a new procedure that takes a string and encrypt
using another string.

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab 28646
Hellyeah
Sarah Sim, sksim
Sang Woo, sangwoo
Bryan Galle, bgalle
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Takes a plain text and produces a ciphertext using a given key
-------------------------------------------------------------------
3. DRAFT SOLUTION
;takes two characters and encodes it.
(define char-xor
  (lambda (char1 char2)
    (let ((int1 (char->integer char1))
          (int2 (char->integer char2)))
      (let ((encoded-int (logxor int1 int2)))
        (integer->char encoded-int)))))

(define xor-encode
    (lambda (str1 str2)
        (let ((str3 (make-string (string-length str1))
              (let loop ((index1 0) (index2 0))
                  (cond
                      [(= (string-length str1) index1)  str3]
                      [(if (= (string-length str2) index2))
                       (loop (index1 (index2 0) (
                      [else (begin (string-set! str3 (char-xor index1 index2)
                                     (string-ref str2 index2))
                              (loop (add1 index1) (add1 index2))])))
(define xor-encode
  (lambda (str1 str2)
    (let ((str3 (make-string (string-length str1))
            (let loop ((index1 0) (index2 0))
              (cond
                [(= (string-length str1) index1)  str3]
                [else (begin (string-set! str3 (char-xor index1 index2)
                               (string-ref str2 index2))
                        (if (= (string-length str2) index2))
                        (loop (add1 index1) 0)
                        (loop (add1 index1) (add1 index2)))])))))))

-------------------------------------------------------------------
4. HAND TRACE
<Choose a non-trivial, medium-size test case from (2) and write out a
detailed hand trace of the application evaluation.>
-------------------------------------------------------------------
|#