(ns frontend.core
  (:require [reagent.core :as reagent]
            [frontend.views.root :refer [root-element]]))

(enable-console-print!)

(defn container []
  [:div.container
   (:h1 "Ticket Viewer")
   (root-element)])

(reagent/render-component [container]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
