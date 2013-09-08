package skolka_utulna.front

import frod.media.domain.MediaGroup
import frod.media.domain.Media

class GalleryController {

    def index() {
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
