(ns tableau-vizql.repl
  (:require [clojure.tools.logging :as log])
  (:gen-class)
  (:import (com.tableausoftware.tabcmd CommandRegistry CommandLineUtils ResponseException VizqlSession RedirectException)
           (com.tableausoftware.tabcmd.session SessionOptions Session)
           (com.tableausoftware.tabcmd.http HttpUtils)))

(def vizql-session-file "vizql.session")

(def session nil)

(defn tabcmd-register-commands
  "Initalize tabcmd and return with a common Command object"
  []
  (CommandRegistry/registerCommands))

(defn parse-command-line
  "Build an org.apache.commons CommandLine object from command line options"
  [& args]
  (clojure.pprint/pprint args)
  (->>
    (flatten args)
    (into-array String)
    (CommandLineUtils/cleanseCommandLine)
    (CommandLineUtils/parse (SessionOptions/getOptions))))

(defn- log-http-response-exception
  [exc]
  (log/error (HttpUtils/getErrorMessageFromResponse (.getRequest exc)))
  (when (= (class exc) "com.tableausoftware.tabcmd.RedirectException")
    (log/error ("Unexpected redirect: " (.getRedirectUrl exc)))))

(defn login
  "login to the server"
  [session]
  (try
    (.logout session)
    (.login session)
    (catch ResponseException exc
      (log-http-response-exception exc)
      (throw exc))))

(defn open-viz
  ([session viz width height]
   (try
     (VizqlSession. session viz width height)
     (catch ResponseException exc
       (log-http-response-exception exc))))
  ([session viz]
   (open-viz session viz 800 600)))

(defn session-from-command-line
  [args]
  (Session. vizql-session-file (parse-command-line (into [] args))))

(defn -main
  "Command line arguments are inherited from tabcmd"
  [& args]
  (log/info "Logging into Tableau Server")
  (tabcmd-register-commands)
  ; build and bind session as VizqlSession
  (with-redefs [session (session-from-command-line args)]
    (in-ns 'tableau-vizql.repl)
    (clojure.main/repl)))
