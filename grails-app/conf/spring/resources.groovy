import skolka_utulna.data.ExampleData

// Place your Spring DSL code here
beans = {
    'skolka_utulna.data.ExampleData'(ExampleData) {
        commandObjectService = ref('commandObjectService')
        articleService = ref('articleService')
    }
}
