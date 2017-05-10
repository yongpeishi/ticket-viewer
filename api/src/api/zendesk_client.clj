(ns api.zendesk-client
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]))

(defn api-url [subdomain]
  (str "https://" subdomain ".zendesk.com/api/v2"))

(defn get-request [url options]
  (let [response @(http/get url options)
        error    (:error response)]
    (if error
      (throw error)
      (select-keys response [:status :body]))))

(defn ticket-details [response-body]
  (let [convert-to-keyword true]
    (-> (json/parse-string response-body convert-to-keyword)
        :ticket
        (select-keys [:subject :description :updated_at]))))

(defn get-ticket [ticket-number config]
  (let [endpoint              (api-url (:subdomain config))
        url                   (str endpoint "/tickets/" ticket-number ".json")
        options               {:basic-auth [(:user config) (:password config)]}
        {:keys [status body]} (get-request url options)]
    (case status
      200 (ticket-details body)
      404 nil
      (throw (ex-info "API error" {:response-status status})))))
