(ns frontend.controllers.ticket
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as m]
            [frontend.api-client :as api]))

(defn set-ticket-number [event]
  (swap! app-state m/set-number event.target.value))

(defn handle-error-response [{:keys [status]}]
  (swap! app-state m/update-page-with-error status))

(defn handle-success-response [response]
  (swap! app-state m/update-ticket-details response))

(defn get-ticket []
  (go
    (let [number        (get-in @app-state [:ticket :number-entered])
          [ok response] (<! (api/get-ticket number))]
      (if ok
        (handle-success-response response)
        (handle-error-response response)))))
