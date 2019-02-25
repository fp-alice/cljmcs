(ns cljmcs.core
  (:gen-class)
  (:require [cljmcs.http.scraper :as scraper]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn get-jars-for
  [release-type]
  (let [jars-map (scraper/get-server-maps)
        jars (case release-type
               "release" (:releases jars-map)
               "snapshot" (:snapshots jars-map)
               nil)]
    jars))

(defn get-release-version
  [jars minecraft-version]
  (let [jar-with-version (fn [{:keys [version]
                              :as release}]
                           (when (= version minecraft-version)
                             release))]
    (some jar-with-version jars)))

(defn get-jar-with
  [release-type minecraft-version]
  (let [jars (get-jars-for release-type)]
    (if (nil? jars)
      {:error (str "invalid release type \"" release-type "\" [expected release/snapshot]")}
      (let [selected-version (get-release-version jars minecraft-version)]
        (if (nil? selected-version)
          {:error (str "no such version: " minecraft-version)}
          {:ok selected-version})))))

(defn list-versions
  [release-type]
  (let [jars (get-jars-for release-type)]
    (map :version jars)))
