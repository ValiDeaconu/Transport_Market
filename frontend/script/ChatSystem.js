
function _error_func(status, responseText) {
    console.log("HttpStatus: " + status);
    console.log("Server response: " + responseText);
}

const urlParams = new URLSearchParams(window.location.search);
var talkingTo = new User();
talkingTo.id = urlParams.get("userId");
var authUser = AuthManager.getAuthentificatedUser();
var _cs_messageCache = [];


if (authUser.id == talkingTo.id) {
    throw new Error("Users cannot commuicate with themselves.");
}

const _cs_requestOtherList = new ServerRequest(function(responseText) {
    if (Object.keys(responseText).length == 0) {
        console.log("No list found for " + authUser.id);
    } else {
        _cs_renderList(JSON.parse(responseText));
    }
}, _error_func);
_cs_requestOtherList.send(RequestMethod.GET, "/api/v1/message/list/" + authUser.id);

function _cs_renderSentMessage(thisUser, message) {
    return '<div class="chat-message submitted" title="' + message.date + '">' +
                '<a href="userProfile.php?userId=' + thisUser.id + '" class="author">' +
                    '<img src="' + thisUser.profilePictureLink + '" width="50px" height="50px"/>' +
                    '<span class="name">' + thisUser.username + '</span>' +
                '</a>' +
                '<span class="message">' +
                    message.message +
                '</span>' +
            '</div>';
}

function _cs_renderReceivedMessage(otherUser, message) {
    return '<div class="chat-message received" title="' + message.date + '">' +
                '<a href="userProfile.php?userId=' + otherUser.id + '" class="author">' +
                    '<img src="' + otherUser.profilePictureLink + '" width="50px" height="50px"/>' +
                    '<span class="name">' + otherUser.username + '</span>' +
                '</a>' +
                '<span class="message">' +
                    message.message +
                '</span>' +
            '</div>';
}

function _cs_clearChat() {
    var block = document.getElementById("messages-block");
    block.innerHTML = "";
}

function _cs_renderChat(thisUser, otherUser, listOfMessages) {
    var block = document.getElementById("messages-block");

    var foundMessage = false;
    for (var i = 0; i < _cs_messageCache.length && !foundMessage; ++i) {
        for (var j = 0; j < listOfMessages.length && !foundMessage; ++j) {
            if (_cs_messageCache[i].message == listOfMessages[j].message) {
                foundMessage = true;
            }
        }
    }

    if (foundMessage) {
        _cs_messageCache = [];
        console.log("Message cache cleared!");
    } else {
        _cs_messageCache.forEach(message => listOfMessages.push(message));
        console.log("Message cache used!");
    }

    for (var i = 0; i < listOfMessages.length; ++i) {
        var message = listOfMessages[i];

        if (message.senderId == thisUser.id) { 
            block.innerHTML += _cs_renderSentMessage(thisUser, message);
        } else {
            block.innerHTML += _cs_renderReceivedMessage(otherUser, message);
        }
    }
}

function _cs_renderList(partners) {
    var block = document.getElementById("chat-list");
    var warning = document.getElementById("warning-stranger");
    
    block.innerHTML = "";

    var selectedFound = false;
    for (var i = 0; i < partners.length; ++i) {
        var partner = partners[i];

        var classes = "author";
        if (partner.id == talkingTo.id) {
            classes = "author selected";
            selectedFound = true;
        }

        block.innerHTML += '<a href="chat.php?userId=' + partner.id + '" class="' + classes + '">' +
                                '<img src="' + partner.profilePictureLink + '" width="50px" height="50px"/>' +
                                '<span class="name">' + partner.username + '</span>' +
                            '</a>';
    }

    if (!selectedFound)
        warning.style = "display: block;";
}

function sendMessage() {
    var message = document.getElementById("message-input").value;

    if (message == "") {
        console.log("Empty message")
        return;
    }

    document.getElementById("message-input").value = "";
    var date = new Date().toISOString().slice(0, 19).replace('T', ' ');

    var messageWrapper = JSON.parse('{ "message":"' + message + '", "date":"' + date + '" }');

    document.getElementById("messages-block").innerHTML += _cs_renderSentMessage(authUser, messageWrapper);   

    var messageJSON = '{ "message":"' + message + '", "date":"' + date + '", "senderId":' + authUser.id + ', "receiverId":' + talkingTo.id + ' }';
    
    _cs_messageCache.push(JSON.parse(messageJSON));
    
    const requestPostMessage = new ServerRequest(function (responseText) {
        console.log("SENT");
    }, _error_func);
    requestPostMessage.send(RequestMethod.POST, "/api/v1/message", messageJSON);
}

document.getElementById("message-submit").onclick = sendMessage;
document.getElementById("message-input").addEventListener('keyup', function(event) {
    if (event.keyCode == 13) 
        sendMessage();
});

var _cs_UpdateTimer = setInterval(function() {
    const _cs_requestHistory = new ServerRequest(function(responseText) {
        if (Object.keys(responseText).length == 0) {
            console.log("No messages found between " + authUser.id + " and " + talkingTo.id);
        } else {
            console.log("Updating chat log...");

            var tuple = JSON.parse(responseText);
            
            authUser = tuple.first;
            talkingTo = tuple.second;
    
            _cs_clearChat();
            _cs_renderChat(tuple.first, tuple.second, tuple.third);
            console.log("Chat updated!");
        }
    }, _error_func);
    console.log("Sending update request...");
    _cs_requestHistory.send(RequestMethod.GET, "/api/v1/message/" + authUser.id + "/" + talkingTo.id);
}, 5000);