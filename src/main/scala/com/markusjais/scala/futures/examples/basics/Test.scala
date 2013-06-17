package com.markusjais.scala.futures.examples.basics

import scala.util.Random

object Test extends App {
  
  for( a <- 1 to 30){ 
    println( Random.nextInt(500) )
  }

}