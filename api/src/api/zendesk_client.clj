(ns api.zendesk-client
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]))

(def api-url
  (let [subdomain (System/getenv "ZENDESK_SUBDOMAIN")]
    (str "https://" subdomain ".zendesk.com/api/v2")))

(def options
  (let [email    (System/getenv "ZENDESK_EMAIL")
        password (System/getenv "ZENDESK_PASSWORD")]
    {:basic-auth [email password]}))

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

(defn get-ticket [ticket-number]
  (let [url                   (str api-url "/tickets/" ticket-number ".json")
        {:keys [status body]} (get-request url options)]
    (case status
      200 (ticket-details body)
      404 nil
      (throw (ex-info "API error" {:response-status status})))))
