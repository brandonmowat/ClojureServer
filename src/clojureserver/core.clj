(ns clojureserver.core
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :refer [run-server]]
            [clojure.string :as str])
  (:gen-class))

(defroutes clojureserver
  (GET "/" [] "Hello World"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (prn 'STARTING-SERVER "http://localhost:5000")
  (def stop-server
    (-> (wrap-reload #'clojureserver)
        (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
        (run-server {:port (Integer/parseInt (System/getenv "PORT"))}))))