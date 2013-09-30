package skolka_utulna.data

import skolka_utulna.ArticleService
import skolka_utulna.ArticleCommand
import frod.routing.domain.Page
import skolka_utulna.ArticleStatusEnum
import frod.routing.domain.UrlTypeEnum
import frod.routing.service.PageCommand

import frod.utils.commandObject.CommandObjectService
import skolka_utulna.Article
import skolka_utulna.MenuItemCommand
import skolka_utulna.MenuItem
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.HttpMethodEnum
import frod.routing.service.PageService
import frod.routing.service.RoutingService

import skolka_utulna.meal.MealMenu
import frod.media.domain.MediaGroup

import frod.media.model.MediaFacade
import skolka_utulna.MenuItemService
import skolka_utulna.Website

/**
 * User: freeman
 * Date: 19.8.13
 */
class ExampleData {

    CommandObjectService commandObjectService
    ArticleService articleService

    PageService pageService

    RoutingService routingService

    MediaFacade mediaFacade

    MenuItemService menuItemService

    public def load(def ctx, def defaultDomain, def fixtures) {
        def root = fixtures.root;
        def pageTypes = fixtures.pageTypes
        def websites = fixtures.websites
        def mainMenuItems = fixtures.mainMenuItems
        def mealMenuTypes = fixtures.mealMenuTypes
        def galleryMediaGroupType = fixtures.galleryMediaGroupType

        def utulna = loadArticlesUtulna(defaultDomain, root, pageTypes, websites, mainMenuItems.utulna)
        def troilova = loadArticlesTroilova(defaultDomain, root, pageTypes, websites, mainMenuItems.troilova)

        loadAktuality(defaultDomain, pageTypes, troilova.homepage, mainMenuItems.troilova)
        loadAktuality(defaultDomain, pageTypes, utulna.homepage, mainMenuItems.utulna)

        loadMediaUtulna(galleryMediaGroupType, mainMenuItems.utulna, defaultDomain, pageTypes, utulna.homepage, websites.utulna)
        loadMediaTroilova(mainMenuItems.troilova, defaultDomain, pageTypes, troilova.homepage)

        loadMealMenus(mealMenuTypes, mainMenuItems.utulna, defaultDomain, pageTypes, utulna.homepage, websites.utulna)
        loadMealMenus(mealMenuTypes, mainMenuItems.troilova, defaultDomain, pageTypes, troilova.homepage, websites.troilova)
        return []
    }

    public def loadMediaTroilova(def mainMenuItems, def defaultHost, def pageTypes, def homepage) {
        Page galleryPage = new Page(
                domain: defaultHost,
                urlPart: '/fotogalerie',
                urlType: UrlTypeEnum.FROM_PARENT,
                requestType: RequestTypeEnum.REGULAR,
                httpMethod: HttpMethodEnum.GET,
                pageType: pageTypes.galleryPageType,
                parent: homepage
        )

        pageService.setDefaults(galleryPage)
        routingService.regenerateUrl(galleryPage)
        galleryPage.save(flush: true)

        mainMenuItems.fotogalerie.page = galleryPage
        mainMenuItems.fotogalerie.save(flush: true)
    }

    public def loadMediaUtulna(def galleryMediaGroupType, def mainMenuItems, def defaultHost, def pageTypes, def homepage, Website website) {
        Page galleryPage = new Page(
                domain: defaultHost,
                urlPart: '/fotogalerie',
                urlType: UrlTypeEnum.ROOT,
                requestType: RequestTypeEnum.REGULAR,
                httpMethod: HttpMethodEnum.GET,
                pageType: pageTypes.galleryPageType,
                parent: homepage
        )

        pageService.setDefaults(galleryPage)
        routingService.regenerateUrl(galleryPage)
        galleryPage.save(flush: true)

        mainMenuItems.fotogalerie.page = galleryPage
        mainMenuItems.fotogalerie.save(flush: true)

        MediaGroup mediaGroup = new MediaGroup()
        mediaGroup.setName('DRAVCI v MŠ Útulná')
        mediaGroup.setType(galleryMediaGroupType)
        mediaGroup.save(flush: true);
        website.addToMediaGroups(mediaGroup)
        website.save(flush:true)

        (1..12).each { number ->
            def resourceName = "data/utulna/images/dravci/" + number + ".jpg"
            def imageStream = this.class.classLoader.getResourceAsStream(resourceName)
            File tmp = File.createTempFile('images', '.jpg')
            tmp.setBytes(imageStream.getBytes())
            mediaFacade.addMediaFromFile(tmp.absolutePath, mediaGroup.id)
            tmp.delete()
        }
    }

