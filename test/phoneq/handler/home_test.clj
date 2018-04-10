(ns phoneq.handler.home-test
  (:require [clojure.test :as t]
            [integrant.core :as ig]
            [ring.mock.request :as mock]
            [phoneq.handler.home :as home]))

(t/deftest test-index
  (t/testing "index page exists"
    (let [handler  (ig/init-key ::home/index {})
          response (handler (mock/request :get "/"))]
      (t/is "response ok"(= 200 (:status response))))))
