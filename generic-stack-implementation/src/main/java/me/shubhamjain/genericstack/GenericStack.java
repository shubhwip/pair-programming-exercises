package me.shubhamjain.genericstack;

public interface GenericStack<T> {
    void push(T element);
    T pop();
    T peek();
    int search(T element);
}
