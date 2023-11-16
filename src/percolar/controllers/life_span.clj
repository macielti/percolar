(ns percolar.controllers.life-span
  (:require [morse.api :as morse-api]
            [schema.core :as s]))

(s/defn notify-lifespan-summary
  [{:keys [telegram life-span]}]
  (let []
    (morse-api/send-text (:token telegram)
                         (:chat-id telegram)
                         (str))))