(ns frontend.core
  (:require [reagent.core :as reagent]
            [frontend.app-state :refer [app-state]]
            [frontend.views.root :refer [root-element]]))

(enable-console-print!)

(defn container []
  (root-element @app-state))

(reagent/render-component [container]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
