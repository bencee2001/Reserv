package hu.bme.onlabor.enviroment

class DevEnv: ApplicationEnv(
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost:5432/reserv?user=admin&password=1234"
)