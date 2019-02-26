(ns cljmcs.commands.download
  (:require [cljmcs.http.downloader :as downloader]
            [cljmcs.minecraft.servers :as servers]
            [cljmcs.os.files :as files]
            [clojure.core.match :refer [match]]))

(defn download!
  [[group version]]
  (let [result (servers/get-server-jar group version)]
    (match result
      [:error error] (println "Error:" error)
      [:ok selected-jar] (do
                           (downloader/download-jar! selected-jar)
                           (files/write-run-script! (:file selected-jar))))))

