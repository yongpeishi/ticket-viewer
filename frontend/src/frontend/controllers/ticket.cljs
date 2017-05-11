(ns frontend.controllers.ticket
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as m]
            [frontend.api-client :as api]))

(defn set-ticket-number [event]
  (let [value event.target.value]
    (swap! app-state (fn [state]
                       (m/set-number state value)))))

(defn get-ticket-handler []
  (go
    (let [number  (get-in @app-state [:ticket :number-entered])
          details (<! (api/get-ticket number))]
      (swap! app-state (fn [state]
                         (m/update-ticket-details state details))))))


