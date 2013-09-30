<g:if test="${flash.message}">
<div class="alert alert-success">
    <button class="close" data-dismiss="alert">×</button>
    <strong>${flash.message}</strong>
</div>
</g:if>
<g:if test="${flash.errors}">
    <g:each in="${flash.errors}" var="error">
    <div class="alert alert-error">
        <button class="close" data-dismiss="alert">×</button>
        <strong>${error}</strong>
    </div>
    </g:each>

</g:if>