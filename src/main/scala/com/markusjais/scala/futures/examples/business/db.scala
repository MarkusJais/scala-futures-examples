package com.markusjais.scala.futures.examples.business

import scala.util.Random
import java.io.IOException

// dummy object to fake some DB calls
object db {

  import sleep._
  
  def readFromSlaveDB(userId: Long) = {
    sleepRandom
    if (userId == 42) "Joe" else throw new NoSuchElementException
  }
  
  def readFromMasterDB(userID: Long) = {
    sleepRandom
    "John"
  }
  
  def readUserNameFromBrokenCacheConnection(userID: Long) = {
    sleepRandom
    throw new IOException("io error")
  }
  
  def readUserNameFromSlowDB(userID: Long) = {
    sleepRandom
    "Maria from slow DB"
  }
  
  def readUserNameFromCache(userID: Long) = {
    Thread.sleep(200)
    "Maria From Cache"
  }


}