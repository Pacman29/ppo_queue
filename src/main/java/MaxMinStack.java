import java.util.*;

public class MaxMinStack<T extends Comparable> {
    Stack<Trio<T>> stack = new Stack<Trio<T>>();

    public MaxMinStack() {
    }

    public T push(T item){
        Trio<T> tmp = new Trio<T>(item,item,item);
        if(stack.isEmpty()){
            stack.push(tmp);
            return item;
        }

        Trio<T> top = stack.peek();

        if(item.compareTo(top.getMax()) == -1){
            tmp.setMax(top.getMax());
        }
        if(item.compareTo(top.getMin()) == 1){
            tmp.setMin(top.getMin());
        }

        stack.push(tmp);
        return item;
    }

    public Trio<T> push_trio(Trio<T> item){
        if(stack.isEmpty()){
            stack.push(item);
            return item;
        }

        Trio<T> top = stack.peek();

        if(item.getValue().compareTo(top.getMax()) == -1){
            item.setMax(top.getMax());
        }
        if(item.getValue().compareTo(top.getMin()) == 1){
            item.setMin(top.getMin());
        }

        stack.push(item);
        return item;
    }

    public T pop(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return stack.pop().getValue();
    }

    public Trio<T> pop_trio() throws EmptyStackException{
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return stack.pop();
    }

    public T peek(){
        return stack.peek().getValue();
    }

    public Trio<T> peek_trio(){
        return stack.peek();
    }

    public int count(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
