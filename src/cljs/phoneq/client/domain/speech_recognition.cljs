(ns phoneq.client.domain.speech-recognition
  (:require [clojure.string :as str]
            [goog.object :as object]
            [phoneq.client.events :as events]
            [re-frame.core :as re-frame]))

(def speech-recognition
  (or js/window.SpeechRecognition
      js/webkitSpeechRecognition))

(defn record [lang]
  (let [[lang-code] (str/split (name lang) #"-")
        sr (new speech-recognition)]
    (object/set sr "lang" lang-code)
    (object/set sr "onresult"
                (fn [event]
                  (let [results (object/get event "results")
                        transcripts (volatile! [])]
                    (doseq [i (range (object/get event "resultIndex")
                                     (object/get results "length"))]
                      (vswap! transcripts conj (-> results
                                                   (aget i 0)
                                                   (object/get "transcript"))))
                    ;; DEBUG
                    (println "transcripts:" @transcripts)
                    (re-frame/dispatch [::events/set-input-text
                                        (str/join @transcripts)]))))
    (.start sr)))
