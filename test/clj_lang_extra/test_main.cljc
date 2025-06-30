(ns clj-lang-extra.test-main
  (:require [clojure.test :refer [run-tests]]
            clj-lang-extra.core-test))

(defn -main
  [& _]
  (run-tests 'clj-lang-extra.core-test))

#?(:cljs (-main))
