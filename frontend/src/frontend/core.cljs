(ns frontend.core
  (:require [reagent.core :as reagent]
            [frontend.app-state :refer [app-state]]
            [frontend.views.view-options :refer [view-options]]))

(enable-console-print!)

(defn container []
  [:div.container
   (:h1 "Ticket Viewer")
   (view-options)])

(reagent/render-component [container]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
