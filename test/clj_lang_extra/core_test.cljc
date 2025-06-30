(ns clj-lang-extra.core-test
  (:require [clojure.test :refer [run-tests deftest testing is]]
            [clj-lang-extra.core :as sut]))

(deftest int-utils-test
  (testing "Parse int string"
    (is (= 11 (sut/parse-bin "1011")))
    (is (= 65282 (sut/parse-hex "ff02"))))
  (testing "Int to string"
    (is (= "1011" (sut/bin-str 11)))
    (is (= "ff02" (sut/hex-str 65282)))))

(deftest inst-utils
  (is (inst? (sut/now)))
  (is (= 0 (-> 0 sut/ms->inst inst-ms))))

(deftest ex-utils-test
  (is (sut/ex? (ex-info "" {})))
  (is (sut/ex-info? (ex-info "" {})))
  (is (nil? (first (sut/try-catch #(throw (ex-info "" {})))))))
