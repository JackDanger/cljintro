(ns intro.start
  (:gen-class)
  (:use [intro.crawler])
  (:use [intro.fizzbuzz])
  (:use [intro.palindrome])
)

(defn -main [ & args ]
    (println "starting!")
    (when-let [cmd (first args)]
      (case cmd
        "crawl"
        (crawl (first (rest args)))
        "fizzbuzz"
        (println (take (Integer/parseInt (first (rest args))) (fizz-buzz)))
        "palindrome"
        (println (intro.palindrome/is-palindrome? (first (rest args)))))))
