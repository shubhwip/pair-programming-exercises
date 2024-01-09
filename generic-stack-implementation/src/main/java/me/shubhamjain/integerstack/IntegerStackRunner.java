package me.shubhamjain.integerstack;

public class IntegerStackRunner {
    public static void main(String[] args) {
        IntegerStack integerStack = new IntegerStackImpl();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        integerStack.push(4);
        integerStack.push(1);
        integerStack.push(5);
        integerStack.push(6);
        System.out.println(integerStack.peek());
        System.out.println(integerStack.pop());
        System.out.println(integerStack);
        System.out.println(integerStack.search(1));
    }
}
