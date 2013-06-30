package com.markusjais.scala.futures.examples.functional.mapFlatMap
import com.markusjais.scala.futures.examples.business.orders
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object MapExample extends App {

 val basePrice = 100.00

  val priceWithVat = Future { orders.computeValueWithVat(basePrice) }
  val priceInDollars = priceWithVat map { result => 
  	orders.euroToDollar(result)
  }
  priceInDollars foreach println  // or use onComplete
  

  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)

}

