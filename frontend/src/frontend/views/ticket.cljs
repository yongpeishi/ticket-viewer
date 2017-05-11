(ns frontend.views.ticket)

(defn ticket [details]
  (if (not (empty? details))
    (let [{:keys [id subject description updated_at]} details]
      [:div
       [:h2 "Ticket #" id]
       [:div.ticket-details
        [:div.section
         [:label "Subject:"]
         [:div subject]]

        [:div.section
         [:label "Description:"]
         [:div description]]

        [:div.section
         [:label "Last updated at:"]
         [:div updated_at]]]])))
