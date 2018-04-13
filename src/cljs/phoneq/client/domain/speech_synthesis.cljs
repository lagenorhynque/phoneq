(ns phoneq.client.domain.speech-synthesis
  (:require [goog.object :as object]))

(defn speak [text lang]
  (let [ss (js/SpeechSynthesisUtterance.)]
    (object/set ss "lang" (name lang))
    (object/set ss "text" text)
    (js/speechSynthesis.speak ss)))
