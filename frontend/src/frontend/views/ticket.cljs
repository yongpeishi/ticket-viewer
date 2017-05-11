(ns frontend.views.ticket
  (:require [frontend.app-state :refer [app-state]]))

(defn ticket []
  (let [{:keys [number subject description updated-at] :as details} (:single @app-state)]
    (if (not (empty? details))
      [:div
       [:h2 "Displaying ticket #" number]
       [:div.ticket-details
        [:p "Subject: " subject]
        [:p "Description: " description]
        [:p "Last updated at: " updated-at]]])))
