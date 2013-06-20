package com.markusjais.scala.futures.examples.functional.advanced

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.orders

object SequenceExample extends App {

  val listOfOrderFutures = List.fill(100)(Future { orders.getOrderInEurosFromDb(42) }) // 100 Futures with a Random value

  val totalAmountFuture = Future.sequence(listOfOrderFutures) map (_.sum) 
  
  print("total value: ")
  totalAmountFuture foreach println
  
  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(50000)

}