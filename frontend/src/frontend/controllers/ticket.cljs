(ns frontend.controllers.ticket
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as model]))

(defn set-requested-ticket-number [event]
  (swap! app-state assoc-in [:single] {:number event.target.value}))

(defn get-ticket [number]
  (println "I should call api and get ticket: " number)
  {:subject     "Panda is missing"
   :description "something something something"
   :updated-at  "some date"})

(defn get-ticket-handler []
  (let [number (get-in @app-state [:single :number])
        details (get-ticket number)]
    (swap! app-state (fn [state]
                       (model/update-ticket state details))))
  (println @app-state))


