(ns frontend.views.ticket
  (:require [frontend.app-state :refer [app-state]]))

(defn ticket []
  (let [details (get-in @app-state [:ticket :details])]
    (if (not (empty? details))
      (let [{:keys [id subject description updated_at]} details]
        [:div
         [:h2 "Displaying ticket #" id]
         [:div.ticket-details
          [:p [:span.label "Subject: "]         [:span subject]]
          [:p [:span.label "Description: "]     [:span description]]
          [:p [:span.label "Last updated at: "] [:span updated_at]]]]))))
