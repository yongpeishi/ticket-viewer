(ns frontend.views.view-options
  (:require [frontend.app-state :refer [app-state]]))


;;TODO: move to controller
(defn set-requested-ticket-number [event]
  (swap! app-state assoc-in [:single] {:number event.target.value}))

(defn get-ticket [number]
  (println "I should call api and get ticket: " number))

(defn get-ticket-handler []
  (let [number (get-in @app-state [:single :number])]
    (get-ticket number)))

(defn view-options []
  [:div
   [:p
    [:span "View ticket number:"]
    [:span [:input {:type      "text"
                    :value     (get-in @app-state [:single :number])
                    :on-change set-requested-ticket-number}]]
    [:button {:type "submit"
              :on-click get-ticket-handler}
     "Submit"]]])


