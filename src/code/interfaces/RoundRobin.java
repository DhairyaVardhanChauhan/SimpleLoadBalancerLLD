package code.interfaces;

import code.LoadBalancer;
import code.Server;
import com.sun.net.httpserver.Request;

import java.util.List;
import java.util.stream.Collectors;

public class RoundRobin implements LoadBalancerStrategy{
    private static int index = 0;


    @Override
    public Server getServer(int ip) {
        int size = LoadBalancer.getInstance().getServers().stream().filter(Server::isServerHealth).toList().size();
        return LoadBalancer.getInstance().getServers().get((index++)%size);
    }
}
