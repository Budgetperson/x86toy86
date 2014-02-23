(ns transpiler.core)

; (re-seq #"\n.*:\n" assembly)
; (re-matches #"(\w+)\s+([\w$%\(\)]+)\s*,\s*([\w$%\(\)]+).*" "movl 8(%ebp), %edx")

(def instruction-regex #"(\w+)\s+([\w$%\(\)]+)\s*,\s*([\w$%\(\)]+).*")

(defn is-label [instruction]
  (re-matches #".*:" instruction))

(defn is-directive [instruction]
  (= 
    (int (get (clojure.string/trim instruction) 0)) 
    46))

(defn remove-directives [assembly]
  (clojure.string/join "\n" 
    (remove is-directive 
        (clojure.string/split-lines assembly))))

(defn preprocess [assembly]
  (remove-directives assembly))

(defn -main [& args]
  (println
    (preprocess (slurp (first args)))))