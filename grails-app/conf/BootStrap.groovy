import org.codehaus.groovy.grails.commons.ApplicationAttributes
import frod.routing.domain.Domain
import skolka_utulna.data.Fixtures
import skolka_utulna.data.ExampleData
import frod.routing.domain.Page

class BootStrap {

    def init = { servletContext ->
        def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
        Domain defaultDomain = new Domain(protocol : 'http', host: 'localhost', port: '8080', domainUrlPart: '/skolka_utulna')
        defaultDomain.save()

        def fixtures = Fixtures.load(ctx, defaultDomain)
        def exampleData = ExampleData.load(ctx, defaultDomain, fixtures.homepage, fixtures.pageTypes)
    }
    def destroy = {
    }
}
