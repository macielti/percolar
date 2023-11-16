(ns percolar.logic.life-span
  (:require [clj-time.core :as t]
            [schema.core :as s]
            [percolar.models.life-span :as models.life-span]
            [clj-time.coerce :as time-coerce])
  (:import (org.joda.time LocalDate)))

(s/defn days-lived :- s/Int
  [birth-date :- LocalDate
   today :- LocalDate]
  (t/in-days (t/interval (time-coerce/to-date-time birth-date) (time-coerce/to-date-time today))))

(s/defn total-expected-days-life-span :- s/Int
  [birth-date :- LocalDate
   expected-years-life-span :- s/Int]
  (t/in-days (t/interval
               (time-coerce/to-date-time birth-date)
              (t/plus (time-coerce/to-date-time birth-date) (t/years expected-years-life-span)))))

(s/defn remaining-expected-days-life-span :- s/Int
  [birth-date :- LocalDate
   today :- LocalDate
   expected-years-life-span :- s/Int]
  (let [expected-death-date (t/plus (time-coerce/to-date-time birth-date) (t/years expected-years-life-span))]
    (t/in-days (t/interval (time-coerce/to-date-time today) expected-death-date))))

(s/defn lived-percentage :- s/Num
  [total-days-expected :- s/Int
   already-lived :- s/Int]
  (float (* (/ already-lived total-days-expected) 100)))

(s/defn remaining-expected-days-to-live-percentage :- s/Num
  [total-days-expected :- s/Int
   remaining-expected-days-life :- s/Int]
  (float (* (/ remaining-expected-days-life total-days-expected) 100)))

(s/defn ->life-span :- models.life-span/LifeSpan
  [birth-date :- LocalDate
   today :- LocalDate
   years-expected :- s/Int]
  (let [total-expected-days (total-expected-days-life-span birth-date years-expected)
        days-lived' (days-lived birth-date today)
        remaining-expected-days (remaining-expected-days-life-span birth-date today years-expected)]
    {:life-span/days-lived                         days-lived'
     :life-span/total-expected-days                total-expected-days
     :life-span/remaining-expected-days            remaining-expected-days
     :life-span/days-lived-percentage              (lived-percentage total-expected-days days-lived')
     :life-span/remaining-expected-days-percentage (remaining-expected-days-to-live-percentage total-expected-days remaining-expected-days)}))
