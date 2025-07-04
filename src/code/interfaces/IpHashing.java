package code.interfaces;

import code.LoadBalancer;
import code.Server;
import com.sun.net.httpserver.Request;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Objects;

public class IpHashing implements LoadBalancerStrategy{

    @Override
    public Server getServer(int ip) {
        List<Server> servers = LoadBalancer.getInstance().getServers();
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        int hash = Objects.hash(ip);
        int index = Math.abs(hash) % servers.size();
        return servers.get(index);
    }
}