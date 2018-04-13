(ns phoneq.client.events
  (:require [phoneq.client.db :as db]
            [re-frame.core :as re-frame]))

(re-frame/reg-event-db
  ::initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::set-current-view
  (fn [db [_ {:keys [handler]}]]
    (assoc db :view handler)))

(re-frame/reg-event-db
  ::set-input-text
  (fn [db [_ text]]
    (assoc db :input-text text)))

(re-frame/reg-event-db
  ::clear-input-text
  (fn [db _]
    (assoc db :input-text nil)))

(re-frame/reg-event-db
  ::set-lang
  (fn [db [_ lang]]
    (assoc db :lang lang)))
