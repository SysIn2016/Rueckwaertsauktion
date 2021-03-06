package Sysint2016.Rueckwaertsauktion;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * Startpunkt fuer den Tomcat Server
 * 
 */
public class ServerStart {
	public static void main(String[] args) throws Exception {
		String webappDirLocation = "src/main/Webseite";
		Tomcat tomcat = new Tomcat();

		/*
		 * festlegen des Ports als Port 80
		 */
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		/*
		 * Tomcat den Port mitteilen
		 */
		tomcat.setPort(Integer.valueOf(webPort));

		/*
		 * Tomcat den Ort fuer die Webseite mitteilen
		 */
		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(
				webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		// Declare an alternative location for your "WEB-INF/classes" dir
		// Servlet 3.0 annotation will work
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources,
				"/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(),
				"/"));
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}
}
