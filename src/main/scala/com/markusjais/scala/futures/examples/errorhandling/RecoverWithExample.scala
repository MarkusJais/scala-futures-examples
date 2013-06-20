package com.markusjais.scala.futures.examples.errorhandling

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.db
import java.io.IOException

object RecoverWithExample extends App {

  val cacheFuture = Future { db.readUserNameFromBrokenCacheConnection(42) }

  val userNameFuture = cacheFuture recoverWith {
    case _: IOException => 
      println("Exception!")
      Future { db.readUserNameFromSlowDB(42) }
  }

  userNameFuture foreach println

  // necessary in this dummy app because otherwise it would terminate before Future can complete
  Thread.sleep(5000)

}