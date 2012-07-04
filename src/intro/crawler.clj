(ns intro.crawler
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]
            org.bovinegenius.exploding-fish))

(def crawled (atom #{}))

(defn fetch
  "Retrieve contents of a page at a given url"
  [url]
  (try
    (html/html-resource (java.net.URL. url))
    (catch Exception e (prn (str "unable to retrieve " url)))
    (finally (swap! crawled conj url))))

(defn base-url
  "Return a uri up until the path"
  [url]
  (let [u (org.bovinegenius.exploding-fish/uri url)]
    (str
      (org.bovinegenius.exploding-fish/scheme u)
      "://"
      (org.bovinegenius.exploding-fish/host u))))

(defn absolutize
  "make any url an absolute url if the domain needs to be prepended"
  [from href]
  (if (= \/ (first href))
      (str (base-url from) href)
      href))

(defn links-inside
  "returns a list of links in the html content of a given url"
  [url]
  (println url)
  (map #(absolutize url %) (map :href (map :attrs (html/select (fetch url) [:a])))))

(defn crawl
  "examines each entry in the queue, appending to the queue while doing so"
  [start]
  (loop [queue [start]]
      (println "Queue size: " (count queue))
      (if-let [link (first queue)]
        (recur
          (if (contains? crawled link)
            (rest queue)
            (rest (concat queue (links-inside link))))))))

