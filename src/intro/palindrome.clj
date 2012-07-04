(ns intro.palindrome
  (:use clojure.test))

(with-test
  (defn is-palindrome?
        "is true if the given argument is a palindrome"
        [string]
        (loop [string string]
          (cond
            (= \space (first string))
            (recur (rest string))
            (= \space (last string))
            (recur (butlast string))
            (< (count string) 2)
            true
            (= (first string) (last string))
            (recur (butlast (rest string)))
            :else false)))
  (is (= true  (is-palindrome? "racecar")))
  (is (= true  (is-palindrome? "a man a plan a canal panama")))
  (is (= false (is-palindrome? "this is not a palindrome")))
  (is (= true  (is-palindrome? "hannah"))))
