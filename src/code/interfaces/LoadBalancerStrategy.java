package code.interfaces;

import code.Server;
import com.sun.net.httpserver.Request;

public interface LoadBalancerStrategy {

    Server getServer(int ip);

}
