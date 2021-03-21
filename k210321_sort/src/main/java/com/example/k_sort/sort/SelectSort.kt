package com.example.k_sort.sort

/**
 * 选择排序
 */
fun main() {
    val arr = intArrayOf(4, 2, 1, 9, 8, 5, 6, 3, 7)
    arr.print()
    val start = System.currentTimeMillis()
    for (i in 0 until arr.size - 1) {
        var minPos = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minPos]) {
                minPos = j
            }
        }
        exchange(arr, i, minPos)
        println("经过第${i}次排序")
        arr.print()
    }
    println("耗时:${System.currentTimeMillis() - start}")
}


fun selectSort(arr: IntArray): IntArray {
    val start = System.currentTimeMillis()
    for (i in 0 until arr.size - 1) {
        var minPos = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minPos]) {
                minPos = j
            }
        }
        exchange(arr, i, minPos)
        println("经过第${i}次排序")
        arr.print()
    }
    println("耗时:${System.currentTimeMillis() - start}")
    return arr
}

fun IntArray.print() {
    forEach {
        print("$it ")
    }
    println()
}

fun exchange(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}