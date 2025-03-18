(ns clj-lang-extra.core
  (:import [java.util Date]))

;;; int utils

(defn str->int [^String s] (Long/parseLong s))
(defn str->float [^String s] (Double/parseDouble s))
(defn hex->int [^String s] (Long/parseLong s 16))

^:rct/test
(comment
  (str->int "123") ; => 123
  (str->float "123.4") ; => 123.4
  (hex->int "ff") ; => 255
  )

;;; inst utils

(defn now [] (Date.))

(comment
  (now))

;;; exc utils

(defn throwable? [ex] (instance? Throwable ex))
(defn exception? [ex] (instance? Exception ex))
(defn ex-info? [ex] (instance? clojure.lang.ExceptionInfo ex))

^:rct/test
(comment
  (throwable? "error") ; => false
  (throwable? (Exception.)) ; => true
  (exception? (Exception.)) ; => true
  (exception? (ex-info "" {})) ; => true
  (ex-info? (Exception.)) ; => false
  (ex-info? (ex-info "" {})) ; => true
  )

(defn try-catch [f] (try [(f) nil] (catch Exception ex [nil ex])))
