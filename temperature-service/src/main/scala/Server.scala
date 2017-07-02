package com.temperature.server

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}

import com.temperature.thrift.{ TemperatureService }

object Server extends App {
    val server = Thrift.server.serveIface("localhost:8080", new TemperatureService[Future] {
        def hi() = {
            Future.value("hi")
        }
    })

    Await.ready(server)
}