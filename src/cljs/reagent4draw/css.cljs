(ns reagent4draw.css
  (:require [reagent.core :as r]
            [garden.units :as u]
            [garden.selectors :as s]
            [garden.core :as g]
            [garden.color :as color]
            [garden.stylesheet :as stylesheet]  
            ))

;;http://userbound.com/blog/Garden-with-Clojurescript/

(defn test[](js/alert "test"))


(defn generate-and-inject-style-tag
  "Injects a style tag with the id 'injected-css' into the page's head tag
   Returns generated style tag"
  []
  (let [ page-head (.-head js/document)
        style-tag (.createElement js/document "style")]    
    (.setAttribute style-tag "id" "injected-css")
    (.appendChild page-head style-tag)))

(defn css [& rest]
  (apply g/css rest))

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




