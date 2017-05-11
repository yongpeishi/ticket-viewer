(ns frontend.app-state
  (:require [reagent.core :as reagent]))

(defonce app-state
  (reagent/atom {:screen :init
                 :ticket {:number-entered ""
                          :details        {}}}))

