import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import hu.bme.onlabor.security.AuthType
import io.ktor.server.application.Application
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Date
import kotlin.test.Test

class JwtGeneratorTest {

    @Test
    fun testGenToken() {
        val token = JWT.create()
            .withClaim("username", "test_name")
            .withClaim("password", "test_pass")
            .withClaim("role", "test_role")
            .withExpiresAt(Date(System.currentTimeMillis() + 60_000))
            .sign(Algorithm.HMAC256("eper"))

        val verifier = JWT.require(Algorithm.HMAC256("eper"))
            .build()
        val decodedJWT = verifier.verify(token)
        val username = decodedJWT.getClaim("username").asString()
        val password = decodedJWT.getClaim("password").asString()
        val role = decodedJWT.getClaim("role").asString()
        println("Verified username: $username, password: $password, role: $role")
        true
    }

    @Test
    fun getSecret(){
        val t = Files.readString(Paths.get("../key/jwt_secret.txt"))
        println(t)
        true
    }

}