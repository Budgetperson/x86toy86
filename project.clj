(defproject transpiler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main transpiler.core
  :jvm-opts ["-Xmx1g"]
  :profiles {:uberjar {:aot :all}})
