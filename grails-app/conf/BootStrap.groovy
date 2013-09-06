import org.codehaus.groovy.grails.commons.ApplicationAttributes
import frod.routing.domain.Domain
import skolka_utulna.data.Fixtures
import skolka_utulna.data.ExampleData
import frod.routing.domain.Page
import grails.util.Environment
import skolka_utulna.data.TestData

class BootStrap {

    def init = { servletContext ->
        def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
        Domain defaultDomain = new Domain(protocol : 'http', host: 'localhost', port: '8080', domainUrlPart: '/skolka_utulna')
        defaultDomain.save()

        def fixtures = Fixtures.load(ctx, defaultDomain)
        if (Environment.current == Environment.TEST) {
            TestData.load(ctx, defaultDomain, fixtures.root, fixtures.pageTypes) // todo
        } else {
            ExampleData exampleData = ctx.'skolka_utulna.data.ExampleData'
            exampleData.load(ctx, defaultDomain, fixtures.root, fixtures.pageTypes, fixtures.websites, fixtures.mainMenuItems)
        }
    }
    def destroy = {
    }
}
