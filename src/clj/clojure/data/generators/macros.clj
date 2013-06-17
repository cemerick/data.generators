(ns clojure.data.generators.macros)

(defmacro primitive-array
  [type]
  (let [fn-name (symbol (str type "-array"))
        factory-name (symbol (str "core/" fn-name))
        cast-name (symbol (str "core/" type))]
    `(defn ~fn-name
       "Create an array with elements from f and sized from sizer."
       ([~'f]
          (~fn-name ~'f ~'default-sizer))
       ([~'f ~'sizer]
          (let [~'arr (~factory-name (~'call-through ~'sizer))]
            (dotimes [~'i (count ~'arr)]
              (aset ~'arr ~'i (~cast-name (~'call-through ~'f))))
            ~'arr)))))

(defmacro primitive-arrays
  [types]
  `(do ~@(map (fn [type] `(primitive-array ~type)) types)))
