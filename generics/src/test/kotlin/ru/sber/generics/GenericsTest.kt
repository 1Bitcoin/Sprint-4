package ru.sber.generics

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class GenericsTest {

    @Test
    fun comparePairsTest() {
        assertTrue(compare(Pair("Hello", 2L), Pair("Hello", 2L)))
        assertFalse(compare(Pair(BigDecimal(5), false), Pair(BigDecimal(5), true)))
    }

    @Test
    fun countGreaterThanTest() {
        assertEquals(2, countGreaterThan(arrayOf(5, 4, 3, 2, 1), 3))
        assertEquals(2, countGreaterThan(arrayOf('a', 'b', 'c', 'd', 'e'), 'c'))
    }

    @Test
    fun sorterStringTest() {
        val sorter = Sorter<String>()

        sorter.add("foo")
        sorter.add("hello")
        sorter.add("bar")

        assertEquals(listOf("bar", "foo", "hello"), sorter.list)
    }

    @Test
    fun sorterIntTest() {
        val sorter = Sorter<Int>()

        sorter.add(89)
        sorter.add(-51)
        sorter.add(0)
        sorter.add(5)
        sorter.add(5)

        assertEquals(listOf(-51, 0, 5, 5, 89), sorter.list)
    }

    @Test
    fun stackIntTest() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        stack.push(5)
        stack.push(3)

        assertEquals(3, stack.pop())
        assertFalse(stack.isEmpty())
        assertEquals(5, stack.pop())

        stack.push(68)
        stack.push(377)
        stack.push(37447)

        assertEquals(3, stack.size())

        stack.printStack()

        stack.pop()

        assertEquals(377, stack.peek())
        stack.pop()

        assertEquals(68, stack.peek())
        stack.pop()

        assertTrue(stack.isEmpty())
    }

    @Test
    fun stackStringTest() {
        val stack = Stack<String>()
        assertTrue(stack.isEmpty())

        stack.push("abc")
        stack.push("xxx")

        assertEquals("xxx", stack.pop())
        assertFalse(stack.isEmpty())
        assertEquals("abc", stack.pop())

        stack.printStack()

        stack.push("zzz")
        assertEquals("zzz", stack.peek())
        assertEquals(1, stack.size())
        stack.pop()

        assertTrue(stack.isEmpty())
    }
}