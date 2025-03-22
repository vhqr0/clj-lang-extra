(ns clj-lang-extra.core
  (:import [java.util Date]))

;;; int utils

(defn str->int [^String s] (Long/parseLong s))
(defn str->float [^String s] (Double/parseDouble s))
(defn hex->int [^String s] (Long/parseLong s 16))
(defn int->hex [^long i] (Long/toHexString i))
(defn bin->int [^String s] (Long/parseLong s 2))
(defn int->bin [^long i] (Long/toBinaryString i))

^:rct/test
(comment
  (str->int "123") ; => 123
  (str->float "123.4") ; => 123.4
  (hex->int "ff") ; => 255
  (int->hex 255) ; => "ff"
  (bin->int "1011") ; => 11
  (int->bin 11) ; => "1011"
  )

;;; inst utils

(defn now [] (Date.))
(defn ms->inst [^long ms] (Date. ms))

(comment
  (now)
  (ms->inst 0))

;;; exc utils

(defn exception? [ex] (instance? Exception ex))
(defn ex-info? [ex] (instance? clojure.lang.ExceptionInfo ex))

^:rct/test
(comment
  (exception? (Exception.)) ; => true
  (exception? (ex-info "" {})) ; => true
  (ex-info? (Exception.)) ; => false
  (ex-info? (ex-info "" {})) ; => true
  )

(defn try-catch [f] (try [(f) nil] (catch Exception ex [nil ex])))

^:rct/test
(comment
  (try-catch
   (fn []
     (throw (ex-info "test error" {}))))
  ;; =>> [nil ex-info?]
  (try-catch
   (fn []
     (ex-info "test error" {})))
  ;; =>> [ex-info? nil]
  )
