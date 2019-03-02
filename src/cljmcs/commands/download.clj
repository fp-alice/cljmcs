(ns cljmcs.commands.download
  (:require [cljmcs.http.downloader :as downloader]
            [cljmcs.minecraft.servers :as servers]
            [cljmcs.os.files :as files]
            [clojure.core.match :refer [match]]))

(defn download!
  "Attempts downloading a server jar & creating a run script with provided arguments"
  [[group version]]
  (println "Attempting to download Minecraft server:" group version)
  (let [result (servers/get-server-jar group version)]
    (match result
      [:error error] (println "Error:" error)
      [:ok selected-jar] (do
                           (println "Downloading...")
                           (downloader/download-jar! selected-jar)
                           (println "Writing default run script to run.sh...")
                           (files/write-run-script! (:file selected-jar))
                           (println "Writing eula.txt...")
                           (files/write-eula-file!)
                           (println "Done")))))

