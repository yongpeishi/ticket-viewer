(ns frontend.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state
  (atom {:view   :single
         :single {}}))

(defn set-requested-ticket-number [event]
  (swap! app-state assoc-in [:single] {:number event.target.value}))

(defn get-ticket [number]
  (println "I should call api and get ticket: " number))

(defn get-ticket-handler []
  (let [number (get-in @app-state [:single :number])]
    (get-ticket number)))

;; TODO: move to view
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

(defn container []
  [:div.container
   (:h1 "Ticket Viewer")
   (view-options)
   ])

(reagent/render-component [container]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
