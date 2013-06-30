package com.markusjais.scala.futures.examples.functional.mapFlatMap
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure

object BrokenConcurreny extends App {

  // broken because those futures will be executed sequentially
  val resultSequential = for {
    catOne <- Future { Thread.sleep(1000); println("doing with F1"); Thread.sleep(1000); println("done with F1"); "Tiger" }
    catTwo <- Future { Thread.sleep(1000); println("doing with F2"); Thread.sleep(1000); println("done with F2"); "Leopard" }
  } yield ("cats: " + catOne + ", " + catTwo)

  resultSequential onComplete {
    case Success(result) => println(s"success: $result")
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  // necessary in this dummy app to let future complete
  System.in.read()
}

