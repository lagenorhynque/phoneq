(ns phoneq.client.views
  (:require [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.icons :as ic]
            [cljs-react-material-ui.reagent :as ui]
            [re-frame.core :as re-frame]
            [stylefy.core :refer [use-style]]))

(def body-style
  {:margin "20px"})

(defn main-panel []
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme
                {:palette {:text-color (color :blue600)}})}
   [:div
    [ui/app-bar {:title "Phoneq - phonetic quiz for language learners"
                 :show-menu-icon-button false}]
    [:div
     [ui/raised-button (use-style body-style {:label "start"})]]]])
