package software.hsharp.demo.impl

import bla1.blablabla
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import java.time.LocalDateTime

@Path("status")
class Status {

    val status: String
        @GET
        @Produces("text/plain")
        get() = "This is active Kotlin code with server time " + LocalDateTime.now() + " source from 11:43 with " + blablabla();
}
