(ns cljmcs.os.files
  (:require [clojure.java.io :as io]
            [cljmcs.os.shell :as shell]))

(defn join-paths
  "Joins many strings together with `/` at the boundary"
  [& paths]
  (apply str (interpose "/" paths)))

(defn write-stream-to-file!
  "Writes file to disk"
  [stream location]
  (io/copy
   stream
   (java.io.File. (join-paths (shell/pwd) location))))

(defn write-run-script!
  "Write a default run script for the server"
  [jar-name]
  (let [script-string (str "#!/bin/bash\n"
                           "java -Xms1024M -Xmx4096M -jar " jar-name " nogui")]
    (spit (join-paths (shell/pwd) "run.sh") script-string)))
