(defn abstractOp [f] (fn [obj1 obj2] (mapv f obj1 obj2)))
(defn abstractS [f] (fn [obj s] (mapv (fn [elem] (f elem s)) obj)))

(def v+ (abstractOp +))
(def v- (abstractOp -))
(def v* (abstractOp *))
(def vd (abstractOp /))

(def m+ (abstractOp v+))
(def m- (abstractOp v-))
(def m* (abstractOp v*))
(def md (abstractOp vd))

(def c+ (abstractOp m+))
(def c- (abstractOp m-))
(def c* (abstractOp m*))
(def cd (abstractOp md))

(defn scalar [v1 v2] (apply + (v* v1 v2)))

(defn det [x1 x2 y1 y2] (- (* x1 y2) (* x2 y1)))
(defn vect [[x1 x2 x3] [y1 y2 y3]] [(det x2 x3 y2 y3)
                                (- (det x1 x3 y1 y3))
                                (det x1 x2 y1 y2)])

(def v*s (abstractS *))
(def m*s (abstractS v*s))
(def m*v (abstractS scalar))

(defn transpose [m] (apply mapv vector m))
(defn m*m [m1 m2] (mapv (fn [v] (mapv (partial scalar v) (transpose m2))) m1))