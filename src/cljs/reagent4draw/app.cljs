(ns reagent4draw.app
  (:require [reagent.core :as reagent :refer [atom]]
            [garden.units :as u]
            [garden.selectors :as s]
            [garden.core :as g]
            [garden.color :as color]
            [garden.stylesheet :as stylesheet]
            ))

(defn some-component []
  [:div
   [:h3 "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red !!!"]
    " text."]])

(defn calling-component []
  [:div "Parent component"
   [some-component]])

(defn init []
  (reagent/render-component [calling-component]
                            (.getElementById js/document "container")))

(init)

(defn heading-content []
  [:div
   [:h1 "This is heading 1"]
   [:p "paragraph test" ]
   [:p [:b "paragraph"] [:i " test"]]
   [:p [:abbrev {:title "professor"} "ppp" ] ]
   [:h2 "This is heading 2"]
   [:p [:strong "test"]]
   [:p "CO" [:sub "2"]]
   [:p "10" [:sup "10"]]
   [:h3 "This is heading 3"]
   [:p [:blockqote "This is heading 3"]]
   [:h4 "This is heading 4"]
   [:h5 "This is heading 5"]
   [:h6 "This is heading 6"]      
   ])

(reagent/render-component [heading-content]
                          (. js/document (getElementById "container")))

(defn- generate-and-inject-style-tag
  "Injects a style tag with the id 'injected-css' into the page's head tag
   Returns generated style tag"
  []
  (let [ page-head (.-head js/document)
        style-tag (.createElement js/document "style")]    
    (.setAttribute style-tag "id" "injected-css")
    (.appendChild page-head style-tag)))

(defn update-page-css
  "Updates #injected-css with provided argument (should be some CSS string 
   -- e.g. output from garden's css fn) If page does not have #injected-css then
   will create it via call to generate-and-inject-style-tag"
  [input-css]
  (let [ style-tag-selector "#injected-css"
        style-tag-query (.querySelector js/document style-tag-selector)
        style-tag (if (nil? style-tag-query)
                    (generate-and-inject-style-tag) 
                    style-tag-query)]
    (aset style-tag "innerHTML" input-css)))


(update-page-css 
 (g/css"
          :font-family "Georgia, Times, serif,あんずもじ湛" :background-color (color/rgb 255 255 255) :opacity 0.8}]
  ["h1, h5" {:color "red" :font-size "100%"
             :font-family "あんずもじ湛" :background-color (color/rgb 0 0 0)
             :font-weight "bold" :font-style "italic" :word-spacing "1em"}]
  [:h2 {:color "red" :font-size "150%"
        :font-family "あんずもじ湛" :background-color (color/rgb 0 0 0)}]
  [:h3 {:color "red" :font-size "120%" :text-transform "uppercase"
        :font-family "あんずもじ湛" :background-color (color/rgb 0 0 0)}]
  
  [:p {:text-align "center" :text-decoration "underline" :line-height "0.5em"}]
  ))


