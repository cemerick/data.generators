(ns clojure.data.generators-test
  #+cljs (:require-macros [cemerick.cljs.test :refer (deftest is)])
  (:require [clojure.data.generators :as gen]
            #+cljs [cemerick.cljs.test :as t]
            #+cljs [cljs.reader :refer (read-string)]
            #+clj [clojure.test :refer (deftest is)]))

(defn print-read-roundtrip
  [o]
  #+cljs (-> o pr-str read-string)
  #+clj (binding [*print-length* nil
                  *print-level* nil]
          (-> o pr-str read-string)))

#+clj ; TODO Clojure-only here pending fix for http://dev.clojure.org/jira/browse/CLJS-523
(deftest test-print-read-roundtrip
  (dotimes [_ 50]
    (let [x (gen/anything)]
      (is (= x (print-read-roundtrip x)) "Value cannot roundtrip"))))

(deftest test-shuffle
  (dotimes [_ 50]
    (let [coll (gen/vec gen/anything)
          shuf (gen/shuffle coll)]
      (is (= (into #{} coll)
             (into #{} shuf))))))

(deftest test-reservoir-sample-consistency
  (dotimes [n 50]
    (let [coll (range 100)
          sample-1 (binding [gen/*rnd* (gen/rng n)]
                     (gen/reservoir-sample 10 coll))
          sample-2 (binding [gen/*rnd* (gen/rng n)]
                     (gen/reservoir-sample 10 coll))]
      (is (= sample-1 sample-2)))))


