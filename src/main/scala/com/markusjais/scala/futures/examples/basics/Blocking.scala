package com.markusjais.scala.futures.examples.basics

import com.markusjais.scala.futures.examples.business.Book
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await

object Blocking extends App {

  val books = List(
    Book("Lord of the Rings", 12.95),
    Book("Effective Java", 39.95),
    Book("Akka in Action", 39.95))

  val bookPriceFuture = Future {
    orders.computeBookValue(books)
  }

  //  Don't do that - blocking is evil! (exception: testing)
  val price = Await.result(bookPriceFuture, 1.second)

  println(s"price: $price")

}








