package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1 == p2
}

// 2.
fun <T> countGreaterThan(anArray: Array<T>, elem: T): Int where T : Comparable<T> {

    return anArray.filter { it > elem }.size
}

// 3.
class Sorter<T> where T: Comparable<T> {

    val list: MutableList<T> = mutableListOf()
    private var insertionPoint = 0

    fun add(value: T) {
        insertionPoint = if (list.contains(value))
            list.binarySearch(value)
        else
            -list.binarySearch(value) - 1

        list.add(insertionPoint, value)
    }
}

// 4.
class Stack<T> {

    private val stack: MutableList<T> = mutableListOf()

    fun isEmpty(): Boolean = stack.isEmpty()

    fun size(): Int = stack.size

    fun push(value: T) = stack.add(value)

    fun pop(): T = stack.removeLast()

    fun printStack() = stack.forEach { print("$it ") }

    fun peek(): T = stack.last()
}