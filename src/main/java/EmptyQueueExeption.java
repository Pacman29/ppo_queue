/**
 * Created by pacman29 on 11.05.17.
 */
public class EmptyQueueExeption extends Exception {
    public EmptyQueueExeption() {
        super("Queue is empty");
    }
}
