(defproject cljmcs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [clj-http "3.9.1"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/core.match "0.3.0-alpha5"]]
  :main ^:skip-aot cljmcs.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
