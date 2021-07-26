package com.tb.db

import slick.jdbc.JdbcBackend.Database

object Connection {
  val db = Database.forConfig("postgres")
}
