(ns phoneq.client.events
  (:require [phoneq.client.db :as db]
            [re-frame.core :as re-frame]))

(re-frame/reg-event-db
  ::initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::navigate
  (fn [db [_ {:keys [handler]}]]
    (assoc db :view handler)))
