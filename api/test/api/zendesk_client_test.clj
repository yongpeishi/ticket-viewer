(ns api.zendesk-client-test
  (:require [api.zendesk-client :as sut]
            [cheshire.core :as json]
            [clojure.test :refer :all]))

(deftest get-ticket
  (testing "get ticket successfully"
    (let [expected {:subject     "Fire! Fire!"
                    :description "My cat jumped on the table and knocked over the candle."
                    :updated_at  "some date"}]
      (with-redefs [sut/get-request (fn [_ _]
                                      (let [data (assoc expected :random "thing")]
                                        {:status 200
                                         :body (json/generate-string {:ticket data})}))]
        (is (= (sut/get-ticket 1) expected)))))

  (testing "when not 200"
    (with-redefs [sut/get-request (fn [_ _] {:status 404})]
      (is (thrown-with-msg? clojure.lang.ExceptionInfo #"API error" (sut/get-ticket 123))))))

