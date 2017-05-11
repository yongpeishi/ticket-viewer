(ns frontend.api-client
  (:require [cljs.core.async :as c]
            [ajax.core :refer [GET]]))

(def api-url "http://localhost:3001")

(defn get-request [url]
  (let [chan (c/promise-chan)]
    (GET url {:response-format :json
              :keywords?       true
              :handler         #(c/put! chan [true %])
              :error-handler   #(c/put! chan [false %])})
    chan))

(defn get-ticket [number]
  (let [url (js/encodeURI (str api-url "/tickets/" number))]
    (get-request url)))
