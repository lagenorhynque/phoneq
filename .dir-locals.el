((nil . ((cider-refresh-before-fn . "integrant.repl/suspend")
         (cider-refresh-after-fn  . "integrant.repl/resume")))
 (clojure-mode . ((eval . (define-clojure-indent
                            ;; re-frame
                            (reg-cofx :defn)
                            (reg-event-db :defn)
                            (reg-event-fx :defn)
                            (reg-fx :defn)
                            (reg-sub :defn))))))
