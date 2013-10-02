package skolka_utulna.acl

/**
 * User: freeman
 * Date: 2.10.13
 */
@grails.validation.Validateable
class UserCommand {

    transient springSecurityService

    User currentUser

    String original_password

    String password

    String password2

    static constraints = {
        currentUser(nullable:false, blank: false)
        original_password(nullable: false, blank: false, validator: { val, obj ->
            if (obj.currentUser) {
                if (obj.currentUser.getPassword() != obj.springSecurityService.encodePassword(val)) {
                    return ['skolka_utulna.acl.UserCommand.original_password.wrong']
                }
            }
        })
        password2(nullable:false, blank: false)
        password (validator: { val, obj ->
            if (val != obj.password2) {
                return ['skolka_utulna.acl.UserCommand.password.notmatch.message']
            }
        }, blank: false)
    }


}
