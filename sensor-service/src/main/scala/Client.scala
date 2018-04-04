package com.temperature.sensor

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}

import com.temperature.thrift.{ TemperatureService }

object Client extends App {
    val client = Thrift.client.newIface[TemperatureService.FutureIface]("localhost:8080")
    
    val display = client.hi()
        .onSuccess({ response => println("Received response: " + response) })

    Await.result(display)
}
