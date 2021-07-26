package com.tb.schema

import com.tb.model.Employee
import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate

/**
  * Used to map, scala objects to postgres db tables
  */
object SlickSchemas {

  /**
    * @param tag
    */
  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    def id =
      column[Int]("id", O.PrimaryKey)

    def firstName = column[String]("firstname")

    def lastName = column[String]("lastname")

    def * =
      (id, firstName, lastName) <> (Employee.tupled, Employee.unapply)
  }
}
