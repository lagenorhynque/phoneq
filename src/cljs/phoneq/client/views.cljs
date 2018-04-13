(ns phoneq.client.views
  (:require [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.icons :as ic]
            [cljs-react-material-ui.reagent :as ui]
            [phoneq.client.domain.speech-recognition :as speech-recognition]
            [phoneq.client.domain.speech-synthesis :as speech-synthesis]
            [phoneq.client.events :as events]
            [phoneq.client.routes :as routes]
            [phoneq.client.subs :as subs]
            [re-frame.core :as re-frame]
            [stylefy.core :refer [use-style]]))

(defmulti view identity)

;;; home view

(def home-style
  {:margin "20px"
   :padding "20px"
   :display "flex"
   :justify-content "space-around"})

(defmethod view ::home []
  [:div
   "Home"
   [ui/paper (use-style home-style)
    [ui/raised-button {:label "Speaking"
                       :primary true
                       :on-click #(routes/navigate ::speaking)}]
    [ui/raised-button {:label "Dictation"
                       :secondary true
                       :on-click #(routes/navigate ::dictation)}]
    [ui/raised-button {:label "Settings"
                       :on-click #(routes/navigate ::settings)}]]])

(defn home-button []
  [ui/raised-button {:label "Home"
                     :on-click #(routes/navigate ::home)}])

;;; speaking mode view

(def speaking-style
  {:margin "20px"
   :padding "20px"
   :display "flex"
   :justify-content "space-around"
   :flex-direction "column"})

(defn lang-switcher [current-lang]
  [ui/selectable-list {:value current-lang
                       :on-change (fn [_ v]
                                    (re-frame/dispatch [::events/clear-input-text])
                                    (re-frame/dispatch [::events/set-lang
                                                        (keyword v)]))}
   [ui/subheader {} "Language"]
   (for [lang @(re-frame/subscribe [::subs/all-langs])]
     ^{:key lang}
     [ui/list-item {:value lang
                    :primary-text (name lang)}])])

(defmethod view ::speaking []
  (let [lang @(re-frame/subscribe [::subs/current-lang])
        text (first @(re-frame/subscribe [::subs/problems lang]))]
    [:div
     "Speaking Mode"
     [lang-switcher lang]
     [ui/paper (use-style speaking-style)
      [:span text]
      [ui/raised-button {:label "speaker"
                         :primary true
                         :on-click #(speech-synthesis/speak text
                                                            lang)}]
      [ui/raised-button {:label "microphone"
                         :secondary true
                         :on-click #(do (re-frame/dispatch [::events/clear-input-text])
                                        (speech-recognition/record lang))}]
      [:span @(re-frame/subscribe [::subs/input-text])]]
     [home-button]]))

;;; dictation mode view

(def dictation-style
  {:margin "20px"
   :padding "20px"
   :display "flex"
   :justify-content "space-around"
   :flex-direction "column"})

(defmethod view ::dictation []
  (let [lang @(re-frame/subscribe [::subs/current-lang])
        text (first @(re-frame/subscribe [::subs/problems lang]))]
    [:div
     "Dictation Mode"
     [lang-switcher lang]
     [ui/paper (use-style dictation-style)
      [ui/raised-button {:label "speaker"
                         :primary true
                         :on-click #(speech-synthesis/speak text
                                                            lang)}]]
     [home-button]]))

;;; settings view

(def settings-style
  {:margin "20px"
   :padding "20px"
   :display "flex"
   :justify-content "space-around"})

(defmethod view ::settings []
  [:div
   "Settings Mode"
   [ui/paper (use-style settings-style)
    [ui/raised-button {:label "C"
                       :primary true}]]
   [home-button]])

;;; main

(defn main-panel []
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme
                {:palette {:text-color (color :blue600)}})}
   [:div
    [ui/app-bar {:title "Phoneq - phonetic quiz for language learners"
                 :show-menu-icon-button false}]
    [view @(re-frame/subscribe [::subs/current-view])]]])
