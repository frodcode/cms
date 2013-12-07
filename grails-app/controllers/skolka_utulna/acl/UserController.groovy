package skolka_utulna.acl

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    def springSecurityService

    def messageSource

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static namespace = 'superadmin'


    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count(), websiteSlug: params.websiteSlug]
    }

    def create() {
        [userInstance: new User(params), websiteSlug: params.websiteSlug]
    }

    private def addUserRoles(User user)
    {
        if (params.roles) {
            if (user?.id) {
                UserRole.removeAll(user)
            }
            params.roles.toString().tokenize(',').each { roleName ->
                def role = Role.findByAuthority(roleName.trim())
                if (role) {
                    UserRole.create(user, role, true)
                }
            }
        }
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
        addUserRoles(userInstance)

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id, params: [websiteSlug: params.websiteSlug])
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list", params: [websiteSlug: params.websiteSlug])
            return
        }

        [userInstance: userInstance, websiteSlug: params.websiteSlug]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        addUserRoles(userInstance)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list", params: [websiteSlug: params.websiteSlug])
            return
        }

        [userInstance: userInstance, websiteSlug: params.websiteSlug]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list", params: [websiteSlug: params.websiteSlug])
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }
        addUserRoles(userInstance)
        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id, params: [websiteSlug: params.websiteSlug])
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list", params: [websiteSlug: params.websiteSlug])
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list", params: [websiteSlug: params.websiteSlug])
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id, params: [websiteSlug: params.websiteSlug])
        }
    }

    def myAccount(UserCommand cmd) {
        def userInstance = User.get(springSecurityService.principal.id)

        if (request.post) {
            cmd.currentUser = userInstance
            if (!cmd.validate()) {
                flash.errors = cmd.getErrors().allErrors.collect{messageSource.getMessage(it,null)}
                redirect(mapping: 'myAccount', params: [websiteSlug: params.websiteSlug])
                return;
            }
            cmd.currentUser.setPassword(cmd.password)
            cmd.currentUser.save(flush:true)
            flash.message = "Heslo změněno"
            redirect(mapping: 'myAccount', params: [websiteSlug: params.websiteSlug])
        }
        [userInstance: userInstance, websiteSlug: params.websiteSlug]
    }
}
