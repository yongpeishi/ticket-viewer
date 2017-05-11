(ns frontend.models.ticket)

(defn update-ticket-details [state details]
  (assoc-in state [:ticket :details] details))

(defn set-number [state num]
  (assoc-in state [:ticket :number-entered] num))
