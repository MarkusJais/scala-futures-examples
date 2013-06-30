package com.markusjais.scala.futures.examples.errorhandling
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.db

object FallbackToExample extends App {

 val basePrice = 100.00;

  val userName = Future { db.readFromSlaveDB(42) } fallbackTo Future { db.readFromMasterDB(43) }

  userName onComplete {
    case Success(userName) => println(s"user: $userName")
    case Failure(t: Throwable) => println(s"Shit, something went wrong: $t")
  }
  

  // necessary in this dummy app to let future complete
  System.in.read()

}

