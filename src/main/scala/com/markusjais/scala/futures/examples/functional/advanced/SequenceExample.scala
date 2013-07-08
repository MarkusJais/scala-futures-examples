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
  
  //From the docs (http://docs.scala-lang.org/overviews/core/futures.html):
  //the function for the foreach gets asynchronously executed only if the future is completed successfully.
  totalAmountFuture foreach println
  
  // necessary in this dummy app to let future complete
  System.in.read()

}