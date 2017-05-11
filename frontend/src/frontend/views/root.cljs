(ns frontend.views.root
  (:require [frontend.views.get-ticket-form :refer [get-ticket-form]]
            [frontend.views.ticket :refer [ticket]]
            [frontend.views.error :as err]))

(defn root-element [state]
  (let [{:keys [number-entered details]} (:ticket state)]
    [:div
     (:h1 "Ticket Viewer")
     (get-ticket-form number-entered)
     (case (:screen state)
       :single-ticket (ticket details)
       :not-found     (err/not-found)
       :init          nil
       (err/something-went-wrong))]))
