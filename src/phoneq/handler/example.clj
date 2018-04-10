(ns phoneq.handler.example
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :phoneq.handler/example [_ options]
  (context "/example" []
    (GET "/" []
      {:body {:example "data"}}(io/resource "phoneq/handler/example/example.html"))))
