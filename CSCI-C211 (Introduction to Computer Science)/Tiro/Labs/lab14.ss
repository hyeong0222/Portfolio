(import (c211 image) (c211 matrix) (c211 color))

;;1.
;;a.
(define infinity
  (lambda ()
    'oo))

;;b.
(define infinity?
  (lambda (x)
    (if (equal? x (infinity))
        #t
        #f)))

;;c.
(define :+
  (lambda (a b)
    (if (or (infinity? a) (infinity? b))
        (infinity)
        (+ a b))))

;;d.
(define :<
  (lambda (a b)
    (if (infinity? a)
        (if (infinity? b)
            #f
            #f)
        (if (infinity? b)
            #t
            (if (< a b)
                #t
                #f)))))


;;2.
(define highlight-seam!
  (lambda (img ls col)
    (let loop ([r 0] [index ls])
      (when (< r (image-rows img))
        (begin
          (image-set! img r (car index) col)
          (loop (add1 r)(cdr index)))))))


;;3.
;;a.
(define brightness
  (lambda (col)
    (+ (color-ref col 'red) (color-ref col 'green) (color-ref col 'blue))))

(define energy
  (lambda (img r c)
    (cond
      [(and (zero? r) (zero? c))
       (sqrt (+ (expt (- (* -2 (brightness (image-ref img r (add1 c))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (* -2 (brightness (image-ref img (add1 r) c)))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(and (equal? r (sub1 (image-rows img))) (zero? c))
       (sqrt (+ (expt (- (* -1 (brightness (image-ref img (sub1 r) (add1 c))))
                        (* 2 (brightness (image-ref img r (add1 c))))) 2)
               (expt (+ (* 2 (brightness (image-ref img (sub1 r) c)))
                       (brightness (image-ref img (sub1 r) (add1 c)))) 2)))]
      [(and (zero? r) (equal? c (sub1 (image-cols img))))
       (sqrt (+ (expt (+ (* 2 (brightness (image-ref img r (sub1 c))))
                        (brightness (image-ref img (add1 r) (sub1 c)))) 2)
               (expt (- (* -1 (brightness (image-ref img (add1 r) (sub1 c))))
                       (* 2 (brightness (image-ref img (add1 r) c)))) 2)))]
      [(and (equal? r (sub1 (image-rows img))) (equal? c (sub1
                                                           (image-cols img))))
       (sqrt (+ (expt (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                        (* 2 (brightness (image-ref img r (sub1 c))))) 2)
               (expt (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                       (* 2 (brightness (image-ref img (sub1 r) c)))) 2)))]
      [(zero? r)
       (sqrt (+ (expt (- (- (+ (* 2 (brightness (image-ref img r (sub1 c))))
                              (brightness (image-ref img (add1 r) (sub1 c))))
                           (* 2 (brightness (image-ref img r (add1 c)))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (- (* -1 (brightness (image-ref img (add1 r) (sub1 c))))
                          (* 2 (brightness (image-ref img (add1 r) c))))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(zero? c)
       (sqrt (+ (expt (- (- (* -1 (brightness (image-ref img (sub1 r)
                                                (add1 c))))
                           (* 2 (brightness (image-ref img r (add1 c)))))
                        (brightness (image-ref img (add1 r) (add1 c)))) 2)
               (expt (- (- (+ (* 2 (brightness (image-ref img (sub1 r) c)))
                             (brightness (image-ref img (sub1 r) (add1 c))))
                          (* 2 (brightness (image-ref img (add1 r) c))))
                       (brightness (image-ref img (add1 r) (add1 c)))) 2)))]
      [(equal? c (sub1 (image-cols img)))
       (sqrt (+ (expt (+ (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                           (* 2 (brightness (image-ref img r (sub1 c)))))
                        (brightness (image-ref img (add1 r) (sub1 c)))) 2)
               (expt (- (- (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                             (* 2 (brightness (image-ref img (sub1 r) c))))
                          (brightness (image-ref img (add1 r) (sub1 c))))
                       (* 2 (brightness (image-ref img (add1 r) c)))) 2)))]
      [(equal? r (sub1 (image-rows img)))
       (sqrt (+ (expt (- (- (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                              (* 2 (brightness (image-ref img r (sub1 c)))))
                           (brightness (image-ref img (sub1 r) (add1 c))))
                        (* 2 (brightness (image-ref img r (add1 c))))) 2)
               (expt (+ (+ (brightness (image-ref img (sub1 r) (sub1 c)))
                          (* 2 (brightness (image-ref img (sub1 r) c))))
                       (brightness (image-ref img (sub1 r) (add1 c)))) 2)))]
      [else (sqrt (+ (expt (- (- (- (+ (+ (brightness (image-ref img
                                                        (sub1 r) (sub1 c)))
                                         (* 2 (brightness (image-ref img r
                                                            (sub1 c)))))
                                      (brightness (image-ref img (add1 r)
                                                    (sub1 c))))
                                   (brightness (image-ref img (sub1 r)
                                                 (add1 c))))
                                (* 2 (brightness (image-ref img r (add1 c)))))
                             (brightness (image-ref img (add1 r) (add1 c)))) 2)
                    (expt (- (- (- (+ (+ (brightness (image-ref img (sub1 r)
                                                       (sub1 c)))
                                        (* 2 (brightness (image-ref img
                                                           (sub1 r) c))))
                                     (brightness (image-ref img (sub1 r)
                                                   (add1 c))))
                                  (brightness (image-ref img (add1 r)
                                                (sub1 c))))
                               (* 2 (brightness (image-ref img (add1 r) c))))
                            (brightness (image-ref img (add1 r)
                                          (add1 c)))) 2)))])))

(define make-energy-matrix
  (lambda (img)
    (matrix-generator (image-rows img) (image-cols img)
      (lambda (r c) (energy img r c)))))

;;b.
(define matrix-map
  (lambda (proc mat)
    (matrix-generator (matrix-rows mat) (matrix-cols mat)
      (lambda (r c) (proc (matrix-ref mat r c))))))

;;c.
(define matrix-copy
  (lambda (mat)
    (matrix-map (lambda (x) x) mat)))

;;d.
(define cropped-matrix-ref
  (lambda (mat r c n)
    (cond
      [(and (<= 0 r)(<= 0 c)(< c n)) (matrix-ref mat r c)]
      [else (infinity)])))


;;4.
(define best-direction
  (lambda (a b c)
    (if (equal? (:< a b) #t)
        (if (equal? (:< a c) #t)
            -1
            1)
        (if (equal? (:< b c) #t)
            0
            1))))


;;5.
(define seam-origin
  (lambda (mat n)
    (let loop ([c 0][least (infinity)][ans -1])
      (if (< c n)
          (if (:< (matrix-ref mat 0 c) least)
              (loop (add1 c) (matrix-ref mat 0 c) c)
              (loop (add1 c) least ans))
          ans))))


;;6.
(define walk-seam
  (lambda (sm c)
    (let loop ([r 0] [c c])
      (if (< r (matrix-rows sm))
          (cons c (loop (add1 r) (matrix-ref sm r c)))
          '()))))


;;7.
(define least-energy-seam
  (lambda (em n)
    (let ((cm (matrix-copy em))
          (sm (matrix-generator (matrix-rows em) (matrix-cols em)
                (lambda (r c) 0))))
      (let rloop ([r (- (matrix-rows sm) 2)])
        (if (>= r 0)
            (let cloop ([c 0])
              (if (>= c n)
                  (rloop (sub1 r))
                  (begin
                    (let ((bd (best-direction
                                (cropped-matrix-ref cm (add1 r) (sub1 c) n)
                                (cropped-matrix-ref cm (add1 r) c n)
                                (cropped-matrix-ref cm (add1 r) (add1 c) n))))
                      (matrix-set! sm r c (+ c bd))
                      (matrix-set! cm r c (+ (cropped-matrix-ref cm r c n)
                                            (cropped-matrix-ref cm
                                              (add1 r) (+ c bd) n))))
                    (cloop (add1 c)))))
        (walk-seam sm (seam-origin cm n)))))))

#|
;;8.
(define image-seam-carve!
  (lambda (img sm n)
    (let cloop ([col sm] [r 0])
      (if (null? col)
          img
          (let rloop ([c (car col)])
            (if (< n c)
                (begin
                  (image-set! img r c (image-ref img r (add1 c)))
                  (rloop (add1 c)))
                (cloop (cdr col) (add1 r))))))))
|#

(define image-seam-carve!
  (lambda (img sm n)
    (let rloop ([sm sm] [r 0])
      (if (< r (image-rows img))
          (let cloop ([c (car sm)])
            (if (< c n)
                (begin
                  (image-set! img r c (image-ref img r (add1 c)))
                  (cloop (add1 c)))
                (rloop (cdr sm) (add1 r))))
          img))))


;;9
(define image-copy
  (lambda (img)
    (image-map (lambda (x) x) img)))

(define image-crop
  (lambda (img n)
    (make-image (image-rows img)
      (if (> n (image-cols img)) 0 (- (image-cols img) n))
      (lambda (row col) (image-ref img row col)))))

(define content-aware-resize
  (lambda (img-orig pixels)
    (let loop ([img (image-copy img-orig)] [n (image-cols img-orig)])
      (if (not (= pixels n))
          (let* ([em (make-matrix (image-rows img-orig) (image-cols img-orig)
                       (lambda (r c) (energy img r c)))]
                [least (least-energy-seam em pixels)])
            (begin
              (image-seam-carve! img least n) (loop img (sub1 n))))
          (image-crop img pixels)))))