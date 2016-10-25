package services


import akka.actor._
import javax.inject._

class DumbCounter extends Actor {

  def receive = {
    case n: Int => 
      println("received : " + n)
      sender ! (n + 1)
  }

}