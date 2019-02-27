(ns cljmcs.commands.list
  (:require [cljmcs.minecraft.servers :as servers]))


(defn list!
  "Lists all available minecraft versions, release or snapshot"
  [group]
  (if-some [versions (servers/list-versions-in-group group)]
    (doseq [version versions]
      (println version))
    (println (str "Error: No such group \"" group "\" [expected release/snapshot]"))))
