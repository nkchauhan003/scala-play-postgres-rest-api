package com.tb.service

import com.tb.db.Connection
import com.tb.model.Employee
import com.tb.schema.SlickSchemas.EmployeeTable
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

trait EmployeeService {

  /**
    * @param employee
    * @return
    */
  def add(employee: Employee): Future[Int]

  /**
    * @param id
    * @return
    */
  def findById(id: Int): Future[Option[Employee]]

  /**
    * @return
    */
  def findAll(): Future[Seq[Employee]]

  /**
    * @param employee
    * @return
    */
  def update(employee: Employee): Future[Int]

  /**
    * @param id
    * @return
    */
  def delete(id: Int): Future[Int]
}

class EmployeeServiceImpl extends EmployeeService {

  val employeeTable = TableQuery[EmployeeTable]

  /**
    * @param employee
    * @return
    */
  override def add(employee: Employee): Future[Int] = {
    val insertEmployeeQuery = employeeTable += employee
    Connection.db.run(insertEmployeeQuery)
  }

  /**
    * @param id
    * @return
    */
  override def findById(id: Int): Future[Option[Employee]] =
    Connection.db.run(employeeTable.filter(_.id === id).result.headOption)

  /**
    * @return
    */
  override def findAll(): Future[Seq[Employee]] =
    Connection.db.run(employeeTable.result)

  /**
    * @param employee
    * @return
    */
  override def update(employee: Employee): Future[Int] = {
    val updateEmployeeQuery = employeeTable
      .filter(_.id === employee.id)
      .map(x => (x.firstName, x.lastName))
      .update(employee.firstName, employee.lastName)
    Connection.db.run(updateEmployeeQuery)
  }

  /**
    * @param id
    * @return
    */
  override def delete(id: Int): Future[Int] =
    Connection.db.run(employeeTable.filter(_.id === id).delete)
}
