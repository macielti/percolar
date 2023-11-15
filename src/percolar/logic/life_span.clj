(ns percolar.logic.life-span
  (:require [clj-time.core :as t]
            [schema.core :as s])
  (:import (org.joda.time DateTime)))

(s/defn days-lived :- s/Int
  [birth-date :- DateTime
   today :- DateTime]
  (t/in-days (t/interval birth-date today)))

(s/defn total-expected-days-life-span :- s/Int
  [birth-date :- DateTime
   expected-years-life-span :- s/Int]
  (t/plus birth-date (t/years expected-years-life-span)))

(s/defn remaining-expected-days-life-span :- s/Int
  [birth-date :- DateTime
   today :- DateTime
   expected-years-life-span :- s/Int]
  (let [expected-death-date (t/plus birth-date (t/years expected-years-life-span))]
    (t/in-days (t/interval today expected-death-date))))
