(ns clj-lang-extra.core)

;;; int utils

(defn str->int [s] (js/parseInt s))
(defn str->float [s] (js/parseFloat s))
(defn hex->int [s] (js/parseInt s 16))

(comment
  (str->int "123") ; => 123
  (str->float "123.4") ; => 123.4
  (hex->int "ff") ; => 255
  )

;;; inst utils

(defn now [] (js/Date.))

(comment
  (now))

;;; exc utils

(defn throwable? [ex] (instance? js/Error ex))
(defn exception? [ex] (instance? js/Error ex))
(defn ex-info? [ex] (instance? ExceptionInfo ex))

(comment
  (throwable? "error") ; => false
  (throwable? (js/Error.)) ; => true
  (exception? (js/Error.)) ; => true
  (exception? (ex-info "" {})) ; => true
  (ex-info? (js/Error.)) ; => false
  (ex-info? (ex-info "" {})) ; => true
  )

(defn try-catch [f] (try [(f) nil] (catch js/Error ex [nil ex])))
