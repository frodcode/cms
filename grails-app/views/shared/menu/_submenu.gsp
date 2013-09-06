<ul>
    <g:each in="menuItems" var="item">
        <li class="${item == activeItem ? 'active' : ''}"><a href="">${item.title}</a></li>
    </g:each>
</ul>