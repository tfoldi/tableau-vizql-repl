(defproject tableau-vizql-repl "0.1.0-SNAPSHOT"
  :description "Tableau VizQL Command Line Client"
  :url "https://github.com/tfoldi/tableau-vizql-repl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["resources", "resources/*"]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot tableau-vizql-repl.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
