package code.interfaces;

import code.Server;
import com.sun.net.httpserver.Request;

import java.util.List;

public interface LoadBalancerStrategy {

    Server getServer(int ip);

}
