(ns frontend.views.ticket
  (:require [frontend.app-state :refer [app-state]]))

(defn ticket []
  (let [details (get-in @app-state [:ticket :details])]
    (if (not (empty? details))
      [:div
       [:h2 "Displaying ticket #" (:id details)]
       [:div.ticket-details
        [:p "Subject: " (:subject details)]
        [:p "Description: " (:description details)]
        [:p "Last updated at: " (:updated-at details)]]])))
