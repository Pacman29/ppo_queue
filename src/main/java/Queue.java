import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by pacman29 on 11.05.17.
 */
public class Queue<T extends Comparable> {

    ReadWriteLock rwlock = new ReentrantReadWriteLock();

    MaxMinStack<T> input_st = new MaxMinStack<T>();
    MaxMinStack<T> output_st = new MaxMinStack<T>();

    public Queue() {

    }

    public T max() throws EmptyQueueExeption{
        T result;
        rwlock.readLock().lock();
        {
            if (this.count() == 0) {
                throw new EmptyQueueExeption();
            }

            boolean in = input_st.isEmpty();
            boolean out = output_st.isEmpty();

            if (in || out) {
                if (in) {
                    result = output_st.peek_trio().getMax();
                } else {
                    result = input_st.peek_trio().getMax();
                }
            } else {
                T in_max = input_st.peek_trio().getMax();
                T out_max = output_st.peek_trio().getMax();

                result = in_max.compareTo(out_max) == 1 ? in_max : out_max;
            }
        }
        rwlock.readLock().unlock();
        return result;
    }

    public T min() throws EmptyQueueExeption{
        T result;
        rwlock.readLock().lock();
        {
            if (this.count() == 0) {
                throw new EmptyQueueExeption();
            }

            boolean in = input_st.isEmpty();
            boolean out = output_st.isEmpty();

            if (in || out) {
                if (in) {
                    result = output_st.peek_trio().getMin();
                } else {
                    result = input_st.peek_trio().getMin();
                }
            } else {
                T in_min = input_st.peek_trio().getMin();
                T out_min = output_st.peek_trio().getMin();

                result = in_min.compareTo(out_min) == 1 ? in_min : out_min;
            }
        }
        rwlock.readLock().unlock();
        return result;
    }

    public T push(T item){
        rwlock.writeLock().lock();
        {
            input_st.push(item);
        }
        rwlock.writeLock().unlock();
        return item;
    }

    public T pop() throws EmptyQueueExeption{
        rwlock.readLock().lock();
        if(output_st.isEmpty()){
            while (!input_st.isEmpty()){
                output_st.push_trio(input_st.pop_trio());
            }
        }

        T result;
        try {
            result = output_st.pop();
        } catch (EmptyStackException e){
            throw new EmptyQueueExeption();
        } finally {
            rwlock.readLock().unlock();
        }

        return result;
    }

    public String toString(){
        rwlock.readLock().lock();
        String res = "";
        {
            Object[] in = input_st.toArray();
            Object[] out = output_st.toArray();

            ArrayUtils.reverse(out);
            Object[] tmp = ArrayUtils.addAll(out,in);
            if(tmp.length == 0){
                return res;
            }
            for(Object iter : tmp){
                res += ((T)iter).toString() + ",";
            }
            res = res.substring(0,res.length()-1);
        }
        rwlock.readLock().unlock();
        return res;
    }

    public int count(){
        rwlock.readLock().lock();
            int res =  input_st.count() + output_st.count();
        rwlock.readLock().unlock();
        return res;
    }

    public boolean isEmpty(){
        rwlock.readLock().lock();
        boolean res =  this.count() == 0;
        rwlock.readLock().unlock();
        return res;
    }

}
