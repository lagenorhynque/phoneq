(ns phoneq.client.routes
  (:require [accountant.core :as accountant]
            [bidi.bidi :as bidi]))

(def routes
  ["/" {"" :phoneq.client.views/home
        "speaking" :phoneq.client.views/speaking
        "dictation" :phoneq.client.views/dictation
        "settings" :phoneq.client.views/settings}])

(defn navigate [view]
  (accountant/navigate! (bidi/path-for routes view)))
