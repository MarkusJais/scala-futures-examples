package com.markusjais.scala.futures.examples.business

import scala.util.Random

case class Fish(name: String, price: Double)
case class Book(title: String, price: Double)

// dummy object to fake some functions for ordering stuff
object orders {

  import sleep._
  
  def computeBookValue(books: List[Book]) = {
    sleepRandom

    // dummy exception to show how to handle failures
    if (Random.nextInt(5) == 1) throw new RuntimeException("shit happens")

    books.foldLeft(0.0)(_ + _.price)
  }

  def computeFishValue(fishes: List[Fish]) = {
    sleepRandom
    for (fish <- fishes) {
      if (fish.name.contains("Shark")) {
        throw new RuntimeException("Don't eat sharks - they are endangered")
      }
    }
    fishes.foldLeft(0.0)(_ + _.price)
  }

  def computeValueWithVat(netValue: Double) = {
    sleepRandom
    netValue * 1.10
  }

  def euroToDollar(amountInDollar: Double) = {
    sleepRandom
    amountInDollar * 1.2
  }
  
  
  def getOrderInEurosFromDb(orderId: Long) = {
    sleepRandom
    Random.nextInt(100)
  }
  
}