(ns frontend.views.root
  (:require [frontend.app-state :refer [app-state]]
            [frontend.views.view-options :refer [view-options]]
            [frontend.views.ticket :refer [ticket]]
            [frontend.views.error :as err]))

(defn root-element []
  [:div
   (view-options)
   (case (:screen @app-state)
     :single-ticket (ticket)
     :not-found     (err/not-found)
     :init          nil
     (err/something-went-wrong))])
