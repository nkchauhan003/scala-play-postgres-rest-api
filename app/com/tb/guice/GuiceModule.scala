package com.tb.guice

import com.google.inject.AbstractModule
import com.tb.service.{EmployeeService, EmployeeServiceImpl}

class GuiceModule() extends AbstractModule {

  /**
    * This will let 'Guice' know about implementation of 'EmployeeService'
    */
  override def configure() = {
    bind(classOf[EmployeeService]).to(classOf[EmployeeServiceImpl])
  }
}
