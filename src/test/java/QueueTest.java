import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by pacman29 on 11.05.17.
 */
public class QueueTest {

    @Rule
    public ExpectedException exc;

    @Test
    public void testIsEmpty() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);
        queues.get(0).push(0);
        queues.get(0).push(0);
        queues.get(0).push(0);
        assertFalse(queues.get(0).isEmpty());

        assertTrue(queues.get(1).isEmpty());

        queues.get(2).push(1);
        queues.get(2).pop();
        assertTrue(queues.get(2).isEmpty());
    }

    @Test
    public void testToArray() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);
        Integer expect[] = new Integer[0]; 
        assertEquals(expect,queues.get(0).toArray());
        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);
        expect[0] = 1;
        expect[1] = 2;
        expect[2] = 3;
        assertEquals(expect,queues.get(1).toArray());

        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals(expect,queues.get(2).toArray());
    }

    @Test
    public void testCount() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);
        assertEquals(0,queues.get(0).count());
        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);
        assertEquals(3,queues.get(1).count());

        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals(3,queues.get(2).count());
    }

    @Test
    public void testToString() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);
        assertEquals("",queues.get(0).toString());
        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);
        assertEquals("1,2,3",queues.get(1).toString());
        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals("1,2,3",queues.get(2).toString());
    }

    @Test
    public void testPop() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);

        queues.get(0).pop();
        exc.expect(EmptyQueueExeption.class);

        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);

        assertEquals(new Integer(1),queues.get(1).pop());

        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals(new Integer(4),queues.get(2).pop());
    }

    @Test
    public void testPush() throws Exception{
        Queue<Integer> queue = new Queue<Integer>();
        queue.push(1);
        assertEquals(new Integer(1),queue.pop());
    }

    @Test
    public void testMin() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);

        queues.get(0).min();
        exc.expect(EmptyQueueExeption.class);

        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);

        assertEquals(new Integer(1),queues.get(1).min());

        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals(new Integer(1),queues.get(2).min());
    }

    @Test
    public void testMax() throws Exception{
        ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>(3);

        queues.get(0).max();
        exc.expect(EmptyQueueExeption.class);

        queues.get(1).push(1);
        queues.get(1).push(2);
        queues.get(1).push(3);

        assertEquals(new Integer(3),queues.get(1).max());

        queues.get(2).push(1);
        queues.get(2).push(2);
        queues.get(2).push(3);
        queues.get(2).push(4);
        queues.get(2).push(5);
        queues.get(2).push(6);

        queues.get(2).pop();
        queues.get(2).pop();
        queues.get(2).pop();

        assertEquals(new Integer(6),queues.get(2).max());
    }
}
