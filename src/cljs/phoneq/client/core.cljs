(ns phoneq.client.core
  (:require [cljsjs.material-ui]
            [phoneq.client.events :as events]
            [phoneq.client.views :as views]
            [phoneq.client.config :as config]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [stylefy.core :as stylefy]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (stylefy/init)
  (mount-root))
