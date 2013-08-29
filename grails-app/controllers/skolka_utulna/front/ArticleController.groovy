package skolka_utulna.front

import skolka_utulna.ArticleService
import frod.routing.service.PageService
import frod.routing.domain.Page

class ArticleController {

    ArticleService articleService

    def main() {
        Page page = Page.get(params.pageId)

    }
}
