(ns percolar.jobs
  (:require [com.stuartsierra.component :as component]
            [overtone.at-at :as at-at]))

(defrecord Jobs [config]
  component/Lifecycle
  (start [component]
    (let [pool (at-at/mk-pool)]

      (merge component {:jobs {:pool pool}})))

  (stop [{:keys [service]}]
    (at-at/stop-and-reset-pool! (:pool service) :stop)))

(defn new-jobs []
  (->Jobs {}))