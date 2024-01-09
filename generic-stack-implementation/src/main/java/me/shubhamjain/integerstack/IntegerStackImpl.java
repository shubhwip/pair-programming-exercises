package me.shubhamjain.integerstack;

import me.shubhamjain.integerstack.IntegerStack;

import java.util.ArrayList;
import java.util.List;


public class IntegerStackImpl implements IntegerStack {

    List<Integer> internalStack;

    public IntegerStackImpl() {
        this.internalStack = new ArrayList<>();
    }

    @Override
    public void push(int element) {
        internalStack.add(element);
    }

    @Override
    public int pop() {
        if(internalStack.isEmpty()) {
            return -1;
        }
        int topElement = internalStack.get(internalStack.size()-1);
        internalStack.remove(internalStack.size()-1);
        return topElement;
    }

    @Override
    public int peek() {
        if(internalStack.isEmpty()) {
            return -1;
        }
        return internalStack.get(internalStack.size()-1);
    }

    @Override
    public int search(int element) {
        for(int i=internalStack.size()-1; i>=0; i--) {
            if(internalStack.get(i) == element) {
                return internalStack.size()-i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "IntegerStackImpl{" +
                "internalStack=" + internalStack +
                '}';
    }
}
