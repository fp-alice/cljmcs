(ns cljmcs.os.shell
  (:require [clojure.java.shell :as shell :refer [sh]]
            [clojure.string :as string]))

(defn pwd
  "Gets the current directory"
  []
  (-> (sh "pwd")
      :out
      string/trim))

(defn start-run-script!
  []
  (sh "run.sh"))
