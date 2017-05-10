(ns api.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [api.handler :refer :all]
            [api.zendesk-client :as z]))

(deftest test-app
  (testing "root"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Ticket Viewer API"))))

  (testing "/tickets/:ticket-number found ticket successfully"
    (with-redefs [z/get-ticket (fn [ticket-number]
                                 (is (= "123" ticket-number))
                                 {:subject "abc"
                                  :description "something"
                                  :updated_at "some date"})]
      (let [response (app (mock/request :get "/tickets/123"))]
        (is (= (:status response) 200))
        (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8")))))

  (testing "return 404 when ticket not found"
    (with-redefs [z/get-ticket (fn [_] nil)]
      (let [response (app (mock/request :get "/tickets/123"))]
        (is (= (:status response) 404))
        (is (= (:body {:error "Unable to get ticket"}))))))

  (testing "return 500 when api responded with error"
    (with-redefs [z/get-ticket (fn [_] (throw (ex-info "something" {:response-status "401"})))]
      (let [response (app (mock/request :get "/tickets/123"))]
        (is (= (:status response) 500))
        (is (= (:body {:error "Zendesk API responded with status: 401"}))))))

  (testing "return 500 on all other errors"
    (with-redefs [z/get-ticket (fn [_] (throw (ex-info "something really serious")))]
      (let [response (app (mock/request :get "/tickets/123"))]
        (is (= (:status response) 500))
        (is (= (:body {:error "Something went wrong"}))))))
    )

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))
