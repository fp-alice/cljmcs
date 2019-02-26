(ns cljmcs.core
  (:gen-class)
  (:require [cljmcs.minecraft.servers :as servers]
            [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string]
            [clojure.tools.cli :as cli]
            [cljmcs.commands.download :as download]))

(def cli-options
  [["-h" "--help"]] )

(defn usage
  []
  (->> ["cljmcs: The clojure minecraft server tool"
        ""
        "Usage: cljmcs <action> [args] [options]"
        ""
        "Actions:"
        "  download [release/snapshot, version]    Downloads minecraft server jar & creates run script"
        "  run                                     Runs script in current directory"
        "  list     [release/snapshot]             Lists all releases or snapshots"
        ""]
       (string/join \newline)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let  [{:keys [arguments options] :as opts} (cli/parse-opts args cli-options)
         [action & arguments] arguments]
    (when (:help options)
      (println (usage)))
    (when action
      (case action
        "download" (download/download! arguments)))
    (clojure.pprint/pprint opts)))

