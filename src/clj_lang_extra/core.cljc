(ns clj-lang-extra.core
  #?@(:clj [(:import [java.util Date])]))

;;; int utils

;; related fn: `parse-long` `parse-double` `str`

#?(:clj (do
          (defn parse-bin ^long [^String s] (Long/parseLong s 2))
          (defn parse-hex ^long [^String s] (Long/parseLong s 16))
          (defn bin-str ^String [^long i] (Long/toBinaryString i))
          (defn hex-str ^String [^long i] (Long/toHexString i)))
   :cljs (do
           (defn parse-bin [s] (js/parseInt s 2))
           (defn parse-hex [s] (js/parseInt s 16))
           (defn bin-str [i] (.toString i 2))
           (defn hex-str [i] (.toString i 16))))

;;; inst utils

;; related fn: `inst?` `inst-ms`

#?(:clj (do
          (defn now ^Date [] (Date.))
          (defn ms->inst ^Date [^long ms] (Date. ms)))
   :cljs (do
           (defn now [] (js/Date.))
           (defn ms->inst [ms] (js/Date. ms))))

;;; ex utils

;; related fn: `ex-info` `ex-data` `ex-message` `ex-cause`

#?(:clj (do
          (defn ex? [ex] (instance? Exception ex))
          (defn ex-info? [ex] (instance? clojure.lang.ExceptionInfo ex)))
   :cljs (do
           (defn ex? [ex] (instance? js/Error ex))
           (defn ex-info? [ex] (instance? ExceptionInfo ex))))

#?(:clj (defn try-catch [f & args] (try [(apply f args) nil] (catch Exception ex [nil ex])))
   :cljs (defn try-catch [f & args] (try [(apply f args) nil] (catch js/Error ex [nil ex]))))
