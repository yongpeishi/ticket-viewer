(ns frontend.views.view-options
  (:require [frontend.controllers.ticket :as c]))

(defn view-options [number-entered]
  [:div
   [:p
    [:span "View ticket number:"]
    [:span [:input {:type      "text"
                    :value     number-entered
                    :on-change c/set-ticket-number}]]
    [:button {:type     "submit"
              :on-click c/get-ticket}
     "Submit"]]])


