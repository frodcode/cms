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

        // todo
        return
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

    private static def getDateForDayOfWeek(def dayOfWeek)
    {
        def currentDate = new Date()
        def cal = Calendar.getInstance()
        cal.setTime(currentDate)
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        return cal.getTime()
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
        Date monday = getDateForDayOfWeek(Calendar.MONDAY)
        Date tuesday = getDateForDayOfWeek(Calendar.TUESDAY)
        Date wednesday = getDateForDayOfWeek(Calendar.WEDNESDAY)
        Date thursday = getDateForDayOfWeek(Calendar.THURSDAY)
        Date friday = getDateForDayOfWeek(Calendar.FRIDAY)

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

        def mainMenuItemArticles = [
                uvod: new Article(
                        headline: 'O školce',
                        text: getTextFromFile('utulna/uvod.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: pages.utulnaHomepage,
                ),
                nase_tridy: new Article(
                        headline: 'Naše třídy',
                        text: getTextFromFile('utulna/our_classes.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/nase-tridy',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.utulnaHomepage
                        ),
                ),
                o_skolce: new Article(
                        headline: 'O školce',
                        text: getTextFromFile('utulna/about.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/o-skolce',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.utulnaHomepage
                        ),
                ),
                akce: new Article(
                        headline: 'Akce',
                        text: getTextFromFile('utulna/events.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/akce',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.utulnaHomepage
                        ),
                ),
                kontakt: new Article(
                        headline: 'Kontakt',
                        text: getTextFromFile('utulna/contact.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/kontakt',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.contactPageType,
                                parent: pages.utulnaHomepage
                        ),
                )
        ]
        mainMenuItemArticles*.value*.page*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        mainMenuItemArticles*.value*.page*.save();
        mainMenuItemArticles.each { it
            if (mainMenuItems[it.key]) {
                mainMenuItems[it.key].page = it.value.page
            }
        }
        mainMenuItemArticles*.value*.save(flush:true);

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

        def mainMenuItemArticles = [
                uvod: new Article(
                        headline: 'O školce',
                        text: getTextFromFile('troilova/uvod.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: pages.troilovaHomepage,
                ),
                nase_tridy: new Article(
                        headline: 'Naše třídy',
                        text: getTextFromFile('troilova/our_classes.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/nase-tridy',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.troilovaHomepage
                        ),
                ),
                o_skolce: new Article(
                        headline: 'O školce',
                        text: getTextFromFile('troilova/about.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/o-skolce',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.troilovaHomepage
                        ),
                ),
                akce: new Article(
                        headline: 'Akce',
                        text: getTextFromFile('troilova/events.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/akce',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.articlePageType,
                                parent: pages.troilovaHomepage
                        ),
                ),
                kontakt: new Article(
                        headline: 'Kontakt',
                        text: getTextFromFile('troilova/contact.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: new Page(
                                domain: defaultDomain,
                                urlPart: '/kontakt',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                requestType: RequestTypeEnum.REGULAR,
                                httpMethod: HttpMethodEnum.GET,
                                pageType: pageTypes.contactPageType,
                                parent: pages.troilovaHomepage
                        ),
                )
        ]
        mainMenuItemArticles*.value*.page*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        mainMenuItemArticles*.value*.page*.save();
        mainMenuItemArticles.each { it
            if (mainMenuItems[it.key]) {
                mainMenuItems[it.key].page = it.value.page
            }
        }
        mainMenuItemArticles*.value*.save(flush:true);

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
        menuItemCommand.putAfterId = MenuItem.findByTitle('Organizace školního roku').id
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
                        headline: 'Kuřátka',
                        text: getTextFromFile('troilova/our_classes/kuratka.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/kuratka',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Kuřátka',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Berušky',
                        text: getTextFromFile('troilova/our_classes/berusky.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/berusky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Berušky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Delfíni',
                        text: getTextFromFile('troilova/our_classes/delfini.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/delfini',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Delfíni',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Žirafy',
                        text: getTextFromFile('troilova/our_classes/zirafy.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/zirafy',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Žirafy',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        'headline': 'Provoz a školné',
                        text: getTextFromFile('troilova/about/provoz_a_skolne.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provoz-a-skolne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
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
                        text: getTextFromFile('troilova/about/program_dne.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/program-dne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Program dne',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Provozní zaměstnanci',
                        text: getTextFromFile('troilova/about/provozni_zamestnanci.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provozni-zamestnanci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Provozní zaměstnanci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Organizace školního roku',
                        text: getTextFromFile('troilova/about/organizace_skolniho_roku.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/organizace-skolniho-roku',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Organizace školního roku',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        text: getTextFromFile('troilova/events/akce_na_letosni_rok.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolni-akce-na-letosni-rok',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školní akce na letošní rok',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Školka v přírodě',
                        text: getTextFromFile('troilova/events/skolka_v_prirode.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolka-v-prirode',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školka v přírodě',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Zájmové kroužky',
                        text: getTextFromFile('troilova/events/zajmove_krouzky.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/zajmove-krouzky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Zájmové kroužky',
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
                        text: getTextFromFile('utulna/our_classes/motylci.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/motylci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
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
                        text: getTextFromFile('utulna/our_classes/vcelky.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/vcelky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Včelky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Žabky',
                        text: getTextFromFile('utulna/our_classes/zabky.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/zabky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Žabky',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.nase_tridy.id
                        ]
                ],
                [
                        headline: 'Ježci',
                        text: getTextFromFile('utulna/our_classes/jezci.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/jezci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.nase_tridy.page.id,
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
                        text: getTextFromFile('utulna/about/provoz_a_skolne.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provoz-a-skolne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
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
                        text: getTextFromFile('utulna/about/program_dne.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/program-dne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Program dne',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Provozní zaměstnanci',
                        text: getTextFromFile('utulna/about/provozni_zamestnanci.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provozni-zamestnanci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Provozní zaměstnanci',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Organizace školního roku',
                        text: getTextFromFile('utulna/about/organizace_skolniho_roku.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/organizace-skolniho-roku',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.o_skolce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Organizace školního roku',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.o_skolce.id
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        text: getTextFromFile('utulna/events/akce_na_letosni_rok.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolni-akce-na-letosni-rok',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školní akce na letošní rok',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Školka v přírodě',
                        text: getTextFromFile('utulna/events/skola_v_prirode.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolka-v-prirode',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Školka v přírodě',
                                putAfterId: null, //will be calculated
                                mainMenuItemId: mainMenuItems.akce.id
                        ]
                ],
                [
                        'headline': 'Zájmové kroužky',
                        text: getTextFromFile('utulna/events/zajmove_krouzky.html'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/zajmove-krouzky',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: mainMenuItems.akce.page.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Zájmové kroužky',
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
