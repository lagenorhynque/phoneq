(ns phoneq.handler.example-test
  (:require [clojure.test :refer :all]
            [integrant.core :as ig]
            [ring.mock.request :as mock]
            [phoneq.handler.example :as example]))

(deftest smoke-test
  (testing "example page exists"
    (let [handler  (ig/init-key :phoneq.handler/example {})
          response (handler (mock/request :get "/example"))]
      (is (= 200 (:status response)) "response ok"))))
