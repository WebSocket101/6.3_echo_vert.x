import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.platform.Verticle;

public class EchoServer extends Verticle {

  public void start() {
    HttpServer webServer = vertx.createHttpServer();
    webServer.websocketHandler(new Handler<ServerWebSocket>() {
      public void handle(final ServerWebSocket ws) {
          ws.dataHandler(new Handler<Buffer>() {
            public void handle(Buffer data) {
              ws.writeTextFrame(data.toString()); 
            }
          });
      }
    });

    webServer.requestHandler(new Handler<HttpServerRequest>() {
      public void handle(HttpServerRequest req) {
        req.response().sendFile("index.html");
      }
    }).listen(4000);
	
  System.out.println("Der EchoServer laeuft auf dem Port 4000");
  }
}