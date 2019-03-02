(ns cljmcs.commands.errors)

(defn group [group]
  (str "No such group \"" group "\" [expected release/snapshot]"))

(defn version [version]
  (str "No such version \"" version "\"."))
