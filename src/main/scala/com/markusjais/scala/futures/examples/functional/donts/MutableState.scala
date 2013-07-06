package com.markusjais.scala.futures.examples.functional.donts

import com.markusjais.scala.futures.examples.business.sleep

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure


// NOTE: Don't do this!!! This is an example of how NOT to use Futures in Scala.
// Don't put shared mutable state in a Future
object MutableState extends App {

  import sleep._

  
  var mutableState = 42

  // Don't put shared mutable state in a Future
  val f1 = Future { sleepRandom; mutableState = 100; sleepRandom; println(Thread.currentThread().getName()); mutableState + 1 }
  val f2 = Future { sleepRandom; mutableState = 200; sleepRandom; println(Thread.currentThread().getName()); mutableState + 1 }
  val f3 = Future { sleepRandom; mutableState = 300; sleepRandom; println(Thread.currentThread().getName()); mutableState + 1 }

  f1 onSuccess {
    case value => println(s"f1: $value")
  }
  f2 onSuccess {
    case value => println(s"f2: $value")
  }
  f3 onSuccess {
    case value => println(s"f3: $value")
  }

  // necessary in this dummy app to let future complete
  System.in.read()
}