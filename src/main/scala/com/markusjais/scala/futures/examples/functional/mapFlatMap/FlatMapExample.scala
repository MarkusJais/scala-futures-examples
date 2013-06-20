package com.markusjais.scala.futures.examples.functional.mapFlatMap

import com.markusjais.scala.futures.examples.business.Book
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FlatMapExample extends App {

  val books = List(
    Book("Lord of the Rings", 20.00),
    Book("Effective Java", 40.00),
    Book("Akka in Action", 40.00))

  val bookPriceFuture = Future {
    orders.computeBookValue(books)
  }

  val priceInDollars = bookPriceFuture flatMap { bookPrice =>
    Future { orders.computeValueWithVat(bookPrice) } map { result =>
      orders.euroToDollar(result)
    }
  }
  priceInDollars foreach println // or use onComplete

  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)

}

