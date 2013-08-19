package skolka_utulna.data

import skolka_utulna.ArticleService
import skolka_utulna.ArticleCommand
import frod.routing.domain.Page
import skolka_utulna.ArticleStatusEnum
import frod.routing.domain.UrlTypeEnum
import frod.routing.service.PageCommand
import org.springframework.beans.factory.config.AutowireCapableBeanFactory

/**
 * User: freeman
 * Date: 19.8.13
 */
class ExampleData {

    public static def load(def ctx, def defaultDomain, Page homepage, def pageTypes) {
        loadArticles(ctx, defaultDomain, homepage, pageTypes)
        return [pages: [], pageTypes: []]
    }

    private static def loadArticles(def ctx, def defaultDomain, Page homepage, def pageTypes) {
        def dataForMainPages = [
                [
                        headline: 'Motýlci',
                        text: "<p><strong>Mladší děti 3-4,5 leté</strong></p><p>Dochází sem děti, které si na pobyt v MŠ zvykají. Cílem je zdravý a radostný pobyt v MŠ, rozvoj sociálních a citových vztahů mezi dětmi, není preferováno speciální ani zájmové zaměření.</p>",
                        status: ArticleStatusEnum.PUBLISHED,
                        page: [
                                urlPart: '/motylci',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ]
                ],
                [
                        'headline': 'Provoz a školné',
                        'text' : '<h2>Provoz mateřské školy</h2>\n' +
                                '<p>\n' +
                                '\tProvoz školy je v pracovních dnech od 7.00 do 17.30 hod.<br />\n' +
                                '\tDěti přichází a odchází v průběhu dne tak, jak rodiče potřebují, po dohodě s učitelkou.<br />\n' +
                                '\tŠkolní vzdělávací program v letošním roce nazvaný\n' +
                                '</p>\n' +
                                '<h2>Školné a stravné</h2>\n' +
                                '<p><strong>Školné</strong> je 670,- Kč (měsíční záloha).<br>\n' +
                                '\tNeplatí se v posledním roce před ZŠ.</p>\n' +
                                '<p>\n' +
                                '\t<strong>Stravné</strong> je 700,- Kč (platí se záloha 700,- Kč měsíčně a 2x za rok dochází k vyúčtování <br>\n' +
                                '\tpodle skutečné docházky dítěte do školky. </p>\n' +
                                '\n' +
                                '\t<p>Podrobnosti k platbě naleznete <a href="kontakt.html" title="">zde</a>.</p>\n' +
                                '\t<p>Poplatek za celodenní stravu činí 35,- Kč denně / 28,- Kč polodenní\n' +
                                '\t(svačina 10 Kč, oběd 18 Kč, svačina 7 Kč).<br>\n' +
                                '\tpro děti s odloženou školní docházkou 38,- Kč denně / 31,- Kč polodenní\n' +
                                '\t(svačina 11 Kč, oběd 19 Kč, svačina 8 Kč).<br></p>',
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/provoz-a-skolne',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ]
                ],
                [
                        'headline': 'Školní akce na letošní rok',
                        'text' : '<table class="table table-striped">\n' +
                                '\t<tbody>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>20.3.</td>\n' +
                                '\t\t\t<td>9:20</td>\n' +
                                '\t\t\t<td>Kouzelník a zlobivé míčky</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>8.4.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Vodníček a rybníček </td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>11.4.</td>\n' +
                                '\t\t\t<td>8:00<br>7:30\n' +
                                '\t\t\t</td>\n' +
                                '\t\t\t<td>Fotografování dětí jednotlivci + celá třída<br>\n' +
                                '\t\t\t\tFotografování se sourozencem\n' +
                                '\t\t\t</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>22.4.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Pohádky naší vesnice</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>26.4.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Výlet Šestajovice Motýli + Včely, odjezd od MŠ v 9:30</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>29.4.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Výlet Šestajovice Ježci + Žáby, odjezd od MŠ v 9:30</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>16.5.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>OO námořníkovi Čepičkovi </td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>21.5.</td>\n' +
                                '\t\t\t<td>&nbsp;</td>\n' +
                                '\t\t\t<td>Sportovní dopoledne se sportovní a taneční přípravou na školní zahradě v dopoledních hodinách</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>30.5.</td>\n' +
                                '\t\t\t<td>15:30</td>\n' +
                                '\t\t\t<td>Slavnost na školní zahradě ke Dni dětí</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>30.5.-6.6.</td>\n' +
                                '\t\t\t<td>&nbsp;</td>\n' +
                                '\t\t\t<td>ŠvP</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>10.6.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Sokolník</td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>17.6.</td>\n' +
                                '\t\t\t<td>9:30</td>\n' +
                                '\t\t\t<td>Káťa a Škubánek jedou na prázdniny </td>\n' +
                                '\t\t</tr>\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>25.6.</td>\n' +
                                '\t\t\t<td>15:30</td>\n' +
                                '\t\t\t<td>Rozloučení s předškoláky<br>\n' +
                                '\t\t\t\tPasování na školáky "ve stylu pirátů"\n' +
                                '\t\t\t</td>\n' +
                                '\t\t</tr>\n' +
                                '\n' +
                                '\n' +
                                '\t\t<tr>\n' +
                                '\t\t\t<td>&nbsp;</td>\n' +
                                '\t\t\t<td>&nbsp;</td>\n' +
                                '\t\t\t<td>&nbsp;</td>\n' +
                                '\t\t</tr>\n' +
                                '\t</tbody></table>',
                        status: ArticleStatusEnum.PUBLISHED,
                        'page': [
                                urlPart: '/skolni-akce-na-letosni-rok',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: pageTypes.articlePageType.id,
                                parentId: homepage.id,
                                domainId: defaultDomain.id,
                        ]
                ],
        ]


        ArticleService articleService = ctx.articleService
        def allArticles = [:]
        def acbf = ctx.autowireCapableBeanFactory

        dataForMainPages.each { articleData ->
            def articleCommand = new ArticleCommand(articleData.findAll{it.key != 'page'})
            def pageCommand = new PageCommand(articleData.page)
            acbf.autowireBeanProperties pageCommand, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false
            allArticles.put(
                    articleData.page.urlPart,
                    articleService.create(articleCommand, pageCommand)
            )
        }
        return [articles: allArticles]
    }

}
