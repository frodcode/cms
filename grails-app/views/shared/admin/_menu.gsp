<a href="index.html#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
<ul>
    <g:each in="${items}" var="item">
        <li class="${item.isCurrent(params) ? 'active' : ''}">
            <g:link action="${item.action}" controller="${item.controller}" params="${item.getAllParams()}">
                <g:if test="${item.ico}">
                    <i class="icon ${item.ico}"></i>
                </g:if>
                ${item.name}</g:link>
    </g:each>
</ul>