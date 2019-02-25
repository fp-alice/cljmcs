(ns cljmcs.http.scraper
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as http]))

(defn get-dom
  []
  (html/html-snippet
   (:body (http/get "https://mcversions.net/"))))

(defn parse-server-element
  [{:keys [href download]}]
  {:file download
   :location  href})

(defn extract-list-groups
  [dom]
  (let [[releases snapshots & rest] (html/select dom [:ul.list-group])]
    [releases snapshots]))

(defn extract-server-element
  [dom]
  (-> (html/select dom [:a.server])
      first
      :attrs
      parse-server-element))

(defn parse-release-element
  [elem]
  (let [version (-> elem
                 :attrs
                 :id)
        server (extract-server-element elem)]
    (merge {:version version} server)))

(defn extract-release-info
  [dom]
  (let [elems (html/select dom [:li.release])]
    (filter
     #(not-any? nil? [(:location %) (:file %)])
     (map parse-release-element elems))))

(defn write-dataset-edn! [out-file raw-dataset-map]
  (with-open [w (clojure.java.io/writer out-file)]
    (binding [*out* w]
      (clojure.pprint/write raw-dataset-map))))

(defn get-server-maps
  []
  (let [dom (get-dom)
        [releases snapshots] (extract-list-groups dom)]
    {:releases (extract-release-info releases)
     :snapshots (extract-release-info snapshots)}))
