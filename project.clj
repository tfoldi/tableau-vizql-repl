(defproject tableau-vizql "0.1.0-SNAPSHOT"
  :description "Tableau VizQL Command Line Client"
  :url "https://github.com/tfoldi/tableau-vizql-repl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["resources", "resources/*"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [commons-logging/commons-logging "1.2"]
                 [dom4j/dom4j "1.6.1"]
                 [jaxen/jaxen "1.1.6"]
                 [com.googlecode.json-simple/json-simple "1.1.1"]
                 [commons-codec/commons-codec "1.10"]
                 [commons-io/commons-io "2.4"]
                 [commons-cli/commons-cli "1.2"]
                 [com.google.guava/guava "18.0"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]]
  :main ^:skip-aot tableau-vizql.repl
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
