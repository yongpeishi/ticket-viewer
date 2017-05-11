(ns frontend.views.view-options
  (:require [frontend.app-state :refer [app-state]]
            [frontend.controllers.ticket :as c]))

(defn view-options []
  [:div
   [:p
    [:span "View ticket number:"]
    [:span [:input {:type      "text"
                    :value     (get-in @app-state [:ticket :number-entered])
                    :on-change c/set-ticket-number}]]
    [:button {:type     "submit"
              :on-click c/get-ticket}
     "Submit"]]])


