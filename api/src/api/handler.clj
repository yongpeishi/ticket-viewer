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
         (let [details (get-ticket ticket-number)]
           (if (nil? details)
             {:status 404 :body {:error "Unable to get ticket"}}
             (response details)))

         (catch Exception e
           (let [status (-> e ex-data :response-status)]
             (if status
               {:status 500 :body {:error (str "Zendesk API responded with status: " status)}}
               {:status 500 :body {:error "Something went wrong"}})))))

  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-defaults site-defaults)))
