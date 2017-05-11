(ns frontend.app-state
  (:require [reagent.core :as reagent]))

(defonce app-state
  (reagent/atom {:display nil
                 :ticket {:number-entered ""
                          :details        {}}}))

