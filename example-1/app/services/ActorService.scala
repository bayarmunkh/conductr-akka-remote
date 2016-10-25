package services

import akka.actor._
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import javax.inject._
import play.api.Logger

import com.typesafe.conductr.bundlelib.akka.LocationService
import com.typesafe.conductr.bundlelib.scala.{LocationCache, URI, URL}
import com.typesafe.conductr.lib.akka.ConnectionContext._
import com.typesafe.conductr.lib.akka.ImplicitConnectionContext

@Singleton
class CounterActor @Inject() (system: ActorSystem) extends Actor with ImplicitConnectionContext {

  val logger: Logger = Logger(this.getClass())

  val remotePath = {
    val uri = Await.result(LocationService.lookup("server", URI("tcp://localhost:2560"), new LocationCache()), 5 seconds).get
    val host = uri.getHost
    val port = uri.getPort
    // Note: the name of actor system is `BUNDLE_SYSTEM`-`BUNDLE_VERSION`.
    s"akka.tcp://ExampleSystem-1@$host:$port/user/dumb-counter"
  }  

  implicit val timeout: Timeout = Timeout.durationToTimeout( 3.seconds )

  (system.actorSelection(remotePath) ? Identify(remotePath)).onComplete {
    case Success(s) => println("Received from remote actor: " + s)
    case Failure(e) => println("failed", e)
  }

  def receive = PartialFunction.empty

}
