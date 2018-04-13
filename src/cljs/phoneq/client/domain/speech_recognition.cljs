(ns phoneq.client.domain.speech-recognition
  (:require [clojure.string :as str]
            [phoneq.client.events :as events]
            [re-frame.core :as re-frame]))

(def speech-recognition
  (or js/window.SpeechRecognition
      js/webkitSpeechRecognition))

(defn record [lang]
  (let [[lang-code] (str/split (name lang) #"-")
        sr (new speech-recognition)]
    (set! (.-lang sr) lang-code)
    (set! (.-onresult sr)
          (fn [event]
            (let [results (.-results event)
                  transcripts (volatile! [])]
              (doseq [i (range (.-resultIndex event)
                               (.-length results))]
                (vswap! transcripts conj (-> results
                                             (aget i 0)
                                             .-transcript )))
              ;; DEBUG
              (println "transcripts:" @transcripts)
              (re-frame/dispatch [::events/set-input-text
                                  (str/join @transcripts)]))))
    (.start sr)))
