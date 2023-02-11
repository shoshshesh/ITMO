package queue;

// Model:
//    [a1, a2, ..., an]
//    n -- размер очереди

//    Inv:
//    n >= 0
//    forall i [1..n]: a[i] != null

public interface Queue {
//  Штрих - новое состояние элементов


//  Pre:  element != null
//  Post: n' = n + 1 && a'[n'] = element && forall i [1..n] : a[i] == a'[i]
    void enqueue(Object element);

//  Pre:  n > 0
//  Post: n' = n - 1 && forall i [1..n'] : a'[i] == a[i+1] && R = a[1]
    Object dequeue();

//  Immutable: n' = n && forall i [1..n] : a[i] == a'[i]

//  Pre:  size > 0
//  Post: R = a[1] && Immutable
    Object element();

//  Pre:  true
//  Post: R = n && Immutable
    int size();

//  Pre:  true
//  Post: R = (n == 0) && Immutable
    boolean isEmpty();

//  Pre:  true
//  Post: n' = 0
    void clear();

//  Pre:  true
//  Post: R = newArray[]: forall i [1..n]: newArray[i] = a[i]
    Object[] toArray();
}