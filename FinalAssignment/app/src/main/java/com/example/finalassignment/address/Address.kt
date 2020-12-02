package com.example.finalassignment.address

class Address (val request: AddressData, val response: List<Times>)

class AddressData (val altitude: String?, val datetime: String?, val latitude: String, val longitude: String, val passes: String?)

class Times (val duration: String, val risetime: String)