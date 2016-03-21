package spray.actors

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.cluster.{Cluster, ClusterEvent}
import akka.cluster.ClusterEvent._
import akka.actor.ActorLogging


class ClusterMain extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  cluster.subscribe(self, classOf[ClusterEvent.MemberUp])
  cluster.subscribe(self, classOf[ClusterEvent.MemberRemoved])

  cluster.join(cluster.selfAddress)

  override def receive: Receive = {

    case ClusterEvent.MemberUp(member) =>
      if (member.address != cluster.selfAddress) {
        // Someone joined ...
      }

    case ClusterEvent.MemberRemoved(member, _) =>
        // Someone got lost ...

  }
}
