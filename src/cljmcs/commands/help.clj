(ns cljmcs.commands.help
  (:require [clojure.string :as string]))

(defn help!
  "Prints the help message for this program"
  []
  (println
   (->> ["cljmcs: The Clojure Minecraft server tool"
         ""
         "Usage: cljmcs <action> [args]"
         ""
         "Actions:"
         "  download [release/snapshot, version]    Downloads Minecraft server jar & creates run script"
         "  list     [release/snapshot]             Lists all releases or snapshots"
         "  help                                    Prints this message"
         ""]
        (string/join \newline))))
