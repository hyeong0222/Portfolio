#|
Name: Sang Hyeong Woo
Email: sangwoo@indiana.edu
Lab Section: Hellyeah
Collaboration Statement: I worked with JiHoon Ban (jiban) and got help during
                         the office hours
|#

(import (c211 image) (c211 matrix) (c211 color))

;;1.
;;a. Returns the representation of infinity
(define infinity
  (lambda ()
    'oo))

;;b. Returns #t if x corresponds to the representation of infinity, and #f
;;   otherwise
(define infinity?
  (lambda (x)
    (if (equal? x (infinity))
        #t
        #f)))

;;c. Takes two inputs and returns the sum
(define :+
  (lambda (a b)
    (if (or (infinity? a) (infinity? b))
        (infinity)
        (+ a b))))

;;d. Takes two inputs and returns #t if the first is less than the second
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


;;2. Mutates the given image by replacing all the pixels along the seam with the
;;   specified highlight color
(define highlight-seam!
  (lambda (img ls col)
    (let loop ([r 0] [index ls])
      (when (< r (image-rows img))
        (begin
          (image-set! img r (car index) col)
          (loop (add1 r)(cdr index)))))))


;;3.
;;a. Returns a matrix of the same dimensions with the energy of the
;;   corresponding pixel
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

;;b. returns a new matrix of the same dimensions as the given matrix and where
;;   each element is the result of applying the given procedure
(define matrix-map
  (lambda (proc mat)
    (matrix-generator (matrix-rows mat) (matrix-cols mat)
      (lambda (r c) (proc (matrix-ref mat r c))))))

;;c. Returns a separate copy of the given matrix
(define matrix-copy
  (lambda (mat)
    (matrix-map (lambda (x) x) mat)))

;;d. If the given indices refer to a legal element in the leftmost n columns
;;   of the given matrix, then the corresponding element is returne
(define cropped-matrix-ref
  (lambda (mat r c n)
    (cond
      [(and (<= 0 r)(<= 0 c)(< c n)) (matrix-ref mat r c)]
      [else (infinity)])))


;;4. returns the best direction for the seam to take, based on the given costs
(define best-direction
  (lambda (a b c)
    (if (equal? (:< a b) #t)
        (if (equal? (:< a c) #t)
            -1
            1)
        (if (equal? (:< b c) #t)
            0
            1))))


;;5. returns the column corresponding to the smallest cost
(define seam-origin
  (lambda (mat n)
    (let loop ([c 0][least (infinity)][ans -1])
      (if (< c n)
          (if (:< (matrix-ref mat 0 c) least)
              (loop (add1 c) (matrix-ref mat 0 c) c)
              (loop (add1 c) least ans))
          ans))))


;;6. returns the seam in sm corresponding to the given column
(define walk-seam
  (lambda (sm c)
    (let loop ([r 0] [c c])
      (if (< r (matrix-rows sm))
          (cons c (loop (add1 r) (matrix-ref sm r c)))
          '()))))

(define test+ +)


;;7. returns a minimal cost vertical seam restricted to the first n
;;   columns of the given matrix
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


;;8. mutates the image by carving out the given seam
(define image-seam-carve!
  (lambda (img sm n)
    (let rloop ([sm sm] [r 0])
      (if (< r (image-rows img))
          (let cloop ([c (car sm)])
            (if (< c (sub1 n))
                (begin
                  (image-set! img r c (image-ref img r (add1 c)))
                  (cloop (add1 c)))
                (rloop (cdr sm) (add1 r))))
          ))))


;;9 returns a new image formed by carving pixels vertical seams from the
;;  given image
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
    (let loop ([img (image-copy img-orig)]
               [n (image-cols img-orig)]
               [count pixels])
      (if (not (= count 0))
          (let* ([em (make-energy-matrix img)]
                [least (least-energy-seam em n)])
            (begin
              (image-seam-carve! img least n)
              (loop img (sub1 n) (sub1 count))))
          (image-crop img pixels)))))