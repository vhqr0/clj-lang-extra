(ns clj-lang-extra.cljs-test
  (:require [cljs.test :refer-macros [run-tests]]
            clj-lang-extra.core-test))

(run-tests
 'clj-lang-extra.core-test)
