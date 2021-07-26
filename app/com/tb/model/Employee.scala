package com.tb.model
import play.api.libs.json.Json

/**
  *
  * @param id
  * @param firstName
  * @param lastName
  */
case class Employee(
    id: Int,
    firstName: String,
    lastName: String
)

/**
  * This is used to convert 'Employee' objects to json
  */
object Formatter {
  implicit val employeeJson = Json.format[Employee]
}
