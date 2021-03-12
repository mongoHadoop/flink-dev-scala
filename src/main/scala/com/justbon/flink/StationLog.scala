package com.justbon.flink

class StationLog(station:String ,callOut:String,callIn :String,status:String,timestamp :Long ,flag :Int) {

  override  def toString=getClass+"[station=" + station + ",callOut="+callOut+",callIn="+callIn+",timestamp="+timestamp+"]"
}
