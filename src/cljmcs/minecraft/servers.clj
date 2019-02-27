(ns cljmcs.minecraft.servers
  (:require [cljmcs.http.scraper :as scraper]))

(defn get-jars-in-group
  "Used to select either all release jars or all snapshot jars.
  `(get-jars-in-group \"release\")` or `(get-jars-in-group \"snapshot\")`
  Returns nil otherwise."
  [group]
  (let [jars-map (scraper/get-minecraft-jars)
        jars (case group
               "release" (:releases jars-map)
               "snapshot" (:snapshots jars-map)
               nil)]
    jars))

(defn get-release-version
  "Selects a specific version from a selection of jars, returns nil if none."
  [jars minecraft-version]
  (let [jar-with-version (fn [{:keys [version]
                               :as release}]
                           (when (= version minecraft-version)
                             release))]
    (some jar-with-version jars)))

(defn get-server-jar
  "Used to get the map that represents the jar using the group and version."
  [group minecraft-version]
  (let [jars (get-jars-in-group group)]
    (if (nil? jars)
      [:error (str "No such group \"" group "\" [expected release/snapshot]")]
      (let [selected-version (get-release-version jars minecraft-version)]
        (if (nil? selected-version)
          [:error (str "No such version: " minecraft-version)]
          [:ok selected-version])))))

(defn list-versions-in-group
  "Used to list all of the versions in a release group. (release or snapshot)"
  [group]
  (if-some [jars (get-jars-in-group group)]
    (map :version jars)))
