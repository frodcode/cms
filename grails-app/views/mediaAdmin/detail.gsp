<%@ page import="org.imgscalr.Scalr; frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:set var="title" value="Galerie ${mediaGroup.name}"/>
    <title>${title}</title>
    <r:require modules="imgupload"/>
</head>

<body>
<div id="content">
    <div id="content-header">
        <h1>${title}</h1>
    </div>


    <div class="container-fluid">
        <div class="span12">
            <skolka:messages/>
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-database"></i>
                    </span>
                    <h5>Přejmenovat</h5>
                </div>
                <div class="widget-content nopadding">
                    <g:form action="rename" useToken="true" params="[websiteSlug: website.slug, id: mediaGroup.id]"  class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">Název galerie</label>
                            <div class="controls">
                                <g:textField name="name" value="${mediaGroup.name}"/>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Přejmenovat galerii</button>
                        </div>
                    </g:form>
                </div>
            </div>
            <div class="widget-box">
            <div class="widget-content nopadding">
           <form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" class="form-horizontal" enctype="multipart/form-data" data-ng-controller="DemoFileUploadController" data-file-upload="options" data-ng-class="{'fileupload-processing': processing() || loadingFiles}">
            <div class="control-group">
                <label class="control-label">Select input</label>


            <!-- The file upload form used as target for the file upload widget -->

                <noscript>Pro nahrávání obrázků musíte mít zaplý javascript.</noscript>
                <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                <div class="row fileupload-buttonbar controls">
                    <div class="col-lg-7">
                        <!-- The fileinput-button span is used to style the file input field as button -->
                        <span class="btn " ng-class="{disabled: disabled}">
                            <i class="glyphicon glyphicon-plus"></i>
                            <input type="file" name="files[]" multiple ng-disabled="disabled">
                        </span>
                        <button type="button" class="btn btn-primary start" data-ng-click="submit()">
                            <i class="glyphicon glyphicon-upload"></i>
                            <span>Nahrát vše</span>
                        </button>
                        <button type="button" class="btn btn-warning cancel" data-ng-click="cancel()">
                            <i class="glyphicon glyphicon-ban-circle"></i>
                            <span>Zrušit vše</span>
                        </button>
                        <!-- The loading indicator is shown during file processing -->
                        <div class="fileupload-loading"></div>
                    </div>
                    <!-- The global progress information -->
                    <div class="col-lg-5 fade" data-ng-class="{in: active()}">
                        <!-- The global progress bar -->
                        <div class="progress progress-striped active" data-file-upload-progress="progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                        <!-- The extended global progress information -->
                        <div class="progress-extended">&nbsp;</div>
                    </div>
                </div>
            </div>
                <!-- The table listing the files available for upload/download -->
                <table class="table table-striped files ng-cloak">
                    <tr data-ng-repeat="file in queue">
                        <td data-ng-switch data-on="!!file.thumbnailUrl">
                            <div class="preview" data-ng-switch-when="true">
                                <a data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery><img data-ng-src="{{file.thumbnailUrl}}" alt=""></a>
                            </div>
                            <div class="preview" data-ng-switch-default data-file-upload-preview="file"></div>
                        </td>
                        <td>
                            <p class="name" data-ng-switch data-on="!!file.url">
                                <span data-ng-switch-when="true" data-ng-switch data-on="!!file.thumbnailUrl">
                                    <a data-ng-switch-when="true" data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery>{{file.name}}</a>
                                    <a data-ng-switch-default data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}">{{file.name}}</a>
                                </span>
                                <span data-ng-switch-default>{{file.name}}</span>
                            </p>
                            <div data-ng-show="file.error"><span class="label label-danger">Chyba</span> {{file.error}}</div>
                        </td>
                        <td>
                            <p class="size">{{file.size | formatFileSize}}</p>
                            <div class="progress progress-striped active fade" data-ng-class="{pending: 'in'}[file.$state()]" data-file-upload-progress="file.$progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                        </td>
                        <td>
                            <button type="button" class="btn btn-warning cancel" data-ng-click="file.$cancel()" data-ng-hide="!file.$cancel">
                                <i class="glyphicon glyphicon-ban-circle"></i>
                                <span>Zrušit</span>
                            </button>
                            <button data-ng-controller="FileDestroyController" type="button" class="btn btn-danger destroy" data-ng-click="file.$destroy()" data-ng-hide="!file.$destroy">
                                <i class="glyphicon glyphicon-trash"></i>
                                <span>Odstranit ze seznamu</span>
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-picture"></i>
                    </span>
                    <h5>${title}</h5>
                </div>

                <div class="widget-content">
                    <ul class="thumbnails">
                        <g:each in="${allMedia}" var="media">
                            <li class="span2">
                                <a href="gallery.html#" class="thumbnail">
                                    <frodm:img media="${media}"
                                               adjustments="${[new ResizeAdjustment(140, 140, Scalr.Mode.AUTOMATIC)]}"/>
                                </a>

                                <div class="actions">
                                    <g:link action="deleteMedia" onclick="return(confirm('Opravdu smazat?'))" params="['websiteSlug': website.slug, id: media.id]"><i class="icon-remove icon-white"></i> </g:link>
                                </div>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){

        $('input[type=checkbox],input[type=radio],input[type=file]').uniform({
            fileDefaultText: 'Nebyl vybrán žádný soubor',
            fileBtnText: 'Vybrat'

        });
        $('.datepicker').datepicker();
    });
    app.invoke(function (appModuleFactory, uploadModuleFactory) {
        var uploadModule = uploadModuleFactory('${createLink(action: 'upload', params: ['websiteSlug': website.slug, id: mediaGroup.id])}', '${createLink(action: 'detail', params: ['websiteSlug': website.slug, id: mediaGroup.id])}');
        appModuleFactory.add(uploadModule);
    });
</script>
</body>
</html>