package com.markusjais.scala.futures.examples.business

import scala.util.Random

object sleep {
  
  def sleepRandom = Thread.sleep(Random.nextInt(500))

}