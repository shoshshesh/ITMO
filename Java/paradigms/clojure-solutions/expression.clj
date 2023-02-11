(defn pcall [obj key & args] (apply ((:proto obj) key) obj args))
(defn method [key] (fn [obj & args] (apply pcall obj key args)))
(defn constructor [ctor proto] (fn [& args] (apply ctor {:proto proto} args)))

(def evaluate (method :evaluate))
(def toString (method :toString))
(def diff (method :diff))

(def Add)
(def Subtract)
(def Multiply)
(def Divide)
(def Negate)
(def Constant)
(def Sin)
(def Cos)

(def diffMap
  {
   "+" (fn [part1 part2 var] (Add (diff part1 var) (diff part2 var)))
   "-" (fn [part1 part2 var] (Subtract (diff part1 var) (diff part2 var)))
   "*" (fn [part1 part2 var] (Add (Multiply (diff part1 var) part2) (Multiply part1 (diff part2 var))))
   "/" (fn [part1 part2 var] (Divide
                               (Subtract
                                 (Multiply (diff part1 var) part2)
                                 (Multiply part1 (diff part2 var)))
                               (Multiply part2 part2)))
   "negate" (fn [part var] (Negate (diff part var)))
   "sin" (fn [part var] (Multiply (Cos part) (diff part var)))
   "cos" (fn [part var] (Multiply (Negate (Sin part)) (diff part var)))
   })


(def BinaryPrototype
  {
   :toString (fn [this] (str "(" (this :sign) " " (toString (this :part1)) " " (toString (this :part2)) ")"))
   :evaluate (fn [this map] ((this :func) (evaluate (this :part1) map) (evaluate (this :part2) map)))
   :diff     (fn [this var] ((get diffMap (this :sign)) (this :part1) (this :part2) var))
   })
(defn Binary [sign func] (fn [this part1 part2]
                           (assoc this
                             :part1 part1
                             :part2 part2
                             :sign sign
                             :func func)))

(def UnaryPrototype
  {
   :toString (fn [this] (str "(" (this :sign) " " (toString (this :part)) ")"))
   :evaluate (fn [this map] ((this :func) (evaluate (this :part) map)))
   :diff (fn [this var] ((get diffMap (this :sign)) (this :part) var))
   })
(defn Unary [sign func] (fn [this part]
                          (assoc this
                            :part part
                            :sign sign
                            :func func)))

(def ConstantPrototype
  {
   :toString (fn [this] (format "%.1f" (double (:value this))))
   :evaluate (fn [this map] (:value this))
   :diff     (fn [this var] (Constant 0))
   })

(def Constant (constructor (fn [this value] (assoc this :value value)) ConstantPrototype))

(def VariablePrototype
  {
   :toString (fn [this] (:name this))
   :evaluate (fn [this map] (get map (:name this)))
   :diff     (fn [this var] (cond
                              (= var (this :name)) (Constant 1)
                              :else (Constant 0)))
   })
(def Variable (constructor (fn [this name] (assoc this :name name)) VariablePrototype))

(def Add (constructor (Binary "+" +) BinaryPrototype))
(def Subtract (constructor (Binary "-" -) BinaryPrototype))
(def Multiply (constructor (Binary "*" *) BinaryPrototype))
(def Divide (constructor (Binary "/" (fn [a b] (/ (double a) b))) BinaryPrototype))
(def Negate (constructor (Unary "negate" -) UnaryPrototype))
(def Sin (constructor (Unary "sin" (fn [a] (Math/sin a))) UnaryPrototype))
(def Cos (constructor (Unary "cos" (fn [a] (Math/cos a))) UnaryPrototype))



(def operationsObj {"+" Add, "-" Subtract, "*" Multiply, "/" Divide, "negate" Negate, "sin" Sin, "cos" Cos})
(defn parseObject [expression] (cond
                                 (number? expression) (Constant expression)
                                 (symbol? expression) (Variable (str expression))
                                 (list? expression) (apply (get operationsObj (str (first expression)))
                                                           (mapv parseObject (rest expression)))
                                 :else (parseObject (read-string expression))))
(def expr (diff (Sin (Subtract (Variable "x") (Variable "y"))) "x"))


(defn constant [value] (fn [map] value))
(defn variable [name] (fn [map] (get map name)))
(defn abstractBin [f] (fn [a b] (fn [map] (f (a map) (b map)))))
(defn abstractUn [f] (fn [a] (fn [map] (f (a map)))))


(def add (abstractBin +))
(def subtract (abstractBin -))
(def multiply (abstractBin *))
(def divide (abstractBin (fn [a b] (/ (double a) (double b)))))
(def negate (abstractUn -))
(def sin (abstractUn (fn [a] (Math/sin a))))
(def cos (abstractUn (fn [a] (Math/cos a))))

(def operations {"+" add "-" subtract "*" multiply "/" divide "negate" negate "sin" sin "cos" cos})

(defn parseFunction [expression] (cond
                                   (number? expression) (constant expression)
                                   (symbol? expression) (variable (str expression))
                                   (list? expression) (apply (get operations (str (first expression)))
                                                             (mapv parseFunction (rest expression)))
                                   :else (parseFunction (read-string expression))))