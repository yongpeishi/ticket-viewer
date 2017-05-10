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
  (let [{:keys [status body error]} @(http/get url options)]
    (if error
      (println "Failed with exception: " error)
      body)))

(defn get-ticket [ticket-number]
  (let [url            (str api-url "/tickets/" ticket-number ".json")
        convert-to-key true]
    (-> (get-request url options)
        (json/parse-string convert-to-key)
        :ticket
        (select-keys [:subject :description :updated_at]))))
