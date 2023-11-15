(defproject percolar "0.1.0"
  :description "Telegram Bot in order to give visibility to time lived"
  :url "https://github.com/macielti/percolar"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [net.clojars.macielti/common-clj "23.44.46"]
                 [clj-time "0.15.2"]
                 [hashp "0.2.1"]]

  :injections [(require 'hashp.core)]

  :repl-options {:init-ns percolar.components}
  :test-paths ["test/unit" "test/integration" "test/helpers"]
  :main percolar.components/start-system!)
