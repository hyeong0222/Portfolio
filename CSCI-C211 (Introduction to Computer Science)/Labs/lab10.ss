;; 5. CODING & DEBUGGING
(import (c211 tree) (c211 image))
(define split-region
    (lambda (row col min-row max-row min-col max-col tr)
        (cond
            [(leaf? tr) (root-value tr)]
            [(equal? (root-value tr) 'cols)
                (if (< col(/ (+ min-col max-col) 2))
                    (split-region row col min-row max-row min-col max-col
                      (left-subtree tr))
                    (split-region row col min-row max-row
                      (+ min-col (/ (+ min-col max-col) 2))
                      max-col (right-subtree tr)))]
            [else
                (if (< row(/ (+ min-row max-row) 2))
                    (split-region row col min-row max-row min-col max-col
                      (left-subtree tr))
                    (split-region row col
                      (+ min-row (/ (+ min-row max-row)))
                      max-row min-col max-col (right-subtree tr)))])))

(define tree->image
    (lambda (rows cols tr)
        (make-image rows cols (lambda (r c)
                                (split-region r c 0 rows 0 cols tr)))))
#|
6. SUMMARY
Learned how tree and image can be combined, and how complicated it can get
when we utilize both at the same time.
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Lab 10
Hellyeah
Taylor Ball
Sang Hyeong Woo - sangwoo
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Split-region will take a point, a region, and a tree, and make an image with the
given colors separated by the given regions.

tree->image will take a tree and turn it into an actual image with the colors
listed in the tree
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define split-region
    (lambda row col min-row max-row min-col max-col tr)
        (cond
            [(leaf? tr) (root-value tr)]
            [(equal? (root-value tr) 'cols)
                (if (col<(/ (+ col-min col-max) 2))
                    (split-region row col min-row max-row min-col max-col
                      (right-subtree tr))
                    (split-region row col min-row max-row min-col max-col
                      (left-subtree tr)))]
            [else
                (if (row<(/ (+ rowl-min row-max) 2))
                    (split-region row col min-row max-row min-col max-col
                      (right-subtree tr))
                    (split-region row col min-row max-row min-col max-col
                      (left-subtree tr)))])))
(make-image row-size col-size (lambda (r c) "does stuff"))
(define tree->image
    (lambda (rows cols tr)
        (make-image rows cols (lambda (r c)
                                (split-region r c 0 rows 0 cols tr)))))
-------------------------------------------------------------------
|#