(import (c211 matrix))

(define index-of-last
  (lambda (sym vec)
    (let loop ([i (sub1 (vector-length vec))])
      (cond
        [(< i 0) #f]
        [(equal? (vector-ref vec i) sym) i]
        [else (loop (sub1 i))]))))

(define index-of-last
  (lambda (sym vec)
    (let loop ([i 0] [best #f])
      (cond
        [(= i (vector-length vec)) best]
        [(equal? (vector-ref vec i) sym) (loop (add1 i) i)]
        [else (loop (add1 i) best)]))))

#|
(matrix-generator rows cols
  (lambda (r c) (+ r c)))
|#

(define matrix-generator^
  (lambda (row col proc)
    (let ([output (make-matrix row col)])
      (let r-loop ([r 0])
        (when (< r row)
          (let c-loop ([c 0])
            (when (< c col)
              (matrix-set! output r c (proc r c))
              (c-loop (add1 c))))
          (r-loop (add1 r)))
        output))))

(define matrix-map
  (lambda (proc mat)
    (matrix-generator (matrix-rows mat) (matrix-cols mat)
      (lambda (r c) (proc (matrix-ref mat r c))))))

(define matrix-add
  (lambda (mat1 mat2)
    (matrix-generator (matrix-rows mat1) (matrix-cols mat1)
      (lambda (r c) (+ (matrix-ref mat1 r c) (matrix-ref mat2 r c))))))