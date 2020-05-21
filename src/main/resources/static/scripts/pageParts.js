$(document).ready(function () {
    showTopInfoPanel();
    showSidePanel();
    showUserInfo();
    showAddFormRolesCheckbox()
})

function showAddFormRolesCheckbox() {
    let addFormRoleCheckbox = $('#addFormRolesCheckBox');
    $.get('/rest/admin/roles', function (roles) {
        for (let i = 0; i < roles.length; i++) {
            addFormRoleCheckbox.append(
                `<div class="form-check" id="addFormRolesCheckBox">
                    <input class="form-check-input" type="checkbox" 
                            id="defaultCheck1" 
                            value="${roles[i].id}" 
                            name="roleIdAdd" 
                            id="${roles[i].roleName + 'add'}">
                    <label class="form-check-label" for="defaultCheck1">
                        ${roles[i].roleName}
                    </label>
                </div>`
            )
        }
    })
}

function showUserInfo() {
    let userInfo = $('#userInfo');
    $.get('/rest/getPrincipal', function (loggedUser) {
        let userRolesStr = rolesAsString(loggedUser.roles);
        userInfo.append(
            `<tr>
                <th>${loggedUser.id}</th>
                <th>${loggedUser.login}</th>
                <th>${loggedUser.surname}</th>
                <th>${loggedUser.name}</th>
                <th>${loggedUser.fathersName}</th>
                <th>${loggedUser.carma}</th>
                <th>${userRolesStr}</th>
            </tr>`
        )
    })
}

function showTopInfoPanel() {
    let topInfoPanel = $('#topInfoPanel')
    $.get('/rest/getPrincipal', function (loggedUser) {
        let userRolesStr = rolesAsString(loggedUser.roles);
        topInfoPanel.append(
            `<div class="col-11">
                <div class="text-white">
                    <b><span>${loggedUser.login} </span></b>
                    <span>with roles: ${userRolesStr}</span>
                </div>
            </div>
            <div class="col-1 text-right">
                <p class="text-right"><a href="/logout" style="color: grey; font-size: small">Logout</a></p>
            </div>`
        )
    })
}

function rolesAsString(roles) {
    let resultString = '';
    for (let j in roles) {
        resultString += roles[j].roleName + ' ';
    }
    return resultString;
}

function showSidePanel() {
    let sideNavPanel = $('#sideNavPanel');
    let pageTitle = $('#pageTitle').text();
    $.get('/rest/isAdmin', function (isAdmin) {
        if (isAdmin) {
            if (pageTitle === "Admin panel") {
                sideNavPanel.append(
                    `<button class="btn btn-white text-left" type="button">
                    <a class="nav-link" href="/user">
                        User
                    </a>
                </button>`);
                sideNavPanel.append(
                    `<button class="btn btn-primary" type="button">
                    <a class="nav-link bg-primary text-white text-left" href="/admin">
                        Admin
                    </a>
                </button>`);
            } else if (pageTitle === "User information page") {
                sideNavPanel.append(
                    `<button class="btn btn-primary" type="button">
                    <a class="nav-link bg-primary text-white text-left" href="/user">
                        User
                    </a>
                </button>`);
                sideNavPanel.append(
                    `<button class="btn btn-white text-left" type="button">
                    <a class="nav-link" href="/admin">
                        Admin
                    </a>
                </button>`);
            }

        } else {
            sideNavPanel.append(
                `<button class="btn btn-primary " type="button">
                    <a class="nav-link bg-primary text-white text-left" href="/user">
                        User
                    </a>
                </button>`);
        }
    })
}
