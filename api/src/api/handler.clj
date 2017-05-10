(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :refer [response]]
            [api.zendesk-client :refer [get-ticket]]))

(defroutes app-routes
  (GET "/" [] "Ticket Viewer API")

  (GET "/tickets/:ticket-number" [ticket-number]
       (try
         (response (get-ticket ticket-number))

         (catch Exception e
           (let [status (-> e ex-data :response-status)]
             (cond
               (= "404" status)    {:status 404 :body {:error "Unable to get ticket"}}
               (not (nil? status)) {:status 500 :body {:error (str "Zendesk API responded with status: " status)}}
               :else               {:status 500 :body {:error "Something went wrong"}})))))

  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-defaults site-defaults)))
