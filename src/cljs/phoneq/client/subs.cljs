(ns phoneq.client.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::current-view
  (fn [db _]
    (:view db :phoneq.client.views/home)))
