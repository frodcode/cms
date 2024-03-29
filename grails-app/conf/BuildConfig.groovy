grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        compile group: 'org.jumpmind.symmetric.jdbc',
                name: 'postgresql',
                version: '9.2-1002-jdbc4'
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        // runtime 'mysql:mysql-connector-java:5.1.22'
    }

    plugins {
        compile ":routing:0.8"
        compile ":diagnostics:0.1"
        compile ":frod-utils:0.1"
        compile ":media:1.6"
        compile ":cms-admin:0.6"
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.3"
        runtime ":resources:1.2"
        build ":release:2.2.1"
        compile ':spring-security-core:1.2.7.3'
        compile ":cache-headers:1.1.5"
        runtime ":cached-resources:1.0"
        runtime ":zipped-resources:1.0"
        //compile: ":routing:0.1"
        //runtime: ":media:0.1"
        //runtime: ":mediabullshitwhat:1.2"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.3.2"

        compile ':cache:1.0.1'
    }

}
// grails.plugin.location.Media = "../../plugins/Media";
//grails.plugin.location.Diagnostics = "../../plugins/Diagnostics"
//grails.plugin.location.FrodUtils = "../../plugins/FrodUtils"
//grails.plugin.location.Routing = "../../plugins/Routing"
//grails.plugin.location.CmsAdmin = "../../plugins/CmsAdmin";
