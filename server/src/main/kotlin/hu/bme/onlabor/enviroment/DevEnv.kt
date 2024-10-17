package hu.bme.onlabor.enviroment

class DevEnv: ApplicationEnv(
    dbDriverClass = "org.postgresql.Driver",
    dbUrl = "jdbc:postgresql://localhost:5432/reserv?user=admin&password=1234"
)