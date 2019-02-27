(ns cljmcs.core
  (:gen-class)
  (:require [cljmcs.commands.list :as list-command]
            [cljmcs.commands.download :as download-command]
            [cljmcs.commands.help :as help-command]))

(defn -main
  [& args]
  (let [[action & arguments] args]
    (if action
      (case action
        "download" (download-command/download! arguments)
        "list"     (list-command/list! (first arguments))
        "help"     (help-command/help!))
      (help-command/help!))))
