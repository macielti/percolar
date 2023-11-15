(ns percolar.components
  (:require [com.stuartsierra.component :as component]
            [common-clj.component.config :as component.config]
            [percolar.jobs :as jobs]))

(def system
  (component/system-map
    :config (component.config/new-config "resources/config.edn" :prod :edn)
    :jobs (component/using (jobs/new-jobs) [:config])))

(defn start-system! []
  (component/start system))