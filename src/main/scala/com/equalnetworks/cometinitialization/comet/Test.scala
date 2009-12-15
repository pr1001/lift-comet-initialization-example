/*
 * Test.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.equalnetworks.cometinitialization.comet

import scala.collection.mutable.{HashMap, ListBuffer}
import scala.Math.round

import net.liftweb.common.{Box, Full, Empty}
import net.liftweb.http._
// import net.liftweb
import net.liftweb.widgets.flot._
import net.liftweb.http.js.JsCmds.Noop
import net.liftweb.http.js.JE._

class Test extends CometActor{
  private var message: Box[String] = Empty
  val options = new FlotOptions() {
    override val xaxis = Full(new FlotAxisOptions() {
      override val mode = Full("time")
    })
  }

  var series: List[FlotSerie] = List(
    new FlotSerie() {
      override val label = Full("Max")
      override val data = List((1.0, 5.0), (2.0, 11.5), (3.0, 7.4))
    },
    new FlotSerie() {
      override val label = Full("Avg")
      override val data = List((1.0, 4.0), (2.0, 6.5), (3.0, 5.1))
    },
    new FlotSerie() {
      override val label = Full("Min")
      override val data = List((1.0, 0.0), (2.0, 3.0), (3.0, 1.0))
    }
  )
  val idPlaceholder = "ph_graph"

  override def defaultPrefix = Full("flot")

  def render = {
    println("Render called")
    bind("flot", "graph" -> Flot.render("ph_graph", series, options, Noop))
  }

  override def highPriority = {
    case Setup(msg) => {
      println("Received Setup with message: " + msg)
      message = Full(msg)
      reRender(true)
    }
  }
}
