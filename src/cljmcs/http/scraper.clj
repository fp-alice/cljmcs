(ns cljmcs.http.scraper
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as http]))

(defn- get-dom
  "Requests the page"
  []
  (html/html-snippet
   (:body (http/get "https://mcversions.net/"))))

(defn- parse-server-element
  "Reformats the server element to be nicer looking"
  [{:keys [href download]}]
  {:file download
   :location  href})

(defn- extract-list-groups
  "Gets the release columns from the page and only returns the first two"
  [dom]
  (let [[releases snapshots & rest] (html/select dom [:ul.list-group])]
    [releases snapshots]))

(defn- extract-server-element
  "Selects the server element from html"
  [dom]
  (-> (html/select dom [:a.server])
      first
      :attrs
      parse-server-element))

(defn- parse-release-element
  "Gets the version with the release element, then extracts the server element from it"
  [elem]
  (let [version (-> elem
                    :attrs
                    :id)
        server (extract-server-element elem)]
    (merge {:version version} server)))

(defn- extract-release-info
  "Extracts all the release elements, filters ones with server downloads, and parses them all"
  [dom]
  (let [elems (html/select dom [:li.release])]
    (filter
     #(not-any? nil? [(:location %) (:file %)])
     (map parse-release-element elems))))

(defn get-minecraft-jars
  "Gets a map of all available server downloads grouped into `:releases` and `:snapshots`"
  []
  (let [dom                  (get-dom)
        [releases snapshots] (extract-list-groups dom)]
    {:releases  (extract-release-info releases)
     :snapshots (extract-release-info snapshots)}))
