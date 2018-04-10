(ns phoneq.handler.home
  (:require [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key ::index [_ _]
  (fn [_] [::response/ok (io/resource "phoneq/handler/home/index.html")]))
