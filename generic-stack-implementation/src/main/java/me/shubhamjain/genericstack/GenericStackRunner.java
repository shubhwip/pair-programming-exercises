package me.shubhamjain.genericstack;

public class GenericStackRunner {

    public static void main(String[] args) {
        GenericStack<String> genericStackString = new GenericStackImpl<>();
        genericStackString.push("shubham");
        genericStackString.push("rajat");
        genericStackString.push("dheeraj");
        genericStackString.push("kapil");
        genericStackString.push("rajat");
        genericStackString.push("radhe");

        System.out.println(genericStackString.peek());
        System.out.println(genericStackString.pop());
        System.out.println(genericStackString);
        System.out.println(genericStackString.search("rajat"));

        GenericStack<Integer> genericStackInteger = new GenericStackImpl<>();
        genericStackInteger.push(1);
        genericStackInteger.push(2);
        genericStackInteger.push(3);
        genericStackInteger.push(2);
        genericStackInteger.push(4);
        genericStackInteger.push(5);

        System.out.println(genericStackInteger.peek());
        System.out.println(genericStackInteger.pop());
        System.out.println(genericStackInteger);
        System.out.println(genericStackInteger.search(1));


        // Accommodating different data types together ?
        GenericStack<Object> genericStackObject = new GenericStackImpl<>();
        genericStackObject.push(1);
        genericStackObject.push(2);
        genericStackObject.push("shubham");
        genericStackObject.push(2);
        genericStackObject.push("rajat");
        genericStackObject.push("shubham");

        System.out.println(genericStackObject.peek());
        System.out.println(genericStackObject.pop());
        System.out.println(genericStackObject);
        System.out.println(genericStackObject.search("shubham"));
        System.out.println(genericStackObject.search(2));


    }
}
