initNamespace('cms.article');

cms.article.articleModuleFactoryFactory = function (saveButtonModule, pageModule) {
    return function (articleData, saveArticleLink) {
        return angular.module('cms.article', [
            saveButtonModule.name, pageModule.name
        ])
            // config
            .value('articleData', articleData)
            .value('saveArticleLink', saveArticleLink)

            .controller('EditPageCtrl', cms.article.editPage.EditPageCtrl)
            .directive('articleEditPage', cms.article.editPage.EditPageDirective)
            ;
    };


};