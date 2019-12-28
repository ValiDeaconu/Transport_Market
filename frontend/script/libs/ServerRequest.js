// Types of requests
const RequestMethod = {
    GET: 'get',
    POST: 'post',
    PUT: 'put',
    DELETE: 'delete'
};

// Server link location
const __SERVER_LINK__ = "http://localhost:8080";

// Wrapper over XMLHttpRequest
class ServerRequest {
    constructor(okFunc, errorFunc) { 
        this.xhr = new XMLHttpRequest();
        this.xhr.onreadystatechange = function() {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    okFunc(this.responseText);
                } else {
                    errorFunc(this.status, this.responseText);
                }
            } 
        }
    }

    send(method, endPoint, object = null) {
        if (!method)
            throw new Error("ServerRequest.send: Method is not defined");

        if (method == RequestMethod.GET || method == RequestMethod.DELETE) {
            this.xhr.open(method, (__SERVER_LINK__ + endPoint), true);
            this.xhr.send();
        } else if (method == RequestMethod.POST || method == RequestMethod.PUT) {
            if (object == null)
                throw new Error("ServerRequest.send: Object is null");

            this.xhr.open(method, (__SERVER_LINK__ + endPoint), true);
            this.xhr.setRequestHeader("Accept", "application/json");
            this.xhr.setRequestHeader("Content-type", "application/json");
            this.xhr.send(object);
        } else {
            throw new Error("ServerRequest.send: Unknown method");
        }
    }
}