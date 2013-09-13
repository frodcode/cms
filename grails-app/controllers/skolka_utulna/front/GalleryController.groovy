package skolka_utulna.front

import frod.media.domain.MediaGroup
import frod.media.domain.Media

class GalleryController {

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
        List<MediaGroup> allGroups = MediaGroup.where {
            type.name == 'Galerie'
        }.findAll()

        def thumbnails = [:]
        allGroups.each {group->
            def firstMedia = Media.findByMediaGroup(group, [sort: 'id', order: 'asc'])
            thumbnails[group.id] = firstMedia
        }
        thumbnails.containsKey()

        [allGroups: allGroups, thumbnails: thumbnails]
    }
}
