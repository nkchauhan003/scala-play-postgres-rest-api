package com.tb.rest

import com.tb.model.Employee
import com.tb.service.EmployeeService
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents, EssentialAction}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import com.tb.model.Formatter._

/**
  * @param cc
  * @param employeeService
  */
@Singleton
class EmployeeController @Inject() (
    cc: ControllerComponents,
    employeeService: EmployeeService
) extends AbstractController(cc) {

  /**
    * Add Employee
    * @return
    */
  def add(): EssentialAction =
    Action.async { implicit request =>
      val employee: Option[Employee] =
        request.body.asJson.flatMap(Json.fromJson[Employee](_).asOpt)

      employee match {
        case Some(newItem) =>
          employeeService
            .add(newItem)
            .map {
              case a: Int if (a > 0) => Ok
              case _                 => InternalServerError
            }
        case None => Future.successful(BadRequest)
      }
    }

  /**
    * Get Employee by ID
    * @param id
    * @return
    */
  def findById(id: Int): EssentialAction =
    Action.async {
      employeeService.findById(id).map {
        case a: Some[Employee] => Ok(Json.toJson(a))
        case _                 => NotFound
      }
    }

  /**
    * Get all employees
    * @return
    */
  def findAll(): EssentialAction =
    Action.async {
      employeeService.findAll.map {
        case s => Ok(Json.toJson(s))
        case _ => NotFound
      }
    }

  /**
    * Update employee details
    * @return
    */
  def update() =
    Action.async { implicit request =>
      val employee: Option[Employee] =
        request.body.asJson.flatMap(Json.fromJson[Employee](_).asOpt)

      employee match {
        case Some(newItem) =>
          employeeService
            .update(newItem)
            .map {
              case a: Int if (a > 0) => Ok
              case _                 => InternalServerError
            }
        case None => Future.successful(BadRequest)
      }
    }

  /**
    * Delete an employee by ID
    * @param id
    * @return
    */
  def delete(id: Int) =
    Action.async {
      employeeService.delete(id).map {
        case a: Int if (a > 0) => Ok
        case _                 => InternalServerError
      }
    }
}
