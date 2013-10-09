package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.MainMenuItem
import skolka_utulna.Website
import org.springframework.web.servlet.ModelAndView

class ContactController {

    def index() {
        Page page = Page.get(params.pageId)
        MainMenuItem mainMenuItem = MainMenuItem.findByPage(page)
        Website website = mainMenuItem.website
        return new ModelAndView("/$website.slug/contact", [website: website])
    }
}
