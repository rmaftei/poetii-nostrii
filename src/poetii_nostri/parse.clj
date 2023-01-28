(ns poetii-nostri.parse
  (:require [net.cgrand.enlive-html :as html]))

(def website "https://poetii-nostri.ro/")

(def website-poeti-clasici "https://poetii-nostri.ro/poeti-clasici/")

(def poeti-clasici (html/html-resource (java.net.URL. website-poeti-clasici)))


(def ion-barbu (str website "ion-barbu-autor-8/"))

(def poezii (html/html-resource (java.net.URL. ion-barbu)))

(def lista-poezii-html (map #(get-in % [:attrs]) (html/select poezii #{[:ul :li :div :a]})))

(def map-poezii (reduce (fn [rezultat poezie]
                          (conj rezultat (hash-map :titlu (:title poezie) :url (str "https://poetii-nostri.ro" (:href poezie)))))
                        [] lista-poezii-html))

(defn retrieve-poezii []
  map-poezii)

