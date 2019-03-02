(ns cljmcs.os.files
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [cljmcs.os.shell :as shell]))

(defn join-paths
  "Joins many strings together with `/` at the boundary"
  [& paths]
  (string/join "/" paths))

(defn write-stream-to-file!
  "Writes file to disk"
  [stream location]
  (io/copy
   stream
   (io/file (shell/pwd) location)))

(defn write-run-script!
  "Write a default run script for the server"
  [jar-name]
  (let [script-string (str "#!/bin/bash\n"
                           "java -Xms1024M -Xmx4096M -jar " jar-name " nogui")]
    (spit (join-paths (shell/pwd) "run.sh") script-string)))

(defn write-eula-file!
  "Writes the damned eula file so I don't have to go do it myself, fuck you Mojang"
  []
  (let [content "eula=true"
        file    "eula.txt"]
    (spit (join-paths (shell/pwd) file) content)))
