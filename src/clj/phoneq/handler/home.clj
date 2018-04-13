(ns phoneq.handler.home
  (:require [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defn index []
  (io/resource "phoneq/handler/home/index.html"))

(defmethod ig/init-key ::index [_ _]
  (fn [_] [::response/ok (index)]))

(defmethod ig/init-key ::speaking [_ _]
  (fn [_] [::response/ok (index)]))

(defmethod ig/init-key ::dictation [_ _]
  (fn [_] [::response/ok (index)]))

(defmethod ig/init-key ::settings [_ _]
  (fn [_] [::response/ok (index)]))
