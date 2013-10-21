<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 4.10.13
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Články</title>
</head>

<body>
<div id="content">
    <div id="content-header">
        <h1>Články</h1>

    </div>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="span12">

                <skolka:messages/>

                <div class="alert alert-info">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Přetažením článků můžete měnit jejich pořadí.</strong>
                </div>

                <div class="alert alert-success" id="sort-message" style="display: none">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Pořadí uloženo</strong>
                </div>
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-th"></i>
                        </span>
                        <h5><g:link action="edit" params="[websiteSlug: website.slug, id: website.homepage.id]">Úvod</g:link></h5>
                    </div>
                </div>

                <g:each in="${allMainMenus}" var="mainMenu">
                    <div class="widget-box">
                        <div class="widget-title">
                            <span class="icon">
                                <g:link mapping="classic_admin" action="newArticle" params="[websiteSlug: website.slug, id: mainMenu.page.id]"><i class="icon-plus"></i> Přidat článek</g:link>
                            </span>
                            <h5><g:link action="edit" params="[websiteSlug: website.slug, id: mainMenu.page.id]">${mainMenu.title}</g:link> </h5>
                        </div>

                        <div class="widget-content nopadding" parent="${mainMenu.id}">
                            <table class="table table-bordered table-striped">

                                <tbody class="sortable">
                                <g:each in="${mainMenu.menuItems}" var="menuItem">
                                    <tr item-id="${menuItem.id}">
                                        <g:if test="${menuItem.page.pageType.slug == 'news'}">
                                            <td><g:link controller="news-admin" action="index" params="[websiteSlug: website.slug]">${menuItem.title}</g:link></td>
                                        </g:if>
                                        <g:else>
                                            <td><g:link action="edit" params="[websiteSlug: website.slug, id: menuItem.page.id]">${menuItem.title}</g:link></td>
                                        </g:else>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </g:each>
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-th"></i>
                        </span>
                        <h5><g:link action="edit" params="[websiteSlug: website.slug, id: website.homepage.subPages.find{it.pageType.slug == 'contact'}?.id]">Kontakt (mimo adresu a email)</g:link></h5>
                    </div>
                </div>



            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getSortedData($parentContainer) {
        var data = []
        $parentContainer.find("tr").each(function(index, item){
            data.push({
                'id': parseInt($(item).attr("item-id")),
                'position': index + 1
            })
        });
        return data
    }
    $(".sortable").sortable({
        stop: function( event, ui ) {
            var $parentContainer = $($(ui.item).parents("div[parent]")[0])
            var data = getSortedData($parentContainer)
            console.log(data)
            $.ajax({
                type: "POST",
                url: "${createLink(action: 'saveSort', params: [websiteSlug: website.slug])}",
                dataType: 'json',
                data: {data: JSON.stringify(data)}
            })
            .success(function( msg ) {
                 $("#sort-message").show()
            });
        },
        start: function() {
            $("#sort-message").hide()
        }
    });
</script>
</body>
</html>