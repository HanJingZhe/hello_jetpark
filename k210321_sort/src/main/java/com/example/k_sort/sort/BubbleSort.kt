package com.example.k_sort.sort

/**
 * 冒泡排序
 */
fun main() {

    val arr = intArrayOf(4, 2, 1, 9, 8, 5, 6, 3, 7)
    arr.print()
    val start = System.currentTimeMillis()

    for (i in 0 until arr.size - 1) {
        for (j in i until arr.size) {
            if (arr[i] > arr[j]) {
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        arr.print()
    }

    println("耗时:${System.currentTimeMillis() - start}")
}

