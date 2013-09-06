<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav">
                <g:each in="menuItems" var="item">
                    <li class="${item == activeItem ? 'active' : ''}"><a href="">${item.title}</a></li>
                </g:each>
            </ul>
        </div>
    </div>
</div>