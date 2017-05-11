(ns frontend.views.root
  (:require [frontend.app-state :refer [app-state]]
            [frontend.views.view-options :refer [view-options]]
            [frontend.views.ticket :refer [ticket]]
            [frontend.views.error :as err]))

(defn root-element []
  [:div
   (view-options)
   (case (:display @app-state)
     :single-ticket        (ticket)
     :not-found            (err/not-found)
     :something-went-wrong (err/something-went-wrong)
     nil)])
