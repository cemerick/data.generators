(defproject com.cemerick/data.generators "0.1.2-1"
  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]
  :source-paths ["src/main/cljx"]
  :resource-paths ["src/main/resources"]
  :test-paths ["src/test/clojure" "src/test/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1830"]]

  :cljx {:builds [{:source-paths ["src/main/cljx"]
                   :output-path "target/classes"
                   :include-meta true
                   :nested-exclusions true
                   :maintain-form-position true
                   :rules cljx.rules/clj-rules}

                  {:source-paths ["src/main/cljx"]
                   :output-path "target/classes"
                   :extension "cljs"
                   :include-meta true
                   :nested-exclusions true
                   :maintain-form-position true
                   :rules cljx.rules/cljs-rules}]}

  ; TODO cljsbuild busted due to https://github.com/emezeske/lein-cljsbuild/issues/210
  :cljsbuild {:test-commands {"phantom" ["runners/phantomjs.js" "target/testable.js"]}
              :builds [{:source-paths ["target/classes" "src/main/resources"]
                        :compiler {:output-to "target/testable.js"
                                   :libs ["src/main/resources/seedrandom.js"]
                                   ; TODO test with advanced optimization
                                   :optimizations :whitespace
                                   :pretty-print true}}]}

  :profiles {:dev {:dependencies [[com.cemerick/clojurescript.test "0.0.4"]
                                  [com.cemerick/piggieback "0.0.5-SNAPSHOT"]
                                  [org.clojars.cemerick/cljx "0.2.3-SNAPSHOT"]]
                   :plugins [[org.clojars.cemerick/cljx "0.2.3-SNAPSHOT"]
                             [lein-cljsbuild "0.3.2"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl
                                                     cljx.repl-middleware/wrap-cljx]}
                   :injections [(require '[cljs.repl.browser :refer (exec-env)]
                                         '[cemerick.piggieback :refer (cljs-repl)])
                                (defn browser-repl []
                                  (cljs-repl :repl-env
                                    (doto (exec-env)
                                      ; TODO https://github.com/cemerick/piggieback/issues/10
                                      cljs.repl/-setup
                                      ; TODO is there no way to get libs recognized
                                      ; by cljs REPLs? Struggled with it for too
                                      ; long, thus this:
                                      (cljs.repl/-evaluate
                                        "src/main/resources/seedrandom.js"
                                        0
                                        (slurp "src/main/resources/seedrandom.js")))))]}})

