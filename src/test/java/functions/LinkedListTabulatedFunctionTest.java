package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    double[] xValue2 = {5};
    double[] yValue2 = {2};
    LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValue, yValue);
    LinkedListTabulatedFunction linkedListTabulatedFunction2 = new LinkedListTabulatedFunction(xValue2, yValue2);

    @Test
    void getCount() {
        assertEquals(5, linkedListTabulatedFunction.getCount());
        assertNotEquals(0, linkedListTabulatedFunction.getCount());
    }

    @Test
    void getX() {
        assertEquals(1.5, linkedListTabulatedFunction.getX(1));
        assertNotEquals(0, linkedListTabulatedFunction.getX(3));
    }

    @Test
    void getY() {
        assertEquals(3, linkedListTabulatedFunction.getY(1));
        assertNotEquals(0, linkedListTabulatedFunction.getY(2));
    }

    @Test
    void setY() {
        linkedListTabulatedFunction.setY(0, 7);
        assertEquals(7, linkedListTabulatedFunction.getY(0));
        assertNotEquals(0, linkedListTabulatedFunction.getY(0));
    }

    @Test
    void leftBound() {
        assertEquals(1, linkedListTabulatedFunction.leftBound());
        assertNotEquals(0, linkedListTabulatedFunction.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(3, linkedListTabulatedFunction.rightBound());
        assertNotEquals(0, linkedListTabulatedFunction.rightBound());
    }

    @Test
    void indexOfX() {
        assertEquals(1, linkedListTabulatedFunction.indexOfX(1.5));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfX(1.5));

        assertEquals(-1, linkedListTabulatedFunction.indexOfX(10));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfX(10));
    }

    @Test
    void indexOfY() {
        assertEquals(1, linkedListTabulatedFunction.indexOfY(3));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfY(3));

        assertEquals(-1, linkedListTabulatedFunction.indexOfY(10));
        assertNotEquals(0, linkedListTabulatedFunction.indexOfY(10));
    }

    @Test
    void floorIndexOfX() {
        assertEquals(0, linkedListTabulatedFunction.floorIndexOfX(-5));
        assertNotEquals(2, linkedListTabulatedFunction.floorIndexOfX(-5));

        assertEquals(5, linkedListTabulatedFunction.floorIndexOfX(10));
        assertNotEquals(0, linkedListTabulatedFunction.floorIndexOfX(10));

        assertEquals(1, linkedListTabulatedFunction.floorIndexOfX(1.5));
        assertNotEquals(0, linkedListTabulatedFunction.floorIndexOfX(1.5));

        assertEquals(3, linkedListTabulatedFunction.floorIndexOfX(2.7));
        assertNotEquals(0, linkedListTabulatedFunction.floorIndexOfX(2.7));
    }

    @Test
    void interpolate() {
        assertEquals(3.4, linkedListTabulatedFunction.interpolate(1.7, 2));
        assertNotEquals(0, linkedListTabulatedFunction.interpolate(1.7, 2));

        assertEquals(2, linkedListTabulatedFunction2.interpolate(1.7, 1));
        assertNotEquals(0, linkedListTabulatedFunction2.interpolate(1.7, 1));
    }

    @Test
    void extrapolateLeft() {
        assertEquals(-10, linkedListTabulatedFunction.extrapolateLeft(-5));
        assertNotEquals(0, linkedListTabulatedFunction.extrapolateLeft(-5));

        assertEquals(2, linkedListTabulatedFunction2.extrapolateLeft(1));
        assertNotEquals(0, linkedListTabulatedFunction2.extrapolateLeft(1));
    }

    @Test
    void extrapolateRight() {
        assertEquals(20, linkedListTabulatedFunction.extrapolateRight(10));
        assertNotEquals(0, linkedListTabulatedFunction.extrapolateRight(10));

        assertEquals(2, linkedListTabulatedFunction2.extrapolateRight(1));
        assertNotEquals(0, linkedListTabulatedFunction2.extrapolateRight(1));
    }

    @Test
    void toStringNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(5, 2);
        String str = node.toString();
        assertEquals("(5.0;2.0), где 5.0 и 2.0 – абсцисса и ордината точки соответственно.", node.toString());
        assertNotEquals("(5.1;2.1), где 5.1 и 2.1 – абсцисса и ордината точки соответственно.", node.toString());

    }

    @Test
    void hashCodeNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(2.30, 5.72);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(2.30, 5.72);
        LinkedListTabulatedFunction.Node node3 = new LinkedListTabulatedFunction.Node(5, 6);


        assertEquals(node2.hashCode(), node.hashCode());
        assertNotEquals(10, node.hashCode());
        assertNotEquals(node3.hashCode(), node.hashCode());


    }

    @Test
    void equalsNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(2.1, 3.2);
        LinkedListTabulatedFunction.Node node2 = new LinkedListTabulatedFunction.Node(2.1, 3.2);
        boolean bool = node.equals(node2);
        assertTrue(bool);


    }

    @Test
    void cloneNodeTest() {
        LinkedListTabulatedFunction.Node node = new LinkedListTabulatedFunction.Node(18.4, 28.1);
        Object nodeCopy = node.clone();
        assertTrue(node.equals(nodeCopy));
    }

    @Test
    void toStringLinkTest() {
        assertEquals("(1.0;2.0) (1.5;3.0) (2.0;4.0) (2.5;5.0) (3.0;6.0) ", linkedListTabulatedFunction.toString());
        assertNotEquals("(0;0)", linkedListTabulatedFunction.toString());

        assertEquals("(5.0;2.0) ", linkedListTabulatedFunction2.toString());
        assertNotEquals("(0;0)", linkedListTabulatedFunction2.toString());
    }

    @Test
    void hashCodeLinkTest() {
        LinkedListTabulatedFunction linkedListTabulatedFunction3 = new LinkedListTabulatedFunction(xValue, yValue);


        assertEquals(linkedListTabulatedFunction.hashCode(), linkedListTabulatedFunction3.hashCode());
        assertNotEquals(10, linkedListTabulatedFunction.hashCode());
        assertNotEquals(linkedListTabulatedFunction3.hashCode(), linkedListTabulatedFunction2.hashCode());

    }

    @Test
    void equalsLinkTest() {
        LinkedListTabulatedFunction linkedListTabulatedFunction3 = new LinkedListTabulatedFunction(xValue, yValue);
        boolean bool1 = linkedListTabulatedFunction.equals(linkedListTabulatedFunction3);
        boolean bool2 = linkedListTabulatedFunction.equals(linkedListTabulatedFunction2);
        assertTrue(bool1);
        assertFalse(bool2);

    }

    @Test
    void cloneLinkTest() {
        Object linkCopy = linkedListTabulatedFunction.clone();
        assertTrue(linkedListTabulatedFunction.equals(linkCopy));
    }
}