package skolka_utulna.front

import frod.media.domain.MediaGroup
import frod.media.domain.Media
import skolka_utulna.Website
import skolka_utulna.WebsiteService
import frod.routing.domain.Page
import org.springframework.web.servlet.ModelAndView

class GalleryController {

    WebsiteService websiteService

    def index() {
        dump('spoustim index')
        if (params.galerie) {
            Page page = Page.get(params.pageId)
            def group =  MediaGroup.get(params.galerie)
            if (!group) {
                response.status = 404;
                return;
            }
            def website = websiteService.getWebsite(page)
            return new ModelAndView("/gallery/groupDetail",
                    [media: Media.findAllByMediaGroup(group), website: website, group: group]
            )
        } else {
            forward(action: 'groups')
        }
    }

    def groups() {
        Page page = Page.get(params.pageId)
        def website = websiteService.getWebsite(page)

//            Website.find("FROM Website w JOIN FETCH w.mediaGroups mg JOIN FETCH mg.type mgt WHERE mgt.name = :mediaGroupType", [mediaGroupType: 'Galerie'])
        def allGroups = website.mediaGroups.findAll{
            it.type.name == 'Galerie'
        }
        def thumbnails = [:]
        allGroups.each {group->
            def firstMedia = Media.findByMediaGroup(group, [sort: 'id', order: 'asc'])
            thumbnails[group.id] = firstMedia
        }
        thumbnails.containsKey()

        [allGroups: allGroups, thumbnails: thumbnails, website: website]
    }
}
