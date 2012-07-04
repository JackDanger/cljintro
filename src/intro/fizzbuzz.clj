(ns intro.fizzbuzz)

(defn fizz-buzz-for
  [n]
  (if (zero? (mod n 15))
    "FizzBuzz"
    (cond
      (zero? (mod n 3))
        "Fizz"
      (zero? (mod n 5))
        "Buzz"
      :default n
    )
  )
)

(defn fizz-buzz
  "seqs numbers and for multiples of three returns 'Fizz' instead of the number and for the multiples of five returns “Buzz”. For numbers which are multiples of both three and five prints 'FizzBuzz'"
  ([]
    (fizz-buzz 1)
  )
  ([n]
    (let [x (+ 1 n)]
      (lazy-seq
        (cons (fizz-buzz-for n) (fizz-buzz x))
      )
    )
  )
)
