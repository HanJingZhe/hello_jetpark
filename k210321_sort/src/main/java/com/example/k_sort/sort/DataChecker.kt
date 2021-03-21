package com.example.k_sort.sort

import java.util.*
import kotlin.random.Random

fun main() {
    check()
}


fun check() {
    val arr = generateRandomArray()
    val arr2 = IntArray(arr.size)
    System.arraycopy(arr, 0, arr2, 0, arr.size)
    Arrays.sort(arr)
    val selectSortArr = selectSort(arr2)

    var same = true
    for (i in selectSortArr.indices) {
        if (arr[i] != selectSortArr[i]) same = false
    }
    println("排序稳定性为:$same")
}


fun generateRandomArray(): IntArray {
    val arr = IntArray(1000)
    for (i in 0 until 1000) {
        arr[i] = Random.nextInt(1000)
    }
    return arr
}