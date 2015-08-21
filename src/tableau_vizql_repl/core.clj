(ns tableau-vizql-repl.core
  (:require [clojure.tools.logging :as log])
  (:gen-class)
  (:import (com.tableausoftware.tabcmd CommandRegistry CommandLineUtils)
           (com.tableausoftware.tabcmd.session SessionOptions Session)))

(def vizql-session-file "vizql.session")

(defn tabcmd-register-and-login-command
  "Initalize tabcmd and return with a common Command object"
  []
  (CommandRegistry/registerCommands)
  (CommandRegistry/get "login"))

(defn parse-command-line
  "Build an org.apache.commons CommandLine object from command line options"
  [args]
  (let [clean-args (CommandLineUtils/cleanseCommandLine (into-array String args))]
    (->
      (.getOptions (tabcmd-register-and-login-command) (SessionOptions/getOptions))
      (CommandLineUtils/parse clean-args))))

(defn -main
  "Command line arguments are inherited from tabcmd"
  [& args]
  (log/info "Logging into Tableau Server")
  ; register tabcmd commands, parse command line arguments and establish
  ; session
  (let [session (Session. vizql-session-file (parse-command-line args))]
    (.login session)
    (log/info "Session established")
    ))
