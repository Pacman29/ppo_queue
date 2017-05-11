import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by pacman29 on 11.05.17.
 */
public class QueueTest {

    @Test
    public void testIsEmpty() {
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);
        queues.get(0).push(0);
        queues.get(0).push(0);
        queues.get(0).push(0);
    }

}
