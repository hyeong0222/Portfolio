;; 5. CODING & DEBUGGING
(import (c211 matrix))
(define matrix-add
  (lambda (mat1 mat2)
    (let ([output (make-matrix (matrix-rows mat1) (matrix-cols mat1))])
      (let r-loop ([r 0])
        (when (< r (matrix-rows mat1))
          (let c-loop ([c 0])
            (when (< c (matrix-cols mat1))
              (matrix-set! output r c (+ (matrix-ref mat1 r c)
                                        (matrix-ref mat2 r c)))
              (c-loop (add1 c))))
          (r-loop (add1 r)))
        output))))
#|
6. SUMMARY
I learned how matrix works and how it is formed. I also learned how to use
loop function to add two different matrices and create a new one with the result

7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Hellyeah
Sang Hyeong Woo - sangwoo
Bryan Galle - bgalle
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Takes two matrices, add the value at the same point, and create a new matrix
with the result
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define matrix-add
  (lambda (mat1 mat2)
    (let ([output (make-matrix row col)])
      (let r-loop ([r 0])
        (when (< r (matrix-rows mat1))
          (let c-loop ([c 0])
            (when (< c (matrix-cols mat1))
              (matrix-set! output r c (+ (matrix-ref mat1 r c)
                                        (matrix-ref mat2 r c)))
              (c-loop (add1 c))))
          (r-loop (add1 r)))
        output))))
-------------------------------------------------------------------
4. HAND TRACE
<Choose a non-trivial, medium-size test case from (2) and write out a
detailed hand trace of the application evaluation.>
-------------------------------------------------------------------
|#