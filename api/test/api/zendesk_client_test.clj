(ns api.zendesk-client-test
  (:require [api.zendesk-client :as sut]
            [cheshire.core :as json]
            [clojure.test :refer :all]))

(deftest get-ticket
  (testing "return ticket details when get successfully"
    (let [expected {:subject     "Fire! Fire!"
                    :description "My cat jumped on the table and knocked over the candle."
                    :updated_at  "some date"}]
      (with-redefs [sut/get-request (fn [_ _]
                                      (let [data (assoc expected :random "thing")]
                                        {:status 200
                                         :body (json/generate-string {:ticket data})}))]
        (is (= (sut/get-ticket 1) expected)))))

  (testing "return nil when not found"
    (with-redefs [sut/get-request (fn [_ _] {:status 404})]
      (is (= (sut/get-ticket 1) nil))))

  (testing "throw error when status not 200 or 404"
    (with-redefs [sut/get-request (fn [_ _] {:status 401})]
      (is (thrown-with-msg? clojure.lang.ExceptionInfo #"API error" (sut/get-ticket 123))))))
