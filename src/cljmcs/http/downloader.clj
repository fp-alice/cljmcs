(ns cljmcs.http.downloader
  (:require [cljmcs.minecraft.servers :as servers]
            [cljmcs.os.files :as files]
            [clj-http.client :as http]))

(defn- request-download!
  "Requests download from location"
  [location]
  (try
    (let [response (http/get location {:as :stream})
          stream   (:body response)]
      stream)
    (catch Exception e (println e))))

(defn download-jar!
  "Downloads jar with map from `servers/get-server-jar` to the current directory as `file`
  Returns nil on failure"
  [{:keys [version file location]}]
  (if-some [stream (request-download! location)]
    (files/write-stream-to-file! stream file)))


