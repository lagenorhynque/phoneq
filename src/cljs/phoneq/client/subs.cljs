(ns phoneq.client.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::current-view
  (fn [db _]
    (:view db :phoneq.client.views/home)))

(re-frame/reg-sub
  ::problems
  (fn [db [_ lang]]
    (get-in db [:config lang :problems])))

(re-frame/reg-sub
  ::input-text
  (fn [db _]
    (:input-text db)))

(re-frame/reg-sub
  ::current-lang
  (fn [db _]
    (:lang db)))

(re-frame/reg-sub
  ::all-langs
  (fn [db _]
    (keys (:config db))))
