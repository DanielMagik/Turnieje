function submit(myVar)
{
    //funkcja ta w GET wysyla JSONa zawierajacego nazwe druzyny, tablice jej czlonkow,
    //oraz tablice wybranych dyscyplin.
    //sekcja nazwy druzyny
    if(document.getElementById("teamName").value=="")
    {
        alert("Wprowadz nazwe druzyny!");
        return;
    }
    var JSONToSend = "{\"name\":\"" + document.getElementById("teamName").value + "\",";

    //sekcja uzytkownikow do dodania
    JSONToSend = JSONToSend + " \"usersToAdd\": [";

    var iframe = document.getElementById("ChoosedUsers");   //dobieram sie do iframe
    var select = iframe.contentWindow.document.getElementById("choosedUsers");
    var options = select.getElementsByTagName('option');    //pobieram opcje z listy
    var i;
    console.log(JSONToSend);
    var JSONArrayOfUsersToAdd = "";
    for (i = 0; i < options.length; i++)
    {
        JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd + "\"" + options[i].text + "\", ";
    }
    //usuwanie ostatniego ", " z uzytkownikow do dodania
    JSONArrayOfUsersToAdd = JSONArrayOfUsersToAdd.substring(0, JSONArrayOfUsersToAdd.length - 2);
    console.log(JSONArrayOfUsersToAdd);
    //dodanie do JSONa znaku konca tablicy
    JSONToSend = JSONToSend + JSONArrayOfUsersToAdd + "]";

    //sekcja odpowiedzialna za dziedziny
    iframe = document.getElementById("ChoosedDisciplines");   //dobieram sie do iframe
    select = iframe.contentWindow.document.getElementById("choosedDisciplines");
    options = select.getElementsByTagName('option');    //pobieram opcje z listy

    JSONToSend = JSONToSend + " , \"disciplinesToAdd\": [";

    var JSONArrayOfDisciplinesToAdd = "";
    for (i = 0; i < options.length; i++)
    {
        JSONArrayOfDisciplinesToAdd = JSONArrayOfDisciplinesToAdd + "\"" + options[i].text + "\", ";
    }
    //usuwanie ostatniego ", " z uzytkownikow do dodania
    JSONArrayOfDisciplinesToAdd = JSONArrayOfDisciplinesToAdd.substring(0, JSONArrayOfDisciplinesToAdd.length - 2);
    console.log(JSONArrayOfDisciplinesToAdd);
    //dodanie do JSONa znaku konca tablicy i konca JSONa na ten moment
    JSONToSend = JSONToSend + JSONArrayOfDisciplinesToAdd + "] }";

    console.log(JSONToSend);
    location.replace("/Turnieje/"+myVar+"Team?JSONFromCreateTeam=" + JSONToSend);
}