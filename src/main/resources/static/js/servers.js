(function () {
    $.get({
        url: "/server/list",
        success: function (response) {
            if (!response.length) {
                $("#servers").text("Nie znaleziono żadnego serwera");
                return;
            }
            for (var key in response)
            {
                var server = response[key];
                var $server = $("<div>");
                $server.attr("id", "server");
                $server.append($("<div>").text(server.id));
                $server.append($("<div>").text(server.name));
                var $editButton = $("<a>").attr("href", "/edit/" + server.id).text("Edytuj serwer");
                $server.append($("<div>").append($editButton));
                $("#servers").append($server);
            }
        }
    })
})();