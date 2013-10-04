<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav">
                <g:each in="${menuItems}" var="item">
                    <g:set var="itemPage" value="${item.page}"/>

                    <li class="${item.id == activeItem.id ? 'active' : ''}"><frodr:link page="${itemPage}">${item.title}</frodr:link></li>
                </g:each>
            </ul>
        </div>
    </div>
</div>