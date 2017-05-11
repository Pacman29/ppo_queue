import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.EmptyStackException;

/**
 * Created by pacman29 on 11.05.17.
 */
public class Queue<T extends Comparable> {

    MaxMinStack<T> input_st = new MaxMinStack<T>();
    MaxMinStack<T> output_st = new MaxMinStack<T>();

    public Queue() {

    }

    public T max() throws EmptyQueueExeption{
        if(this.count() == 0){
            throw new EmptyQueueExeption();
        }

        boolean in = input_st.isEmpty();
        boolean out = output_st.isEmpty();

        if(in || out){
            if(in){
                return output_st.peek_trio().getMax();
            } else {
                return input_st.peek_trio().getMax();
            }
        } else {
            T in_max = input_st.peek_trio().getMax();
            T out_max = output_st.peek_trio().getMax();

            return in_max.compareTo(out_max) == 1 ? in_max : out_max;
        }
    }

    public T min() throws EmptyQueueExeption{
        if(this.count() == 0){
            throw new EmptyQueueExeption();
        }

        boolean in = input_st.isEmpty();
        boolean out = output_st.isEmpty();

        if(in || out){
            if(in){
                return output_st.peek_trio().getMin();
            } else {
                return input_st.peek_trio().getMin();
            }
        } else {
            T in_min = input_st.peek_trio().getMin();
            T out_min = output_st.peek_trio().getMin();

            return in_min.compareTo(out_min) == -1 ? in_min : out_min;
        }
    }

    public T push(T item){
        input_st.push(item);
        return item;
    }

    public T pop() throws EmptyQueueExeption{
        if(output_st.isEmpty()){
            while (!input_st.isEmpty()){
                output_st.push_trio(input_st.pop_trio());
            }
        }

        try {
            return output_st.pop();
        } catch (EmptyStackException e){
            throw new EmptyQueueExeption();
        }
    }

    public String toString(){
        return null;
    }

    public int count(){
        return input_st.count() + output_st.count();
    }

    public boolean isEmpty(){
        return this.count() == 0;
    }

}
