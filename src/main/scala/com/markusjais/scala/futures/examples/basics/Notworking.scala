package com.markusjais.scala.futures.examples.basics

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await

object Notworking extends App {

  val myFuture = Future {
    12
  }

  //val result = Await.result(myFuture, 2 seconds)
  //println("result:" + result)
  val tmp = myFuture.value.get

  myFuture onComplete {
    case Success(value) => println("value:" + value)
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  println("done")

}