    public def loadMealMenus(mealMenuTypes, mainMenuItems, def defaultHost, def pageTypes, def homepage, def website) {
        def mealMenuPage = new Page(
                domain: defaultHost,
                urlPart: '/jidelnicek',
                urlType: UrlTypeEnum.FROM_PARENT,
                requestType: RequestTypeEnum.REGULAR,
                httpMethod: HttpMethodEnum.GET,
                pageType: pageTypes.mealMenuPageType,
                parent: homepage
        )
        pageService.setDefaults(mealMenuPage)
        routingService.regenerateUrl(mealMenuPage)

        mealMenuPage.save(flush:true)
        mainMenuItems.jidelnicek.page = mealMenuPage
        mainMenuItems.jidelnicek.save(flush:true)

        def currentDate = new Date()
        Date monday = currentDate.clone()
        monday.setDate(Calendar.MONDAY)

        Date tuesday = currentDate.clone()
        tuesday.setDate(Calendar.TUESDAY)

        Date wednesday = currentDate.clone()
        wednesday.setDate(Calendar.WEDNESDAY)

        Date thursday = currentDate.clone()
        thursday.setDate(Calendar.THURSDAY)

        Date friday = currentDate.clone()
        friday.setDate(Calendar.FRIDAY)

        def mealMenus = [
                new MealMenu(
                        name: 'Salámová pom. , chléb Šumava, jablko, čaj lesní směs',
                        mealMenuType: mealMenuTypes.presnidavka,
                        validDate: monday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Medové kroužky , mléko, bylinný čaj s meduňkou',
                        mealMenuType: mealMenuTypes.presnidavka,
                        validDate: tuesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Pomazánka ze strouhaného sýra, houska, salátová okurka, mléko',
                        mealMenuType: mealMenuTypes.presnidavka,
                        validDate: wednesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Rbičková pomazánka, chléb, švestky, čaj goji-limetka',
                        mealMenuType: mealMenuTypes.presnidavka,
                        validDate: thursday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Bobík vanilka, veka, čaj dobré ráno',
                        mealMenuType: mealMenuTypes.presnidavka,
                        validDate: friday,
                        website: website,
                ),

                new MealMenu(
                        name: 'Hovězí polévka, Krupicová kaše s Grankem, čaj divoká třešeň',
                        mealMenuType: mealMenuTypes.obed,
                        validDate: monday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Selská polévka, Vepřová játra na slanině, rýže, čaj',
                        mealMenuType: mealMenuTypes.obed,
                        validDate: tuesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Fazolová polévka, Přírodní kuřecí plátek, bramborová kaše, salát z čínského zelí, šťáva',
                        mealMenuType: mealMenuTypes.obed,
                        validDate: wednesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Vývar se sýrovým strouháním, Svíčková na smetaně, houskový knedlík, šťáva',
                        mealMenuType: mealMenuTypes.obed,
                        validDate: thursday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Česneková polévka s houstičkou, Zapečené těstoviny, červená řepa, šťáva',
                        mealMenuType: mealMenuTypes.obed,
                        validDate: friday,
                        website: website,
                ),

                new MealMenu(
                        name: 'Dýňová karta, mrkev, bílá káva',
                        mealMenuType: mealMenuTypes.svacina,
                        validDate: monday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Párty chléb s máslem, meloun, čaj s citronem',
                        mealMenuType: mealMenuTypes.svacina,
                        validDate: tuesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Ovocná miska, piškoty, čaj borůvka-rakytník',
                        mealMenuType: mealMenuTypes.svacina,
                        validDate: wednesday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Jogurtové mléko, karlovarský rohlík',
                        mealMenuType: mealMenuTypes.svacina,
                        validDate: thursday,
                        website: website,
                ),
                new MealMenu(
                        name: 'Křehký chléb, zelenina, čaj s citronem',
                        mealMenuType: mealMenuTypes.svacina,
                        validDate: friday,
                        website: website,
                ),
        ]
        mealMenus*.save(flush: true, failOnError: true);
        return mealMenus
    }

