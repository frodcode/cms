var app = new cms.Application();

app.configure(function (dic) {
    // postup pro vytvareni nazvu sluzeb modulu:
    //   - odstranit jedno factory z konce (pokud je definovana sluzba pomoci metody faktory)
    //   - zrusit top level namespace
    //   - nahradit . za camel case
    //   - pokud posledni namespace je shodny s nazvem modulu, tak tuhle duplicitu zrusit
    //
    // nebo:
    //   - vzit namespace
    //   - odstranit top level namespace
    //   - nahradit . za camel case
    //   - pridat Module nebo ModuleFactory (podle toho, co to je)
    //
    // oba postupy by mely vest ke stejnym vysledkum

    dic
        .service('appModuleFactory', cms.AppModuleFactory)

        .factory('mealModuleFactory', cms.meal.mealModuleFactoryFactory)
    ;
});