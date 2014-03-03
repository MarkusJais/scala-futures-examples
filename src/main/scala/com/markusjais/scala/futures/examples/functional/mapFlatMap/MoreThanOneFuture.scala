package com.markusjais.scala.futures.examples.functional.mapFlatMap

import com.markusjais.scala.futures.examples.business.Book
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.Fish

object MoreThanOneFuture extends App {

  val books = List(
    Book("Lord of the Rings", 10.00),
    Book("Effective Java", 10.00),
    Book("Akka in Action", 10.00))

  val fishes = List(
    Fish("Pazific Salmon, MSC", 50.00),
    Fish("Mackerel, MSC", 20.00))

  val bookPriceFuture = Future { orders.computeBookValue(books) }
  val fishPriceFuture = Future { orders.computeFishValue(fishes) }

  val completeValueInDollarsFuture = for {
    bookPrice <- bookPriceFuture
    fishPrice <- fishPriceFuture
    valueWithVat <- Future { orders.computeValueWithVat(bookPrice + fishPrice) }
    valueInDollars <- Future { orders.euroToDollar(valueWithVat) }
  } yield (valueInDollars)

  completeValueInDollarsFuture onComplete {
    case Success(priceTotal) => println(s"complete Value in Dollars: $priceTotal")
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  // necessary in this dummy app to let future complete
  System.in.read()
}

