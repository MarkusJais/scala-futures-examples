package com.markusjais.scala.futures.examples.promises

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Promise
import scala.util.Random

object promiseExample extends App {

  def getNumber = Random.nextInt(500)

  val promise = Promise[Long]
  val future = promise.future
  val producer = Future {
    val number = getNumber
    if (number % 2 == 1)
      promise failure (new RuntimeException("not an even number"))
    else {
      // optionally do more business logig
      promise success number
    }
  }

  future onComplete {
    case Success(value) => println(s"value: $value")
    case Failure(throwable) => println(throwable.getMessage)
  }

  // necessary in this dummy app to let future complete
  System.in.read
}