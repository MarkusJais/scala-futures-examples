package com.markusjais.scala.futures.examples.functional.advanced

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.db

object FirstCompletedExample extends App {

  val cacheFuture1 = Future { db.readUserNameFromCacheServer_1(42) }
  val cacheFuture2 = Future { db.readUserNameFromCacheServer_2(42) }

  Future.firstCompletedOf(List(cacheFuture1, cacheFuture2)) onSuccess { // ignore failure here
    case userName => println("user: $userName")
  }

  // necessary in this dummy app to let future complete
  System.in.read()

}