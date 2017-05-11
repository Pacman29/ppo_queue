import java.util.Objects;

/**
 * Created by pacman29 on 11.05.17.
 */
public class Trio<T>{
    T value;
    T max;
    T min;


    public Trio(T value,T max, T min) {
        this.max = max;
        this.min = min;
        this.value = value;
    }

    public Trio(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trio<?> trio = (Trio<?>) o;
        return Objects.equals(value, trio.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
