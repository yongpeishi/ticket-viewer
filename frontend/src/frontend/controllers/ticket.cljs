(ns frontend.controllers.ticket
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as m]
            [frontend.api-client :as api]))

(defn set-ticket-number [event]
  (let [value event.target.value]
    (swap! app-state (fn [state]
                       (m/set-number state value)))))

(defn handle-error-response [{:keys [status]}]
  (swap! app-state m/update-page-with-error status))

(defn get-ticket []
  (go
    (let [number         (get-in @app-state [:ticket :number-entered])
          [ok response] (<! (api/get-ticket number))]
      (if ok
        (swap! app-state (fn [state]
                           (m/update-ticket-details state response)))
        (handle-error-response response)))))


