(ns frontend.views.get-ticket-form
  (:require [frontend.controllers.ticket :as c]))

(defn get-ticket-form [number-entered]
  [:div
   [:p
    [:span "Enter ticket number: "]
    [:span [:input {:type      "text"
                    :value     number-entered
                    :on-change c/set-ticket-number}]]
    [:button {:type     "submit"
              :on-click c/get-ticket}
     "View"]]])


