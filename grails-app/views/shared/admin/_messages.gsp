<g:if test="${message}">
<div class="alert alert-success">
    <button class="close" data-dismiss="alert">×</button>
    <strong>${message}</strong>
</div>
</g:if>
<g:if test="${errors}">
    <g:each in="${errors}" var="error">
    <div class="alert alert-error">
        <button class="close" data-dismiss="alert">×</button>
        <strong>${error}</strong>
    </div>
    </g:each>

</g:if>