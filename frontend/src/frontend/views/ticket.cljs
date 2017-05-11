(ns frontend.views.ticket
  (:require [frontend.app-state :refer [app-state]]))

(defn ticket []
  (let [{:keys [number-entered details]} (:ticket @app-state)]
    (if (not (empty? details))
      [:div
       [:h2 "Displaying ticket #" number-entered]
       [:div.ticket-details
        [:p "Subject: " (:subject details)]
        [:p "Description: " (:description details)]
        [:p "Last updated at: " (:updated-at details)]]])))
