(ns frontend.controllers.ticket
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as m]))

(defn set-ticket-number [event]
  (let [value event.target.value]
    (swap! app-state (fn [state]
                       (m/set-number state value)))))

(defn get-ticket [number]
  (println "******I should call api and get ticket: " number)
  {:subject     "Panda is missing"
   :description "something something something"
   :updated-at  "some date"})

(defn get-ticket-handler []
  (let [number (get-in @app-state [:single :number])
        details (get-ticket number)]
    (swap! app-state (fn [state]
                       (m/update-ticket-details state details))))
  (println @app-state))


