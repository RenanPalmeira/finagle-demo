package com.temperature.server

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}

import com.temperature.thrift.{ TemperatureService }

object Server extends App {
    val service = new TemperatureService[Future] {
        def hi() = Future.value("hi")
    }

    val server = Thrift.server
        .withLabel("temperature-service")
        .serveIface("localhost:8080", service)
        .announce("zk!127.0.0.1:2181!/service/temperature!0")

    Await.ready(server)
}