    private String getTextFromFile(filename) {
        InputStream stream = this.class.classLoader.getResourceAsStream('data/'+filename)
        if (!stream) {
            throw new IllegalArgumentException(sprintf('Cannot load example data. Cannot find file "%s"', filename))
        }
        return stream.getText('UTF-8')
    }

    def loadArticlesUtulna(def defaultDomain, def root, def pageTypes, def websites, def mainMenuItems) {
        def allArticles = []

        def pages = [
                utulnaHomepage: new Page(
                        domain: defaultDomain,
                        urlPart: '/utulna',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType,
                        parent: root
                ),
        ]

        pages*.value*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        pages*.value*.save(flush:true)

        mainMenuItems.uvod.page = pages.utulnaHomepage
        mainMenuItems.uvod.save(flush: true)

        websites.utulna.homepage = pages.utulnaHomepage
        websites.utulna.save(flush:true)


        def utulnaHomepageArticle = new Article(
                headline: 'O školce',
                text: getTextFromFile('uvod.txt'),
                status: ArticleStatusEnum.PUBLISHED,
                page: pages.utulnaHomepage
        )
        utulnaHomepageArticle.save(flush:true)



        def allData = getDataForUtulnaArticles(defaultDomain, pages.utulnaHomepage, pageTypes, mainMenuItems)

        def putAfters = [:]
        allData.each { data ->
            def index = allData.indexOf(data)
            if (!putAfters.containsKey(data.menuItem.mainMenuItemId)) {
                putAfters[data.menuItem.mainMenuItemId] = []
            } else {
                putAfters[data.menuItem.mainMenuItemId].add(allArticles[index-1].menuItem.id)
                data.menuItem.putAfterId =  putAfters[data.menuItem.mainMenuItemId].last()
            }
            allArticles << createFromData(data)
        }

        return [articles: allArticles, homepage: pages.utulnaHomepage]
    }

    def loadArticlesTroilova(def defaultDomain, def root, def pageTypes, def websites, def mainMenuItems) {
        def allArticles = []

        def pages = [
                troilovaHomepage: new Page(
                        domain: defaultDomain,
                        urlPart: '/troilova',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType,
                        parent: root
                ),
        ]

        pages*.value*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        pages*.value*.save(flush:true)

        mainMenuItems.uvod.page = pages.troilovaHomepage
        mainMenuItems.uvod.save(flush: true)

        websites.troilova.homepage = pages.troilovaHomepage
        websites.troilova.save(flush:true)


        def troilovaHomepageArticle = new Article(
                headline: 'O školce',
                text: getTextFromFile('uvod.txt'),
                status: ArticleStatusEnum.PUBLISHED,
                page: pages.troilovaHomepage
        )
        troilovaHomepageArticle.save(flush:true)



        def allData = getDataForTroilovaArticles(defaultDomain, pages.troilovaHomepage, pageTypes, mainMenuItems)

        def putAfters = [:]
        allData.each { data ->
            def index = allData.indexOf(data)
            if (!putAfters.containsKey(data.menuItem.mainMenuItemId)) {
                putAfters[data.menuItem.mainMenuItemId] = []
            } else {
                putAfters[data.menuItem.mainMenuItemId].add(allArticles[index-1].menuItem.id)
                data.menuItem.putAfterId =  putAfters[data.menuItem.mainMenuItemId].last()
            }
            allArticles << createFromData(data)
        }

        return [articles: allArticles, homepage: pages.troilovaHomepage]
    }

