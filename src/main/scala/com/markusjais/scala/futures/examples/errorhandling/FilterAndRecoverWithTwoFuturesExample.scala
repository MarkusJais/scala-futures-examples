package com.markusjais.scala.futures.examples.errorhandling
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import com.markusjais.scala.futures.examples.business.Fish
import com.markusjais.scala.futures.examples.business.Book

object FilterAndRecoverWithTwoFuturesExample extends App {

  val books = List(
    Book("Lord of the Rings", 10.00),
    Book("Effective Java", 40.00),
    Book("Akka in Action", 50.00))

  val fishes = List(
    Fish("Pazific Salmon, MSC", 30.00),
    Fish("Mackerel, MSC", 20.00))
    
// with the following "fish list" the Future will throw an exception because there is a shark in it.  
//      val fishes = List(
//      Fish("Pazific Salmon, MSC", 30.00),
//      Fish("Bull Shark", 20.00))

  val priceForBooksFuture = Future { orders.computeBookValue(books) }
  val priceForFishFuture = Future { orders.computeFishValue(fishes) }

  val priceForAllFuture = for {
    valueForBooks <- priceForBooksFuture recover { case m: RuntimeException => 0.0 }
    valueForFish <- priceForFishFuture recover { case m: RuntimeException => {println (s"error: $m")}; 0.0 }
  } yield (valueForBooks + valueForFish)

  print("final price: ")
  priceForAllFuture foreach println

  // necessary in this dummy app to let future complete
  System.in.read()

}

