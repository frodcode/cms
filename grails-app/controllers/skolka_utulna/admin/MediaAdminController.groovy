package skolka_utulna.admin

import frod.media.domain.Media
import frod.media.domain.MediaGroup
import frod.media.domain.MediaGroupType
import skolka_utulna.Website
import frod.media.image.OriginalImageRepository

class MediaAdminController {

    public def static galleryType = 'Galerie'

    static layout = "admin"

    OriginalImageRepository originalImageRepository

    def index() {
        Website website = getWebsite()
        def allGroups = website.mediaGroups.findAll{it.type.name == galleryType}

        [website: website, allGroups: allGroups]
    }

    def detail() {
        Website website = getWebsite()
        MediaGroup mediaGroup = MediaGroup.get(params.id)
        def allMedia = getMediaInGroup(params.id)

        [mediaGroup: mediaGroup, allMedia: allMedia, website: website]
    }

    private def getWebsite() {
        return Website.findBySlug(params.websiteSlug)
    }

    private def getMediaInGroup(def groupId)
    {
        return Media.findAllByMediaGroup(MediaGroup.get(groupId))
    }

    def createNew() {
        Website website = getWebsite()
        if (!params.name) {
            flash.errors = ['Vyplňte prosím název']
            redirect(action: 'index', websiteSlug: website.slug)
        }
        def type = MediaGroupType.findByName(galleryType)
        MediaGroup mediaGroup = new MediaGroup()
        website.addToMediaGroups(mediaGroup)
        mediaGroup.name = params.name
        mediaGroup.type = type;
        mediaGroup.save(flush:true)
        website.save(flush: true)
        flash.message = 'Galerie vytvořena.'
        redirect(action: 'detail', params: [websiteSlug: website.slug, id: mediaGroup.id])
    }

    def delete() {
        Website website = getWebsite()
        MediaGroup group = MediaGroup.get(params.id)
        def allMedia = getMediaInGroup(params.id)
        allMedia.each { media ->
            originalImageRepository.remove(media.mediaImages.first())
            media.delete(flush:true)
        }
        website.removeFromMediaGroups(group)
        website.save(flush:true)
        group.delete(flush:true)
        flash.message = 'Galeria byla smazána'
        redirect(action: 'index', params: [websiteSlug: website.slug])
    }

}
