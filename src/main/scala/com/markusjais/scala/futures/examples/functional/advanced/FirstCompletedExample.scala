package com.markusjais.scala.futures.examples.functional.advanced


import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.db

object FirstCompletedExample extends App {
  
  
  val cacheFuture = Future { db.readUserNameFromCache(42) }
  val dbFuture = Future { db.readUserNameFromSlowDB(42) }

  Future.firstCompletedOf(List(cacheFuture, dbFuture)) onSuccess {  // ignore failure here
    case userName => println("user: " + userName)
  }
  
  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)

}