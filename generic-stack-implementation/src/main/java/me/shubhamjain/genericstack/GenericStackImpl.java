package me.shubhamjain.genericstack;

import java.util.ArrayList;
import java.util.List;

public class GenericStackImpl<T> implements GenericStack<T> {
    
    List<T> genericInternalStack;
    
    public GenericStackImpl() {
        genericInternalStack = new ArrayList<>();
    }
    @Override
    public void push(T element) {
        genericInternalStack.add(element);
    }

    @Override
    public T pop() {
        if(genericInternalStack.isEmpty()) {
            throw new RuntimeException();
        }
        T topElement = genericInternalStack.get(genericInternalStack.size()-1);
        genericInternalStack.remove(genericInternalStack.size()-1);
        return topElement;
    }

    @Override
    public T peek() {
        if(genericInternalStack.isEmpty()) {
            throw new RuntimeException();
        }
        return genericInternalStack.get(genericInternalStack.size()-1);
    }

    @Override
    public int search(T element) {
        for(int i=genericInternalStack.size()-1; i>=0; i--) {
            if(genericInternalStack.get(i) == element) {
                return genericInternalStack.size()-i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "IntegerStackImpl{" +
                "internalStack=" + genericInternalStack +
                '}';
    }
}
