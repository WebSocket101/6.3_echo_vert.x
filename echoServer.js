var vertx = require('vertx');
var console = require('vertx/console');
var webServer = vertx.createHttpServer();

webServer.websocketHandler(function(ws){
	ws.dataHandler(function(buffer){
		ws.writeTextFrame(buffer.toString());
	});
});

webServer.requestHandler(function(req){
	req.response.sendFile("index.html");
});

webServer.listen(4000);
console.log("Der EchoServer laeuft auf dem Port 4000");