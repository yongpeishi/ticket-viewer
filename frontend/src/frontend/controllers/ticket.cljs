(ns frontend.controllers.ticket
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [frontend.app-state :refer [app-state]]
            [frontend.models.ticket :as ticket]
            [frontend.models.screen :as screen]
            [frontend.api-client :as api]))

(defn set-ticket-number [event]
  (swap! app-state ticket/set-number event.target.value))

(defn handle-error-response [{:keys [status]}]
  (swap! app-state screen/set-error-with-status status))

(defn handle-success-response [response]
  (swap! app-state ticket/update-ticket-details response))

(defn show-loading-screen []
  (swap! app-state screen/set-loading))

(defn get-ticket []
  (go
    (show-loading-screen)
    (let [number        (get-in @app-state [:ticket :number-entered])
          [ok response] (<! (api/get-ticket number))]
      (if ok
        (handle-success-response response)
        (handle-error-response response)))))
