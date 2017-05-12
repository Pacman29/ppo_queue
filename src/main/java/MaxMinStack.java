import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MaxMinStack<T extends Comparable> {

   // ReadWriteLock rwlock = new ReentrantReadWriteLock();
    Stack<Trio<T>> stack = new Stack<Trio<T>>();

    public MaxMinStack() {
    }

    public T push(T item){
        //rwlock.writeLock().lock();
        Trio<T> tmp = new Trio<T>(item,item,item);
        if(stack.isEmpty()){
            stack.push(tmp);
            //rwlock.writeLock().unlock();
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
        //rwlock.writeLock().unlock();
        return item;
    }

    public Trio<T> push_trio(Trio<T> item){
        //rwlock.writeLock().lock();
        if(stack.isEmpty()){
            stack.push(item);
            //rwlock.writeLock().unlock();
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
        //rwlock.writeLock().unlock();
        return item;
    }

    public T pop(){
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        //rwlock.readLock().lock();
        T res =  stack.pop().getValue();
        //rwlock.readLock().unlock();
        return res;
    }

    public Trio<T> pop_trio() throws EmptyStackException{
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        //rwlock.readLock().lock();
        Trio res =  stack.pop();
        //rwlock.readLock().unlock();
        return res;
    }

    public T peek(){
        //rwlock.readLock().lock();
        T res =  stack.peek().getValue();
        //rwlock.readLock().unlock();
        return res;
    }

    public Trio<T> peek_trio(){
        //rwlock.readLock().lock();
        Trio res =  stack.peek();
        //rwlock.readLock().unlock();
        return res;
    }

    public int count(){
        //rwlock.readLock().lock();
        int res =  stack.size();
       // rwlock.readLock().unlock();
        return res;
    }

    public boolean isEmpty(){
        //rwlock.readLock().lock();
        boolean res =  stack.isEmpty();
        //rwlock.readLock().unlock();
        return res;
    }

    public Object[] toArray(){
       // rwlock.readLock().lock();
        Object tmp[] = new Object[stack.size()];
        int i = 0;
        for (Trio<T> trio: stack ){
            tmp[i++] = trio.getValue();
        }
       // rwlock.readLock().unlock();
        return tmp;
    }
}
