(ns poetii-nostri.listing
  (:require [poetii-nostri.parse :refer [retrieve-poezii]]))

(def poezii-without-position (retrieve-poezii))

(def lista-poezii (map (fn [elem] (assoc (second elem) :poz (first elem)))
                       (map vector (range 1 (inc (count poezii-without-position))) poezii-without-position)))

(defn slice
  [page page-size]
  (take page-size (drop (* (dec page) page-size)
                        lista-poezii)))

(defn compute-menu
  [afis elem]
  (str afis (elem :poz) "." (elem :titlu) " " (elem :url) "\n"))

(defn print-page-offset
  [page page-size]
  (str page "-" (int (Math/ceil (/ (count lista-poezii) page-size)))))

(defn listare
  ([nr]
   (listare 1 nr))
  ([page page-size]
   (str (reduce compute-menu
                "" (slice page page-size))
        (print-page-offset page page-size))))
