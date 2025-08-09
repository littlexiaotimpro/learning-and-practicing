package com.practice.kotlin

class KotlinTest {

    var s: String = "";

    var l: List<Int> = listOf();

    constructor() {

    }

    constructor(s: String) {
        this.s = s;
    }

    fun print() {
        println("hello$s");
    }


}

fun main(args: Array<String>) {
    KotlinTest().print();

    for (i in KotlinTest("sss").l) {
        println(i);
    }
}