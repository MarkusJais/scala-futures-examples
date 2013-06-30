package com.markusjais.scala.futures.examples.functional.advanced

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success
import scala.util.Failure
import com.markusjais.scala.futures.examples.business.orders
import com.markusjais.scala.futures.examples.business.sleep
import scala.util.Random

case class Animal(name: String, kind: String)

object animals {
	import sleep._
  
	def getRandonAnimal = {
	  val animals = Vector(
			  Animal("Tiger", "cat"),
			  Animal("Leopard Cat", "cat"),
			  Animal("Anaconda", "snake"),
			  Animal("Harpy Eagle", "bird"),
			  Animal("Wolf", "canid"),
			  Animal("Blue Whale", "whale")
	  )
	sleepRandom
	animals(Random.nextInt(animals.length))
	}
	
}

object Findxample extends App {

  val listOfAnimalFutures = List.fill(10)(Future { animals.getRandonAnimal }) // 100 Futures with a Random value

  val firstCatFuture = Future.find(listOfAnimalFutures)( _.kind == "cat" ) 
  
  print("first cat:  ")
  firstCatFuture foreach println

  
  
  
  // necessary in this dummy app to let future complete
  System.in.read()

}








