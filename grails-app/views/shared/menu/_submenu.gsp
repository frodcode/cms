<ul>
    <g:each in="${menuItems}" var="item">
        <li class="${item == activeItem ? 'active' : ''}"><frodr:link page="${item.page}">${item.title}</frodr:link></li>
    </g:each>
</ul>