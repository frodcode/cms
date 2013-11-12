import org.codehaus.groovy.grails.commons.ApplicationAttributes
import frod.routing.domain.Domain
import skolka_utulna.data.Fixtures
import skolka_utulna.data.ExampleData
import frod.routing.domain.Page
import grails.util.Environment
import skolka_utulna.data.TestData
import grails.converters.JSON;

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)

        JSON.registerObjectMarshaller(Date) {
            return it?.format("yyyy-MM-dd")
        }

        if (grailsApplication.config.dataSource.dbCreate != 'update') {

            Domain defaultDomain
            if (Environment.current == Environment.PRODUCTION) {
                defaultDomain = new Domain(protocol: 'http', host: 'www.hedvabnepovleceni.cz', port: '80', domainUrlPart: '')
            } else {
                defaultDomain = new Domain(protocol: 'http', host: 'localhost', port: '8080', domainUrlPart: '')
            }
            defaultDomain.save()

            def fixtures = Fixtures.load(ctx, defaultDomain)
            if (Environment.current == Environment.TEST) {
                TestData.load(ctx, defaultDomain, fixtures.root, fixtures.pageTypes) // todo
            } else {
                ExampleData exampleData = ctx.'skolka_utulna.data.ExampleData'
                exampleData.load(ctx, defaultDomain, fixtures)
            }
        } else {
            println '---------- just updating db, no example data loaded ----'
        }
    }
    def destroy = {
    }
}
