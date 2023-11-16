(ns percolar.models.life-span
  (:require [schema.core :as s]))

(def life-span-skeleton
  {:life-span/days-lived                         s/Int
   :life-span/total-expected-days                s/Int
   :life-span/remaining-expected-days            s/Int
   :life-span/days-lived-percentage              Float
   :life-span/remaining-expected-days-percentage Float})

(s/defschema LifeSpan
  life-span-skeleton)