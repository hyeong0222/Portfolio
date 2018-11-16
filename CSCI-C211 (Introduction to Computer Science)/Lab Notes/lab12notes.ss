(define vector-max
  (lambda (vec)
    (let loop ([index 0] [max-num (vector-ref vec 0)])
      (cond
        [(= index (vector-length vec)) max-num]
        [else (loop (add1 index) (max (vector-ref vec index) max-num))]))))

#|
(import (c211 matrix))
(make-matrix rows cols)
(matrix-rows mat)
(matrix-cols mat)
(matrix-ref r c)
(matrix-set! r c v)
(draw-matrix mat)
|#

(define display-matrix
  (lambda (mat)
    (let ([rows (matrix-rows mat)] [cols (matrix-cols mat)])
      (let r-loop ([r 0])
        (if (< r rows)
            (begin
              (let c-loop ([c 0])
                (if (< c cols)
                    (begin
                      (display (matrix-ref mat r c))
                      (c-loop (add1 c)))))
            (newline)
            (r-loop (add1 r))))))))