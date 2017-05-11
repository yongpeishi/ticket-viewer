(ns frontend.views.ticket
  (:require [frontend.app-state :refer [app-state]]))

(defn ticket []
  (let [details (get-in @app-state [:ticket :details])]
    (if (not (empty? details))
      [:div
       [:h2 "Displaying ticket #" (:id details)]
       [:div.ticket-details
        [:p [:span.label "Subject: "]         [:span (:subject details)]]
        [:p [:span.label "Description: "]     [:span (:description details)]]
        [:p [:span.label "Last updated at: "] [:span (:updated_at details)]]]])))
