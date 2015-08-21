(defproject tableau-vizql-repl "0.1.0-SNAPSHOT"
  :description "Tableau VizQL Command Line Client"
  :url "https://github.com/tfoldi/tableau-vizql-repl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["resources", "resources/*"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [commons-logging/commons-logging "1.2"]
                 [commons-cli/commons-cli "1.2"]
                 [com.google.guava/guava "18.0"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]]
  :main ^:skip-aot tableau-vizql-repl.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
