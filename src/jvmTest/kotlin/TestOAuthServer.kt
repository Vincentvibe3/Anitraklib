import io.ktor.utils.io.core.*
import org.http4k.client.ApacheClient
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Undertow
import org.http4k.server.asServer
import java.nio.ByteBuffer
import kotlin.text.toByteArray

fun main() {
    val body = ByteBuffer.wrap(
        """
            <html>        
                <body>
                <p id="text"></p>
                </body>
                <script>
                    document.getElementById("text").textContent = window.location.hash
                </script>
            </html>
        """.trimIndent().toByteArray()
    )
    val app = { request: Request -> Response(OK).body(
        Body(body)
    ).header("Content-Type", "text/html") }
    val server = app.asServer(Undertow(8000)).start()
//    server.stop()
}