(ns percolar.controllers.life-span
  (:require [clj-time.core :as t]
            [clostache.parser :as parser]
            [morse.api :as morse-api]
            [schema.core :as s]
            [clj-time.coerce :as time-coerce]
            [percolar.logic.life-span :as logic.life-span]))

(s/defn notify-lifespan-summary
  [{:keys [telegram life-span]}]
  (let [birth-date (-> (:birth-date life-span)
                       time-coerce/from-string
                       time-coerce/to-local-date)
        life-span' (logic.life-span/->life-span birth-date
                                                (t/today)
                                                (:life-span-years-expectation life-span))]
    (morse-api/send-text (:token telegram)
                         (:chat-id telegram)
                         (parser/render-resource
                           (format "%s/life-span-summary.mustache" (:message-template-dir telegram))
                           {:today                   (t/today)
                            :days-lived              (:life-span/days-lived life-span')
                            :days-lived-percentage   (:life-span/days-lived-percentage life-span')
                            :remaining-expected-days (:life-span/remaining-expected-days life-span')}))))