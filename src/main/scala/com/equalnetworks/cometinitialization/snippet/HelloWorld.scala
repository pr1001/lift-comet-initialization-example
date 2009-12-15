package com.equalnetworks.cometinitialization.snippet

import scala.xml.NodeSeq
import net.liftweb.common.{Box, Full, Empty}
import net.liftweb.http.S
import com.equalnetworks.cometinitialization.comet._

class HelloWorld {
  def howdy = <span>Welcome to cometinitialization at {new _root_.java.util.Date}</span>
  def test(in: NodeSeq): NodeSeq = {
    S.session.map(session => {
      session.setupComet("Test", Empty, Setup("This is a setup message"))
      <lift:comet type="Test">
        <flot:graph>Loading...</flot:graph>
      </lift:comet>
      <br />
      <div id="ph_graph" style="width:600px;height:300px"></div>
    }) openOr NodeSeq.Empty
  }
}

