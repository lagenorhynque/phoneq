(ns phoneq.client.views
  (:require [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.icons :as ic]
            [cljs-react-material-ui.reagent :as ui]
            [phoneq.client.events :as events]
            [phoneq.client.routes :as routes]
            [phoneq.client.subs :as subs]
            [re-frame.core :as re-frame]
            [stylefy.core :refer [use-style]]))

(def body-style
  {:margin "20px"
   :padding "20px"
   :display "flex"
   :justify-content "space-around"})

(defmulti view identity)

;;; home view

(defmethod view ::home []
  [:div
   "Home"
   [ui/paper (use-style body-style)
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

(defmethod view ::speaking []
  [:div
   "Speaking Mode"
   [ui/paper (use-style body-style)
    [ui/raised-button {:label "speaker"
                       :primary true}]]
   [home-button]])

;;; dictation mode view

(defmethod view ::dictation []
  [:div
   "Dictation Mode"
   [ui/paper (use-style body-style)
    [ui/raised-button {:label "speaker"
                       :primary true}]]
   [home-button]])

;;; settings view

(defmethod view ::settings []
  [:div
   "Settings Mode"
   [ui/paper (use-style body-style)
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
