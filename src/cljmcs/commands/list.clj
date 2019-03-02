(ns cljmcs.commands.list
  (:require [cljmcs.minecraft.servers :as servers]
            [clojure.core.match :refer [match]]))

(defn list!
  "Lists all available minecraft versions, release or snapshot"
  [group]
  (let [versions (servers/list-versions-in-group group)]
    (match versions
      [:error error] (println "Error:" error)
      [:ok coll] (doseq [version versions]
                   (println version)))))
