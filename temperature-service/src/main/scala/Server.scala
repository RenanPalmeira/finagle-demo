package com.temperature.server

import com.twitter.finagle.Thrift
import com.twitter.finagle.thrift.Protocols
import com.twitter.util.{Await, Future}

import com.temperature.thrift.{ TemperatureService }

object Server extends App {
    val service = new TemperatureService.FutureIface {
        def hi() = Future.value("hi")
    }

    // scrooge generates class to wrap a thrift service into a finagle compatible service definition
    val finagledService = new TemperatureService.FinagledService(
      service, Protocols.binaryFactory()
    )

    // run and announce the server
     val server = Thrift.server
          .withLabel("temperature-service")
          .serveAndAnnounce(
            // static announcemnt via zookeeper
            name = "zk!127.0.0.1:2181!/service/temperature!0",
            // bind this service to this address
            addr = ":8082",
            // the service to announce
            service = finagledService
          )

    Await.ready(server)
}
