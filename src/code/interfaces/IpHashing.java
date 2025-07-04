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
        List<Server> healthyServers = LoadBalancer.getInstance().getServers()
                .stream()
                .filter(Server::isServerHealth)
                .toList();

        if (healthyServers.isEmpty()) {
            throw new IllegalStateException("No healthy servers available");
        }
        int hash = Objects.hash(ip);
        int index = Math.abs(hash) % healthyServers.size();

        return healthyServers.get(index);
    }

}