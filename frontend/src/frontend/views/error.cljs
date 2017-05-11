(ns frontend.views.error)

(defn error-msg [msg]
  [:div.error msg])

(defn not-found []
  (error-msg "Cannot find the ticket you requested."))

(defn something-went-wrong []
  (error-msg "Something went wrong. Please try again."))
