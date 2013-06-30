package com.markusjais.scala.futures.examples.functional.mapFlatMap

import com.markusjais.scala.futures.examples.business.Book
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure

object AndThen extends App {

  val books = List(
    Book("Lord of the Rings", 12.95),
    Book("Effective Java", 39.95),
    Book("Akka in Action", 39.95))


  val bookPriceFuture = Future {
    orders.computeBookValue(books)
  }

  bookPriceFuture andThen {
    case Success(priceTotal) => println(s"price: $priceTotal")
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  println("done")
  
  // necessary in this dummy app to let future complete
  System.in.read()

}








