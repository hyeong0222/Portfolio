(import (c211 tree) (c211 image))

(define tree-zerox
  (lambda (tr)
    (cond
      [(empty-tree? tr) (empty-tree)]
      [else (tree 0 (tree-zerox (left-subtree tr)) (tree-zerox (right-subtree tr)))])))

(define test-tr
  (tree 'cols (leaf black) (tree 'rows (leaf red) (tree 'cols (leaf green) (leaf yellow)))))

;;(make-image row-size col-size (lambda (r c) "does stuff"))
;;(make-image 255 255 (lambda (r c) (color r c r))))