;;(string-length "abc")
;;3

;;(string-ref "abcde" 2)
;; #\c

;;(string-set! str 0 #\z)
;; "a b z d e"

(define string-reverse
  (lambda (str)
    (let ((str2 (make-string (string-length str))))
          (let string-loop ((index1 0) (index2 (sub1 (string-length str))))
            (cond
              [(= index1 (string-length str)) str2]
              [else (begin (string-set! str2 index2 (string-ref str index1))
                      (string-loop (add1 index1) (sub1 index2)))])))))

(define char-xor
  (lambda (char1 char2)
    (let ((int1 (char->integer char1))
          (int2 (char->integer char2)))
      (let ((encoded-int (logxor int1 int2))) (integer->char encoded-int)))))