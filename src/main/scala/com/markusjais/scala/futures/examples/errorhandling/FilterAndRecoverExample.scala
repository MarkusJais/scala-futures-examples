package com.markusjais.scala.futures.examples.errorhandling
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FilterAndRecoverExample extends App {

  val MIN_PRICE_FOR_ORDER = 100.00

  val bookPrice = 50.00

  val priceWithVatFuture = Future { orders.computeValueWithVat(bookPrice) } filter (_ >= MIN_PRICE_FOR_ORDER)

  val finalPrice = priceWithVatFuture recover {
    case m: NoSuchElementException => MIN_PRICE_FOR_ORDER
  }

  print("final price: ")
  finalPrice foreach println
  
  
  // necessary in this dummy app to let future complete
  System.in.read()

}

