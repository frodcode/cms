package skolka_utulna.data

import skolka_utulna.ArticleService
import skolka_utulna.ArticleCommand
import frod.routing.domain.Page
import skolka_utulna.ArticleStatusEnum
import frod.routing.domain.UrlTypeEnum
import frod.routing.service.PageCommand
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import frod.utils.commandObject.CommandObjectService
import skolka_utulna.Article
import skolka_utulna.MenuItemCommand
import skolka_utulna.MenuItem

/**
 * User: freeman
 * Date: 19.8.13
 */
class ExampleData {

    CommandObjectService commandObjectService
    ArticleService articleService

    public def load(def ctx, def defaultDomain, Page homepage, def pageTypes) {
        loadArticles(defaultDomain, homepage, pageTypes)
        return []
    }

    private String getTextFromFile(filename) {
        InputStream stream = this.class.classLoader.getResourceAsStream('data/'+filename)
        if (!stream) {
            throw new IllegalArgumentException(sprintf('Cannot load example data. Cannot find file "%s"', filename))
        }
        return stream.getText('UTF-8')
    }

    private getMenuItemByPositionAndLevel(def position, def level)
    {
        return MenuItem.findByPositionAndLevel(position, level)
    }

    public def getDataForArticles(def defaultDomain, Page homepage, def pageTypes) {
        return [
                [
                        headline: 'Motýlci',
                        text: getTextFromFile('our_classes/motylci.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(2, 1).id
                        ]
                ],
                [
                        headline: 'Včelky',
                        text: getTextFromFile('our_classes/vcelky.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(2, 1).id
                        ]
                ],
                [
                        headline: 'Žablky',
                        text: getTextFromFile('our_classes/zabky.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(2, 1).id
                        ]
                ],
                [
                        headline: 'Ježci',
                        text: getTextFromFile('our_classes/jezci.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(2, 1).id
                        ]
                ],
                [
                        'headline': 'Provoz a školné',
                        text: getTextFromFile('about/provoz_a_skolne.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(3, 1).id
                        ]
                ],
                [
                        'headline': 'Program dne',
                        text: getTextFromFile('about/program_dne.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(3, 1).id
                        ]
                ],
                [
                        'headline': 'Prázdniny',
                        text: getTextFromFile('about/prazdniny.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(3, 1).id
                        ]
                ],
                [
                        'headline': 'Aktuality',
                        text: getTextFromFile('about/aktuality.txt'),
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/aktuality',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Aktuality',
                                putAfterId: null, //will be calculated
                                parentId: getMenuItemByPositionAndLevel(3, 1).id
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        text: getTextFromFile('events/akce_na_letosni_rok.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(4, 1).id
                        ]
                ],
                [
                        'headline': 'Den otevřených dvěří',
                        text: getTextFromFile('events/den_otevrenych_dveri.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(4, 1).id
                        ]
                ],
                [
                        'headline': 'Školka v přírodě',
                        text: getTextFromFile('events/skola_v_prirode.txt'),
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
                                parentId: getMenuItemByPositionAndLevel(4, 1).id
                        ]
                ],
        ]
    }

    def loadArticles(def defaultDomain, def homepage, def pageTypes) {
        def allArticles = []
        def allData = getDataForArticles(defaultDomain, homepage, pageTypes)
        def putAfters = [:]
        allData.each { data ->
            def index = allData.indexOf(data)
            if (!putAfters.containsKey(data.menuItem.parentId)) {
                putAfters[data.menuItem.parentId] = []
            } else {
                putAfters[data.menuItem.parentId].add(allArticles[index-1].menuItem.id)
                data.menuItem.putAfterId =  putAfters[data.menuItem.parentId].last()
            }
            allArticles << createFromData(data)
        }

        return allArticles
    }

    public Article createFromData(def data) {
        MenuItemCommand menuItemCommand = commandObjectService.create(MenuItemCommand, data.menuItem)
        PageCommand pageCommand = commandObjectService.create(PageCommand, data.page)
        ArticleCommand articleCommand = commandObjectService.create(ArticleCommand, data.findAll{ !['page', 'menuItem'].contains(it.key)})
        return articleService.create(articleCommand, menuItemCommand, pageCommand)
    }

}
