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
                                <i class="icon-th"></i>
                            </span>
                            <h5><g:link action="edit" params="[websiteSlug: website.slug, id: mainMenu.page.id]">${mainMenu.title}</g:link></h5>
                        </div>

                        <div class="widget-content nopadding">
                            <table class="table table-bordered table-striped">

                                <tbody>
                                <g:each in="${mainMenu.menuItems}" var="menuItem">
                                    <tr>
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


            </div>
        </div>
    </div>
</div>
</body>
</html>