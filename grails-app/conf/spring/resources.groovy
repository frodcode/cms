import skolka_utulna.data.ExampleData
import skolka_utulna.data.AdminMenuFactory

// Place your Spring DSL code here
beans = {
    'skolka_utulna.data.ExampleData'(ExampleData) {
        commandObjectService = ref('commandObjectService')
        articleService = ref('articleService')
        pageService = ref('pageService')
        routingService = ref('routingService')
        mediaFacade = ref('mediaFacade')
        menuItemService = ref('menuItemService')
    }
    adminMenuFactory(AdminMenuFactory) { bean->
        bean.autowire = 'byType'
    }
}
