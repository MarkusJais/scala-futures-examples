package com.markusjais.scala.futures.examples.basics

import com.markusjais.scala.futures.examples.business.Book
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import com.markusjais.scala.futures.examples.business.Fish

object BrokenConcurreny extends App {

  // broken because those futures will be executed sequentially
  val resultSequential = for {
    catOne <- Future { Thread.sleep(1000); println("doing with F1"); Thread.sleep(1000); println("done with F1"); "Tiger" }
    catTwo <- Future { Thread.sleep(1000); println("doing with F2"); Thread.sleep(1000); println("done with F2"); "Leopard" }
  } yield ("cats: " + catOne + ", " + catTwo)

  resultSequential onComplete {
    case Success(result) => println("success: " + result)
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)
}

