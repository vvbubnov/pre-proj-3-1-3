$(document).ready(function () {

    fillOutTableRows();

    let modalWindow = $('#editUser');
    let modalSubmitButton = $('#modalSubmitButton');
    let rolesCheckbox = $('#rolesCheckbox');
    let addSubmitButton = $('#addSubmitButton');

    addSubmitButton.on('click', function () {
        let selectedRoles = [];
        $.each($("input[name='roleIdAdd']:checked"), function () {
            selectedRoles.push({
                id: $(this).val(),
                roleName: "noRole"
            })
        });

        let newUser = {
            login: document.getElementById('loginAdd').value,
            password: document.getElementById('passwordAdd').value,
            name: document.getElementById('nameAdd').value,
            fathersName:document.getElementById('fathersNameAdd').value,
            surname: document.getElementById('surnameAdd').value,
            carma: document.getElementById('carmaAdd').value,
            roles: selectedRoles
        }

        $.ajax({
            type: "POST",
            url: "/rest/admin/addUser",
            data: JSON.stringify(newUser),
            dataType: "json",
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (data) {
                fillOutTableRows();
            },
            error: function (data) {
                console.log(data);
            }
        });
    })

    modalSubmitButton.on('click', function () {
        let action = modalSubmitButton.text();
        let id = document.getElementById('idEdit').value;
        if (action === "Delete") {
            $.ajax({
                type: "DELETE",
                url: "/rest/admin/delete/" + id,
                success: function (data) {
                    modalWindow.modal('hide');
                    fillOutTableRows();
                },
                error: function (data) {
                    console.log(data);
                }
            });
        } else {
            let selectedRoles = [];
            $.each($("input[name='roleId']:checked"), function () {
                selectedRoles.push({
                    id: parseInt($(this).val()) + 1,    // костыльная инкрементация id-шки
                    roleName: "noRole"
                })
            });
            let editedUser = {
                id: id,
                login: document.getElementById('loginEdit').value,
                password: document.getElementById('passwordEdit').value,
                name: document.getElementById('nameEdit').value,
                fathersName: document.getElementById('fathersNameEdit').value,
                surname: document.getElementById('surnameEdit').value,
                carma: document.getElementById('carmaEdit').value,
                roles: selectedRoles
            }
            $.ajax({
                type: "PUT",
                url: "/rest/admin/edit/" + id,
                data: JSON.stringify(editedUser),
                dataType: "json",
                headers: {
                    'Content-Type': 'application/json'
                },
                success: function (data) {
                    modalWindow.modal('hide');
                    fillOutTableRows();
                },
                error: function (data) {
                    console.log(data);
                }
            });
        }
    })

    modalWindow.on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget);
        const idEdit = button.data('id');
        const del = button.data('del');
        let allInputs = document.getElementsByClassName("modalInput");

        if (del) {
            for (let i = 0; i < allInputs.length; i++) {
                allInputs[i].disabled = "disabled";
            }
            modalWindow.find('#idEdit').prop('disabled', false);
            modalWindow.find('#modal-title').text("Delete user");
            modalSubmitButton.removeClass("btn-primary");
            modalSubmitButton.addClass("btn-danger");
            modalSubmitButton.text("Delete");

        } else {
            for (let i = 0; i < allInputs.length; i++) {
                allInputs[i].removeAttribute('disabled');
            }
            modalWindow.find('#idEdit').prop('readOnly', true);
            modalSubmitButton.removeClass("btn-danger");
            modalSubmitButton.addClass("btn-primary");
            modalSubmitButton.text("Edit");
        }

        $.get('/rest/admin/user/' + idEdit, function (user) {
            $.get('/rest/admin/roles', function (roles) {
                modalWindow.find('#idEdit').val(user.id);
                modalWindow.find('#loginEdit').val(user.login);
                modalWindow.find('#surnameEdit').val(user.surname);
                modalWindow.find('#nameEdit').val(user.name);
                modalWindow.find('#fathersNameEdit').val(user.fathersName);
                modalWindow.find('#carmaEdit').val(user.carma);

                rolesCheckbox.empty();
                let userRoles = user.roles;
                console.log(userRoles);

                // TODO монструонзое заполнение checkbox-ов. подумать как можно по-лучше
                let rolesArr = [];
                for (let i = 0; i < roles.length; i++) {
                    let noSuchRole = true;
                    for (let j = 0; j < userRoles.length; j++) {
                        if (roles[i].id === userRoles[j].id) {
                            let role1 = {
                                id: i,
                                userHasRole: true
                            };
                            rolesArr.push(role1);
                            noSuchRole = false;
                        }
                    }
                    if (noSuchRole) {
                        let role2 = {
                            id: i,
                            userHasRole: false
                        };
                        rolesArr.push(role2);
                    }
                }

                for (let i = 0; i < rolesArr.length; i++) {
                    rolesCheckbox.append(
                        `<div class="form-check">
                            <input class="form-check-input modalInput" type="checkbox"
                                   value="${rolesArr[i].id}"
                                   name="roleId"
                                   id="${rolesArr[i].roleName}"
                                   ${rolesArr[i].userHasRole ? 'checked' : ''}>
                            <label class="form-check-label" for="${rolesArr[i].roleName}">
                                ${roles[i].roleName}
                            </label>
                        </div>`
                    )
                }
            })
        })
    })
})

function fillOutTableRows() {
    let tableRows = $('#tableRows');
    tableRows.empty();

    $.get('/rest/admin/users', function (users) {
        for (let i in users) {
            let userRoles = rolesAsString(users[i].roles);
            tableRows.append(
                `<tr>
                <td>${users[i].id}</td>
                <td>${users[i].login}</td>
                <td>${users[i].surname}</td>
                <td>${users[i].name}</td>
                <td>${users[i].fathersName}</td>
                <td>${users[i].carma}</td>
                <td>${userRoles}</td>
                <td><button class="btn btn-primary" data-target="#editUser" id="editBtn" data-toggle="modal" 
                data-id=${users[i].id} data-del="false" type="button">Edit</button></td>
                <td><button class="btn btn-danger " data-target="#editUser" id="deleteBtn" data-toggle="modal" 
                data-id=${users[i].id} data-del="true" type="button">Delete</button></td>
                </tr>`
            )
        }
    })

    function rolesAsString(roles) {
        let resultString = '';
        for (let j in roles) {
            resultString += roles[j].roleName + ' ';
        }
        return resultString;
    }
}

