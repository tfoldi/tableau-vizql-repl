(ns tableau-vizql-repl.core
  (:require [clojure.tools.logging :as log])
  (:gen-class)
  (:import (com.tableausoftware.tabcmd CommandRegistry)))

(def vizql-session-file "vizql.session")

(defn tabcmd-bootstrap-and-login-command
  "Initalize tabcmd and return with a common Command object"
  []
  (CommandRegistry/registerCommands)
  (CommandRegistry/get "login"))

(def tabcmd-login tabcmd-bootstrap-and-login-command)

(defn -main
  "Command line arguments are inherited from tabcmd"
  [& args]
  (log/info "Logging into Tableau Server"))
