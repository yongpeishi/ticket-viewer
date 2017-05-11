(ns frontend.models.ticket)

(defn set-number [state num]
  (assoc-in state [:ticket :number-entered] num))

(defn update-ticket-details [state details]
  (-> state
      (assoc-in [:screen] :single-ticket)
      (assoc-in [:ticket :details] details)))

