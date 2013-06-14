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

object MoreThanOneFuture extends App {

  val books = List(
    Book("Lord of the Rings", 10.00),
    Book("Effective Java", 10.00),
    Book("Akka in Action", 10.00))

  val fishes = List(
    Fish("Pazific Salmon, MSC", 10.00),
    //Fish("Great White Shark", 10.00),
    Fish("Mackerel, MSC", 20.00))

  val completeValueInDollarsFuture = for {
    bookPrice <- Future { orders.computeBookValue(books) }
    fishPrice <- Future { orders.computerFishalue(fishes) }
    valueWithVat <- Future { orders.computeValueWithVat(bookPrice + fishPrice) }
    valueInDollars <- Future { orders.euroToDollar(valueWithVat) }
  } yield (valueInDollars)

  completeValueInDollarsFuture onComplete {
    case Success(priceTotal) => println("complete Value in Dollars:" + priceTotal)
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }

  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)

}

