<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 23.10.13
  Time: 8:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Galerie</title>
</head>

<body>
<div id="content">
    <div id="content-header">
        <h1>Galerie</h1>

    </div>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="span12">

                <skolka:messages/>
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-plus"></i>
                        </span>
                        <h5>Vytvořit novou galerii</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <g:form action="createNew" useToken="true" params="[websiteSlug: website.slug]"  class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Název galerie</label>
                                <div class="controls">
                                    <g:textField name="name" placeholder="Zde vyplňte název galerie"/>
                                </div>
                            </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Vytvořit galerii</button>
                        </div>
                    </g:form>
                    </div>
            </div>

                <div class="widget-box">
                        <div class="widget-content nopadding">
                            <table class="table table-bordered table-striped">

                                <tbody class="sortable">
                                <g:each in="${allGroups}" var="group">
                                    <tr>
                                        <td><g:link action="detail"
                                                    params="[websiteSlug: website.slug, id: group.id]">${group.name}</g:link></td>
                                        <td>
                                            <g:form action="delete" useToken="true" params="[websiteSlug: website.slug]"
                                                    onclick="return(confirm('Opravdu smazat?'))">
                                                <g:hiddenField name="id" value="${group.id}"/>
                                                <g:submitButton name="delete" class="btn btn-danger" value="smazat"/>
                                            </g:form>
                                        </td>

                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>



            </div>
        </div>
    </div>
</div>
</body>
</html>