;; 5. CODING & DEBUGGING
(import (c211 image) (c211 matrix))
(define ascii "@#&o:*. ")
(define image->matrix
  (lambda (img proc)
    (let ([rows (image-rows img)] [cols (image-cols img)])
     (let ([mat (make-matrix rows cols)])
      (let r-loop ([r 0])
        (if (< r rows)
            (begin
              (let c-loop ([c 0])
                (if (< c cols)
                    (begin
                      (matrix-set! mat r c (proc (image-ref img r c)))
                      (c-loop (add1 c)))))
              (r-loop (add1 r)))))
       mat))))
(define pixel->ascii
  (lambda (clr)
    (let ([greyscale (quotient (+ (color-ref clr 'red)
                                 (color-ref clr 'green)
                                 (color-ref clr 'blue)) 3)])
      (cond
        [(< greyscale 32) (string-ref ascii 0)]
        [(< greyscale 64) (string-ref ascii 1)]
        [(< greyscale 96) (string-ref ascii 2)]
        [(< greyscale 128) (string-ref ascii 3)]
        [(< greyscale 160) (string-ref ascii 4)]
        [(< greyscale 196) (string-ref ascii 5)]
        [(< greyscale 224) (string-ref ascii 6)]
        [else (string-ref ascii 7)]))))

(define image->ascii
  (lambda (img)
    (image->matrix img pixel->ascii)))
#|
6. SUMMARY
We learned how every image can be converted into a matrix format, and how we can
manipulate such matrix to display in ascii form.
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab 12
Hellyeah
Kyle Nealy (knealy)
Sang Hyeong Woo (sangwoo)
Bryan Galle (bgalle)
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
a) takes an image and a function of one argument and returns a matrix the same
size as the original image where each element is the result of applying the
function to each pixel in the image.

b) takes a color, converts it to greyscale, and returns the associated ascii
character. Use the string ascii you defined to get the values.

 c) takes an image and converts it into a matrix containing ascii values.

-------------------------------------------------------------------
3. DRAFT SOLUTION
(import (c211 image) (c211 matrix))
(define ascii "@#&o:*. ")
(define image->matrix
  (lambda (img proc)
    (let ([img2 (image-map proc img)] [rows (image-ref img rows)]
          [cols (image-ref img cols)])
       (let r-loop ([r 0])
         (if (< r rows)
           (let (c-loop ([c 0])
             (if (< c cols)
               (matrix-set! (make-matrix rows cols) r c v)
               (c-loop (add1 c)))
         (r-loop (add1 r)))))

(define pixel->ascii
  (lambda (clr)
    (let ([clr (quotient (+ (color-ref c 'red) (color-ref c 'green)
                           (color-ref c 'blue)) 3)])
      (cond
        [(< clr 32) (string-ref ascii 0)]
        [(< clr 64) (string-ref ascii 1)]
        [(< clr 96) (string-ref ascii 2)]
        [(< clr 128) (string-ref ascii 3)]
        [(< clr 160) (string-ref ascii 4)]
        [(< clr 196) (string-ref ascii 5)]
        [(< clr 224) (string-ref ascii 6)]
        [else (string-ref ascii 7)])))

(define image->ascii
  (lambda (img)
    (image->matrix img pixel->ascii)))

-------------------------------------------------------------------
4. HAND TRACE
~ (pixel->ascii (color 0 0 0))
== let ([clr (quotient (+ 0 0 0) 3)])
== let ([clr 0])
== [(<clr 32) (string-ref ascii 0)]
== #\@
-------------------------------------------------------------------
|#