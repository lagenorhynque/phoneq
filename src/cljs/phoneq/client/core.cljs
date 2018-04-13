(ns phoneq.client.core
  (:require [accountant.core :as accountant]
            [bidi.bidi :as bidi]
            [cljsjs.material-ui]
            [phoneq.client.config :as config]
            [phoneq.client.events :as events]
            [phoneq.client.routes :refer [routes]]
            [phoneq.client.views :as views]
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
  (accountant/configure-navigation!
   {:nav-handler (fn [path]
                   (re-frame/dispatch [::events/navigate
                                       (bidi/match-route routes path)]))
    :path-exists? (fn [path]
                    (boolean (bidi/match-route routes path)))})
  (accountant/dispatch-current!)
  (stylefy/init)
  (dev-setup)
  (mount-root))
