package com.justbon.flink

class StationLog(station:String ,cout:String,cin :String,ctype:String,ct :Long ,du :Long) {
  var sid:String =station
  var callOut:String=cout
  var callIn:String=cin
  var callType:String=ctype
  var callTime:Long=ct
  var duration :Long=du

  def this(){
    this(null,null,null,null,-1l ,-1l)
  }

  override  def toString=getClass+"[station=" +sid+ ",callOut="+callOut+",callIn="+callIn+".callType="+callType+",timestamp="+callTime+",duration="+duration+"]"
}
