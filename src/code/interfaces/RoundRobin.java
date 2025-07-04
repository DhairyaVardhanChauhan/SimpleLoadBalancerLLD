package code.interfaces;

import code.LoadBalancer;
import code.Server;
import com.sun.net.httpserver.Request;

public class RoundRobin implements LoadBalancerStrategy{
    private static int index = 0;


    @Override
    public Server getServer(int ip) {
        int size = LoadBalancer.getInstance().getServers().size();
        return LoadBalancer.getInstance().getServers().get((index++)%size);
    }
}