    public def loadAktuality(def defaultDomain, def pageTypes, def homepage, def mainMenuItems) {
        def newsPage = new Page(
                domain: defaultDomain,
                urlPart: '/aktuality',
                urlType: UrlTypeEnum.FROM_PARENT,
                requestType: RequestTypeEnum.REGULAR,
                httpMethod: HttpMethodEnum.GET,
                pageType: pageTypes.newsPageType,
                parent: homepage
        )
        pageService.setDefaults(newsPage)
        routingService.regenerateUrl(newsPage)

        newsPage.save(flush:true)

        MenuItemCommand menuItemCommand = new MenuItemCommand()
        menuItemCommand.mainMenuItemId =  mainMenuItems.o_skolce.id
        menuItemCommand.putAfterId = MenuItem.findByTitle('Prázdniny').id
        menuItemCommand.title = 'Aktuality'
        menuItemCommand.pageId = newsPage.id
        if (menuItemCommand.hasErrors()) {
            throw new IllegalStateException(menuItemCommand.getErrors())
        }
        menuItemService.create(menuItemCommand)
    }

    public def getDataForTroilovaArticles(def defaultDomain, Page homepage, def pageTypes, def mainMenuItems) {
        return [
                [
                        headline: 'Motýlci',
                        text: getTextFromFile('troilova/our_classes/motylci.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/motylci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Motýlci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Včelky',
                        text: getTextFromFile('troilova/our_classes/vcelky.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/vcelky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Včelky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Žablky',
                        text: getTextFromFile('troilova/our_classes/zabky.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/zabky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Žablky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Ježci',
                        text: getTextFromFile('troilova/our_classes/jezci.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/jezci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Ježci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        'headline': 'Provoz a školné',
                        text: getTextFromFile('troilova/about/provoz_a_skolne.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provoz-a-skolne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Provoz a školné',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Program dne',
                        text: getTextFromFile('troilova/about/program_dne.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/program-dne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Program dne',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Prázdniny',
                        text: getTextFromFile('troilova/about/prazdniny.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/prazdniny',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Prázdniny',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        text: getTextFromFile('troilova/events/akce_na_letosni_rok.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolni-akce-na-letosni-rok',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školní akce na letošní rok',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Den otevřených dvěří',
                        text: getTextFromFile('troilova/events/den_otevrenych_dveri.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/den-otevrenych-dveri',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Den otevřených dvěří',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Školka v přírodě',
                        text: getTextFromFile('troilova/events/skola_v_prirode.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolka-v-prirode',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školka v přírodě',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
        ]
    }

    public def getDataForUtulnaArticles(def defaultDomain, Page homepage, def pageTypes, def mainMenuItems) {
        return [
                [
                        headline: 'Motýlci',
                        text: getTextFromFile('utulna/our_classes/motylci.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/motylci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Motýlci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Včelky',
                        text: getTextFromFile('utulna/our_classes/vcelky.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/vcelky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Včelky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Žablky',
                        text: getTextFromFile('utulna/our_classes/zabky.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/zabky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Žablky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Ježci',
                        text: getTextFromFile('utulna/our_classes/jezci.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/jezci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Ježci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        'headline': 'Provoz a školné',
                        text: getTextFromFile('utulna/about/provoz_a_skolne.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provoz-a-skolne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Provoz a školné',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Program dne',
                        text: getTextFromFile('utulna/about/program_dne.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/program-dne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Program dne',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Prázdniny',
                        text: getTextFromFile('utulna/about/prazdniny.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/prazdniny',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Prázdniny',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        text: getTextFromFile('utulna/events/akce_na_letosni_rok.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolni-akce-na-letosni-rok',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školní akce na letošní rok',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Den otevřených dvěří',
                        text: getTextFromFile('utulna/events/den_otevrenych_dveri.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/den-otevrenych-dveri',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Den otevřených dvěří',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Školka v přírodě',
                        text: getTextFromFile('utulna/events/skola_v_prirode.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolka-v-prirode',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školka v přírodě',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
        ]
    }



    public Article createFromData(def data) {
        MenuItemCommand menuItemCommand = commandObjectService.create(MenuItemCommand, data.menuItem)
        PageCommand pageCommand = commandObjectService.create(PageCommand, data.page)
        ArticleCommand articleCommand = commandObjectService.create(ArticleCommand, data.findAll{ !['page', 'menuItem'].contains(it.key)})
        return articleService.create(articleCommand, menuItemCommand, pageCommand)
    }

}
