package software.hsharp.demo

import org.glassfish.jersey.internal.OsgiRegistry
import org.glassfish.jersey.internal.util.ReflectionHelper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer
import org.osgi.service.component.ComponentContext
import org.osgi.service.component.annotations.*
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants

import javax.servlet.*
import java.io.IOException
import java.net.URISyntaxException
import java.net.URL
import java.util.Enumeration
import java.util.logging.Logger

//import org.glassfish.jersey.server.internal.scanning.CompositeResourceFinder;
//import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;

@Component(property = arrayOf(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/test/*"))
class Config @Throws(URISyntaxException::class, ClassNotFoundException::class)
constructor() : Servlet {

    @Transient
    private val sc: ServletContainer

    init {
        val rc = ResourceConfig()

        //rc.packages() not working properly atm..., add all classes manually here
        //rc.registerClasses(Status.class /*, AnotherResource.class, ...*/);

        val packages = arrayOf("software.hsharp.demo.impl")
        val cl = javaClass.classLoader
        val recur = false
        val reg = ReflectionHelper.getOsgiRegistryInstance()
        logger.info("Created! OSGI runtime? " + if (reg == null) "No" else "Yes")

        //CompositeResourceFinder finder = new CompositeResourceFinder();
        //BundleSchemeResourceFinderFactory f = new BundleSchemeResourceFinderFactory(); until it is made public...

        for (p in packages) {
            val p1 = p.replace('.', '/')
            val list = reg!!.getPackageResources(p1, cl, recur)
            while (list.hasMoreElements()) {
                val url = list.nextElement()
                val path = url.path
                val cls = (OsgiRegistry.bundleEntryPathToClassName(path, "") + "class").replace(".class", "")
                logger.info("Found(" + url.toURI().scheme + "):" + path + "-->" + cls)

                rc.registerClasses(reg.classForNameWithException(cls))
                //finder.push(f.create(url.toURI(), recur));
            }
        }
        //rc.registerFinder(finder);

        //rc.registerFinder(new PackageNamesScanner(cl, packages, false)); !bundleentry is not bundle...
        //INFO: Found(bundleentry):/com/demo/app/restapi/impl/Status.class-->com.demo.app.restapi.impl.Status.

        //////////////////////////////////////////////////////////////////////////
        this.sc = ServletContainer(rc)
    }

    @Activate
    internal fun activate(context: ComponentContext) {
        logger.info("Activated! " + javaClass)
    }

    @Deactivate
    internal fun deactivate(context: ComponentContext) {
        logger.info("Deactivated... " + javaClass)
    }

    override fun destroy() {
        // TODO Auto-generated method stub
        this.sc.destroy()
    }

    override fun getServletConfig(): ServletConfig {
        // TODO Auto-generated method stub
        return this.sc.servletConfig
    }

    override fun getServletInfo(): String {
        // TODO Auto-generated method stub
        return this.sc.servletInfo
    }

    @Throws(ServletException::class)
    override fun init(cfg: ServletConfig) {
        // TODO Auto-generated method stub
        this.sc.init(cfg)
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(arg0: ServletRequest, response: ServletResponse) {
        this.sc.service(arg0, response)
    }

    companion object {
        //logger.info() to log
        private val logger = Logger.getLogger(Config::class.java.name)
    }


}