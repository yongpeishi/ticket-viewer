(ns frontend.models.ticket)

(defn set-number [state num]
  (assoc-in state [:ticket :number-entered] num))

(defn update-ticket-details [state details]
  (-> state
      (assoc-in [:display] :single-ticket)
      (assoc-in [:ticket :details] details)))

(defn update-page-with-error [state status]
  (assoc-in state [:display] (if (= 404 status)
                               :not-found
                               :something-went-wrong)))
