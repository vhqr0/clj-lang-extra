(ns clj-lang-extra.core)

;;; int utils

(defn str->int [s] (js/parseInt s))
(defn str->float [s] (js/parseFloat s))
(defn hex->int [s] (js/parseInt s 16))
(defn int->hex [i] (.toString i 16))
(defn bin->int [s] (js/parseInt s 2))
(defn int->bin [i] (.toString i 2))

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

(defn now [] (js/Date.))
(defn ms->inst [ms] (js/Date. ms))

(comment
  (now)
  (ms->inst 0))

;;; exc utils

(defn exception? [ex] (instance? js/Error ex))
(defn ex-info? [ex] (instance? ExceptionInfo ex))

^:rct/test
(comment
  (exception? (js/Error.)) ; => true
  (exception? (ex-info "" {})) ; => true
  (ex-info? (js/Error.)) ; => false
  (ex-info? (ex-info "" {})) ; => true
  )

(defn try-catch [f] (try [(f) nil] (catch js/Error ex [nil ex])))

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
