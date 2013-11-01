package skolka_utulna.admin

import frod.media.domain.Media
import frod.media.domain.MediaGroup
import frod.media.domain.MediaGroupType
import skolka_utulna.Website
import frod.media.image.OriginalImageRepository
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import frod.media.model.MediaFacade
import java.sql.SQLException

class MediaAdminController {

    public def static galleryType = 'Galerie'

    static layout = "admin"

    MediaFacade mediaFacade

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

    private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                multipart.getOriginalFilename());
        multipart.transferTo(tmpFile);
        return tmpFile;
    }

    private def getMediaInGroup(def groupId)
    {
        return Media.findAllByMediaGroup(MediaGroup.get(groupId))
    }

    def deleteMedia() {
        Website website = getWebsite()
        Media media = Media.get(params.id)
        def groupId = media.mediaGroup.id
        originalImageRepository.remove(media.mediaImages.first())
        media.delete(flush:true)

        flash.message = 'Obrázek byl smazán'
        redirect(action: 'detail', params: [websiteSlug: website.slug, id: groupId])
    }

    def upload() {
        def results = []
        def mediaGroup = MediaGroup.get(params.id)
        if (request instanceof MultipartHttpServletRequest){
            for(filename in request.getFileNames()){
                MultipartFile multipartFile = request.getFile(filename)
                File file = multipartToFile(multipartFile)
                try {
                mediaFacade.addMediaFromFile(file, mediaGroup.id)
                }catch (SQLException e) {
                    println(e);
                    Exception exception = e;
                    while(exception.getNextException() != null) {
                        println(exception.getNextException()); //whatever you want to print out of exception
                        exception = exception.getNextException();
                    }
                    throw e
                }
                if (file.exists()) {
                    file.delete()
                }
                results << [
                        name: multipartFile.originalFilename,
                        size: multipartFile.size,
                        status:  'uploaded'
                ]
            }
            render(contentType: "text/json") {
                [files: results]
            }
        }
        render(contentType: "text/json") {
            [status: 'ok']
        }
    }

    def createNew() {
        Website website = getWebsite()
        if (!params.name) {
            flash.errors = ['Vyplňte prosím název']
            redirect(action: 'index', params: [websiteSlug: website.slug])
            return
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
