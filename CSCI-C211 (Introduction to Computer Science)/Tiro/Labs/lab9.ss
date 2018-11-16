;; 5. CODING & DEBUGGING
(import (c211 tree))
(define tree-calc
  (lambda (tr)
    (cond
      [(leaf? tr) (root-value tr)]
      [else (let ([opr (root-value tr)]
                  [lvalue (tree-calc (left-subtree tr))]
                  [rvalue (tree-calc (right-subtree tr))])
              (opr lvalue rvalue))])))
(define rpn->tree-help
  (lambda (ls stack)
    (cond
      [(null? ls) (car stack)]
      [(integer? (car ls))
       (rpn->tree-help (cdr ls) (cons (leaf (car ls)) stack))]
      [else (rpn->tree-help (cdr ls)
              (cons (tree (car ls) (cadr stack) (car stack)) (cddr stack)))])))
(define rpn->tree
    (lambda (ls)
        (rpn->tree-help ls '())))
#|
6. SUMMARY
We leanred about RPN and tree system. We learned the basic skelaton of
tree system and how trees can be created from RPN.
7. KNOW YOUR INSTRUCTORS
<Every week, we will be asking you a question about your instructor(s).>
Ask your instructors for this week's question!
+=================================================================+
| Do not change anything below this line after moving to the IDE!! |
+=================================================================+
1. IDENTITY
Hellyeah
Sang Hyeong Woo - sangwoo
Yueyang Wu-yueywu
-------------------------------------------------------------------
2. UNDERSTANDING THE PROBLEM
Define a procedure, rpn->tree, which takes a list representing an RPN
expression (all operators are binary)
and returns the corresponding expression tree.
-------------------------------------------------------------------
3. DRAFT SOLUTION
(define rpn->tree
    (lambda (ls)
        (tree-calc (rpn->tree-help ls'())))
(define rpn->tree-help
    (lambda (ls stack)
        (cond
            [(null? ls) '()]
            [(integer? (car ls)) (rpn->tree-help (cdr ls)
                                   (cons (leaf (car ls)) stack))]
            [else (rpn->tree-help (cdr ls) (cons (tree (car ls)) stack))])))

-------------------------------------------------------------------
4. HAND TRACE
<Choose a non-trivial, medium-size test case from (2) and write out a
detailed hand trace of the application evaluation.>
  (define rpn->tree
    (lambda (ls)
        (rpn->tree-help ls '())))

  (define rpn->tree-help
  (lambda (ls stack)
    (cond
      [(null? ls) (car stack)]
      [(integer? (car ls))
       (rpn->tree-help (cdr ls) (cons (leaf (car ls)) stack))]
      [else (rpn->tree-help
              (cdr ls) (cons (tree (car ls)
                               (cadr stack) (car stack)) (cddr stack)))])))
(define tree-calc
  (lambda (tr)
    (cond
      [(leaf? tr) (root-value tr)]
      [else (let ([opr (root-value tr)]
                  [lvalue (tree-calc (left-subtree tr))]
                  [rvalue (tree-calc (right-subtree tr))])
              (opr lvalue rvalue))])))

~  (tree-calc (rpn->tree (list 5)))
== (rpn->tree-help (rpn->tree-help '() (cons (leaf 5)) stack))
== (rpn->tree-help (leaf 5))
== (rpn->tree (leaf 5))
== (tree-calc (rpn->tree (leaf 5)))
== (tree-calc (root-value (leaf 5)))
== 5
-------------------------------------------------------------------
|#