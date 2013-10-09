package skolka_utulna.front

import frod.media.domain.MediaGroup
import frod.media.domain.Media
import skolka_utulna.Website
import skolka_utulna.WebsiteService
import frod.routing.domain.Page

class GalleryController {

    WebsiteService websiteService

    def index() {
        if (params.galerie) {
            forward(action: 'groupDetail')
        } else {
            forward(action: 'groups')
        }
    }

    def groupDetail() {
        def group =  MediaGroup.findByName(params.galerie)
        if (!group) {
            response.status = 404;
            return;
        }
        [media: Media.findAllByMediaGroup(group)]
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
