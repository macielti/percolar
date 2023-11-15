(ns percolar.components
  (:require [com.stuartsierra.component :as component]
            [common-clj.component.config :as component.config]
            [common-clj.component.telegram.consumer :as component.telegram.consumer]
            [percolar.jobs :as jobs]))

(def system
  (component/system-map
    :config (component.config/new-config "resources/config.edn" :prod :edn)
    :jobs (component/using (jobs/new-jobs) [:config])
    #_:telegram-consumer #_(component/using (component.telegram.consumer/new-telegram-consumer diplomat.telegram.consumer/consumers) [:config :jobs])))

(defn start-system! []
  (component/start system))