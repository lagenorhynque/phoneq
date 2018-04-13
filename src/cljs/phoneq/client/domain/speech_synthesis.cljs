(ns phoneq.client.domain.speech-synthesis)

(defn speak [text lang]
  (let [ss (js/SpeechSynthesisUtterance.)]
    (set! (.-lang ss) (name lang))
    (set! (.-text ss) text)
    (js/speechSynthesis.speak ss)))
