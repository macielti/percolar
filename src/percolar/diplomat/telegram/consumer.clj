(ns percolar.diplomat.telegram.consumer
  (:require [percolar.controllers.life-span :as controllers.life-span]))

(defn notify-life-span-summary
  [{_update          :update
    {:keys [config]} :components}]
  (controllers.life-span/notify-lifespan-summary config))


(def consumers
  {:bot-command {:life-span-summary {:handler notify-life-span-summary}}})