package data.remote

import app.cash.sqldelight.db.SqlDriver

expect class DatabasesDriverFactory {
    fun createDriver(): SqlDriver
}