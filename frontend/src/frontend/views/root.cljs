(ns frontend.views.root
  (:require [frontend.app-state :as app-state]
            [frontend.views.view-options :refer [view-options]]
            [frontend.views.ticket :refer [ticket]]))

(defn root-element []
  [:div
   (view-options)
   (ticket)])
