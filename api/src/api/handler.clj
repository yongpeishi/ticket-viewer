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
       (let [result (get-ticket ticket-number)]
         (response result)))

  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-defaults site-defaults)))
