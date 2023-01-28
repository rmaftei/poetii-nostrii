(ns poetii-nostri.core
  (:gen-class)
  (:require
   [poetii-nostri.listing :refer [listare]]
   
   [clojure.string :as string]
   [clojure.tools.cli :refer [parse-opts]]))


(def cli-options
  [["-h" "--help"]
   ["-l" "--list" "Afișează lista de poeti"]
   ["-a" "--autor" "<numar-poet> Afișează lista poeziilor a poetului ales"]
   ["-p" "--poezia" "<numar-poet> <numar-poezie> Afișează poezia aleasă"]])

(defn usage [options-summary]
  (->> ["Acest program afișează poezii de pe site-ul https://poetii-nostrii.ro"
        ""
        "Utilizare: poetii-nostrii [options]"
        ""
        "Options:"
        options-summary
        ""
        ]
       (string/join \newline)))

(defn parse-int [number]
  (if (re-matches #"\d+" number)
    (Integer/parseInt number)
    (+ 0 0)))

(defn print-summary [summary]
  (println (usage summary)))
  
(defn -main
  [& args]
  (let [{:keys [options arguments summary errors]} (parse-opts args cli-options)] 
    (if errors 
      (print-summary summary)
      (cond
        (:poezia options) (if (= (count arguments) 2)
                           (println (listare (parse-int (first arguments)) (parse-int (second arguments))))
                           (print-summary summary))
        (:help options) (print-summary summary)
        )
      )
    )
  )
