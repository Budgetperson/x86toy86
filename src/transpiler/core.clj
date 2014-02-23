(ns transpiler.core
  (:use transpiler.core)
  (:gen-class))

; (re-seq #"\n.*:\n" assembly)
; (re-matches #"(\w+)\s+([\w$%\(\)]+)\s*,\s*([\w$%\(\)]+).*" "movl 8(%ebp), %edx")
(defmacro dbg[x] `(let [x# ~x] (println '~x "=" x#) x#))


(def instruction-regex #"\s*(\w+)\s+([\w$%\(\)]+)\s*(?:,\s*([\w$%\(\)]+).*)?")

(defn is-label [instruction]
  (re-matches #".*:" instruction))

(defn split-into-procedures-without-label-names [preprocessed-assembly]
  (remove clojure.string/blank?
    (map clojure.string/trim (clojure.string/split preprocessed-assembly #".*:\n"))))

(defn instruction-to-cons [instruction]
  (filter identity (rest (re-matches instruction-regex instruction))))

(defn procedure-to-cons-tree [procedure]
  (seq (map instruction-to-cons (clojure.string/split-lines procedure))))


(defn is-directive [instruction]
  (= 
    (int (get (clojure.string/trim instruction) 0)) 
    46))

(defn remove-directives [assembly]
  (clojure.string/join "\n" 
    (remove is-directive 
        (map clojure.string/trim 
          (clojure.string/split-lines assembly)))))

(defn preprocess [assembly]
  (remove-directives assembly))

(defn convert-to-cons-tree [assembly]
  (procedure-to-cons-tree 
    (first (split-into-procedures-without-label-names 
      (preprocess assembly)))))

(defn -main [& args]
  (println
    (convert-to-cons-tree (slurp (first args)))))